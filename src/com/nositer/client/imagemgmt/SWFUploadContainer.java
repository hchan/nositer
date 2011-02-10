package com.nositer.client.imagemgmt;

import org.swfupload.client.File;
import org.swfupload.client.SWFUpload;
import org.swfupload.client.UploadBuilder;
import org.swfupload.client.SWFUpload.ButtonAction;
import org.swfupload.client.event.DebugHandler;
import org.swfupload.client.event.UploadProgressHandler;
import org.swfupload.client.event.UploadSuccessHandler;
import org.swfupload.client.event.DebugHandler.DebugEvent;
import org.swfupload.client.event.UploadProgressHandler.UploadProgressEvent;
import org.swfupload.client.event.UploadSuccessHandler.UploadSuccessEvent;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.nositer.client.util.GWTUtil;
import com.nositer.shared.Global;

public class SWFUploadContainer extends LayoutContainer {
	public static String SWFUPLOADSLOT = "swfupload";
	private HtmlContainer swfuploadSlot;

	public SWFUploadContainer() {
		init();
	}

	private void init() {
		swfuploadSlot = new HtmlContainer("Initializing...");
		swfuploadSlot.setId(SWFUPLOADSLOT);
		this.add(swfuploadSlot);
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
		builder.setButtonImageURL("/public/image/bol.png");
		builder.setButtonWidth(180);
		builder.setButtonHeight(18);
		builder.setFileSizeLimit(10);
		builder.setButtonText("<span class=\"uploadBrowse\">Select Images</span>");
		builder.setButtonTextStyle(".uploadBrowse { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; }");
		//builder.setButtonTextLeftPadding(7);
		//builder.setButtonTextTopPadding(4);

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



		final SWFUpload upload = builder.build();

		/*
	uploadButton.addListener(Events.OnClick, new Listener<BaseEvent>() {


		@Override
		public void handleEvent(BaseEvent be) {

			upload.startUpload();
		}
	});

		 */


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
