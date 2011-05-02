package com.nositer.client.managefiles;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.nositer.client.Scope;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.directorytree.AbstractFileDirectoryTreeGridContainer;
import com.nositer.client.widget.fileviewer.FileViewerContainer;

abstract public class AbstractManageFiles extends LayoutContainer implements Resizable {
	protected AbstractFileSelector fileSelector;
	protected FileViewerContainer fileViewerContainer;
	protected ContentPanel contentPanel;

	public AbstractFileSelector getFileSelector() {
		return fileSelector;
	}

	public void setFileSelector(AbstractFileSelector fileSelector) {
		this.fileSelector = fileSelector;
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

	public AbstractManageFiles() {
		init();
	}

	abstract public FileViewerContainer createFileViewerContainer();
	abstract public AbstractFileSelector createFileSelector();
	
	public void init() {
		BorderLayout layout = new BorderLayout();
		contentPanel = new ContentPanel();
		contentPanel.setHeaderVisible(false);
		contentPanel.setFrame(true);
		contentPanel.setHeight(MainPanel.getInstance().getHeight());
		contentPanel.setWidth(MainPanel.getInstance().getWidth());
		contentPanel.setLayout(layout);
		
		
		fileViewerContainer = createFileViewerContainer();
	
		fileSelector = createFileSelector();

		BorderLayoutData westBorderLayoutData = new BorderLayoutData(LayoutRegion.WEST);
		westBorderLayoutData.setSize(MainPanel.getInstance().getWidth()/2);
		westBorderLayoutData.setSplit(true);
		contentPanel.add(fileSelector, westBorderLayoutData);
		BorderLayoutData centerBorderLayoutData = new BorderLayoutData(LayoutRegion.CENTER);
		centerBorderLayoutData.setSplit(true);
		contentPanel.add(fileViewerContainer, centerBorderLayoutData);


		add(contentPanel);
	}

	
}
