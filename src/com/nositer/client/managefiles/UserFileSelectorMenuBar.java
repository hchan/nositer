package com.nositer.client.managefiles;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.widget.directorytree.AbstractFileDirectoryTreeGridContainer;

public class UserFileSelectorMenuBar extends AbstractFileSelectorMenuBar {

	public UserFileSelectorMenuBar(
			AbstractFileDirectoryTreeGridContainer fileDirectoryTreeGridContainer) {
		super(fileDirectoryTreeGridContainer);
	}

	@Override
	public void doCreateFolderService(String fullFolderName,
			AsyncCallback<Void> callback) 
	{
		ServiceBroker.fileService.createFolder(fullFolderName, callback);

	}
}


