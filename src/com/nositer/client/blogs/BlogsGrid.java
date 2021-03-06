package com.nositer.client.blogs;
/**
 * @author Henry Chan
 * Sep/2010
 * 
 * This class is a UI component (Grid) that will display a grid of blogs 
 */

import java.util.ArrayList;
import java.util.List;

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
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
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
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Blog;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.menuitem.DeleteMenuItem;
import com.nositer.client.widget.menuitem.EditMenuItem;
import com.nositer.client.widget.menuitem.ViewMenuItem;
import com.nositer.client.widget.messagebox.ConfirmMessageBox;

@SuppressWarnings({"rawtypes"})
public class BlogsGrid extends Grid<BeanModel> {
	private static final String BASE_STYLE = "x-grid-panel";
	private static final String ROW_STYLE = "myBlogsRow";
	protected RpcProxy<ArrayList<Blog>> proxy;
	protected BaseListLoader<PagingLoadResult<ModelData>> loader;
	protected Menu contextMenu;

	// Getters/Setters
	public RpcProxy getProxy() {
		return proxy;
	}
	
	public Loader getLoader() {
		return loader;
	}
	
	public Menu getContextMenu() {
		return contextMenu;
	}

	public void setContextMenu(Menu contextMenu) {
		this.contextMenu = contextMenu;
		super.setContextMenu(contextMenu); // don't forget to call super =)
	}

	public void setProxy(RpcProxy<ArrayList<Blog>> proxy) {
		this.proxy = proxy;
	}

	public void setLoader(BaseListLoader<PagingLoadResult<ModelData>> loader) {
		this.loader = loader;
	}

	// ctor
	public BlogsGrid() {
		init();
	}
	
	// init is separated from ctor as it may be re-init from another component
	public void init() {
		initContextMenuAndClicks();
		proxy = new RpcProxy<ArrayList<Blog>>() {
			@Override
			protected void load(Object loadConfig,
					AsyncCallback<ArrayList<Blog>> callback) {
				ServiceBroker.blogService.getMyBlogs(callback);  
			}
		};  		
		loader = new BaseListLoader<PagingLoadResult<ModelData>>(  
				proxy, new BeanModelReader());  
		loader.setRemoteSort(false);  
		cm = createColumnModel();
		store = new ListStore<BeanModel>(loader);  		
		this.view = new GridView();
		disabledStyle = null;
		baseStyle = BASE_STYLE;
		setSelectionModel(new GridSelectionModel<BeanModel>());
		disableTextSelection(true);
		
		
		store.getLoader().load();
		setLoadMask(true);  
		setBorders(true);  
		setAutoExpandColumn(Blog.Column.description.toString());  

	}

	// TODO refactor column names, width to a properties file?
	private ColumnModel createColumnModel() {
		ColumnModel retval = null;
		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();  
		
		columns.add(new ColumnConfig(Blog.Column.name.toString(), "Name", 100));		
	
		ColumnConfig descriptionColumnConfig = new ColumnConfig(Blog.Column.description.toString(), "Description", 200);
		descriptionColumnConfig.setRenderer(getDescriptionGridCellRenderer());
		columns.add(descriptionColumnConfig);  
		ColumnConfig date = new ColumnConfig(Blog.Column.createdtime.toString(), "Created On", 100);  
		date.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/y"));  
		columns.add(date);  
		retval = new ColumnModel(columns);
		setAutoExpandColumn(Blog.Column.description.toString());  
		setAutoWidth(true);
		setAutoExpandMin(200);
		return retval;
	}

	
	protected GridCellRenderer getDescriptionGridCellRenderer() {
		GridCellRenderer retval = new GridCellRenderer() {

			@Override
			public Object render(ModelData model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore store, Grid grid) {
				HtmlContainer retval = new HtmlContainer();
				retval.setStyleName(ROW_STYLE);
				try {
					BeanModel beanModel = (BeanModel) model;
					Blog blog = beanModel.getBean();
					retval.setHtml(blog.getDescription());
				} catch (Exception e) {
					GWTUtil.log("", e);
				}
				return retval;
			}  
		};
		return retval;
	}
	


	protected void showContextMenu(GridEvent<BeanModel> gridEvent) {
		BeanModel beanModel = gridEvent.getGrid().getSelectionModel().getSelectedItem();
		final Blog blog = beanModel.getBean();	
		ModelData selectedItem = this.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			contextMenu.removeAll();
			ViewMenuItem viewMenuItem = new ViewMenuItem() {
				public void doSelect() {
					doViewBlog(blog);
				};
			};
			contextMenu.add(viewMenuItem);		
			if (ManageBlog.isBlogIOwn(blog)) {
				EditMenuItem editMenuItem = new EditMenuItem() {
					public void doSelect() {
						doEditBlog(blog);	
					};
				};
				contextMenu.add(editMenuItem);
				DeleteMenuItem deleteMenuItem = new DeleteMenuItem(){
					public void doSelect() {
						doDeleteBlog(blog);	
					};
				};
				contextMenu.add(deleteMenuItem);
			}
			contextMenu.showAt(gridEvent.getClientX(), gridEvent.getClientY());

		} else {
			gridEvent.setCancelled(true);
		}
		
		
	}
	
	// initializes right-click
	private void initContextMenuAndClicks() {
		contextMenu = new Menu();
		setContextMenu(contextMenu);
		contextMenu.addListener(Events.OnClick, new Listener<MenuEvent>() {

			@Override
			public void handleEvent(MenuEvent me) {
				if (me.getMenu().getBounds(true).x == me.getClientX()) {
					contextMenu.hide();
					BeanModel beanModel = BlogsGrid.this.getSelectionModel().getSelectedItem();
					final Blog blog = beanModel.getBean();	
					doViewBlog(blog);
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
				BeanModel beanModel = BlogsGrid.this.getSelectionModel().getSelectedItem();
				final Blog blog = beanModel.getBean();	
				doViewBlog(blog);
			}
		});
	
	}
	
	// view the blog via GWT History
	public void doViewBlog(Blog blog) {
		HistoryManager.addHistory(HistoryToken.VIEWBLOG + HistoryManager.SUBTOKENSEPARATOR + blog.getId());
	}
	
	// edit the blog via GWT History
	public void doEditBlog(Blog blog) {
		HistoryManager.addHistory(HistoryToken.EDITBLOG + HistoryManager.SUBTOKENSEPARATOR + blog.getId());
	}
	
	// delete blog (prompts a confirm)
	public void doDeleteBlog(final Blog blog) {
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
					ServiceBroker.blogService.disableBlog(blog, deleteCallback);
				}			
			}			
		};
		ConfirmMessageBox.show("Confirm", "Are you sure you want to Delete " + blog.getName(), callback);
	}

	// refreshes the grid
	public void refresh() {
		store.getLoader().load();
	}
}
