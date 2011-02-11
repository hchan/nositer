package com.nositer.client.imagemgmt;

import org.swfupload.client.File;
import org.swfupload.client.SWFUpload;
import org.swfupload.client.SWFUpload.ButtonAction;
import org.swfupload.client.SWFUpload.WindowMode;
import org.swfupload.client.UploadBuilder;
import org.swfupload.client.event.DebugHandler;
import org.swfupload.client.event.DialogStartHandler;
import org.swfupload.client.event.FileQueuedHandler;
import org.swfupload.client.event.UploadProgressHandler;
import org.swfupload.client.event.UploadSuccessHandler;
import org.swfupload.client.event.DebugHandler.DebugEvent;
import org.swfupload.client.event.UploadProgressHandler.UploadProgressEvent;
import org.swfupload.client.event.UploadSuccessHandler.UploadSuccessEvent;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.nositer.client.ServiceBroker;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.Resizable;
import com.nositer.shared.Global;

public class UploadImages extends LayoutContainer implements Resizable {
	public static int FILESIZELIMIT = 5; // MB
	private Button uploadButton;
	private UploadQueue uploadQueue;
	private LayoutContainer uploadButtonContainer;
	private FileDirectoryTreeGrid fileDirectoryTreeGrid;
	private static UploadImages instance;
	public UploadImages() {
		init();
		instance = this;
	}

	public void init() {
		TableLayout layout = new TableLayout(2);
		layout.setWidth("100%");
		this.setLayout(layout);
		fileDirectoryTreeGrid = new FileDirectoryTreeGrid();
		add(fileDirectoryTreeGrid);
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
		uploadQueue.setWidth(MainPanel.getInstance().getWidth() - FileDirectoryTreeGrid.WIDTH - spacing);
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

	public void doSWFUploadInit(String sessionId) {		
		final UploadBuilder builder = new UploadBuilder();
		// Configure which file types may be selected
		builder.setFileTypes("*.png;*.jpg;*.jpeg;*.gif");
		builder.setFileTypesDescription("Images");


		//setFlashURL(builder, getFlashURL());
		setUploadBuilderSettings(builder, "flash_url", getFlashURL());

		builder.setUploadURL(Global.UPLOADURL + "?" + Global.UPLOADCREDENTIALKEY + "=" + sessionId);


		// Configure the button to display
		builder.setButtonPlaceholderID(SWFUploadContainer.SWFUPLOADSLOT);
		builder.setButtonImageURL("/public/image/spyGlass.png");
		builder.setButtonWidth(180);
		builder.setButtonHeight(20);
		builder.setFileSizeLimit(FILESIZELIMIT);
		builder.setButtonText("<span class=\"uploadBrowse\">Select Images <span class=\"fileSize\">" + "(" + FILESIZELIMIT + " MB Max)</span></span>");
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
				GWTUtil.log("size: " + event.getFile().getSize());
				GWTUtil.log(event.getFile().getName());
				GWTUtil.log(event.getFile().getId());
			}});

		final SWFUpload upload = builder.build();


		uploadButton.addListener(Events.OnClick, new Listener<BaseEvent>() {


			@Override
			public void handleEvent(BaseEvent be) {

				upload.startUpload();
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

