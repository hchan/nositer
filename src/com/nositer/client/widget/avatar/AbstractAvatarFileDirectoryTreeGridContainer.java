package com.nositer.client.widget.avatar;

import com.nositer.client.widget.directorytree.AbstractFileDirectoryTreeGridContainer;
import com.nositer.client.widget.directorytree.FileModel;

abstract public class AbstractAvatarFileDirectoryTreeGridContainer extends AbstractFileDirectoryTreeGridContainer {
private AvatarSelectorWindow avatarSelectorWindow;
	
	public AbstractAvatarFileDirectoryTreeGridContainer(
			boolean useSelectedFilePanel, AvatarSelectorWindow avatarSelectorWindow) {
		super(useSelectedFilePanel);
		this.avatarSelectorWindow = avatarSelectorWindow;
	}

	@Override
	protected void onResize(int width, int height) {
		avatarSelectorWindow.onResize(avatarSelectorWindow.getWidth(), avatarSelectorWindow.getHeight());
	};
	
	@Override
	public void doFileModelClick(FileModel fileModel) {
		avatarSelectorWindow.getFileViewerContainer().setImage(fileModel);
		avatarSelectorWindow.getFileViewerContainer().getSelectedFilePanel().getSelectedFile().setValue(fileModel.getPath());
	}
}
