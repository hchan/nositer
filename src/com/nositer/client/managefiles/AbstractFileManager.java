package com.nositer.client.managefiles;

import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.nositer.client.widget.directorytree.AbstractFileDirectoryTreeGridContainer;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.client.widget.fileviewer.FileViewerContainer;
import com.nositer.shared.FileNameUtil;

abstract public class AbstractFileManager extends AbstractFileDirectoryTreeGridContainer {
	protected FileManagerMenuBar fileManagerMenuBar;
	protected FileViewerContainer fileViewerContainer;

	public AbstractFileManager(FileViewerContainer fileViewerContainer) {
		super(false);
		this.fileViewerContainer = fileViewerContainer;
	}

	@Override
	protected void init() {
		super.init();
		setLayout(new FlowLayout(0));  
		fileManagerMenuBar = new FileManagerMenuBar(this);
		getContentPanel().setTopComponent(fileManagerMenuBar);
	}

	@Override
	public void doFileModelClick(FileModel fileModel) {
		if (FileNameUtil.isImageFile(fileModel.getName())) {
			fileViewerContainer.setImage(fileModel);
		} else if (FileNameUtil.isTextFile(fileModel.getName())) {
			fileViewerContainer.setText(fileModel);
		}
		fileViewerContainer.getSelectedFilePanel().getSelectedFile().setValue(fileModel.getPath());
	}
}
