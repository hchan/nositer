package com.nositer.client.managefiles;

import java.util.List;

import com.extjs.gxt.ui.client.data.RpcProxy;
import com.nositer.client.Scope;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.client.widget.directorytree.GroupFileDirectoryTreeGridContainer;
import com.nositer.client.widget.fileviewer.FileViewerContainer;

public class GroupFileSelector extends AbstractFileSelector {
	private GroupPlusView groupPlusView;

	


	public GroupFileSelector(FileViewerContainer fileViewerContainer, GroupPlusView groupPlusView) {
		super(fileViewerContainer);
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

	@Override
	public AbstractFileSelectorMenuBar createFileSelectorMenuBar() {
		AbstractFileSelectorMenuBar retval = new GroupFileSelectorMenuBar(this, groupPlusView);
		return retval;
	}

}
