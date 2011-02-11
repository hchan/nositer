package com.nositer.client.imagemgmt;

import java.util.Arrays;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridViewConfig;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.nositer.client.widget.AlertMessageBox;

@SuppressWarnings("rawtypes")
public class UploadQueue extends LayoutContainer {
	private SWFUploadContainer swfUploadContainer;
	private ContentPanel contentPanel;
	private Grid<FileModel> grid;
	private ListStore<FileModel> store;
	private Button clearAll;

	public ContentPanel getContentPanel() {
		return contentPanel;
	}

	public ListStore<FileModel> getStore() {
		return store;
	}

	public void setStore(ListStore<FileModel> store) {
		this.store = store;
	}

	public Button getClearAll() {
		return clearAll;
	}

	public void setClearAll(Button clearAll) {
		this.clearAll = clearAll;
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
		contentPanel.setHeight(FileDirectoryTreeGridContainer.HEIGHT - 7);
		initGrid();
		swfUploadContainer = new SWFUploadContainer();
		contentPanel.add(grid);
		LayoutContainer bottomComponent = new LayoutContainer(new FlowLayout());
		bottomComponent.add(swfUploadContainer);
		clearAll = new Button("Clear All");
		
		clearAll.setHeight(28);
		bottomComponent.add(clearAll, new FlowData(0, 0, 0, 200));
		contentPanel.setBottomComponent(bottomComponent);
		add(contentPanel);  
		layout();
	}

	private void initGrid() {
		ColumnConfig name = new ColumnConfig("name", "Name", 100);  
		ColumnConfig size = new ColumnConfig("size", "Size", 100);  
		ColumnModel cm = new ColumnModel(Arrays.asList(name, size));  
		store = new ListStore<FileModel>(); 
		grid = new Grid<FileModel>(store, cm);  
		grid.setHeight(FileDirectoryTreeGridContainer.HEIGHT - 84);
		grid.setBorders(true);
		grid.setAutoExpandColumn("name");  
		grid.setAutoExpandMax(5000);
		grid.setStripeRows(true);  
		grid.setColumnLines(true);  
		grid.setColumnReordering(true);  
		
		grid.getView().setViewConfig(new GridViewConfig() {

			@Override
			public String getRowStyle(ModelData model, int rowIndex,
					ListStore<ModelData> ds) {
				String retval = "";
				if (model.get(FileModel.Attribute.errorMessage.toString()) != null) {
					retval = "uploadError";
				}
				return retval;
			}
		});

		grid.addListener(Events.RowClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				
				GridEvent gridEvent = (GridEvent)be;
				FileModel fileModel = (FileModel)gridEvent.getModel();
				if (fileModel.get(FileModel.Attribute.errorMessage.toString()) != null) {
					AlertMessageBox.show("Warning", (String)fileModel.get(FileModel.Attribute.errorMessage.toString()), null);
				}
			}
			
		});
	}  
	
	public void addRow (FileModel fileModel) {
		store.add(fileModel);
	}
}
