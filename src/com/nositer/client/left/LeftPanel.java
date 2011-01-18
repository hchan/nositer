package com.nositer.client.left;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.nositer.client.Nositer;

public class LeftPanel extends ContentPanel {
	private Nositer nositer;
	private BorderLayoutData leftLayoutData;
	public Nositer getNositer() {
		return nositer;
	}

	public BorderLayoutData getLeftLayoutData() {
		return leftLayoutData;
	}

	public void setLeftLayoutData(BorderLayoutData leftLayoutData) {
		this.leftLayoutData = leftLayoutData;
	}

	public void setNositer(Nositer nositer) {
		this.nositer = nositer;
	}

	public LeftPanel (Nositer nositer, BorderLayoutData leftLayoutData) {
		this.nositer = nositer;
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
