package com.nositer.client.mygroups;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.Resizable;


public class MyGroupsContainer extends LayoutContainer implements Resizable {

	private ContentPanel contentPanel;
	private GroupsGrid groupsGrid;

	public MyGroupsContainer() {
		init();
	}

	public void init() {
		setLayout(new FitLayout());
		groupsGrid = new GroupsGrid(); 
		contentPanel = new ContentPanel();  
		contentPanel.setFrame(true);  
		contentPanel.setCollapsible(true);  
		contentPanel.setAnimCollapse(false);  
		contentPanel.setButtonAlign(HorizontalAlignment.CENTER);  
		contentPanel.setHeaderVisible(false);
		
		contentPanel.setLayout(new FitLayout());  
		contentPanel.add(groupsGrid);
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
