package com.nositer.client.managefiles;

import java.util.List;

import com.extjs.gxt.ui.client.data.RpcProxy;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.client.widget.directorytree.UserFileDirectoryTreeGridContainer;
import com.nositer.client.widget.fileviewer.FileViewerContainer;

public class UserFolderSelector extends AbstractFolderSelector {

	public UserFolderSelector(FileViewerContainer fileViewerContainer) {
		super(fileViewerContainer);
	}

	@Override
	public RpcProxy<List<FileModel>> createProxy() {
		return UserFileDirectoryTreeGridContainer.createUserProxy();
	}

}
