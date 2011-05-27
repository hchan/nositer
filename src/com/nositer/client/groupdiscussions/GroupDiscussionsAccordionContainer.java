package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;

public class GroupDiscussionsAccordionContainer extends ContentPanel {
	private ContentPanel tools;
	
	public GroupDiscussionsAccordionContainer() {
		init();
	}

	private void init() {
		this.setHeaderVisible(false);
		setLayout(new AccordionLayout());
		tools = new ContentPanel();
		tools.setHeading("Tools");
		this.add(tools);
	}
}
