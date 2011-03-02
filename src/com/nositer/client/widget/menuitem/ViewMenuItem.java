package com.nositer.client.widget.menuitem;

import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;

public class ViewMenuItem extends MenuItem {

	public ViewMenuItem() {
		super("View");
		setIcon(IconHelper.createPath("/public/image/view.png"));
	}
}
