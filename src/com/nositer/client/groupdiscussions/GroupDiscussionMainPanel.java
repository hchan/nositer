package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Container;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.google.gwt.user.client.ui.Button;
import com.nositer.client.widget.Resizable;

public class GroupDiscussionMainPanel extends ContentPanel implements Resizable {

	
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

	@Override
	public void resize(int width, int height) {
		for (Component component : this.getItems()) {
			Resizable resizable = (Resizable) component;
			resizable.resize(width, height);
		}
	}
}
