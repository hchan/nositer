package com.nositer.client.manageimages;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.nositer.client.main.MainPanel;
import com.nositer.client.uploadimages.FileDirectoryTreeGridContainer;
import com.nositer.client.widget.Resizable;

public class ManageImages extends LayoutContainer implements Resizable {
	private FileDirectoryTreeGridContainer fileManager;
	private ImageViewerContainer imageViewerContainer;
	private ContentPanel contentPanel;
	private static ManageImages instance;
	
	public FileDirectoryTreeGridContainer getFileManager() {
		return fileManager;
	}

	public void setFileManager(FileDirectoryTreeGridContainer fileManager) {
		this.fileManager = fileManager;
	}

	public ImageViewerContainer getImageViewerContainer() {
		return imageViewerContainer;
	}

	public void setImageViewerContainer(ImageViewerContainer imageViewerContainer) {
		this.imageViewerContainer = imageViewerContainer;
	}

	public ContentPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(ContentPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public static ManageImages getInstance() {
		return instance;
	}

	public static void setInstance(ManageImages instance) {
		ManageImages.instance = instance;
	}

	public ManageImages() {
		init();
		instance = this;
	}

	public void init() {
		BorderLayout layout = new BorderLayout();
		contentPanel = new ContentPanel();
		contentPanel.setHeaderVisible(false);
		contentPanel.setFrame(true);
		contentPanel.setHeight(MainPanel.getInstance().getHeight());
		contentPanel.setWidth(MainPanel.getInstance().getWidth());
		contentPanel.setLayout(layout);
		fileManager = new FileManager() {
			protected void onResize(int width, int height) {
				resize(0,0);
			};
		};
		
		imageViewerContainer = new ImageViewerContainer();
	

		BorderLayoutData westBorderLayoutData = new BorderLayoutData(LayoutRegion.WEST);
		westBorderLayoutData.setSize(MainPanel.getInstance().getWidth()/2);
		westBorderLayoutData.setSplit(true);
		contentPanel.add(fileManager, westBorderLayoutData);
		BorderLayoutData centerBorderLayoutData = new BorderLayoutData(LayoutRegion.CENTER);
		centerBorderLayoutData.setSplit(true);
		contentPanel.add(imageViewerContainer, centerBorderLayoutData);


		add(contentPanel);
	}

	@Override
	public void resize(int width, int height) {
		fileManager.getContentPanel().setSize(fileManager.getWidth(), 
				fileManager.getHeight());
		
		imageViewerContainer.setHeight(MainPanel.getInstance().getHeight()-13);
		imageViewerContainer.getContentPanel().setHeight(imageViewerContainer.getHeight());
		//imageViewerContainer.getSelectedFilePanel().getSelectedFile().setWidth(imageViewerContainer.getWidth());
		
		contentPanel.setSize(MainPanel.getInstance().getWidth(),
				MainPanel.getInstance().getHeight());
		imageViewerContainer.getSelectedFilePanel().setWidth(imageViewerContainer.getWidth() - 10);
		
	}

}
