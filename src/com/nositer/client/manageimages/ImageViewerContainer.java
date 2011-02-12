package com.nositer.client.manageimages;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;

public class ImageViewerContainer extends LayoutContainer {
	private ContentPanel contentPanel;
	private HtmlContainer imageContainer;
	public ImageViewerContainer() {
		init();
	}

	private void init() {
		setLayout(new FlowLayout(10));  
		contentPanel = new ContentPanel();
		contentPanel.setHeading("Image Viewer");
		imageContainer = new HtmlContainer("abc");
		
		contentPanel.add(imageContainer);
		this.add(contentPanel);
	}
}
