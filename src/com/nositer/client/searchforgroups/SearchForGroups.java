package com.nositer.client.searchforgroups;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.BorderLayoutEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.Resizable;

public class SearchForGroups extends LayoutContainer implements Resizable {
	private ContentPanel contentPanel;
	private SearchGroupsGrid searchGroupsGrid;
	private SearchCriteriaForGroupsPanel searchCriteriaForGroupsPanel;
	private PagingToolBar pagingToolBar;
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
		pagingToolBar = new PagingToolBar(50);
		contentPanel.setBottomComponent(pagingToolBar);
		addDefaultListeners();
		resize(0,0);
		instance = this;
	}


	private void addDefaultListeners() {
		searchCriteriaForGroupsPanel.addListener(Events.Collapse, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent baseEvent) {				
				resize(0,0);
			}}
		);
		searchCriteriaForGroupsPanel.addListener(Events.Expand, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent baseEvent) {	
				
				resize(0,0);
				
			}}
		);
	}

	@Override
	public void resize(int width, int height) {
		int gridHeightOffset = 258;
		if (searchCriteriaForGroupsPanel.isRendered()) {
		
			if (searchCriteriaForGroupsPanel.isCollapsed()) {
				gridHeightOffset -= 200;
			}
			if (searchCriteriaForGroupsPanel.getErrorPanel().isVisible()) {
				gridHeightOffset += searchCriteriaForGroupsPanel.getErrorPanel().getHeight();
			}
		}
		searchGroupsGrid.setHeight(MainPanel.getInstance().getHeight() - gridHeightOffset);
		searchCriteriaForGroupsPanel.getLocation().getGeographyCode().layout();
	}
}
