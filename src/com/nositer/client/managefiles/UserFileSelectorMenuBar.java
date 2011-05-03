package com.nositer.client.managefiles;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.Nositer;
import com.nositer.client.ServiceBroker;
import com.nositer.client.widget.directorytree.AbstractFileDirectoryTreeGridContainer;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.shared.Global;

public class UserFileSelectorMenuBar extends AbstractFileSelectorMenuBar {

	public UserFileSelectorMenuBar(AbstractFileDirectoryTreeGridContainer fileDirectoryTreeGridContainer) {
		super(fileDirectoryTreeGridContainer);
	}

	@Override
	public void doCreateFolderService(String fullFolderName, AsyncCallback<Void> callback) {
		ServiceBroker.fileService.createFolder(fullFolderName, callback);

	}

	@Override
	public void doDownloadFile(FileModel fileModel) {
		Window.open(Global.USER_URL_PREFIX + "/" + Nositer.getInstance().getUser() + fileModel.getPath() 
				+ "?" + Global.DOWNLOAD + "=true",
				"_blank", 
				null);		
	}
}


