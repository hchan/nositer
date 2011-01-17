package com.nositer.client;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.ui.HTMLPanel;

public class TopPanel extends ContentPanel {
	private Nositer nositer;
	private BorderLayoutData topLayoutData;
	public BorderLayoutData getTopLayoutData() {
		return topLayoutData;
	}

	public void setTopLayoutData(BorderLayoutData topLayoutData) {
		this.topLayoutData = topLayoutData;
	}

	public Nositer getNositer() {
		return nositer;
	}

	public void setNositer(Nositer nositer) {
		this.nositer = nositer;
	}

	public TopPanel (Nositer nositer, BorderLayoutData topLayoutData) {
		this.nositer = nositer;
		this.topLayoutData = topLayoutData;
		init();
	}

	private void init() {
		topLayoutData.setSize(50);
		//topLayoutData.setCollapsible(true);
		this.setHeaderVisible(false);
		HTMLPanel htmlPanel = new HTMLPanel("Journey with us");
		this.add(htmlPanel);
	}
}
