package com.nositer.client.managefiles;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.widget.directorytree.AbstractFileDirectoryTreeGridContainer;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.shared.Global;

public class GroupFileSelectorMenuBar extends AbstractFileSelectorMenuBar {
	private GroupPlusView groupPlusView;
	
	public GroupFileSelectorMenuBar(
			AbstractFileDirectoryTreeGridContainer fileDirectoryTreeGridContainer, GroupPlusView groupPlusView) {
		super(fileDirectoryTreeGridContainer);
		this.groupPlusView = groupPlusView;
	}

	@Override
	public void doCreateFolderService(String fullFolderName, AsyncCallback<Void> callback) {
		ServiceBroker.fileService.createFolder(fullFolderName, groupPlusView, callback);
	}


	@Override
	public String getURLPath(FileModel fileModel) {
		return Global.GROUP_URL_PREFIX + "/" + groupPlusView.getId() + fileModel.getPath();
	}
}


