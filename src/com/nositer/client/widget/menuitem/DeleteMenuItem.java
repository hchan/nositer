package com.nositer.client.widget.menuitem;

import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;

public class DeleteMenuItem extends MenuItem {

	public DeleteMenuItem() {
		super("Delete");
		setIcon(IconHelper.createPath("/public/image/delete.png"));
	}
}
