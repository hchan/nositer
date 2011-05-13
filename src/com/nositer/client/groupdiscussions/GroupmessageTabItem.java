package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.widget.TabItem;

public class GroupmessageTabItem extends TabItem {

	private GroupDiscussionsContainer groupDiscussionsContainer;

	public GroupmessageTabItem(GroupDiscussionsContainer groupDiscussionsContainer) {
		super();
		this.setText("Message");
		this.groupDiscussionsContainer = groupDiscussionsContainer;
	}

}
