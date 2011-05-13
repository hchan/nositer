package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.widget.TabItem;

public class GrouptopicTabItem extends TabItem {
	private GroupDiscussionsContainer groupDiscussionsContainer;
	
	public GrouptopicTabItem(GroupDiscussionsContainer groupDiscussionsContainer) {
		super();
		this.setText("Topic");
		this.groupDiscussionsContainer = groupDiscussionsContainer;
	}
}
