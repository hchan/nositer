package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.widget.TabItem;

public class GrouptopicsTabItem extends TabItem {
	private GroupDiscussionsContainer groupDiscussionsContainer;
	
	public GrouptopicsTabItem(GroupDiscussionsContainer groupDiscussionsContainer) {
		super();
		this.setText("Topics");
		this.groupDiscussionsContainer = groupDiscussionsContainer;
	}
}
