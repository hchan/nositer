package com.nositer.client.groups;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.Resizable;


public class GroupsContainer extends LayoutContainer implements Resizable {

	private ContentPanel contentPanel;
	private GroupsGrid groupsGrid;
	private ToolBar toolBar;
	
	public GroupsContainer() {
		init();
	}

	public void init() {
		setLayout(new FitLayout());
		groupsGrid = new GroupsGrid(); 
		toolBar = new ToolBar();			
		toolBar.add(new FillToolItem());
		toolBar.add(new Button("Refresh", IconHelper.create("/public/image/refresh.gif"), new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				groupsGrid.refresh();
			}
		}));			
		contentPanel = new ContentPanel();  
		contentPanel.setFrame(true);  
		contentPanel.setCollapsible(true);  
		contentPanel.setAnimCollapse(false);  
		contentPanel.setButtonAlign(HorizontalAlignment.CENTER);  
		contentPanel.setHeaderVisible(false);
		
		contentPanel.setLayout(new FitLayout());  
		contentPanel.add(groupsGrid);
		contentPanel.setBottomComponent(toolBar);
		
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
