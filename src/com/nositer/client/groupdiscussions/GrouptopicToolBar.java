package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.nositer.client.dto.generated.Grouptopic;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.button.RefreshButton;

public class GrouptopicToolBar extends ToolBar implements Resizable {
	private GroupDiscussionsContainer groupDiscussionsContainer;
	private Grouptopic grouptopic;
	private RefreshButton refreshButton;

	public GrouptopicToolBar(GroupDiscussionsContainer groupDiscussionsContainer, Grouptopic grouptopic) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
		this.grouptopic = grouptopic;

		init();
	}

	private void init() {
		refreshButton = new RefreshButton();
		add(refreshButton);		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}
}
