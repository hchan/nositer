package com.nositer.client.groupsubscriptions;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.GridView;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.Nositer;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.dto.generated.GroupSubscriptionView;
import com.nositer.client.groups.Groups;
import com.nositer.client.groups.GroupsGrid;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.util.HttpGetFileHelper;
import com.nositer.client.widget.avatar.Avatar;
import com.nositer.client.widget.menuitem.SubscribeMenuItem;
import com.nositer.client.widget.menuitem.ViewMenuItem;

@SuppressWarnings("rawtypes")
public class GroupSubscriptionsGrid extends GroupsGrid {
	public static final String USERCRITERIA = "USERCRITERIA";

	private SearchCriteriaForGroupSubscriptionsPanel searchCriteriaForGroupsPanel;
	private GroupSubscriptionsContainer groupSubscriptionsContainer;
	protected RpcProxy<ArrayList<GroupSubscriptionView>> proxy;

	public SearchCriteriaForGroupSubscriptionsPanel getSearchCriteriaForGroupsPanel() {
		return searchCriteriaForGroupsPanel;
	}

	public void setSearchCriteriaForGroupsPanel(
			SearchCriteriaForGroupSubscriptionsPanel searchCriteriaForGroupsPanel) {
		this.searchCriteriaForGroupsPanel = searchCriteriaForGroupsPanel;
	}


	public GroupSubscriptionsGrid(final GroupSubscriptionsContainer groupSubscriptionsContainer) {
		setLoadMask(false);

		this.groupSubscriptionsContainer = groupSubscriptionsContainer;
		proxy = new RpcProxy<ArrayList<GroupSubscriptionView>>() {
			@Override
			protected void load(Object loadConfig,
					AsyncCallback<ArrayList<GroupSubscriptionView>> callback) {
				GroupSubscriptionsGrid.this.load(loadConfig,callback);
			}
		};  		


		BeanModelReader reader = new BeanModelReader() {
			@Override
			protected ListLoadResult<ModelData> newLoadResult(Object loadConfig, List<ModelData> models) {
				//return super.newLoadResult(loadConfig, models);
				return new BasePagingLoadResult<ModelData>(models);
			}
		};
		loader = new BasePagingLoader<PagingLoadResult<ModelData>>(  
				proxy, reader) {

			@Override
			protected void loadData(final Object config) {
				super.loadData(config);
				groupSubscriptionsContainer.resize(0,0);
			}
		}; 


		//new BaseListLoader<PagingLoadResult<ModelData>>(  
		//proxy, reader);
		loader.setRemoteSort(false);  
		store = new ListStore<BeanModel>(loader);  

		//toolBar.bind(loader);  
		cm = createColumnModel();
		this.view = new GridView();
		disabledStyle = null;
		baseStyle = "x-grid-panel";
		setSelectionModel(new GridSelectionModel<BeanModel>());
		disableTextSelection(true);
		init();
	}

	@Override
	public ColumnModel createColumnModel() {
		ColumnModel retval = null;
		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();  
		ColumnConfig avatarColumnConfig = new ColumnConfig(GroupSubscriptionView.Column.avatarlocation.toString(), "Avatar", 50);
		avatarColumnConfig.setRenderer(getAvatarGridCellRenderer());
		columns.add(avatarColumnConfig);  
		columns.add(new ColumnConfig(GroupSubscriptionView.Column.lastname.toString(), "Last Name", 100));		
		columns.add(new ColumnConfig(GroupSubscriptionView.Column.firstname.toString(), "First Name", 100));	
		ColumnConfig date = new ColumnConfig(GroupSubscriptionView.Column.createdtime.toString(), "Subscribed On", 100);  
		date.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/y"));  
		columns.add(date);  
		retval = new ColumnModel(columns);
		return retval;
	}

	@Override
	public void init() {
		//contextMenu = new Menu();

		//setContextMenu(contextMenu);


		addListeners();

		store.getLoader().load();
		setLoadMask(true);  
		setBorders(true);  
		setAutoExpandColumn(GroupSubscriptionView.Column.createdtime.toString());  

	}

	public void load() {
		store.getLoader().load();
	}


	public void load(Object loadConfig, AsyncCallback<ArrayList<GroupSubscriptionView>> callback) {
		PagingLoadConfig config = (PagingLoadConfig) loadConfig;
		if (config.get(USERCRITERIA) != null) {
			ServiceBroker.groupService.findSubscriptions(groupSubscriptionsContainer.getGroupPlusView(), config.getProperties().get(GroupSubscriptionView.Column.lastname.toString()).toString(), callback);
		} else {
			ServiceBroker.groupService.getSubscriptions(groupSubscriptionsContainer.getGroupPlusView(), callback);
		}
		unmask();
	}



	@Override
	protected GridCellRenderer getAvatarGridCellRenderer() {
		GridCellRenderer retval = new GridCellRenderer() {

			@Override
			public Object render(ModelData model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore store, Grid grid) {
				Avatar retval = new Avatar();
				try {
					BeanModel beanModel = (BeanModel) model;
					GroupSubscriptionView groupSubscriptionView = beanModel.getBean();
					retval.setPathToSmallImage(HttpGetFileHelper.getUserPathURL(groupSubscriptionView.getAvatarlocation(), groupSubscriptionView.getUserid()));
				} catch (Exception e) {
					GWTUtil.log("", e);
				}
				return retval;
			}  
		};
		return retval;
	}


	@Override
	protected void addListeners() {
		// sigh ... can't listen to single AND doubleclick - can listen to one of the two
		this.addListener(Events.OnDoubleClick,  new Listener<GridEvent<BeanModel>>() {  

			@Override
			public void handleEvent(GridEvent<BeanModel> gridEvent) {  			
				BeanModel beanModel = gridEvent.getGrid().getSelectionModel().getSelectedItem();
				final GroupSubscriptionView subscriber = beanModel.getBean();	
				doViewSubscriber(subscriber);
			}
		});
	};

	/*
	@Override
	protected void showContextMenu(GridEvent<BeanModel> gridEvent) {
		BeanModel beanModel = gridEvent.getGrid().getSelectionModel().getSelectedItem();
		final GroupSubscriptionView subscriber = beanModel.getBean();	
		ModelData selectedItem = this.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			contextMenu.removeAll();
			ViewMenuItem viewMenuItem = new ViewMenuItem() {
				public void doSelect() {
					doViewSubscriber(subscriber);
				};
			};
			contextMenu.add(viewMenuItem);		
			if (Blogs.isGroupIOwn(this.groupSubscriptionsContainer.getGroupPlusView())) {
				
				SubscribeMenuItem subscribeMenuItem = new SubscribeMenuItem() {
					public void doSelect() {
						//doViewGroup(subscriber);
					};
				};
				contextMenu.add(subscribeMenuItem);
			}
		} else {
			gridEvent.setCancelled(true);
		}
	}
	*/
	
	private void doViewSubscriber(GroupSubscriptionView subscriber) {
		HistoryManager.addHistory(HistoryToken.USER + HistoryManager.SUBTOKENSEPARATOR + subscriber.getUserid());
		
	}
}
