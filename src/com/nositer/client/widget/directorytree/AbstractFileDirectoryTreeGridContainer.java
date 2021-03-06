package com.nositer.client.widget.directorytree;

import java.util.Arrays;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreFilter;
import com.extjs.gxt.ui.client.store.StoreSorter;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGrid.TreeNode;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGridCellRenderer;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.Scope;
import com.nositer.client.ServiceBroker;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.util.TreeNodeHelper;
import com.nositer.client.widget.SelectedFilePanel;
import com.nositer.client.widget.SelectedFolderPanel;
import com.nositer.shared.Global;

@SuppressWarnings({"unchecked", "rawtypes"})
abstract public class AbstractFileDirectoryTreeGridContainer extends LayoutContainer {

	public static final int HEIGHT = 300;
	public static final int WIDTH = 400;
	private SelectedFolderPanel selectedFolderPanel;
	private SelectedFilePanel selectedFilePanel;
	private ContentPanel contentPanel;	
	private boolean useSelectedFilePanel;
	protected MyTreeGrid tree;


	public boolean isUseSelectedFilePanel() {
		return useSelectedFilePanel;
	}

	public void setUseSelectedFilePanel(boolean useSelectedFilePanel) {
		this.useSelectedFilePanel = useSelectedFilePanel;
	}


	public ContentPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(ContentPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public SelectedFolderPanel getSelectedFolderPanel() {
		return selectedFolderPanel;
	}

	public void setSelectedFolderPanel(SelectedFolderPanel selectedFolderPanel) {
		this.selectedFolderPanel = selectedFolderPanel;
	}

	public SelectedFilePanel getSelectedFilePanel() {
		return selectedFilePanel;
	}

	public void setSelectedFilePanel(SelectedFilePanel selectedFilePanel) {
		this.selectedFilePanel = selectedFilePanel;
	}

	public MyTreeGrid getTree() {
		return tree;
	}

	public void setTree(MyTreeGrid tree) {
		this.tree = tree;
	}

	// default uses selectedFolderPanel
	//public FileDirectoryTreeGridContainer() {
	//	this(false);
	//}

	public AbstractFileDirectoryTreeGridContainer() {		
	}

	public AbstractFileDirectoryTreeGridContainer(boolean useSelectedFilePanel) {
		this.useSelectedFilePanel = useSelectedFilePanel;
		init();
	}


	public abstract RpcProxy<List<FileModel>> createProxy ();

	protected void init() {

		setLayout(new FlowLayout(10));  
		// data proxy  
		RpcProxy<List<FileModel>> proxy = createProxy();

		// tree loader  
		final TreeLoader<FileModel> loader = new BaseTreeLoader<FileModel>(proxy) {  

			@Override  
			public boolean hasChildren(FileModel parent) {  
				return parent instanceof FolderModel;  
			} 

			@Override
			protected void onLoadSuccess(Object loadConfig,
					List<FileModel> result) {				
				super.onLoadSuccess(loadConfig, result);
				doAfterTreeIsLoaded(loadConfig, result);
			}

		};  

		// trees store  
		final TreeStore<FileModel> store = new TreeStore<FileModel>(loader);  
		store.setStoreSorter(new StoreSorter<FileModel>() {  

			@Override  
			public int compare(Store<FileModel> store, FileModel m1, FileModel m2, String property) {  
				boolean m1Folder = m1 instanceof FolderModel;  
				boolean m2Folder = m2 instanceof FolderModel;  

				if (m1Folder && !m2Folder) {  
					return -1;  
				} else if (!m1Folder && m2Folder) {  
					return 1;  
				}  else if (m1Folder && m2Folder) {
					return m1.getName().compareTo(m2.getName());
				} else if (!m1Folder && !m2Folder) {
					return m1.getName().compareTo(m2.getName());
				}

				return super.compare(store, m1, m2, property);  
			}  
		});  



		ColumnConfig name = new ColumnConfig(FileModel.Attribute.name.toString(), "Name", 100);  
		name.setRenderer(new TreeGridCellRenderer<ModelData>());  

		ColumnConfig date = new ColumnConfig(FileModel.Attribute.date.toString(), "Date", 100);  
		date.setDateTimeFormat(DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM)); 

		ColumnConfig size = new ColumnConfig(FileModel.Attribute.size.toString(), "Size", 100);  

		ColumnModel cm = new ColumnModel(Arrays.asList(name, date, size));  

		contentPanel = new ContentPanel();  
		contentPanel.setBodyBorder(false);  
		contentPanel.setHeading("My Files");  
		contentPanel.setButtonAlign(HorizontalAlignment.CENTER);  
		contentPanel.setLayout(new FitLayout());  
		contentPanel.setFrame(true);  
		contentPanel.setSize(WIDTH, HEIGHT);


		tree = new MyTreeGrid(store, cm) {

			@Override
			protected void onClick(GridEvent<ModelData> e) {
				super.onClick(e);
				setSelectedFileOrFolder((FileModel)e.getModel(), this.findNode(e.getModel()));
			}


			@Override
			protected void onDoubleClick(com.extjs.gxt.ui.client.event.GridEvent<ModelData> e) {
				super.onClick(e);
				FileModel fileModel = (FileModel) e.getModel();
				if (fileModel instanceof FolderModel) {
					FolderModel folderModel = (FolderModel) fileModel;
					doFolderModelDoubleClick(folderModel);
				} else {
					doFileModelDoubleClick(fileModel);
				}
			}			
		};  




		tree.setBorders(true);  
		tree.getStyle().setLeafIcon(IconHelper.createPath(Global.LEAFIMAGE));  
		//tree.getStyle().setJointExpandedIcon(IconHelper.createPath("/public/image/bol.png"));
		tree.setCaching(false);

		tree.setAutoExpandColumn(FileModel.Attribute.name.toString());  
		tree.setTrackMouseOver(false);  
		contentPanel.add(tree);  


		selectedFolderPanel = new SelectedFolderPanel();
		selectedFilePanel = new SelectedFilePanel();
		if (useSelectedFilePanel) {
			contentPanel.setBottomComponent(selectedFilePanel);
		} else {
			contentPanel.setBottomComponent(selectedFolderPanel);
		}
		add(contentPanel);  

	}  

	public void doFileModelDoubleClick(FileModel fileModel) {	
	};

	public void doFolderModelDoubleClick(FileModel fileModel) {	
	};



	public void setSelectedFileOrFolder(FileModel fileModel, TreeNode treeNode) {
		if (fileModel != null) {
			getTree().getSelectionModel().select(fileModel, false);
			if (fileModel instanceof FolderModel) {
				FolderModel folderModel = (FolderModel)fileModel;
				selectedFolderPanel.getSelectedFolder().setValue(folderModel.getPath());					
				selectedFolderPanel.setFolderModel(folderModel);
				selectedFolderPanel.setTreeNode(treeNode);
				doFolderModelClick(folderModel);
			} else {			
				selectedFilePanel.getSelectedFile().setValue(fileModel.getPath());
				selectedFilePanel.setFileModel(fileModel);
				selectedFilePanel.setTreeNode(treeNode);
				doFileModelClick(fileModel);
			}
		}
	}

	public void applyImageStoreFilter() {
		StoreFilter<ModelData> imageStoreFilter = new ImageStoreFilter();
		getTree().getStore(). addFilter(imageStoreFilter);
		getTree().getStore().applyFilters(FileModel.Attribute.name.toString());
	}

	public void refreshSelectedTreeNode() {
		TreeNodeHelper.refresh(getSelectedFolderPanel().getTreeNode());
	}


	public void doFileModelClick(FileModel fileModel) {

	}

	public void doFolderModelClick(FolderModel folderModel) {

	}

	public void doAfterTreeIsLoaded(Object loadConfig,
			List<FileModel> result) {

	}


	public void selectPublicFolder(List<FileModel> result) {
		for (FileModel fileModel : result) {
			if (fileModel.getPath() != null && fileModel.getPath().equals(Global.PUBLICFOLDER) && ("/" + fileModel.getName()).equals(Global.PUBLICFOLDER)) {
				if (this.getSelectedFolderPanel().getFileModel() == null) {
					this.setSelectedFileOrFolder(fileModel, getTree().findNode(fileModel));					
					break;
				}
			}
		}
	}
}
