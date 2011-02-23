package com.nositer.client.mygroups;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.User;
import com.nositer.client.top.TopPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.viewprofile.ViewProfile;
import com.nositer.client.viewprofile.ViewProfileContainer;

public class MyGroups extends TabPanel {

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
		

		myGroupsItem.setScrollMode(Scroll.AUTO);
		myGroupsItem.addListener(Events.Select, new Listener<ComponentEvent>() {  
			public void handleEvent(ComponentEvent be) {  
				myGroupsContainer = new MyGroupsContainer();
				myGroupsItem.add(myGroupsContainer);
				MyGroups.this.layout();
			}
		});  
		add(myGroupsItem);
	}  
}
