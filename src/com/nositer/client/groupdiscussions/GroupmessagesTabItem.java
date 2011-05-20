package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.widget.TabItem;

public class GroupmessagesTabItem extends TabItem {

	private GroupDiscussionsContainer groupDiscussionsContainer;

	public GroupmessagesTabItem(GroupDiscussionsContainer groupDiscussionsContainer) {
		super();
		this.setText("Messages");
		this.groupDiscussionsContainer = groupDiscussionsContainer;
	}

}
