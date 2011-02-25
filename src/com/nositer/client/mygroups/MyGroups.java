package com.nositer.client.mygroups;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.TabItemPlus;

@SuppressWarnings({"rawtypes", "unchecked"})
public class MyGroups extends TabPanel implements Resizable {

	private TabItemPlus myGroupsItem;
	private MyGroupsContainer myGroupsContainer;
	private static MyGroups instance;

	public MyGroupsContainer getMyGroupsContainer() {
		return myGroupsContainer;
	}

	public void setMyGroupsContainer(MyGroupsContainer myGroupsContainer) {
		this.myGroupsContainer = myGroupsContainer;
	}
	

	public static MyGroups getInstance(boolean createIfNecessary) {
		MyGroups retval = null;
		if (instance != null) {
			retval = instance;
		} else if (createIfNecessary) {
			retval = new MyGroups();			
		}
		return retval;
	}
	
	public static void setInstance(MyGroups instance) {
		MyGroups.instance = instance;
	}

	public TabItemPlus getMyGroupsItem() {
		return myGroupsItem;
	}

	public void setMyGroupsItem(TabItemPlus myGroupsItem) {
		this.myGroupsItem = myGroupsItem;
	}

	public MyGroups() {
		init();
	}

	
	public void init() {
		setAutoHeight(true);
		setAutoWidth(true);
		myGroupsItem = new TabItemPlus("My Groups") {

			@Override
			public void resize(int width, int height) {
				
			}
			
		};  
		myGroupsItem.setClosable(false);
		myGroupsItem.addListener(Events.Select, new Listener() {

			@Override
			public void handleEvent(BaseEvent be) {
				resize(0,0);
				
			}});
		FitLayout layout = new FitLayout();
		myGroupsItem.setLayout(layout);
		myGroupsContainer = new MyGroupsContainer();
		myGroupsItem.add(myGroupsContainer);
		add(myGroupsItem);
		instance = this;
	}
	
	public void showTab(String tabId) {
		TabItem tabItem = null;
		tabItem = findItem(tabId, false);
		if (tabItem == null) {
			tabItem = new GroupTabItem(tabId);			
			add(tabItem);
		}
		
		setSelection(tabItem);
	}

	@Override
	public void resize(int width, int height) {
		Resizable resizable = null;
		if (this.getSelectedItem().getItems().get(0) instanceof Resizable) {
			resizable = (Resizable)this.getSelectedItem().getItems().get(0);
			resizable.resize(width, height);
		}
	}  
}
