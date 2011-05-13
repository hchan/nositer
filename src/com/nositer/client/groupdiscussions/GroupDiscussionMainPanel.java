package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.google.gwt.user.client.ui.Button;

public class GroupDiscussionMainPanel extends ContentPanel {

	
	private GroupDiscussionsContainer groupDiscussionsContainer;
	
	public GroupDiscussionMainPanel() {
		init();
	}

	public GroupDiscussionMainPanel(
			GroupDiscussionsContainer groupDiscussionsContainer) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
		init();
	}

	private void init() {
		this.setHeaderVisible(false);
	}
}
