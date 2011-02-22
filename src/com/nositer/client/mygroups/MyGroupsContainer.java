package com.nositer.client.mygroups;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Group;


public class MyGroupsContainer extends LayoutContainer {

	public MyGroupsContainer() {
		init();
	}

	public void init() {
		setLayout(new FitLayout());

		RpcProxy<ArrayList<Group>> proxy = new RpcProxy<ArrayList<Group>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<ArrayList<Group>> callback) {
				ServiceBroker.groupService.getMyGroups(callback);  

			}


		};  
		// loader  
		final BasePagingLoader<PagingLoadResult<ModelData>> loader = new BasePagingLoader<PagingLoadResult<ModelData>>(  
				proxy, new BeanModelReader());  
		loader.setRemoteSort(true);  

		ListStore<BeanModel> store = new ListStore<BeanModel>(loader);  

		final PagingToolBar toolBar = new PagingToolBar(50);  
		toolBar.bind(loader);  

		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();  
		columns.add(new ColumnConfig("forum", "Forum", 150));  
		columns.add(new ColumnConfig("username", "Username", 100));  
		columns.add(new ColumnConfig("subject", "Subject", 200));  
		ColumnConfig date = new ColumnConfig("date", "Date", 100);  
		date.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/y"));  
		columns.add(date);  

		ColumnModel cm = new ColumnModel(columns);  

		Grid<BeanModel> grid = new Grid<BeanModel>(store, cm);  
		grid.addListener(Events.Attach, new Listener<GridEvent<BeanModel>>() {  
			public void handleEvent(GridEvent<BeanModel> be) {  
				loader.load(0, 50);  
			}  
		});  
		grid.setLoadMask(true);  
		grid.setBorders(true);  
		grid.setAutoExpandColumn("forum");  

		ContentPanel panel = new ContentPanel();  
		panel.setFrame(true);  
		panel.setCollapsible(true);  
		panel.setAnimCollapse(false);  
		panel.setButtonAlign(HorizontalAlignment.CENTER);  

		panel.setHeading("Paging Grid");  
		panel.setLayout(new FitLayout());  
		panel.add(grid);  
		panel.setSize(600, 350);  
		panel.setBottomComponent(toolBar);  

		add(panel);  
	}
}
