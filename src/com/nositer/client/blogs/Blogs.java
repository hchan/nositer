package com.nositer.client.blogs;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.nositer.client.Nositer;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.dto.generated.Groupmessage;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.searchforblogs.SearchForBlogsContainer;
import com.nositer.client.widget.TabItemPlus;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Blogs extends TabPanel {

	public enum TabItemType {
		BLOGS, SEARCHFORBLOGS
	}

	private TabItemPlus blogsTabItem;
	private TabItem searchForBlogsTabItem;
	private BlogsContainer blogsContainer;
	private SearchForBlogsContainer searchForBlogsContainer;
	private static Blogs instance;


	public TabItem getSearchForBlogsTabItem() {
		return searchForBlogsTabItem;
	}

	public void setSearchForBlogsTabItem(TabItem searchForGroupsTabItem) {
		this.searchForBlogsTabItem = searchForGroupsTabItem;
	}

	public BlogsContainer getBlogsContainer() {
		return blogsContainer;
	}

	public void setBlogsContainer(BlogsContainer blogsContainer) {
		this.blogsContainer = blogsContainer;
	}


	public static Blogs getInstance(boolean createIfNecessary) {
		Blogs retval = null;
		if (instance != null) {
			retval = instance;
		} else if (createIfNecessary) {
			retval = new Blogs();			
		}
		return retval;
	}

	public static void setInstance(Blogs instance) {
		Blogs.instance = instance;
	}

	public TabItemPlus getBlogsTabItem() {
		return blogsTabItem;
	}

	public void setBlogsTabItem(TabItemPlus blogsTabItem) {
		this.blogsTabItem = blogsTabItem;
	}

	public Blogs() {
		init();
	}


	public void init() {
		setAutoHeight(true);
		setAutoWidth(true);
		blogsTabItem = createBlogsTabItem();
		searchForBlogsTabItem = createSearchForBlogsTabItem();

		add(blogsTabItem);
		add(searchForBlogsTabItem);
		instance = this;
	}

	private TabItemPlus createBlogsTabItem() {
		TabItemPlus retval;
		retval = new TabItemPlus("Blogs") {
			@Override
			public void resize(int width, int height) {				
			}			
			@Override
			public void addDefaultListeners() {			
				addListener(Events.Select, new Listener() {
					@Override
					public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {						
						HistoryManager.addHistory(HistoryToken.BLOGS.toString());
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
		blogsContainer = new BlogsContainer();
		retval.setLayout(new FitLayout());
		retval.add(blogsContainer);
		return retval;
	}

	private TabItem createSearchForBlogsTabItem() {
		TabItem retval;
		searchForBlogsContainer = new SearchForBlogsContainer();
		retval = new TabItemPlus("Search For Blogs") {
			@Override
			public void resize(int width, int height) {				
			}			
			@Override
			public void addDefaultListeners() {			
				addListener(Events.Select, new Listener() {
					@Override
					public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {						
						HistoryManager.addHistory(HistoryToken.SEARCHFORBLOGENTRIES.toString());
						searchForBlogsContainer.resize(0,0);
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
		retval.add(searchForBlogsContainer);
		return retval;
	}

	public TabItem showClosableTab(String tabId, GroupTabPanel.TabItemType tabItemType) {
		TabItem tabItem = null;
		tabItem = findItem(tabId, false);
		if (tabItem == null) {
			if (tabItemType.equals(GroupTabPanel.TabItemType.SUBSCRIBER)) {
				tabItem = new UserTabItem(tabId);
			}
			// fixes race condition of getGroupTabPanel not being created yet
			// uploads a are a special case as it uses flash so that means that we
			// do lazy init as the focus has to be set so that the upload screen is selected
			else if (tabItemType.equals(GroupTabPanel.TabItemType.UPLOAD)) {
				GroupTabItem groupTabItem = new GroupTabItem(tabId, tabItemType) {
					public void init(GroupPlusView groupPlusView) {
						super.init(groupPlusView);
						if (Blogs.isGroupIOwn(groupPlusView)) {
							getGroupTabPanel().getUploadGroupTabItem().initSWF();
							getGroupTabPanel().getUploadGroupTabItem().layout();
						}
					};
				};
				tabItem = groupTabItem;
			} 

			else {
				tabItem = new GroupTabItem(tabId, tabItemType);				
			}
			add(tabItem);
		} else {
			if (tabItemType.equals(GroupTabPanel.TabItemType.UPLOAD)) {
				GroupTabItem groupTabItem = (GroupTabItem) tabItem;
				groupTabItem.
				getGroupTabPanel().
				show(
						tabItemType);
				groupTabItem.getGroupTabPanel().getUploadGroupTabItem().initSWF();
				groupTabItem.getGroupTabPanel().getUploadGroupTabItem().layout();
			}
		}

		if (tabItem instanceof GroupTabItem) {
			GroupTabItem groupTabItem = (GroupTabItem) tabItem;
			if (groupTabItem.getGroupTabPanel() != null) {
				groupTabItem.getGroupTabPanel().show(tabItemType);
			}
		}
		setSelection(tabItem);	
		return tabItem;
	}

	public TabItem showNonClosableTab(Blogs.TabItemType tabItemType) {
		TabItem tabItem = null;
		if (tabItemType.equals(Blogs.TabItemType.BLOGS)) {
			tabItem = blogsTabItem;
			blogsContainer.resize(0,0);
		} else if (tabItemType.equals(Blogs.TabItemType.SEARCHFORBLOGS)) {
			tabItem = searchForBlogsTabItem;
			searchForBlogsContainer.resize(0, 0);
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

	// TODO setup so that owner of the group can also modify anyone's messages;
	public static boolean isGroupmessageICanEdit(Groupmessage groupmessage) {
		boolean retval = false;
		retval = groupmessage.getUserid().equals(Nositer.getInstance().getUser().getId());
		return retval;
	}

}
