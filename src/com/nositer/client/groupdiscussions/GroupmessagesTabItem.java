package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.widget.Resizable;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class GroupmessagesTabItem extends TabItem implements Resizable {

	private GroupDiscussionsContainer groupDiscussionsContainer;
	private ContentPanel contentPanel;
	private GroupmessagesGrid groupmessagesGrid;
	
	public GroupmessagesTabItem(GroupDiscussionsContainer groupDiscussionsContainer) {
		super();
		this.setText("Messages");
		this.groupDiscussionsContainer = groupDiscussionsContainer;
		init();
	}

	private void init() {
		setLayout(new FlowLayout(0));  
		contentPanel = new ContentPanel();

		contentPanel.setLayout(new FlowLayout(0));
		contentPanel.setHeaderVisible(false);
		groupmessagesGrid = new GroupmessagesGrid(groupDiscussionsContainer);
		this.add(groupmessagesGrid);
		
		this.add(contentPanel);
		resize(0,0);
		addDefaultListeners();
	}


	
	
	private void addDefaultListeners() {
		addListener(Events.Select, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {				
				HistoryManager.addHistory(HistoryToken.DISCUSSIONSMESSAGESGROUP + HistoryManager.SUBTOKENSEPARATOR + groupDiscussionsContainer.getGroupPlusView().getTagname());

			}
		});
	}

	@Override
	public void resize(int width, int height) {
		if (groupDiscussionsContainer.getGroupDiscussionLeftPanel() != null) {
			contentPanel.
			setWidth(groupDiscussionsContainer.
					getGroupDiscussionLeftPanel().getWidth());
			groupmessagesGrid.setWidth(contentPanel.getWidth());
		}
		
	}

}
