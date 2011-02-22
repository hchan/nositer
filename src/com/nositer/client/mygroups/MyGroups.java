package com.nositer.client.mygroups;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class MyGroups extends TabPanel {

	private TabItem myGroupsItem;


	public TabItem getMyGroupsItem() {
		return myGroupsItem;
	}

	public void setMyGroupsItem(TabItem myGroupsItem) {
		this.myGroupsItem = myGroupsItem;
	}

	public MyGroups() {
		init();
	}

	public void init() {
		setAutoHeight(true);
		setAutoWidth(true);

		myGroupsItem = new TabItem("My Groups");  
		myGroupsItem.setClosable(false);
		FitLayout layout = new FitLayout();
		myGroupsItem.setLayout(layout);


		myGroupsItem.setScrollMode(Scroll.AUTO);
		
		add(myGroupsItem);
	}  
}
