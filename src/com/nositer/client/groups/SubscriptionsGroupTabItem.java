package com.nositer.client.groups;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.nositer.client.createoreditgroup.CreateOrEditGroup;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.UserHasGroupView;
import com.nositer.client.groupsubscriptions.GroupSubscriptionsContainer;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.widget.Resizable;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class SubscriptionsGroupTabItem extends TabItem implements Resizable {

	private GroupSubscriptionsContainer groupSubscriptionsContainer;
	private UserHasGroupView userHasGroupView;
	private boolean populated;
	
	public GroupSubscriptionsContainer getGroupSubscriptionsContainer() {
		return groupSubscriptionsContainer;
	}

	public void setGroupSubscriptionsContainer(
			GroupSubscriptionsContainer groupSubscriptionsContainer) {
		this.groupSubscriptionsContainer = groupSubscriptionsContainer;
	}

	public UserHasGroupView getUserHasGroupView() {
		return userHasGroupView;
	}

	public void setUserHasGroupView(UserHasGroupView userHasGroupView) {
		this.userHasGroupView = userHasGroupView;
	}

	public boolean isPopulated() {
		return populated;
	}

	public void setPopulated(boolean populated) {
		this.populated = populated;
	}

	public SubscriptionsGroupTabItem(UserHasGroupView userHasGroupView) {
		super("Subscriptions");
		this.populated = false;
		this.userHasGroupView = userHasGroupView;
		init();
	}

	public void init() {
		groupSubscriptionsContainer = new GroupSubscriptionsContainer(userHasGroupView);		
		add(groupSubscriptionsContainer);
		addDefaultListeners();
	}

	
	private void addDefaultListeners() {
		addListener(Events.Select, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {				
				HistoryManager.addHistory(HistoryToken.SUBSCRIPTIONSGROUP + HistoryManager.SUBTOKENSEPARATOR + userHasGroupView.getTagname());
				resize(0,0);
				if (!populated) {
					populate();
					populated = true;
				}
			}
		});
	}

	
	public void populate() {
		
	}
	
	@Override
	public void resize(int width, int height) {
		//contentPanel.setHeight(MainPanel.getInstance().getHeight()-57);
		//contentPanel.setWidth(MainPanel.getInstance().getWidth()-3);
	
		
	}

	

}