package com.nositer.client.main;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;

public class MainPanel extends ContentPanel {
	private static MainPanel instance;
	public static MainPanel getInstance() {
		return instance;
	}

	public static void setInstance(MainPanel instance) {
		MainPanel.instance = instance;
	}

	private BorderLayoutData mainLayoutData;

	public BorderLayoutData getMainLayoutData() {
		return mainLayoutData;
	}

	public void setMainLayoutData(BorderLayoutData mainLayoutData) {
		this.mainLayoutData = mainLayoutData;
	}


	public MainPanel (BorderLayoutData mainLayoutData) {
		this.mainLayoutData = mainLayoutData;
		init();
		instance = this;
	}

	public void init() {
		this.setHeaderVisible(false);
		this.setHeading("Nos Iter - Journey with us");
	}
}
