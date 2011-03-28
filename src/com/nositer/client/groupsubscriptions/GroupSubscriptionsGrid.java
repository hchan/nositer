package com.nositer.client.groupsubscriptions;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.Loader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoader; 
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.GridView;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.Postalcode;
import com.nositer.client.dto.generated.Zipcode;
import com.nositer.client.dto.generated.UserHasGroupView;
import com.nositer.client.groups.Groups;
import com.nositer.client.groups.GroupsGrid;
import com.nositer.client.top.TopPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.ErrorPanel;
import com.nositer.client.widget.Location;
import com.nositer.client.widget.menuitem.DeleteMenuItem;
import com.nositer.client.widget.menuitem.EditMenuItem;
import com.nositer.client.widget.menuitem.SubscribeMenuItem;
import com.nositer.client.widget.menuitem.ViewMenuItem;


public class GroupSubscriptionsGrid extends GroupsGrid {
	private SearchCriteriaForGroupSubscriptionsPanel searchCriteriaForGroupsPanel;
	private GroupSubscriptionsContainer groupSubscriptionsContainer;

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
		proxy = new RpcProxy<ArrayList<UserHasGroupView>>() {
			@Override
			protected void load(Object loadConfig,
					AsyncCallback<ArrayList<UserHasGroupView>> callback) {
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



	public void init() {
		contextMenu = new Menu();

		setContextMenu(contextMenu);


		addListeners();

		//store.getLoader().load();
		setLoadMask(true);  
		setBorders(true);  
		setAutoExpandColumn(Group.ColumnType.description.toString());  

	}

	public void load() {
		store.getLoader().load();
	}

	public void load(Object loadConfig,
			AsyncCallback<ArrayList<UserHasGroupView>> callback) {
		Location location = searchCriteriaForGroupsPanel.getLocation();

		ErrorPanel errorPanel = searchCriteriaForGroupsPanel.getErrorPanel();

		if (location.getCountry().getValue().getData(Location.COUNTRYCODE).equals(Location.COUNTRYCODE_CAN)) {					
			Postalcode postalcode = location.getPostalcode().getBean();			
			if (postalcode != null) {
				searchCriteriaForGroupsPanel.setLatitude(postalcode.getLatitude());
				searchCriteriaForGroupsPanel.setLongitude(postalcode.getLongitude());
			}
		} else {
			Zipcode zipcode = location.getZipcode().getBean();		
			if (zipcode != null) {
				searchCriteriaForGroupsPanel.setLatitude(zipcode.getLatitude());
				searchCriteriaForGroupsPanel.setLongitude(zipcode.getLongitude());
			}
		}
		ArrayList<String> errors = searchCriteriaForGroupsPanel.getErrors();
		if (errors.size() > 0) {
			errorPanel.setErrors(errors);
			errorPanel.show();	
			groupSubscriptionsContainer.getPagingToolBar().setEnabled(true);
			groupSubscriptionsContainer.getPagingToolBar().setImages(groupSubscriptionsContainer.getPagingToolBar().getImages());
			//getRefreshButton().setIcon(SearchForGroups.getInstance().getPagingToolBar().getImages().getRefresh());
		} else {
			errorPanel.hide();				
			if (callback == null) {
				callback = new AsyncCallback<ArrayList<UserHasGroupView>>() {

					@Override
					public void onFailure(Throwable caught) {
						GWTUtil.log("", caught);
					}

					@Override
					public void onSuccess(ArrayList<UserHasGroupView> result) {
						GWTUtil.log("Wow, this actually succeeded");
					}

				};
			}
			ServiceBroker.groupService.search(
					searchCriteriaForGroupsPanel.getGroupName().getValue(), 
					searchCriteriaForGroupsPanel.getLatitude(), 
					searchCriteriaForGroupsPanel.getLongitude(), 
					searchCriteriaForGroupsPanel.getRadius().getValue(), 
					callback);
		}		
		unmask();
	}
	
	
	protected void showContextMenu(GridEvent<BeanModel> gridEvent) {
		BeanModel beanModel = gridEvent.getGrid().getSelectionModel().getSelectedItem();
		final UserHasGroupView userHasGroupView = beanModel.getBean();	
		ModelData selectedItem = this.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			contextMenu.removeAll();
			ViewMenuItem viewMenuItem = new ViewMenuItem() {
				public void doSelect() {
					doViewGroup(userHasGroupView);
				};
			};
			
			contextMenu.add(viewMenuItem);		
			if (!userHasGroupView.getUserid().equals(
					TopPanel.getInstance().getUser().getId())) {
				SubscribeMenuItem subscribeMenuItem = new SubscribeMenuItem() {
					public void doSelect() {
						doViewGroup(userHasGroupView);
					};
				};
				contextMenu.add(subscribeMenuItem);
			}
			

		} else {
			gridEvent.setCancelled(true);
		}
	}
}
