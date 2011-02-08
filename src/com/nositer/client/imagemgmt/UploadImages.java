package com.nositer.client.imagemgmt;

import org.swfupload.client.File;
import org.swfupload.client.SWFUpload;
import org.swfupload.client.SWFUpload.ButtonAction;
import org.swfupload.client.UploadBuilder;
import org.swfupload.client.event.UploadProgressHandler;
import org.swfupload.client.event.UploadSuccessHandler;

import gwtupload.client.IUploader;
import gwtupload.client.MultiUploader;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.nositer.client.util.GWTUtil;
import com.nositer.shared.ServiceBroker;

public class UploadImages extends LayoutContainer {
	private Button uploadButton;

	public UploadImages() {
		init();
	}

	public void init() {


		// The placeholder to be replaced by the swfupload control
		// This could also be provided statically by your host HTML page
		HtmlContainer htmlContainer = new HtmlContainer("abc");
		htmlContainer.setId("swfupload");
		this.setMonitorWindowResize(true);
		this.add(htmlContainer);

		AsyncCallback<Void> callback = new  AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				GWTUtil.log("", caught);
			}

			@Override
			public void onSuccess(Void result) {
				doUploadInit();
			}

		};
		uploadButton = new Button("Upload");
		this.add(uploadButton);
		ServiceBroker.noopService.noop(callback);

	}


	public native void setFlashURL(UploadBuilder builder, String url) /*-{
	    builder.@org.swfupload.client.UploadBuilder::settings['flash_url'] = url;
	  }-*/;
	
	public void doUploadInit() {

		final UploadBuilder builder = new UploadBuilder();

		// Configure which file types may be selected
		builder.setFileTypes("*.asf;*.wma;*.wmv;*.avi;*.flv;*.swf;*.mpg;*.mpeg;*.mp4;*.mov;*.m4v;*.aac;*.mp3;*.wav;*.png;*.jpg;*.jpeg;*.gif");
		builder.setFileTypesDescription("Images, Video & Sound");

		setFlashURL(builder, "/public/swf/swfupload.swf");
		builder.setUploadURL("/upload");

		// Configure the button to display
		builder.setButtonPlaceholderID("swfupload");
		//builder.setButtonImageURL("XPButtonUploadText_61x22.png");
		builder.setButtonWidth(61);
		builder.setButtonHeight(22);
		builder.setButtonText("<span class=\"label\">Browse</span>");
		builder.setButtonTextStyle(".label { color: #000000; font-family: sans font-size: 16pt; }");
		builder.setButtonTextLeftPadding(7);
		builder.setButtonTextTopPadding(4);

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

