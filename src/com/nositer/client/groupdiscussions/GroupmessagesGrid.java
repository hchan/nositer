package com.nositer.client.groupdiscussions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.Loader;
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
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.dto.generated.Groupmessage;
import com.nositer.client.dto.generated.GroupmessagePlusView;
import com.nositer.client.dto.generated.Grouptopic;
import com.nositer.client.dto.generated.User;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.util.HttpGetFileHelper;
import com.nositer.client.widget.avatar.Avatar;
import com.nositer.client.widget.button.RefreshButton;
import com.nositer.client.widget.menuitem.DeleteMenuItem;
import com.nositer.client.widget.menuitem.EditMenuItem;
import com.nositer.client.widget.menuitem.ViewMenuItem;
import com.nositer.client.widget.messagebox.ConfirmMessageBox;

@SuppressWarnings({"rawtypes"})
public class GroupmessagesGrid extends Grid<BeanModel> {

	protected RpcProxy<ArrayList<GroupmessagePlusView>> proxy;
	protected BaseListLoader<PagingLoadResult<ModelData>> loader;
	protected GroupingView groupingView;
	protected Menu contextMenu;
	protected GroupDiscussionsContainer groupDiscussionsContainer;


	public RpcProxy getProxy() {
		return proxy;
	}

	public Loader getLoader() {
		return loader;
	}


	public GroupmessagesGrid(final GroupDiscussionsContainer groupDiscussionsContainer) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
		proxy = new RpcProxy<ArrayList<GroupmessagePlusView>>() {
			@Override
			protected void load(Object loadConfig,
					AsyncCallback<ArrayList<GroupmessagePlusView>> callback) {
				ServiceBroker.groupService.getGroupmessages(groupDiscussionsContainer.getGroupPlusView(), callback);  
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
		disableTextSelection(true);
		init();
	}

	public ColumnModel createColumnModel() {
		ColumnModel retval = null;
		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();  
		ColumnConfig messageColumnConfig = new ColumnConfig(GroupmessagePlusView.Column.description.toString(), "Message", 100);
		messageColumnConfig.setRenderer(getDescriptionGridCellRenderer());
		columns.add(messageColumnConfig);  
		ColumnConfig topicColumnConfig = new ColumnConfig(GroupmessagePlusView.Column.name.toString(), "Topic", 100);	
		topicColumnConfig.setRenderer(getNameGridCellRenderer());
		columns.add(topicColumnConfig);
		ColumnConfig authorColumnConfig = new ColumnConfig("author", "Author", 100);
		authorColumnConfig.setRenderer(getAuthorGridCellRenderer());
		columns.add(authorColumnConfig);  
		ColumnConfig date = new ColumnConfig(GroupmessagePlusView.Column.createdtime.toString(), "Created On", 100);  

		date.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/y"));  
		columns.add(date);  
		setAutoExpandColumn(Group.Column.createdtime.toString());  
		retval = new ColumnModel(columns);
		return retval;
	}



	protected GridCellRenderer getAuthorGridCellRenderer() {
		GridCellRenderer retval = new GridCellRenderer() {

			@Override
			public Object render(ModelData model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore store, Grid grid) {
				String retval = "";
				retval += model.get(GroupmessagePlusView.Column.firstname.toString()) + " " + model.get(GroupmessagePlusView.Column.lastname.toString());
				return retval;
			}  
		};
		return retval;
	}

	protected GridCellRenderer getDescriptionGridCellRenderer() {
		GridCellRenderer retval = new GridCellRenderer() {

			@Override
			public Object render(ModelData model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore store, Grid grid) {
				String retval = "";
				int showLength = 20;
				BeanModel beanModel = (BeanModel) model;
				GroupmessagePlusView groupmessagePlusView = beanModel.getBean();

				if (groupmessagePlusView.getDescription().length() > showLength) {
					retval = groupmessagePlusView.getDescription().substring(0,showLength);
					retval += "...";
				} else {
					retval = groupmessagePlusView.getDescription();
				}
				return retval;
			}  
		};
		return retval;
	}

	protected GridCellRenderer getNameGridCellRenderer() {
		GridCellRenderer retval = new GridCellRenderer() {

			@Override
			public Object render(ModelData model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore store, Grid grid) {
				String retval = "";
				int showLength = 20;
				BeanModel beanModel = (BeanModel) model;
				GroupmessagePlusView groupmessagePlusView = beanModel.getBean();

				if (groupmessagePlusView.getName().length() > showLength) {
					retval = groupmessagePlusView.getName().substring(0,showLength);
					retval += "...";
				} else {
					retval = groupmessagePlusView.getName();
				}
				return retval;
			}  
		};
		return retval;
	}

	public void init() {		
		//contextMenu = new Menu();
		//setContextMenu(contextMenu);
		addListeners();
		store.getLoader().load();
		setLoadMask(true);  
		setBorders(true);  
	}

	protected void addListeners() {
		addListener(Events.RowClick, new Listener<GridEvent>() {

			@Override
			public void handleEvent(GridEvent me) {

				BeanModel beanModel = GroupmessagesGrid.this.getSelectionModel().getSelectedItem();
				final GroupmessagePlusView groupmessagePlusView = beanModel.getBean();	
				Grouptopic grouptopic = new Grouptopic();
				// TODO STOP HERE - fetch group topic from Service
				
				
				HashSet<Groupmessage> groupmessages = new HashSet<Groupmessage>();
				Groupmessage groupmessage = new Groupmessage();
				groupmessage.setCreatedtime(groupmessagePlusView.getCreatedtime());
				User user = new User();
				user.setId(groupmessagePlusView.getUserid());
				user.setFirstname(groupmessagePlusView.getFirstname());
				user.setLastname(groupmessagePlusView.getLastname());
				groupmessage.setUser(user);
				groupmessage.setDescription(groupmessagePlusView.getDescription());
				groupmessages.add(groupmessage);
				grouptopic.setGroupmessages(groupmessages);
				grouptopic.setName(groupmessagePlusView.getName());
				GroupmessagePanel groupmessagePanel = new GroupmessagePanel(groupDiscussionsContainer, grouptopic);
				groupmessagePanel.show();

			}
		});

		/*
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
				final GroupPlusView groupPlusView = beanModel.getBean();	
				doViewGroup(groupPlusView);
			}
		});\*/
	}

	/*
	protected void showContextMenu(GridEvent<BeanModel> gridEvent) {
		/*
		BeanModel beanModel = gridEvent.getGrid().getSelectionModel().getSelectedItem();
		final GroupPlusView groupPlusView = beanModel.getBean();	
		ModelData selectedItem = this.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			contextMenu.removeAll();
			ViewMenuItem viewMenuItem = new ViewMenuItem() {
				public void doSelect() {
					doViewGroup(groupPlusView);
				};
			};
			contextMenu.add(viewMenuItem);		
			if (Groups.isGroupIOwn(groupPlusView)) {
				EditMenuItem editMenuItem = new EditMenuItem() {
					public void doSelect() {
						doEditGroup(groupPlusView);	
					};
				};
				contextMenu.add(editMenuItem);
				DeleteMenuItem deleteMenuItem = new DeleteMenuItem(){
					public void doSelect() {
						doDeleteGroup(groupPlusView);	
					};
				};
				contextMenu.add(deleteMenuItem);
			}
			contextMenu.showAt(gridEvent.getClientX(), gridEvent.getClientY());

		} else {
			gridEvent.setCancelled(true);
		}

	}

	public void doViewGroup(GroupPlusView groupPlusView) {
		//HistoryManager.addSubHistoryToken(group.getTagname());
		//HistoryManager.addHistory(HistoryToken.GROUPS + HistoryManager.SUBTOKENSEPARATOR + groupPlusView.getTagname());
	}

	public void doEditGroup(GroupPlusView groupPlusView) {
		//HistoryManager.addHistory(HistoryToken.EDITGROUP + HistoryManager.SUBTOKENSEPARATOR + groupPlusView.getTagname());
	}

	public void doSubscriptionsGroup(GroupPlusView groupPlusView) {
		//HistoryManager.addHistory(HistoryToken.SUBSCRIPTIONSGROUP + HistoryManager.SUBTOKENSEPARATOR + groupPlusView.getTagname());
	}

	public void doDeleteGroup(final GroupPlusView groupPlusView) {
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
					ServiceBroker.groupService.disableGroup(groupPlusView, deleteCallback);
				}			
			}			
		};
		ConfirmMessageBox.show("Confirm", "Are you sure you want to Delete " + groupPlusView.getName(), callback);
	}
	 */
	public void refresh() {
		store.getLoader().load();
	}

}
