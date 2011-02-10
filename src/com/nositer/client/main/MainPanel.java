package com.nositer.client.main;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.nositer.client.widget.Resizable;

public class MainPanel extends ContentPanel {
	private static MainPanel instance;
	private BorderLayoutData mainLayoutData;
	public static MainPanel getInstance() {
		return instance;
	}

	public static void setInstance(MainPanel instance) {
		MainPanel.instance = instance;
	}


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
	
	@Override
	protected void onResize(int width, int height) {		
		super.onResize(width, height);
		for (Component component : getItems()) {
			if (component instanceof Resizable) {
				Resizable resizable = (Resizable)component;
				resizable.resize(width, height);
			}
		}
	}
}
