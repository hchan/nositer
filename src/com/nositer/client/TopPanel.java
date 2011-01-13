package com.nositer.client;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;

public class TopPanel extends ContentPanel {
	private Nositer nositer;

	public Nositer getNositer() {
		return nositer;
	}

	public void setNositer(Nositer nositer) {
		this.nositer = nositer;
	}
	
	public TopPanel (Nositer nositer) {
		this.nositer = nositer;
		init();
	}

	private void init() {
		this.setHeading("TOP PANEL");
		this.add(new Button("TOP"));
	}
}
