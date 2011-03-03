package com.nositer.client.widget.menuitem;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;

public class MenuItemPlus extends MenuItem {

	
	public MenuItemPlus(String text) {
		super(text);
		addSelectionListener(new SelectionListener<MenuEvent>() {
			@Override
			public void componentSelected(MenuEvent ce) {
				doSelect();
			}
		});
	}
	
	public void doSelect() {
		
	}
}
