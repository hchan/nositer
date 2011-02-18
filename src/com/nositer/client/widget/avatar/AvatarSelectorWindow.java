package com.nositer.client.widget.avatar;

import com.extjs.gxt.ui.client.widget.Window;
import com.nositer.client.widget.directorytree.FileDirectoryTreeGridContainer;

public class AvatarSelectorWindow extends Window {
	public AvatarSelectorWindow() {
		init();
	}

	private void init() {
		FileDirectoryTreeGridContainer fileDirectoryTreeGridContainer = new FileDirectoryTreeGridContainer();
		setHeading("Select Avatar");
		add(fileDirectoryTreeGridContainer);
		setModal(true);
		setBlinkModal(true);
	}
}
