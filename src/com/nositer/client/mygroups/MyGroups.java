package com.nositer.client.mygroups;

import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.nositer.client.widget.Resizable;

public class MyGroups extends TabPanel implements Resizable {

	private TabItem myGroupsItem;
	private MyGroupsContainer myGroupsContainer;


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
		myGroupsContainer = new MyGroupsContainer();
		myGroupsItem.add(myGroupsContainer);
		add(myGroupsItem);
	}

	@Override
	public void resize(int width, int height) {
		Resizable resizable = null;
		if (this.getSelectedItem() instanceof Resizable) {
			resizable = (Resizable)this.getSelectedItem();
			resizable.resize(width, height);
		}
	}  
}
