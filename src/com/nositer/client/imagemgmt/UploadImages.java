package com.nositer.client.imagemgmt;

import org.swfupload.client.File;
import org.swfupload.client.SWFUpload;
import org.swfupload.client.SWFUpload.ButtonAction;
import org.swfupload.client.UploadBuilder;
import org.swfupload.client.event.DebugHandler;
import org.swfupload.client.event.DialogStartHandler;
import org.swfupload.client.event.UploadProgressHandler;
import org.swfupload.client.event.UploadSuccessHandler;

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
import com.nositer.client.util.GWTUtil;
import com.nositer.shared.Global;

public class UploadImages extends LayoutContainer {

	private Button uploadButton;
	private UploadQueue uploadQueue;
	private LayoutContainer uploadButtonContainer;
	public UploadImages() {
		init();
	}

	public void init() {
		TableLayout layout = new TableLayout(2);
		layout.setWidth("100%");
		this.setLayout(layout);
		FileDirectoryTreeGrid fileDirectoryTreeGrid = new FileDirectoryTreeGrid();
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
				uploadQueue.getSwfUploadContainer().doSWFUploadInit(result);
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
		layout();
		ServiceBroker.securityService.getSessionId(callbackWithSessionId);

	}



	

}

