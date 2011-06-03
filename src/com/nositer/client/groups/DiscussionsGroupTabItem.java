package com.nositer.client.groups;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.groupdiscussions.GroupDiscussionsContainer;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.widget.Resizable;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class DiscussionsGroupTabItem extends TabItem implements Resizable {

	private GroupDiscussionsContainer groupDiscussionsContainer;
	private GroupPlusView groupPlusView;
	private GroupTabPanel groupTabPanel;
	

	public GroupTabPanel getGroupTabPanel() {
		return groupTabPanel;
	}


	public void setGroupTabPanel(GroupTabPanel groupTabPanel) {
		this.groupTabPanel = groupTabPanel;
	}


	public GroupDiscussionsContainer getGroupDiscussionsContainer() {
		return groupDiscussionsContainer;
	}


	public void setGroupDiscussionsContainer(
			GroupDiscussionsContainer groupDiscussionsContainer) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
	}


	public GroupPlusView getGroupPlusView() {
		return groupPlusView;
	}


	public void setGroupPlusView(GroupPlusView groupPlusView) {
		this.groupPlusView = groupPlusView;
	}


	public DiscussionsGroupTabItem(GroupPlusView groupPlusView, GroupTabPanel groupTabPanel) {
		super("Discussions");		
		this.groupPlusView = groupPlusView;
		this.groupTabPanel = groupTabPanel;
		init();
	}

	
	public void init() {
		groupDiscussionsContainer = new GroupDiscussionsContainer(groupPlusView);		
		add(groupDiscussionsContainer);
		addDefaultListeners();
	}

	
	private void addDefaultListeners() {
		addListener(Events.Select, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {				
				HistoryManager.addHistory(HistoryToken.DISCUSSIONSGROUP + HistoryManager.SUBTOKENSEPARATOR + groupPlusView.getTagname());
				resize(0,0);				
			}
		});
		
		
	}

	@Override
	public void resize(int width, int height) {
		groupDiscussionsContainer.resize(0,0);
	}
}
