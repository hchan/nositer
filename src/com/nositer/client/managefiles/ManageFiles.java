package com.nositer.client.managefiles;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.directorytree.FileDirectoryTreeGridContainer;
import com.nositer.client.widget.fileviewer.FileViewerContainer;

public class ManageFiles extends LayoutContainer implements Resizable {
	private FileDirectoryTreeGridContainer fileManager;
	private FileViewerContainer fileViewerContainer;
	private ContentPanel contentPanel;
	private static ManageFiles instance;
	
	public FileDirectoryTreeGridContainer getFileManager() {
		return fileManager;
	}

	public void setFileManager(FileDirectoryTreeGridContainer fileManager) {
		this.fileManager = fileManager;
	}

	public FileViewerContainer getFileViewerContainer() {
		return fileViewerContainer;
	}

	public void setFileViewerContainer(FileViewerContainer fileViewerContainer) {
		this.fileViewerContainer = fileViewerContainer;
	}

	public ContentPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(ContentPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public static ManageFiles getInstance() {
		return instance;
	}

	public static void setInstance(ManageFiles instance) {
		ManageFiles.instance = instance;
	}

	public ManageFiles() {
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
		
		
		fileViewerContainer = new FileViewerContainer() {
			@Override
			protected void onResize(int width, int height) {
				resize(0,0);
			};
		};
	
		fileManager = new FileManager(fileViewerContainer) {
			@Override
			protected void onResize(int width, int height) {
				resize(0,0);
			};
		};

		BorderLayoutData westBorderLayoutData = new BorderLayoutData(LayoutRegion.WEST);
		westBorderLayoutData.setSize(MainPanel.getInstance().getWidth()/2);
		westBorderLayoutData.setSplit(true);
		contentPanel.add(fileManager, westBorderLayoutData);
		BorderLayoutData centerBorderLayoutData = new BorderLayoutData(LayoutRegion.CENTER);
		centerBorderLayoutData.setSplit(true);
		contentPanel.add(fileViewerContainer, centerBorderLayoutData);


		add(contentPanel);
	}

	@Override
	public void resize(int width, int height) {
		fileManager.getContentPanel().setSize(fileManager.getWidth(), 
				fileManager.getHeight());
		
		fileViewerContainer.setHeight(MainPanel.getInstance().getHeight()-13);
		fileViewerContainer.getContentPanel().setHeight(fileViewerContainer.getHeight());
		//fileViewerContainer.getSelectedFilePanel().getSelectedFile().setWidth(fileViewerContainer.getWidth());
		
		contentPanel.setSize(MainPanel.getInstance().getWidth(),
				MainPanel.getInstance().getHeight());
		fileViewerContainer.getSelectedFilePanel().setWidth(fileViewerContainer.getWidth() - 10);
		
	}

}
