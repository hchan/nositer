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
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.combobox.ComboBoxPlus;

@SuppressWarnings("rawtypes")
public class GroupSubscriptionsContainer extends LayoutContainer implements Resizable {
	private GroupPlusView groupPlusView;
	private ContentPanel contentPanel;
	private GroupSubscriptionsGrid groupSubscriptionsGrid;	
	private GroupSubscriptionsTopComponent groupSubscriptionsTopComponent;
	private GroupSubscriptionsPagingToolBar pagingToolBar;
	

	public ContentPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(ContentPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public GroupSubscriptionsGrid getGroupSubscriptionsGrid() {
		return groupSubscriptionsGrid;
	}

	public void setGroupSubscriptionsGrid(
			GroupSubscriptionsGrid groupSubscriptionsGrid) {
		this.groupSubscriptionsGrid = groupSubscriptionsGrid;
	}

	public GroupSubscriptionsTopComponent getGroupSubscriptionsTopComponent() {
		return groupSubscriptionsTopComponent;
	}

	public void setGroupSubscriptionsTopComponent(
			GroupSubscriptionsTopComponent groupSubscriptionsTopComponent) {
		this.groupSubscriptionsTopComponent = groupSubscriptionsTopComponent;
	}

	public GroupPlusView getGroupPlusView() {
		return groupPlusView;
	}

	public void setGroupPlusView(GroupPlusView groupPlusView) {
		this.groupPlusView = groupPlusView;
	}

	public GroupSubscriptionsPagingToolBar getPagingToolBar() {
		return pagingToolBar;
	}

	public void setPagingToolBar(GroupSubscriptionsPagingToolBar pagingToolBar) {
		this.pagingToolBar = pagingToolBar;
	}



	public GroupSubscriptionsContainer(GroupPlusView groupPlusView) {
		this.groupPlusView = groupPlusView;
		init();
	}

	public void init() {
		contentPanel = new ContentPanel();
		contentPanel.setHeaderVisible(false);

		groupSubscriptionsTopComponent = new GroupSubscriptionsTopComponent(this);
		SearchCriteriaForGroupSubscriptionsPanel searchCriteriaForGroupSubscriptionsPanel = groupSubscriptionsTopComponent.getSearchCriteriaForGroupSubscriptionsPanel();
		groupSubscriptionsGrid = new GroupSubscriptionsGrid(this);
		searchCriteriaForGroupSubscriptionsPanel.setSearchGroupsGrid(groupSubscriptionsGrid);
		contentPanel.setLayout(new FitLayout());
		contentPanel.setTopComponent(groupSubscriptionsTopComponent);
		contentPanel.add(groupSubscriptionsGrid);
		add(contentPanel);
		initToolBar();
		contentPanel.setBottomComponent(pagingToolBar);
		setAutoHeight(true);
		setAutoWidth(true);
		setStateful(false);
		resize(0,0);
		
	}


	private void initToolBar() {
		pagingToolBar = new GroupSubscriptionsPagingToolBar(ComboBoxPlus.DEFAULTLIMIT);



		pagingToolBar.bind((PagingLoader) groupSubscriptionsGrid.getLoader());
		groupSubscriptionsGrid.getLoader().addLoadListener(new LoadListener() {
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

	
	@Override
	public void resize(int width, int height) {
		// hackery to get the Collapse/Expand Working
		contentPanel.setSize(MainPanel.getInstance().getWidth()-5, MainPanel.getInstance().getHeight() - 55);
		if (contentPanel.getTopComponent() != null && contentPanel.getTopComponent().isRendered()) {
			contentPanel.setSize(MainPanel.getInstance().getWidth()-6, MainPanel.getInstance().getHeight()-55);
		}	
		contentPanel.setSize(MainPanel.getInstance().getWidth()-5, MainPanel.getInstance().getHeight() - 55);		
	}
}
