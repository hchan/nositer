package com.nositer.client;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;

public class MainPanel extends ContentPanel {
	private Nositer nositer;

	public Nositer getNositer() {
		return nositer;
	}

	public void setNositer(Nositer nositer) {
		this.nositer = nositer;
	}
	
	public MainPanel (Nositer nositer) {
		this.nositer = nositer;
		init();
	}

	private void init() {
		this.setHeading("Nos Iter - Journey with us");
	}
}
