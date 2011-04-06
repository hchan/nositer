package com.nositer.client.managefiles;

import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.nositer.client.widget.directorytree.FileDirectoryTreeGridContainer;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.client.widget.fileviewer.FileViewerContainer;
import com.nositer.shared.FileNameUtil;

public class FileManager extends FileDirectoryTreeGridContainer {
	private FileManagerMenuBar fileManagerMenuBar;
	private FileViewerContainer fileViewerContainer;

	public FileManager(FileViewerContainer fileViewerContainer) {
		super();
		this.fileViewerContainer = fileViewerContainer;
	}

	@Override
	protected void init(boolean useSelectedFilePanel) {
		super.init(false);
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
