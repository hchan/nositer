package com.nositer.client.searchforgroups;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.Resizable;

public class SearchForGroups extends LayoutContainer implements Resizable {
	private ContentPanel contentPanel;
	private SearchGroupsGrid searchGroupsGrid;
	private SearchCriteriaForGroupsPanel searchCriteriaForGroupsPanel;
	
	private static SearchForGroups instance;
	
	public static SearchForGroups getInstance() {
		return instance;
	}

	public static void setInstance(SearchForGroups instance) {
		SearchForGroups.instance = instance;
	}

	public SearchForGroups() {
		init();
	}

	public void init() {
		contentPanel = new ContentPanel();
		contentPanel.setHeaderVisible(false);
	
		searchCriteriaForGroupsPanel = new SearchCriteriaForGroupsPanel();
		searchGroupsGrid = new SearchGroupsGrid(searchCriteriaForGroupsPanel);
		contentPanel.setTopComponent(searchCriteriaForGroupsPanel);
		contentPanel.add(searchGroupsGrid);
		add(contentPanel);
	}
	
	
	@Override
	public void resize(int width, int height) {
		searchCriteriaForGroupsPanel.getLocation().setWidth(MainPanel.getInstance().getWidth()-20);
		//contentPanel.setWidth(MainPanel.getInstance().getWidth()-20);
	}
	
	
}
