package com.nositer.client.searchforgroups;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;

public class SearchForGroups extends LayoutContainer {
	private ContentPanel contentPanel;
	private SearchGroupsGrid searchGroupsGrid;
	private SearchCriteriaForGroupsPanel searchCriteriaForGroupsPanel;
	
	public SearchForGroups() {
		init();
	}

	public void init() {
		contentPanel = new ContentPanel();
		contentPanel.setHeaderVisible(false);
		
		searchCriteriaForGroupsPanel = new SearchCriteriaForGroupsPanel();
		contentPanel.setTopComponent(searchCriteriaForGroupsPanel);
		searchGroupsGrid = new SearchGroupsGrid();
		contentPanel.add(searchGroupsGrid);
		add(contentPanel);
	}
}
