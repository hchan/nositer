package com.nositer.client.manageimages;

import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.nositer.client.uploadimages.FileDirectoryTreeGridContainer;

public class FileManager extends FileDirectoryTreeGridContainer {
	private FileMenuBar fileMenuBar;

	public FileManager() {
		super();
	}

	@Override
	protected void init() {
		super.init();
		setLayout(new FlowLayout(0));  
		fileMenuBar = new FileMenuBar();
		getContentPanel().setTopComponent(fileMenuBar);
	}
}
