package com.nositer.client.main;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.nositer.client.Nositer;

public class MainPanel extends ContentPanel {
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
	}

	private void init() {
		this.setHeaderVisible(false);
		this.setHeading("Nos Iter - Journey with us");
	}
}
