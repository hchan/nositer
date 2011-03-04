package com.nositer.client.groups;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TabPanelEvent;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.top.TopPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.TabItemPlus;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Groups extends TabPanel {

	private TabItemPlus groupsItem;
	private GroupsContainer groupsContainer;
	private static Groups instance;

	public GroupsContainer getGroupsContainer() {
		return groupsContainer;
	}

	public void setGroupsContainer(GroupsContainer groupsContainer) {
		this.groupsContainer = groupsContainer;
	}
	

	public static Groups getInstance(boolean createIfNecessary) {
		Groups retval = null;
		if (instance != null) {
			retval = instance;
		} else if (createIfNecessary) {
			retval = new Groups();			
		}
		return retval;
	}
	
	public static void setInstance(Groups instance) {
		Groups.instance = instance;
	}

	public TabItemPlus getGroupsItem() {
		return groupsItem;
	}

	public void setGroupsItem(TabItemPlus groupsItem) {
		this.groupsItem = groupsItem;
	}

	public Groups() {
		init();
	}

	
	public void init() {
		setAutoHeight(true);
		setAutoWidth(true);
		groupsItem = new TabItemPlus("Groups") {

			@Override
			public void resize(int width, int height) {
				
			}
			
			@Override
			public void addDefaultListeners() {
			
				addListener(Events.Select, new Listener() {
					@Override
					public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {						
						HistoryManager.addHistory(HistoryToken.GROUPS.toString());
						resize(0,0);
					}
				});
			}
			
		};  
		groupsItem.setClosable(false);
		groupsItem.addListener(Events.Select, new Listener() {

			@Override
			public void handleEvent(BaseEvent be) {
				//resize(0,0);
				
			}});
		FitLayout layout = new FitLayout();
		groupsItem.setLayout(layout);
		groupsContainer = new GroupsContainer();
		groupsItem.add(groupsContainer);
		add(groupsItem);
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

	public void showEditTab(String tabId) {
		TabItem tabItem = null;
		tabItem = findItem(tabId, false);
		if (tabItem == null) {
			tabItem = new GroupTabItem(tabId);			
			add(tabItem);
		}	
		setSelection(tabItem);		
	}
	
	

	public static boolean isGroupIOwn(Group group) {
		boolean retval = false;
		retval = group.getUserid().equals(TopPanel.getInstance().getUser().getId());
		return retval;
	}  
}
