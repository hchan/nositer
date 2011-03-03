package com.nositer.client.groups;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
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
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.top.TopPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.util.ImageHelper;
import com.nositer.client.widget.avatar.Avatar;
import com.nositer.client.widget.menuitem.DeleteMenuItem;
import com.nositer.client.widget.menuitem.EditMenuItem;
import com.nositer.client.widget.menuitem.ViewMenuItem;
import com.nositer.client.widget.messagebox.ConfirmMessageBox;

@SuppressWarnings({"rawtypes", "unchecked"})
public class GroupsGrid extends Grid<BeanModel> {

	private RpcProxy<ArrayList<Group>> proxy;
	private BaseListLoader<PagingLoadResult<ModelData>> loader;
	private GroupingView groupingView;
	private Menu contextMenu;

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
		loader.setRemoteSort(false);  
		store = new GroupingStore<BeanModel>(loader);  
		cm = createColumnModel();
		this.view = new GridView();
		disabledStyle = null;
		baseStyle = "x-grid-panel";
		setSelectionModel(new GridSelectionModel<BeanModel>());
		disableTextSelection(true);
		init();
	}

	public ColumnModel createColumnModel() {
		ColumnModel retval = null;
		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();  
		ColumnConfig avatarColumnConfig = new ColumnConfig(Group.ColumnType.avatarlocation.toString(), "Avatar", 50);
		avatarColumnConfig.setRenderer(getAvatarGridCellRenderer());
		columns.add(avatarColumnConfig);  
		columns.add(new ColumnConfig(Group.ColumnType.name.toString(), "Name", 100));		
		columns.add(new ColumnConfig(Group.ColumnType.tagname.toString(), "Tag Name", 100));
		ColumnConfig descriptionColumnConfig = new ColumnConfig(Group.ColumnType.description.toString(), "Description", 200);
		descriptionColumnConfig.setRenderer(getDescriptionGridCellRenderer());
		columns.add(descriptionColumnConfig);  
		ColumnConfig date = new ColumnConfig(Group.ColumnType.createdtime.toString(), "Created On", 100);  
		date.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/y"));  
		columns.add(date);  
		retval = new ColumnModel(columns);
		return retval;
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

	private GridCellRenderer getDescriptionGridCellRenderer() {
		GridCellRenderer retval = new GridCellRenderer() {

			@Override
			public Object render(ModelData model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore store, Grid grid) {
				HtmlContainer retval = new HtmlContainer();
				retval.setStyleName("myGroupsRow");
				try {
					BeanModel beanModel = (BeanModel) model;
					Group group = beanModel.getBean();
					retval.setHtml(group.getDescription());
				} catch (Exception e) {
					GWTUtil.log("", e);
				}
				return retval;
			}  
		};
		return retval;
	}
	public void init() {
		contextMenu = new Menu();

		setContextMenu(contextMenu);
		groupingView = new GroupingView();


		groupingView.setForceFit(true);
		//groupingView.setShowGroupedColumn(false);
		groupingView.setGroupRenderer(new GridGroupRenderer() {

			@Override
			public String render(GroupColumnData data) {
				BeanModel beanModel   = (BeanModel) data.models.get(0);
				Group group = beanModel.getBean();
				String text = null;
				if (isGroupIOwn(group)) {
					text = "Groups I own";
				} else {
					text = "Groups I am subscribed too";
				}
				String length = data.models.size() == 1 ? "Item" : "Items";  				
				return text + ": (" + data.models.size() + " " + length + ")";  
			}


		});
		setView(groupingView);

		addListeners();

		GroupingStore<BeanModel> groupingStore = (GroupingStore<BeanModel>) store;
		groupingStore.groupBy(Group.ColumnType.userid.toString());
		store.getLoader().load();
		setLoadMask(true);  
		setBorders(true);  
		setAutoExpandColumn(Group.ColumnType.description.toString());  

	}


	private boolean isGroupIOwn(Group group) {
		boolean retval = false;
		retval = group.getUserid().equals(TopPanel.getInstance().getUser().getId());
		return retval;
	}


	private void addListeners() {
		contextMenu.addListener(Events.OnClick, new Listener<MenuEvent>() {

			@Override
			public void handleEvent(MenuEvent me) {
				if (me.getMenu().getBounds(true).x == me.getClientX()) {
					contextMenu.hide();
					BeanModel beanModel = GroupsGrid.this.getSelectionModel().getSelectedItem();
					final Group group = beanModel.getBean();	
					doViewGroup(group);
				}
			}
		});

		addListener(Events.RowClick, new Listener<GridEvent<BeanModel>>() {  

			@Override
			public void handleEvent(GridEvent<BeanModel> gridEvent) {  
				showContextMenu(gridEvent);
			}
		});

		this.addListener(Events.ContextMenu,  new Listener<GridEvent<BeanModel>>() {  

			@Override
			public void handleEvent(GridEvent<BeanModel> gridEvent) {  
				showContextMenu(gridEvent);
			}
		});

		// sigh ... can't listen to single AND doubleclick - can listen to one of the two
		this.addListener(Events.OnDoubleClick,  new Listener<GridEvent<BeanModel>>() {  

			@Override
			public void handleEvent(GridEvent<BeanModel> gridEvent) {  			
				BeanModel beanModel = gridEvent.getGrid().getSelectionModel().getSelectedItem();
				final Group group = beanModel.getBean();	
				doViewGroup(group);
			}
		});
	}


	private void showContextMenu(GridEvent<BeanModel> gridEvent) {
		BeanModel beanModel = gridEvent.getGrid().getSelectionModel().getSelectedItem();
		final Group group = beanModel.getBean();	
		ModelData selectedItem = this.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			contextMenu.removeAll();
			ViewMenuItem viewMenuItem = new ViewMenuItem() {
				public void doSelect() {
					doViewGroup(group);
				};
			};
			contextMenu.add(viewMenuItem);		
			if (isGroupIOwn(group)) {
				EditMenuItem editMenuItem = new EditMenuItem() {
					public void doSelect() {
						doEditGroup(group);	
					};
				};
				contextMenu.add(editMenuItem);
				DeleteMenuItem deleteMenuItem = new DeleteMenuItem(){
					public void doSelect() {
						doDeleteGroup(group);	
					};
				};
				contextMenu.add(deleteMenuItem);
			}
			contextMenu.showAt(gridEvent.getClientX(), gridEvent.getClientY());

		} else {
			gridEvent.setCancelled(true);
		}
	}

	public void doViewGroup(Group group) {
		HistoryManager.addSubHistoryToken(group.getTagname());
	}

	public void doEditGroup(Group group) {
		HistoryManager.addSubHistoryToken(group.getTagname());
	}

	public void doDeleteGroup(final Group group) {
		Listener<MessageBoxEvent> callback = new Listener<MessageBoxEvent>() {
			@Override
			public void handleEvent(MessageBoxEvent be) {
				if (be.getButtonClicked().getText().equalsIgnoreCase("yes")) {
					AsyncCallback<Void> deleteCallback = new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							GWTUtil.log("", caught);
						}

						@Override
						public void onSuccess(Void result) {
							refresh();
						}						
					};
					ServiceBroker.groupService.deleteGroup(group, deleteCallback);
				}			
			}			
		};
		ConfirmMessageBox.show("Confirm", "Are you sure you want to Delete " + group.getName(), callback);
	}

	public void refresh() {
		store.getLoader().load();
	}
}
