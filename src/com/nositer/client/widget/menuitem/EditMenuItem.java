package com.nositer.client.widget.menuitem;

import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;

public class EditMenuItem extends MenuItem {

	public EditMenuItem() {
		super("Edit");
		setIcon(IconHelper.createPath("/public/image/edit.png"));
	}
}
