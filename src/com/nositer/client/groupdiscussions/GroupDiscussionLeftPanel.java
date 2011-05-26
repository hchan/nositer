package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.Resizable;

public class GroupDiscussionLeftPanel extends ContentPanel implements Resizable {


	private GroupDiscussionsContainer groupDiscussionsContainer;


	public GroupDiscussionLeftPanel() {
		init();
	}

	public GroupDiscussionLeftPanel(
			GroupDiscussionsContainer groupDiscussionsContainer) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
		init();
	}

	private void init() {
		this.setLayout(new FlowLayout(0));
		this.setHeaderVisible(false);
		GroupmessagesGrid groupmessagesGrid = new GroupmessagesGrid(groupDiscussionsContainer);
		groupmessagesGrid.setHeight(400);
		this.add(groupmessagesGrid);
	}

	// called when the borderlayout split is resized
	@Override
	protected void onResize(int width, int height) {
		super.onResize(width, height);
		resize(0,0);
	}

	@Override
	public void resize(int width, int height) {
		/*
		Resizable resizable = (Resizable) groupDiscussionTabPanel.getSelectedItem();
		if (resizable != null) {
			resizable.resize(0,0);
		}
		*/
		groupDiscussionsContainer.getGroupDiscussionMainPanel().resize(0,0);
	}
}
