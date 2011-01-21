package com.nositer.client.left;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;

public class LeftPanel extends ContentPanel {

	private BorderLayoutData leftLayoutData;
	
	public BorderLayoutData getLeftLayoutData() {
		return leftLayoutData;
	}

	public void setLeftLayoutData(BorderLayoutData leftLayoutData) {
		this.leftLayoutData = leftLayoutData;
	}


	public LeftPanel (BorderLayoutData leftLayoutData) {	
		this.leftLayoutData = leftLayoutData;
		init();
	}

	private void init() {
		leftLayoutData.setSize(150);
		leftLayoutData.setCollapsible(true);
		//this.setHeaderVisible(false);
		AccordionLayout accordionLayout = new AccordionLayout();
		setLayout(accordionLayout);	
		ContentPanel myGroups = new ContentPanel();
		myGroups.setHeading("My Groups");
	}
}
