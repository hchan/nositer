package com.nositer.client;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;

public class LeftPanel extends ContentPanel {
	private Nositer nositer;

	public Nositer getNositer() {
		return nositer;
	}

	public void setNositer(Nositer nositer) {
		this.nositer = nositer;
	}
	
	public LeftPanel (Nositer nositer) {
		this.nositer = nositer;
		init();
	}

	private void init() {
		AccordionLayout accordionLayout = new AccordionLayout();
		setLayout(accordionLayout);
		
		ContentPanel myGroups = new ContentPanel();
		myGroups.setHeading("My Groups");
	}
}
