package com.nositer.client.manageimages;

import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.nositer.client.top.TopPanel;
import com.nositer.client.uploadimages.FileModel;
import com.nositer.client.uploadimages.SelectedFilePanel;
import com.nositer.shared.Global;

public class ImageViewerContainer extends LayoutContainer {
	private ContentPanel contentPanel;
	private HtmlContainer imageContainer;
	private SelectedFilePanel selectedFilePanel;
	
	public SelectedFilePanel getSelectedFilePanel() {
		return selectedFilePanel;
	}

	public void setSelectedFilePanel(SelectedFilePanel selectedFilePanel) {
		this.selectedFilePanel = selectedFilePanel;
	}

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
		
		selectedFilePanel = new SelectedFilePanel();
		contentPanel.setBottomComponent(selectedFilePanel);
		this.add(contentPanel, new FlowData(new Margins(0, 0, 0, 0)));
	}


	public void setImage(FileModel fileModel) {
		String imageUrl = Global.USER_URL_PREFIX + "/" + TopPanel.getInstance().getUser().getId() + "/image" + fileModel.getPath();
		imageContainer.setHtml("<IMG SRC='" + imageUrl + "'/>");		
	}
}
