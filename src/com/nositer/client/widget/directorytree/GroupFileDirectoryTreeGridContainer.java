package com.nositer.client.widget.directorytree;

import java.util.List;

import com.extjs.gxt.ui.client.data.RpcProxy;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.GroupPlusView;

public class GroupFileDirectoryTreeGridContainer extends AbstractFileDirectoryTreeGridContainer {
	private GroupPlusView groupPlusView;

	public GroupPlusView getGroupPlusView() {
		return groupPlusView;
	}

	public void setGroupPlusView(GroupPlusView groupPlusView) {
		this.groupPlusView = groupPlusView;
	}

	public GroupFileDirectoryTreeGridContainer(boolean useSelectedFilePanel, GroupPlusView groupPlusView) {
		super(useSelectedFilePanel);
		this.groupPlusView = groupPlusView;
	}

	@Override
	public RpcProxy<List<FileModel>> createProxy() {
		return createGroupProxy(groupPlusView);
	}

	public static  RpcProxy<List<FileModel>> createGroupProxy(final GroupPlusView groupPlusView) {
		return new RpcProxy<List<FileModel>>() {  
			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<FileModel>> callback) {
				ServiceBroker.fileService.getImageFolderChildren((FileModel) loadConfig, groupPlusView, callback);  
			}  			
		};  
	}

}
