package com.nositer.client.groups;

import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.nositer.client.dto.generated.Group;


public class GroupTabPanel extends TabPanel {
	private ViewGroupTabItem viewGroupTabItem;
	private TabItem editTabItem;
	private Group group;

	public GroupTabPanel(Group group) {
		super();
		this.group = group;
		init();
	}



	public TabItem getEditTabItem() {
		return editTabItem;
	}

	public void setEditTabItem(TabItem editTabItem) {
		this.editTabItem = editTabItem;
	}

	public void init() {
		setTabPosition(TabPosition.BOTTOM);
		viewGroupTabItem = new ViewGroupTabItem(group);
		add(viewGroupTabItem);

		if (Groups.isGroupIOwn(group)) {
			editTabItem = new TabItem("Edit");
			add(editTabItem);
		}
		layout();
	}
}
