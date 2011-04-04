package com.nositer.client.uploadfiles;

import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.AnchorLayout;

public class SWFUploadContainer extends LayoutContainer {
	public static String SWFUPLOADSLOT = "swfupload";
	private HtmlContainer swfuploadSlot;

	public SWFUploadContainer() {
		init();
	}

	private void init() {
		this.setLayout(new AnchorLayout());
		this.setStyleName("uploadBrowseContainer");
		swfuploadSlot = new HtmlContainer("Initializing...");
		swfuploadSlot.setId(SWFUPLOADSLOT);
		this.add(swfuploadSlot);		
	}

	
}
