package com.nositer.client.uploadimages;

import java.util.Arrays;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreSorter;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGridCellRenderer;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.util.TreeNodeHelper;

public class FileDirectoryTreeGridContainer extends LayoutContainer {

	public static final int HEIGHT = 300;
	public static final int WIDTH = 400;
	private SelectedFolderPanel selectedFolderPanel;
	private MyTreeGrid tree;
	private ContentPanel contentPanel;


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

	public MyTreeGrid getTree() {
		return tree;
	}

	public void setTree(MyTreeGrid tree) {
		this.tree = tree;
	}

	public FileDirectoryTreeGridContainer() {
		init();
	}

	protected void init() {

		setLayout(new FlowLayout(10));  
		// data proxy  
		RpcProxy<List<FileModel>> proxy = new RpcProxy<List<FileModel>>() {  
			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<FileModel>> callback) {
				ServiceBroker.fileService.getImageFolderChildren((FileModel) loadConfig, callback);  
			}  
		};  

		// tree loader  
		final TreeLoader<FileModel> loader = new BaseTreeLoader<FileModel>(proxy) {  

			@Override  
			public boolean hasChildren(FileModel parent) {  
				return parent instanceof FolderModel;  
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
				}  

				return super.compare(store, m1, m2, property);  
			}  
		});  

		ColumnConfig name = new ColumnConfig("name", "Name", 100);  
		name.setRenderer(new TreeGridCellRenderer<ModelData>());  

		ColumnConfig date = new ColumnConfig("date", "Date", 100);  
		date.setDateTimeFormat(DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM)); 

		ColumnConfig size = new ColumnConfig("size", "Size", 100);  

		ColumnModel cm = new ColumnModel(Arrays.asList(name, date, size));  

		contentPanel = new ContentPanel();  
		contentPanel.setBodyBorder(false);  
		contentPanel.setHeading("My Images");  
		contentPanel.setButtonAlign(HorizontalAlignment.CENTER);  
		contentPanel.setLayout(new FitLayout());  
		contentPanel.setFrame(true);  
		contentPanel.setSize(WIDTH, HEIGHT);


		tree = new MyTreeGrid(store, cm) {

			@Override
			protected void onClick(GridEvent<ModelData> e) {
				super.onClick(e);
				if (e.getModel() instanceof FolderModel) {
					FolderModel folderModel = (FolderModel)e.getModel();
					selectedFolderPanel.getSelectedFolder().setValue(folderModel.getPath());					
					selectedFolderPanel.setFolderModel(folderModel);
					selectedFolderPanel.setTreeNode(this.findNode(folderModel));
				} else {
					doFileModelClick((FileModel) e.getModel());
				}
			}
		};  

		

		
		tree.setBorders(true);  
		tree.getStyle().setLeafIcon(IconHelper.createPath("/public/image/list.gif"));  
		//tree.getStyle().setJointExpandedIcon(IconHelper.createPath("/public/image/bol.png"));
		tree.setCaching(false);

		tree.setAutoExpandColumn("name");  
		tree.setTrackMouseOver(false);  
		contentPanel.add(tree);  


		
		selectedFolderPanel = new SelectedFolderPanel();
		contentPanel.setBottomComponent(selectedFolderPanel);
		add(contentPanel);  

	}  

	public void refreshSelectedTreeNode() {
		TreeNodeHelper.refreh(getSelectedFolderPanel().getTreeNode());
	}
	
	
	public void doFileModelClick(FileModel fileModel) {
		
	}
}
