package com.nositer.client.managefiles;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.Nositer;
import com.nositer.client.ServiceBroker;
import com.nositer.client.widget.directorytree.AbstractFileDirectoryTreeGridContainer;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.client.widget.directorytree.FolderModel;
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
	public String getURLPath(FileModel fileModel) {
		return Global.USER_URL_PREFIX + "/" + Nositer.getInstance().getUser() + fileModel.getPath();
	}


	@Override
	public void doRenameFolderService(String pathName,
			String oldRelativeFolderName, String newRelativeFolderName,
			AsyncCallback<Void> callback) {
		ServiceBroker.fileService.renameFolder(pathName, oldRelativeFolderName, newRelativeFolderName, callback);
	}

	@Override
	public void doDeleteFolderService(FolderModel folderModel,
			AsyncCallback<Void> callback) {
		ServiceBroker.fileService.deleteFolder(folderModel, callback);
	}

	@Override
	public void doDeleteFileService(FileModel fileModel,
			AsyncCallback<Void> callback) {
		ServiceBroker.fileService.deleteFile(fileModel, callback);
	}

	@Override
	public void doRenameFileService(String pathName,
			String oldRelativeFileName, String newRelativeFileName,
			AsyncCallback<Void> callback) {
		ServiceBroker.fileService.renameFile(pathName, oldRelativeFileName, newRelativeFileName, callback);
	}
}


