package com.nositer.client.manageimages;

import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.nositer.client.uploadimages.FileDirectoryTreeGridContainer;
import com.nositer.client.uploadimages.FileModel;

public class FileManager extends FileDirectoryTreeGridContainer {
	private FileManagerMenuBar fileManagerMenuBar;

	public FileManager() {
		super();
	}

	@Override
	protected void init() {
		super.init();
		setLayout(new FlowLayout(0));  
		fileManagerMenuBar = new FileManagerMenuBar();
		getContentPanel().setTopComponent(fileManagerMenuBar);
	}
	
	@Override
	public void doFileModelClick(FileModel fileModel) {
		ManageImages.getInstance().getImageViewerContainer().setImage(fileModel);
		ManageImages.getInstance().getImageViewerContainer().getSelectedFilePanel().getSelectedFile().setValue(fileModel.getPath());
	}
}
