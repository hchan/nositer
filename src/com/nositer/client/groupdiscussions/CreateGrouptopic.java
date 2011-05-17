package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.MarginData;

public class CreateGrouptopic extends ContentPanel {


	private GroupDiscussionsContainer groupDiscussionsContainer;
	private Label theHeading;

	public CreateGrouptopic(GroupDiscussionsContainer groupDiscussionsContainer) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
	}

	public void populateMainPanel() {
		theHeading = new Label("Create New Topic");
		theHeading.setStyleName("formHeading");
		groupDiscussionsContainer.getGroupDiscussionMainPanel().removeAll();
		groupDiscussionsContainer.getGroupDiscussionMainPanel().add(theHeading, new MarginData(5, 0, 0, 5));
		groupDiscussionsContainer.layout();
	}
}
