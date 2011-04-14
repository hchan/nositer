package com.nositer.client.groups;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.nositer.client.Nositer;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.dto.generated.GroupSubscriptionView;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.searchforgroups.SearchForGroupsContainer;
import com.nositer.client.widget.TabItemPlus;
import com.nositer.client.widget.menuitem.SubscribeMenuItem;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Groups extends TabPanel {

	public enum TabItemType {
		GROUPS, SEARCHFORGROUPS
	}

	private TabItemPlus groupsTabItem;
	private TabItem searchForGroupsTabItem;
	private GroupsContainer groupsContainer;
	private SearchForGroupsContainer searchForGroupsContainer;
	private static Groups instance;


	public TabItem getSearchForGroupsTabItem() {
		return searchForGroupsTabItem;
	}

	public void setSearchForGroupsTabItem(TabItem searchForGroupsTabItem) {
		this.searchForGroupsTabItem = searchForGroupsTabItem;
	}

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

	public TabItemPlus getGroupsTabItem() {
		return groupsTabItem;
	}

	public void setGroupsItem(TabItemPlus groupsTabItem) {
		this.groupsTabItem = groupsTabItem;
	}

	public Groups() {
		init();
	}


	public void init() {
		setAutoHeight(true);
		setAutoWidth(true);
		groupsTabItem = createGroupsTabItem();
		searchForGroupsTabItem = createSearchForGroupsTabItem();

		add(groupsTabItem);
		add(searchForGroupsTabItem);
		instance = this;
	}

	private TabItemPlus createGroupsTabItem() {
		TabItemPlus retval;
		retval = new TabItemPlus("Groups") {
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
		retval.setClosable(false);
		retval.addListener(Events.Select, new Listener() {

			@Override
			public void handleEvent(BaseEvent be) {
				//resize(0,0);

			}});		
		groupsContainer = new GroupsContainer();
		retval.setLayout(new FitLayout());
		retval.add(groupsContainer);
		return retval;
	}

	private TabItem createSearchForGroupsTabItem() {
		TabItem retval;
		searchForGroupsContainer = new SearchForGroupsContainer();
		retval = new TabItemPlus("Search For Groups") {
			@Override
			public void resize(int width, int height) {				
			}			
			@Override
			public void addDefaultListeners() {			
				addListener(Events.Select, new Listener() {
					@Override
					public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {						
						HistoryManager.addHistory(HistoryToken.SEARCHFORGROUPS.toString());
						searchForGroupsContainer.resize(0,0);
					}
				});
			}			
		};  
		retval.setClosable(false);
		retval.addListener(Events.Select, new Listener() {

			@Override
			public void handleEvent(BaseEvent be) {
				//resize(0,0);

			}});		
		
		retval.setLayout(new FitLayout());
		retval.add(searchForGroupsContainer);
		return retval;
	}

	public TabItem showClosableTab(String tabId, GroupTabPanel.TabItemType tabItemType) {
		TabItem tabItem = null;
		tabItem = findItem(tabId, false);
		if (tabItem == null) {
			tabItem = new GroupTabItem(tabId, tabItemType);			
			add(tabItem);
		}	
		setSelection(tabItem);		
		return tabItem;
	}

	public TabItem showNonClosableTab(Groups.TabItemType tabItemType) {
		TabItem tabItem = null;
		if (tabItemType.equals(Groups.TabItemType.GROUPS)) {
			tabItem = groupsTabItem;
			groupsContainer.resize(0,0);
		} else if (tabItemType.equals(Groups.TabItemType.SEARCHFORGROUPS)) {
			tabItem = searchForGroupsTabItem;
			searchForGroupsContainer.resize(0, 0);
		}
		setSelection(tabItem);		

		return tabItem;
	}



	public static boolean isGroupIOwn(GroupPlusView groupPlusView) {
		boolean retval = false;
		retval = groupPlusView.getUserid().equals(Nositer.getInstance().getUser().getId())
		&& groupPlusView.getOwner();
		return retval;
	}  

	public static boolean isGroupICanSubscribeTo(GroupPlusView groupPlusView) {
		boolean retval = false;
		retval = (!(groupPlusView.getUserid().equals(Nositer.getInstance().getUser().getId()))) 
		|| groupPlusView.getUserHasGroupDisable();
		return retval;
	}

	
}
