package com.nositer.client.searchforgroups;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridGroupRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.GridView;
import com.extjs.gxt.ui.client.widget.grid.GroupColumnData;
import com.extjs.gxt.ui.client.widget.grid.GroupingView;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.Postalcode;
import com.nositer.client.dto.generated.Zipcode;
import com.nositer.client.groups.GroupsGrid;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.util.ImageHelper;
import com.nositer.client.widget.ErrorPanel;
import com.nositer.client.widget.Location;
import com.nositer.client.widget.avatar.Avatar;
import com.nositer.client.widget.menuitem.DeleteMenuItem;
import com.nositer.client.widget.menuitem.EditMenuItem;
import com.nositer.client.widget.menuitem.ViewMenuItem;
import com.nositer.client.widget.messagebox.ConfirmMessageBox;


public class SearchGroupsGrid extends GroupsGrid {
	private SearchCriteriaForGroupsPanel searchCriteriaForGroupsPanel;
	
	public SearchCriteriaForGroupsPanel getSearchCriteriaForGroupsPanel() {
		return searchCriteriaForGroupsPanel;
	}

	public void setSearchCriteriaForGroupsPanel(
			SearchCriteriaForGroupsPanel searchCriteriaForGroupsPanel) {
		this.searchCriteriaForGroupsPanel = searchCriteriaForGroupsPanel;
	}

	public SearchGroupsGrid(final SearchCriteriaForGroupsPanel searchCriteriaForGroupsPanel) {
		this.searchCriteriaForGroupsPanel = searchCriteriaForGroupsPanel;
		proxy = new RpcProxy<ArrayList<Group>>() {
			@Override
			protected void load(Object loadConfig,
					AsyncCallback<ArrayList<Group>> callback) {
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
					
				} else {
					errorPanel.hide();				
					if (callback == null) {
						callback = new AsyncCallback<ArrayList<Group>>() {

							@Override
							public void onFailure(Throwable caught) {
								GWTUtil.log("", caught);
							}

							@Override
							public void onSuccess(ArrayList<Group> result) {
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
				
			}
		};  		
		loader = new BaseListLoader<PagingLoadResult<ModelData>>(  
				proxy, new BeanModelReader());  
		loader.setRemoteSort(false);  

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


}
