package com.nositer.client.groups;

import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.Resizable;


public class GroupTabPanel extends TabPanel implements Resizable {
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
		resize(0,0);
		//if (Groups.isGroupIOwn(group)) {
		//	editTabItem = new TabItem("Edit");
		//	add(editTabItem);
		//}
	}



	@Override
	public void resize(int width, int height) {
		setSize(MainPanel.getInstance().getWidth(), 
				MainPanel.getInstance().getHeight()-30);
	
	}
}
