package com.nositer.client.groups;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.groupsubscriptions.GroupSubscriptionsContainer;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.widget.HistoryWidget;
import com.nositer.client.widget.Resizable;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class SubscriptionsGroupTabItem extends TabItem implements Resizable, HistoryWidget {

	private GroupSubscriptionsContainer groupSubscriptionsContainer;
	private GroupPlusView groupPlusView;
	private boolean populated;
	private GroupTabPanel groupTabPanel;
	
	public GroupTabPanel getGroupTabPanel() {
		return groupTabPanel;
	}

	public void setGroupTabPanel(GroupTabPanel groupTabPanel) {
		this.groupTabPanel = groupTabPanel;
	}

	public GroupSubscriptionsContainer getGroupSubscriptionsContainer() {
		return groupSubscriptionsContainer;
	}

	public void setGroupSubscriptionsContainer(
			GroupSubscriptionsContainer groupSubscriptionsContainer) {
		this.groupSubscriptionsContainer = groupSubscriptionsContainer;
	}

	public GroupPlusView getGroupPlusView() {
		return groupPlusView;
	}

	public void setGroupPlusView(GroupPlusView groupPlusView) {
		this.groupPlusView = groupPlusView;
	}

	public boolean isPopulated() {
		return populated;
	}

	public void setPopulated(boolean populated) {
		this.populated = populated;
	}

	public SubscriptionsGroupTabItem(GroupPlusView groupPlusView, GroupTabPanel groupTabPanel) {
		super("Subscriptions");
		this.populated = false;
		this.groupPlusView = groupPlusView;
		this.groupTabPanel = groupTabPanel;
		init();
	}

	public void init() {
		groupSubscriptionsContainer = new GroupSubscriptionsContainer(groupPlusView);		
		add(groupSubscriptionsContainer);
		addDefaultListeners();
	}

	
	private void addDefaultListeners() {
		addListener(Events.Select, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {				
				HistoryManager.addHistory(getNewHistory());
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

	@Override
	public String getNewHistory() {
		return HistoryToken.SUBSCRIPTIONSGROUP + HistoryManager.SUBTOKENSEPARATOR + groupPlusView.getTagname();
	}

	

}