package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabPanel;

public class GroupDiscussionLeftPanel extends ContentPanel {

	private GroupDiscussionTabPanel groupDiscussionTabPanel;
	private GroupDiscussionsContainer groupDiscussionsContainer;
	private GrouptopicTabItem grouptopicTabItem;
	private GroupmessageTabItem groupmessageTabItem;
	private GrouptoolsTabItem grouptoolsTabItem;
	
	
	
	

	public GroupDiscussionTabPanel getGroupDiscussionTabPanel() {
		return groupDiscussionTabPanel;
	}

	public void setGroupDiscussionTabPanel(
			GroupDiscussionTabPanel groupDiscussionTabPanel) {
		this.groupDiscussionTabPanel = groupDiscussionTabPanel;
	}

	public GroupDiscussionsContainer getGroupDiscussionsContainer() {
		return groupDiscussionsContainer;
	}

	public void setGroupDiscussionsContainer(
			GroupDiscussionsContainer groupDiscussionsContainer) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
	}

	public GrouptopicTabItem getGrouptopicTabItem() {
		return grouptopicTabItem;
	}

	public void setGrouptopicTabItem(GrouptopicTabItem grouptopicTabItem) {
		this.grouptopicTabItem = grouptopicTabItem;
	}

	public GroupmessageTabItem getGroupmessageTabItem() {
		return groupmessageTabItem;
	}

	public void setGroupmessageTabItem(GroupmessageTabItem groupmessageTabItem) {
		this.groupmessageTabItem = groupmessageTabItem;
	}

	public GrouptoolsTabItem getGrouptoolsTabItem() {
		return grouptoolsTabItem;
	}

	public void setGrouptoolsTabItem(GrouptoolsTabItem grouptoolsTabItem) {
		this.grouptoolsTabItem = grouptoolsTabItem;
	}

	public GroupDiscussionLeftPanel() {
		init();
	}

	public GroupDiscussionLeftPanel(
			GroupDiscussionsContainer groupDiscussionsContainer) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
		init();
	}

	private void init() {
		this.setHeaderVisible(false);
		groupDiscussionTabPanel = new GroupDiscussionTabPanel();
		grouptopicTabItem = new GrouptopicTabItem(groupDiscussionsContainer);
		groupmessageTabItem = new GroupmessageTabItem(groupDiscussionsContainer);
		grouptoolsTabItem = new GrouptoolsTabItem(groupDiscussionsContainer);
		groupDiscussionTabPanel.add(grouptopicTabItem);
		groupDiscussionTabPanel.add(groupmessageTabItem);		
		groupDiscussionTabPanel.add(grouptoolsTabItem);
		add(groupDiscussionTabPanel);
	}
}
