package com.nositer.client.managefiles;

import com.nositer.client.Scope;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.widget.fileviewer.FileViewerContainer;

public class GroupManageFiles extends AbstractManageFiles {
	
	private GroupPlusView groupPlusView;

	public GroupPlusView getGroupPlusView() {
		return groupPlusView;
	}

	public void setGroupPlusView(GroupPlusView groupPlusView) {
		this.groupPlusView = groupPlusView;
	}

	public GroupManageFiles(GroupPlusView groupPlusView) {
		super();
		this.groupPlusView = groupPlusView;
		super.init();
	}

	@Override
	public void init() {
	}
	
	@Override
	public FileViewerContainer createFileViewerContainer() {
		Scope scope = new Scope(Scope.Type.group);
		scope.setGroupPlusView(groupPlusView);
		return new FileViewerContainer(scope) {
			@Override
			protected void onResize(int width, int height) {
				resize(0,0);
			}
		};
	}
	
	@Override
	public AbstractFolderSelector createFolderSelector() {
		return new GroupFolderSelector(getFileViewerContainer(), groupPlusView) {
			@Override
			protected void onResize(int width, int height) {
				resize(0,0);
			};
		};
	}
}