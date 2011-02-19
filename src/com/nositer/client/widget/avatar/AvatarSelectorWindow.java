package com.nositer.client.widget.avatar;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.directorytree.FileDirectoryTreeGridContainer;
import com.nositer.client.widget.imageviewer.ImageViewerContainer;


public class AvatarSelectorWindow extends Window {
	private FileDirectoryTreeGridContainer fileDirectoryTreeGridContainer;
	private ContentPanel contentPanel;
	private ImageViewerContainer imageViewerContainer;

	public AvatarSelectorWindow() {
		init();
	}

	private void init() {
		setLayout(new FitLayout());
		setSize(750, 500);
		setModal(true);
		setBlinkModal(true);	
		contentPanel = new ContentPanel();
		contentPanel.setHeaderVisible(false);
		BorderLayout layout = new BorderLayout();
		contentPanel.setLayout(layout);
		
		fileDirectoryTreeGridContainer = new FileDirectoryTreeGridContainer(true) {
			protected void onResize(int width, int height) {
				AvatarSelectorWindow.this.onResize(AvatarSelectorWindow.this.getWidth(), AvatarSelectorWindow.this.getHeight());
			};
		};
		
		fileDirectoryTreeGridContainer.setLayout(new FlowLayout(0));
		imageViewerContainer = new ImageViewerContainer() {
			protected void onResize(int width, int height) {
				AvatarSelectorWindow.this.onResize(AvatarSelectorWindow.this.getWidth(), AvatarSelectorWindow.this.getHeight());
			};
		};
		
		setHeading("Select Avatar");
		BorderLayoutData westBorderLayoutData = new BorderLayoutData(LayoutRegion.WEST);
		westBorderLayoutData.setSize(400);
		westBorderLayoutData.setSplit(true);
		
		BorderLayoutData centerBorderLayoutData = new BorderLayoutData(LayoutRegion.CENTER);
		centerBorderLayoutData.setSplit(true);

		contentPanel.add(fileDirectoryTreeGridContainer, westBorderLayoutData);
		contentPanel.add(imageViewerContainer, centerBorderLayoutData);
		this.add(contentPanel);
	
			
	}
	
	@Override
	protected void onResize(int width, int height) {	
		super.onResize(width, height);
		fileDirectoryTreeGridContainer.getContentPanel().setSize(fileDirectoryTreeGridContainer.getWidth(), 
				height-35);
		imageViewerContainer.setHeight(height-35);
		imageViewerContainer.getContentPanel().setHeight(height-35);
		
		
		imageViewerContainer.getSelectedFilePanel().setWidth(imageViewerContainer.getWidth() - 10);
		//contentPanel.setSize(width, height);
		//fileDirectoryTreeGridContainer.setHeight(height);
		//fileDirectoryTreeGridContainer.getContentPanel().setHeight(height);
		//fileDirectoryTreeGridContainer.setWidth(fileDirectoryTreeGridContainer.getWidth());
		
		//fileDirectoryTreeGridContainer.getContentPanel().setHeight(height);		
	}
	
	
}
