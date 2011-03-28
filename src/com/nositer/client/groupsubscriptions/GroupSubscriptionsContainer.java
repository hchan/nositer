package com.nositer.client.groupsubscriptions;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.combobox.ComboBoxPlus;

@SuppressWarnings("rawtypes")
public class GroupSubscriptionsContainer extends LayoutContainer implements Resizable {
	private ContentPanel contentPanel;
	private GroupSubscriptionsGrid searchGroupsGrid;
	private SearchCriteriaForGroupSubscriptionsPanel searchCriteriaForGroupsPanel;
	private GroupSubscriptionsPagingToolBar pagingToolBar;
	private static GroupSubscriptionsContainer instance;

	public GroupSubscriptionsPagingToolBar getPagingToolBar() {
		return pagingToolBar;
	}

	public void setPagingToolBar(GroupSubscriptionsPagingToolBar pagingToolBar) {
		this.pagingToolBar = pagingToolBar;
	}

	public static GroupSubscriptionsContainer getInstance() {
		return instance;
	}

	public static void setInstance(GroupSubscriptionsContainer instance) {
		GroupSubscriptionsContainer.instance = instance;
	}

	public GroupSubscriptionsContainer() {
		init();
	}

	public void init() {
		contentPanel = new ContentPanel();
		contentPanel.setHeaderVisible(false);

		searchCriteriaForGroupsPanel = new SearchCriteriaForGroupSubscriptionsPanel();
		searchGroupsGrid = new GroupSubscriptionsGrid(searchCriteriaForGroupsPanel);
		searchCriteriaForGroupsPanel.setSearchGroupsGrid(searchGroupsGrid);
		contentPanel.setLayout(new FitLayout());
		contentPanel.setTopComponent(searchCriteriaForGroupsPanel);
		contentPanel.add(searchGroupsGrid);
		add(contentPanel);
		initToolBar();
		contentPanel.setBottomComponent(pagingToolBar);
		addDefaultListeners();
		setAutoHeight(true);
		setAutoWidth(true);
		setStateful(false);
		resize(0,0);
		instance = this;
	}


	private void initToolBar() {
		pagingToolBar = new GroupSubscriptionsPagingToolBar(ComboBoxPlus.DEFAULTLIMIT);



		pagingToolBar.bind((PagingLoader) searchGroupsGrid.getLoader());
		searchGroupsGrid.getLoader().addLoadListener(new LoadListener() {
			@Override
			public void loaderLoad(LoadEvent le) {

				BasePagingLoadResult basePagingLoadResult = le.getData();

				int actualSize = basePagingLoadResult.getData().size();
				if (actualSize == ComboBoxPlus.DEFAULTLIMIT) {
					basePagingLoadResult.setTotalLength(ComboBoxPlus.FICTITIOUSCOUNT);
				}
				super.loaderLoad(le);
			}
		});

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
		contentPanel.setSize(MainPanel.getInstance().getWidth()-5, MainPanel.getInstance().getHeight()-30);
		//int gridHeightOffset = 258;
		if (searchCriteriaForGroupsPanel.isRendered()) {
			if (searchCriteriaForGroupsPanel.getErrorPanel().isVisible()) {
				searchCriteriaForGroupsPanel.getErrorPanel().hide();
				searchCriteriaForGroupsPanel.getErrorPanel().show();
			}
			//if (searchCriteriaForGroupsPanel.isCollapsed()) {
			//	gridHeightOffset -= 200;
			contentPanel.setSize(MainPanel.getInstance().getWidth()-6, MainPanel.getInstance().getHeight()-30);
			//}
			//if (searchCriteriaForGroupsPanel.getErrorPanel().isVisible()) {
			//	gridHeightOffset += searchCriteriaForGroupsPanel.getErrorPanel().getHeight();
			//}
		}
		contentPanel.setSize(MainPanel.getInstance().getWidth()-5, MainPanel.getInstance().getHeight()-30);
		searchCriteriaForGroupsPanel.getLocation().getGeographyCode().layout();

	}
}
