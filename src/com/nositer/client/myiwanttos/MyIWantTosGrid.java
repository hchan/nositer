package com.nositer.client.myiwanttos;

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
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.GridView;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Iwantto;
import com.nositer.client.history.HistoryManager;

public class MyIWantTosGrid extends Grid<BeanModel> {

	private RpcProxy<ArrayList<Iwantto>> proxy;
	private BaseListLoader<PagingLoadResult<ModelData>> loader;

	public MyIWantTosGrid() {
		proxy = new RpcProxy<ArrayList<Iwantto>>() {
			@Override
			protected void load(Object loadConfig,
					AsyncCallback<ArrayList<Iwantto>> callback) {
				ServiceBroker.iWantToService.getMyIWantTos(callback);  
			}
		};  		
		loader = new BaseListLoader<PagingLoadResult<ModelData>>(  
				proxy, new BeanModelReader());  
		loader.setRemoteSort(false);  
		store = new ListStore<BeanModel>(loader);  
		cm = createColumnModel();
		this.view = new GridView();
		disabledStyle = null;
		baseStyle = "x-grid-panel";
		setSelectionModel(new GridSelectionModel<BeanModel>());
		disableTextSelection(false);
		init();
	}

	public ColumnModel createColumnModel() {
		ColumnModel retval = null;
		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();  		 
		columns.add(new ColumnConfig(Iwantto.ColumnType.description.toString(), "I want to ...", 100));			
		ColumnConfig date = new ColumnConfig(Iwantto.ColumnType.createdtime.toString(), "Created On", 100);  
		date.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/y"));  
		columns.add(date);  
		retval = new ColumnModel(columns);
		return retval;
	}

	
	public void init() {		
		addListener(Events.RowClick, new Listener<GridEvent<BeanModel>>() {  
			@Override
			public void handleEvent(GridEvent<BeanModel> be) {  
				BeanModel beanModel = be.getGrid().getSelectionModel().getSelectedItem();
				Iwantto iwantto = beanModel.getBean();		    	
				HistoryManager.addSubHistoryToken(iwantto.getId().toString());
			}
		});
		
		store.getLoader().load();
		setLoadMask(true);  
		setBorders(true);  
		setAutoExpandColumn(Iwantto.ColumnType.description.toString());  				
	}

	public void refresh() {
		store.getLoader().load();
	}
}
