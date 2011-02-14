package com.nositer.client.manageimages;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.nositer.client.main.MainPanel;
import com.nositer.client.uploadimages.FileDirectoryTreeGridContainer;
import com.nositer.client.widget.Resizable;

public class ManageImages extends LayoutContainer implements Resizable {
	private FileDirectoryTreeGridContainer fileDirectoryTreeGridContainer;
	private ImageViewerContainer imageViewerContainer;
	private ContentPanel contentPanel;

	public ManageImages() {
		init();
	}

	public void init() {
		BorderLayout layout = new BorderLayout();
		contentPanel = new ContentPanel();
		contentPanel.setHeaderVisible(false);
		contentPanel.setFrame(true);
		contentPanel.setHeight(MainPanel.getInstance().getHeight());
		contentPanel.setWidth(MainPanel.getInstance().getWidth());
		contentPanel.setLayout(layout);
		fileDirectoryTreeGridContainer = new FileDirectoryTreeGridContainer() {
			protected void onResize(int width, int height) {
				resize(0,0);
			};
		};
		fileDirectoryTreeGridContainer.setLayout(new FlowLayout(0));  
		imageViewerContainer = new ImageViewerContainer();
	

		BorderLayoutData westBorderLayoutData = new BorderLayoutData(LayoutRegion.WEST);
		westBorderLayoutData.setSize(MainPanel.getInstance().getWidth()/2);
		westBorderLayoutData.setSplit(true);
		contentPanel.add(fileDirectoryTreeGridContainer, westBorderLayoutData);
		BorderLayoutData centerBorderLayoutData = new BorderLayoutData(LayoutRegion.CENTER);
		centerBorderLayoutData.setSplit(true);
		contentPanel.add(imageViewerContainer, centerBorderLayoutData);


		add(contentPanel);
	}

	@Override
	public void resize(int width, int height) {
		fileDirectoryTreeGridContainer.getContentPanel().setSize(fileDirectoryTreeGridContainer.getWidth(), 
				fileDirectoryTreeGridContainer.getHeight());
		
		imageViewerContainer.setHeight(MainPanel.getInstance().getHeight()-13);
		imageViewerContainer.getContentPanel().setHeight(imageViewerContainer.getHeight());
		
		contentPanel.setSize(MainPanel.getInstance().getWidth(),
				MainPanel.getInstance().getHeight());
		
	}

}
