package com.nositer.client.groupsubscriptions;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.nositer.client.widget.Resizable;

public class GroupSubscriptionsTopComponent extends ContentPanel implements Resizable {
	private MyGroupSubscription myGroupSubscription;
	private SearchCriteriaForGroupSubscriptionsPanel searchCriteriaForGroupSubscriptionsPanel;
	private GroupSubscriptionsContainer groupSubscriptionsContainer;

	public SearchCriteriaForGroupSubscriptionsPanel getSearchCriteriaForGroupSubscriptionsPanel() {
		return searchCriteriaForGroupSubscriptionsPanel;
	}

	public void setSearchCriteriaForGroupSubscriptionsPanel(
			SearchCriteriaForGroupSubscriptionsPanel searchCriteriaForGroupSubscriptionsPanel) {
		this.searchCriteriaForGroupSubscriptionsPanel = searchCriteriaForGroupSubscriptionsPanel;
	}

	public GroupSubscriptionsContainer getGroupSubscriptionsContainer() {
		return groupSubscriptionsContainer;
	}

	public void setGroupSubscriptionsContainer(
			GroupSubscriptionsContainer groupSubscriptionsContainer) {
		this.groupSubscriptionsContainer = groupSubscriptionsContainer;
	}

	public GroupSubscriptionsTopComponent(GroupSubscriptionsContainer groupSubscriptionsContainer) {
		this.groupSubscriptionsContainer = groupSubscriptionsContainer;	
		init();
	}

	public void init() {
		setHeaderVisible(false);
		myGroupSubscription = new MyGroupSubscription(groupSubscriptionsContainer);
		setTopComponent(myGroupSubscription);
		searchCriteriaForGroupSubscriptionsPanel = new SearchCriteriaForGroupSubscriptionsPanel(groupSubscriptionsContainer);
		searchCriteriaForGroupSubscriptionsPanel.collapse();
		setBottomComponent(searchCriteriaForGroupSubscriptionsPanel);
		addDefaultListeners();
	}
	
	private void addDefaultListeners() {
		searchCriteriaForGroupSubscriptionsPanel.addListener(Events.Collapse, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent baseEvent) {				
				resize(0,0);
			}}
		);
		searchCriteriaForGroupSubscriptionsPanel.addListener(Events.Expand, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent baseEvent) {				
				resize(0,0);				
			}}
		);
		
		
		myGroupSubscription.addListener(Events.Collapse, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent baseEvent) {				
				resize(0,0);
			}}
		);
		myGroupSubscription.addListener(Events.Expand, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent baseEvent) {				
				resize(0,0);
			}}
		);
	}

	@Override
	public void resize(int width, int height) {
		groupSubscriptionsContainer.resize(width, height);
	}
	
}
