package com.nositer.client.main;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.nositer.client.Nositer;
import com.nositer.client.left.LeftPanel;

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

	private void init() {
		this.setHeaderVisible(false);
		this.setHeading("Nos Iter - Journey with us");
	}
}
