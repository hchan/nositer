package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.google.gwt.user.client.ui.Button;

public class GroupDiscussionLeftPanel extends ContentPanel {

	private TabPanel tabPanel;
	private GroupDiscussionsContainer groupDiscussionsContainer;
	
	public GroupDiscussionLeftPanel() {
		init();
	}

	public GroupDiscussionLeftPanel(
			GroupDiscussionsContainer groupDiscussionsContainer) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
	}

	private void init() {
		tabPanel = new TabPanel();
		add(tabPanel);
		add(new Button("hi"));
	}
}
