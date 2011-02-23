package com.nositer.client.mygroups;

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
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.GridView;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.util.ImageHelper;
import com.nositer.client.widget.avatar.Avatar;

@SuppressWarnings("rawtypes")
public class GroupsGrid extends Grid<BeanModel> {

	private RpcProxy<ArrayList<Group>> proxy;
	private BaseListLoader<PagingLoadResult<ModelData>> loader;

	public GroupsGrid() {
		proxy = new RpcProxy<ArrayList<Group>>() {
			@Override
			protected void load(Object loadConfig,
					AsyncCallback<ArrayList<Group>> callback) {
				ServiceBroker.groupService.getMyGroups(callback);  
			}
		};  
		loader = new BaseListLoader<PagingLoadResult<ModelData>>(  
				proxy, new BeanModelReader());  
		loader.setRemoteSort(true);  
		store = new ListStore<BeanModel>(loader);  

		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();  
		columns.add(new ColumnConfig(Group.ColumnType.name.toString(), "Name", 150));  
		ColumnConfig avatarColumnConfig = new ColumnConfig(Group.ColumnType.avatarlocation.toString(), "Avatar", 150);
		avatarColumnConfig.setRenderer(getAvatarGridCellRenderer());
		columns.add(avatarColumnConfig);  
		ColumnConfig date = new ColumnConfig(Group.ColumnType.createdtime.toString(), "Created On", 100);  
		date.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/y"));  
		columns.add(date);  

		cm = new ColumnModel(columns);  
		this.view = new GridView();
		disabledStyle = null;
		baseStyle = "x-grid-panel";
		setSelectionModel(new GridSelectionModel<BeanModel>());
		disableTextSelection(true);
		init();
	}
	
	private GridCellRenderer getAvatarGridCellRenderer() {
		GridCellRenderer retval = new GridCellRenderer() {

			@Override
			public Object render(ModelData model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore store, Grid grid) {
				Avatar retval = new Avatar();
				try {
					BeanModel beanModel = (BeanModel) model;
					Group group = beanModel.getBean();
					retval.setPathToSmallImage(ImageHelper.getUserImagePathURL(group.getAvatarlocation(), group.getUserid()));
				} catch (Exception e) {
					GWTUtil.log("", e);
				}
				return retval;
			}  
		};
		return retval;
	}

	public void init() {
		addListener(Events.Attach, new Listener<GridEvent<BeanModel>>() {  
			public void handleEvent(GridEvent<BeanModel> be) {  
				store.getLoader().load();
			}  
		});  
		setLoadMask(true);  
		setBorders(true);  
		setAutoExpandColumn("name");  
	}
}
