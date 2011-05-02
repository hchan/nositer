package com.nositer.client.managefiles;

import java.util.List;

import com.extjs.gxt.ui.client.data.RpcProxy;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.client.widget.directorytree.UserFileDirectoryTreeGridContainer;
import com.nositer.client.widget.fileviewer.FileViewerContainer;

public class UserFileSelector extends AbstractFileSelector {

	public UserFileSelector(FileViewerContainer fileViewerContainer) {
		super(fileViewerContainer);
	}

	@Override
	public RpcProxy<List<FileModel>> createProxy() {
		return UserFileDirectoryTreeGridContainer.createUserProxy();
	}

	@Override
	public AbstractFileSelectorMenuBar createFileSelectorMenuBar() {
		AbstractFileSelectorMenuBar retval = new UserFileSelectorMenuBar(this);
		return retval;
	}

}
