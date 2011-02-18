package com.nositer.client.uploadimages;

import org.swfupload.client.File;
import org.swfupload.client.SWFUpload;
import org.swfupload.client.SWFUpload.ButtonAction;
import org.swfupload.client.SWFUpload.WindowMode;
import org.swfupload.client.UploadBuilder;
import org.swfupload.client.event.DebugHandler;
import org.swfupload.client.event.FileQueueErrorHandler;
import org.swfupload.client.event.FileQueuedHandler;
import org.swfupload.client.event.UploadCompleteHandler;
import org.swfupload.client.event.UploadProgressHandler;
import org.swfupload.client.event.UploadSuccessHandler;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.nositer.client.ServiceBroker;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.AlertMessageBox;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.directorytree.FileDirectoryTreeGridContainer;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.shared.Global;


public class UploadImages extends LayoutContainer implements Resizable {
	public static int FILESIZELIMIT = 5000; //kB
	private Button uploadButton;
	private UploadQueue uploadQueue;
	private LayoutContainer uploadButtonContainer;
	private FileDirectoryTreeGridContainer fileDirectoryTreeGridContainer;
	private static UploadImages instance;
	private SWFUpload swfUpload;
	public UploadImages() {
		init();
		instance = this;
	}

	public void init() {
		TableLayout layout = new TableLayout(2);
		layout.setWidth("100%");
		this.setLayout(layout);
		fileDirectoryTreeGridContainer = new FileDirectoryTreeGridContainer();
		add(fileDirectoryTreeGridContainer);
		uploadQueue = new UploadQueue();

		add(uploadQueue);

		AsyncCallback<String> callbackWithSessionId = new  AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				GWTUtil.log("", caught);
			}

			@Override
			public void onSuccess(String result) {
				doSWFUploadInit(result);
			}

		};

		uploadButtonContainer = new LayoutContainer();
		uploadButton = new Button();
		uploadButton.setHeight(55);
		uploadButton.setWidth(140);
		AbstractImagePrototype icon = IconHelper.createPath("/public/image/beginUpload.gif",50,50);

		uploadButton.setIcon(icon);
		uploadButton.setText("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Begin Upload");
		uploadButton.setAutoWidth(true);
		uploadButtonContainer.add(uploadButton, new FlowData(0, 0, 0, 10));
		this.add(uploadButtonContainer);

		resize(0,0);
		ServiceBroker.securityService.getSessionId(callbackWithSessionId);

	}

	@Override
	public void resize(int width, int height) {
		int spacing = 30;
		uploadQueue.setWidth(MainPanel.getInstance().getWidth() - FileDirectoryTreeGridContainer.WIDTH - spacing);
		uploadQueue.getGrid().getView().layout();
	}

	public native void setUploadBuilderSettings(UploadBuilder builder, String key, String value) /*-{
	builder.@org.swfupload.client.UploadBuilder::settings[key] = value;
 }-*/;

	public String getFlashURL() {
		String retval = null;
		if (GWTUtil.getFlashVersion() >= 10) {
			retval = Global.SWFLOADDIRFLASHPLAYER10;
		} else {
			retval = Global.SWFLOADDIRFLASHPLAYER9;
		}
		return retval;
	}

	public void doSWFUploadInit(final String sessionId) {		
		final UploadBuilder builder = new UploadBuilder();
		// Configure which file types may be selected
		builder.setFileTypes("*.png;*.jpg;*.jpeg;*.gif");
		builder.setFileTypesDescription("Images");


		//setFlashURL(builder, getFlashURL());
		setUploadBuilderSettings(builder, "flash_url", getFlashURL());

		// upload URL
		builder.setUploadURL(Global.UPLOADURL + "?" + 
				Global.UPLOADCREDENTIALKEY + "=" + sessionId);


		// Configure the button to display
		builder.setButtonPlaceholderID(SWFUploadContainer.SWFUPLOADSLOT);
		builder.setButtonImageURL("/public/image/spyGlass.png");
		builder.setButtonWidth(180);
		builder.setButtonHeight(20);
		builder.setFileSizeLimit(FILESIZELIMIT);
		builder.setButtonText("<span class=\"uploadBrowse\">Select Images <span class=\"fileSize\">" + "(" + FILESIZELIMIT/1000 + " MB Max)</span></span>");
		builder.setButtonTextStyle(".uploadBrowse { font-family: Helvetica, Arial, sans-serif; font-size: 14pt; } .fileSize {font-size: 10pt;}");
		builder.setButtonTextLeftPadding(18);
		builder.setButtonTextTopPadding(0);


		builder.setWindowMode(WindowMode.TRANSPARENT);
		// Use ButtonAction.SELECT_FILE to only allow selection of a single file
		builder.setButtonAction(ButtonAction.SELECT_FILES);
		builder.setUploadProgressHandler(new UploadProgressHandler() {

			public void onUploadProgress(UploadProgressEvent e) {
				File f = e.getFile();
				/*
			f.getName();
			String text = html.getHTML();
			text += "<br />" + e.getBytesComplete() + "; " + f.getName();
			html.setHTML(text);
				 */
				GWTUtil.log("onUploadProgress: " + f.getName());
			}
		});

		builder.setUploadSuccessHandler(new UploadSuccessHandler() {
			public void onUploadSuccess(UploadSuccessEvent e) {
				/*
			String t = html.getHTML(); 
			t += "<br />server data : " + e.getServerData(); 
			html.setHTML(t); 
				 */
				GWTUtil.log("onUploadSuccess: " + e.getServerData());
			}
		}); 

		builder.setDebugHandler(new DebugHandler() {
			@Override
			public void onDebug(DebugEvent e) {
				GWTUtil.log(e.toString());
				GWTUtil.log(e.getMessage());
			}
		});

		builder.setFileQueuedHandler(new FileQueuedHandler() {
			@Override
			public void onFileQueued(FileQueuedEvent event) {	
				GWTUtil.log(event.getFile().getName() + " has been queued");
				GWTUtil.log(event.getFile().getId() + " has been queued");
				FileModel fileModel = new FileModel(event.getFile());
				uploadQueue.addRow(fileModel);
			}
		});

		builder.setFileQueueErrorHandler(new FileQueueErrorHandler() {
			@Override
			public void onFileQueueError(FileQueueErrorEvent event) {
				FileModel fileModel = new FileModel(event.getFile());				
				fileModel.set(FileModel.Attribute.errorMessage.toString(), event.getMessage());
				uploadQueue.addRow(fileModel);
			}});


		builder.setUploadCompleteHandler(new UploadCompleteHandler() {
			@Override
			public void onUploadComplete(UploadCompleteEvent e) {
				GWTUtil.log(e.getFile().getName() + " has been uploaded");
				GWTUtil.log(e.getFile().getId() + " (id) has been uploaded");
				//FileModel fileModel = fileDirectoryTreeGridContainer.getSelectedFolderPanel().getFolderModel();
				//fileDirectoryTreeGridContainer.getTree().findNode( fileDirectoryTreeGridContainer.getSelectedFolderPanel().getModel()).setExpanded(true);
				// fileDirectoryTreeGridContainer.getSelectedFolderPanel().getTreeNode().relo
				fileDirectoryTreeGridContainer.refreshSelectedTreeNode();
				
				
				uploadQueue.removeRow(e.getFile().getId());
				/*
				fileDirectoryTreeGridContainer.getSelectedFolderPanel().getTreeNode().setExpanded(true);
				fileDirectoryTreeGridContainer.getSelectedFolderPanel().getTreeNode().setExpanded(true);
				fileDirectoryTreeGridContainer.getSelectedFolderPanel().getTreeNode().setExpanded(true);
				 fileDirectoryTreeGridContainer.getTree().getSelectionModel().select(fileModel, false);
				 fileDirectoryTreeGridContainer.getTree().getSelectionModel().refresh();
				 */
				swfUpload.startUpload();
			}
		});
		swfUpload = builder.build();

		uploadButton.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {


				if (fileDirectoryTreeGridContainer.getSelectedFolderPanel().getSelectedFolder().getValue() == null) {
					AlertMessageBox.show("Warning", "You must select a folder to upload to", null);
				} else if (uploadQueue.getGrid().getStore().getCount() == 0) {
					AlertMessageBox.show("Warning", "The Upload Queue is empty.\nClick on Select Images to Browse for local files", null);
				} else {

					swfUpload.setUploadURL(Global.UPLOADURL + "?" + 
							Global.UPLOADCREDENTIALKEY + "=" + sessionId + "&" +
							"uploadDir=" + URL.encode(fileDirectoryTreeGridContainer.getSelectedFolderPanel().getSelectedFolder().getValue())
					);

					swfUpload.startUpload();
				}
			}
		});

		uploadQueue.getClearAll().addListener(Events.Select,  new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				for (int i = 0; i < uploadQueue.getGrid().getStore().getCount(); i++) {
					FileModel fileModel = uploadQueue.getGrid().getStore().getAt(i);
					swfUpload.cancelUpload((String)fileModel.get(FileModel.Attribute.id.toString()), false);
				}
				uploadQueue.getGrid().getStore().removeAll();
			}
		});


		// Attach any other handlers and custom logic here
		// see:
		// setDebugHandler
		// setDialogStartHandler         - The browse dialog was opened
		// setFileDialogCompleteHandler  - The browse dialog was closed
		// setFileQueuedHandler          - A file was queued for upload
		// setFileQueueErrorHandler      - A requested file could not be queued (max file size, queue limits, etc)
		// setUploadStartHandler         - A file has begun uploading
		// setUploadProgressHandler      - Receives updates asynchronously to report progress of an upload
		// setUploadCompleteHandler      - A file has completed transferring
		// setUploadSuccessHandler       - The server has accepted the upload
		// setUploadErrorHandler         - An error occurred during the transfer

	}


}

