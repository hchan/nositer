package com.nositer.client.managefiles;

import java.util.List;

import com.extjs.gxt.ui.client.data.RpcProxy;
import com.nositer.client.Scope;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.client.widget.directorytree.GroupFileDirectoryTreeGridContainer;
import com.nositer.client.widget.fileviewer.FileViewerContainer;

public class GroupFolderSelector extends AbstractFolderSelector {
	private GroupPlusView groupPlusView;

	


	public GroupFolderSelector(FileViewerContainer fileViewerContainer, GroupPlusView groupPlusView) {
		super();
		Scope scope = new Scope(Scope.Type.group);
		scope.setGroupPlusView(groupPlusView);
		
		this.groupPlusView = groupPlusView;
		super.init();
	}
	
	@Override
	protected void init() {
		
	}

	@Override
	public RpcProxy<List<FileModel>> createProxy() {
		return GroupFileDirectoryTreeGridContainer.createGroupProxy(groupPlusView);
	}

}
