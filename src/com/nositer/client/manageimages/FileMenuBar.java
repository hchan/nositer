package com.nositer.client.manageimages;

import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuBar;
import com.extjs.gxt.ui.client.widget.menu.MenuBarItem;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;

public class FileMenuBar extends MenuBar {

	public FileMenuBar() {
		Menu subMenu = new Menu();
		subMenu.add(new MenuItem("Create Folder"));
		MenuBarItem file = new MenuBarItem("File", subMenu);
		setBorders(true);
		this.add(file);
	}
}
