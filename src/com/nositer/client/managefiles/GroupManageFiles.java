package com.nositer.client.managefiles;

import com.nositer.client.Scope;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.main.MainPanel;
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
	public AbstractFileSelector createFileSelector() {
		return new GroupFileSelector(getFileViewerContainer(), groupPlusView) {
			@Override
			protected void onResize(int width, int height) {
				resize(0,0);
			};
		};
	}
	
	@Override
	public void resize(int width, int height) {
		fileSelector.getContentPanel().setSize(fileSelector.getWidth(), 
				fileSelector.getHeight() - 52);
		
		fileViewerContainer.setHeight(MainPanel.getInstance().getHeight()-65);
		fileViewerContainer.getContentPanel().setHeight(fileViewerContainer.getHeight());
		//fileViewerContainer.getSelectedFilePanel().getSelectedFile().setWidth(fileViewerContainer.getWidth());
		
		contentPanel.setSize(MainPanel.getInstance().getWidth(),
				MainPanel.getInstance().getHeight());
		fileViewerContainer.getSelectedFilePanel().setWidth(fileViewerContainer.getWidth() - 10);
		
	}
	
}