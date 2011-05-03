package com.nositer.client.managefiles;

import com.extjs.gxt.ui.client.widget.menu.MenuItem;

public class MenuItemManageFiles extends MenuItem {
	private boolean isFolderMenuItem = false;

	public boolean isFolderMenuItem() {
		return isFolderMenuItem;
	}

	public void setFolderMenuItem(boolean isFolderMenuItem) {
		this.isFolderMenuItem = isFolderMenuItem;
	}
	
	public MenuItemManageFiles(String text) {
		super(text);
	}
}
