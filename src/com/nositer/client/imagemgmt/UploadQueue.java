package com.nositer.client.imagemgmt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreSorter;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGrid;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGridCellRenderer;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.nositer.client.ServiceBroker;

public class UploadQueue extends LayoutContainer {
	private SWFUploadContainer swfUploadContainer;
	private ContentPanel contentPanel;
	private Grid<FileModel> grid;

	public ContentPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(ContentPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public Grid<FileModel> getGrid() {
		return grid;
	}

	public void setGrid(Grid<FileModel> grid) {
		this.grid = grid;
	}

	public SWFUploadContainer getSwfUploadContainer() {
		return swfUploadContainer;
	}

	public void setSwfUploadContainer(SWFUploadContainer swfUploadContainer) {
		this.swfUploadContainer = swfUploadContainer;
	}

	public UploadQueue() {
		init();
	}

	private void init() {
		FlowLayout layout = new FlowLayout();
		layout.setMargins(new Margins(0, 10, 0, 0));
		setLayout(layout);  
		contentPanel = new ContentPanel();
		contentPanel.setHeading("Upload Queue");
		contentPanel.setFrame(true);
		contentPanel.setHeight(FileDirectoryTreeGrid.HEIGHT - 7);
		
		initGrid();
		swfUploadContainer = new SWFUploadContainer();
		contentPanel.add(grid);
		contentPanel.setBottomComponent(swfUploadContainer);
		add(contentPanel);  
		layout();
	}

	private void initGrid() {

		ColumnConfig name = new ColumnConfig("name", "Name", 100);  

		ColumnConfig size = new ColumnConfig("size", "Size", 100);  

		ColumnModel cm = new ColumnModel(Arrays.asList(name, size));  

		ListStore<FileModel> store = new ListStore<FileModel>(); 
		FileModel testModel = new FileModel("accc", "b");
		testModel.set("size", "1234");
		store.insert(testModel, 0);
		grid = new Grid<FileModel>(store, cm);  
		grid.setHeight(FileDirectoryTreeGrid.HEIGHT - 84);
		grid.setBorders(true);
		grid.setAutoExpandColumn("name");  
		grid.setAutoExpandMax(5000);
		grid.setStripeRows(true);  
		grid.setColumnLines(true);  
		grid.setColumnReordering(true);  



	}  
}
