package com.nositer.client.manageimages;

import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;

public class ImageViewerContainer extends LayoutContainer {
	private ContentPanel contentPanel;
	private HtmlContainer imageContainer;
	public ContentPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(ContentPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public HtmlContainer getImageContainer() {
		return imageContainer;
	}

	public void setImageContainer(HtmlContainer imageContainer) {
		this.imageContainer = imageContainer;
	}

	public ImageViewerContainer() {
		init();
	}

	private void init() {
		setLayout(new FlowLayout(0));  
		contentPanel = new ContentPanel();
		contentPanel.setHeading("Image Viewer");
		contentPanel.setFrame(true);
		imageContainer = new HtmlContainer("No Image Selected");
		
		contentPanel.add(imageContainer);
		this.add(contentPanel, new FlowData(new Margins(0, 0, 0, 0)));
	}
}
