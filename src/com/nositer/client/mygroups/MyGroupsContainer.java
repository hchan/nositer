package com.nositer.client.mygroups;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseListLoader;
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
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.combobox.ComboBoxPlus;


public class MyGroupsContainer extends LayoutContainer implements Resizable {

	private ContentPanel contentPanel;
	private Grid<BeanModel> grid;

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
		final BaseListLoader<PagingLoadResult<ModelData>> loader = new BaseListLoader<PagingLoadResult<ModelData>>(  
				proxy, new BeanModelReader());  
		loader.setRemoteSort(true);  

		ListStore<BeanModel> store = new ListStore<BeanModel>(loader);  

		

		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();  
		columns.add(new ColumnConfig(Group.ColumnType.name.toString(), "Name", 150));  
		
		ColumnConfig date = new ColumnConfig(Group.ColumnType.createdtime.toString(), "Created On", 100);  
		date.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/y"));  
		columns.add(date);  

		ColumnModel cm = new ColumnModel(columns);  

		grid = new Grid<BeanModel>(store, cm);  
		grid.addListener(Events.Attach, new Listener<GridEvent<BeanModel>>() {  
			public void handleEvent(GridEvent<BeanModel> be) {  
				loader.load();
			}  
		});  
		grid.setLoadMask(true);  
		grid.setBorders(true);  
		grid.setAutoExpandColumn("name");  
		//grid.setSize(600, 350);
		
		contentPanel = new ContentPanel();  
		contentPanel.setFrame(true);  
		contentPanel.setCollapsible(true);  
		contentPanel.setAnimCollapse(false);  
		contentPanel.setButtonAlign(HorizontalAlignment.CENTER);  
		contentPanel.setHeaderVisible(false);
		
		contentPanel.setLayout(new FitLayout());  
		contentPanel.add(grid);
		
		//contentPanel.setSize(600, 350);  
	//	panel.setBottomComponent(toolBar);  
		setAutoHeight(true);
		setAutoWidth(true);
		resize(0,0);
		add(contentPanel);  
	}

	@Override
	public void resize(int width, int height) {
		
		contentPanel.setSize(MainPanel.getInstance().getWidth()-4, MainPanel.getInstance().getHeight()-30);
		//grid.setSize(MainPanel.getInstance().getWidth()-15, MainPanel.getInstance().getHeight()-30);
	}
}
