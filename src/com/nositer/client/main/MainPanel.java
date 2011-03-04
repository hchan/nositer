package com.nositer.client.main;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Container;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.nositer.client.widget.Resizable;

@SuppressWarnings("unchecked")
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
		resizeChildren(width, height, this);
	}


	public void resizeChildren(int width, int height, Container<Component> container) {
		for (Component childComponent : container.getItems()) {
			if (childComponent.isVisible()) {
				if (childComponent instanceof Resizable) {
					Resizable resizable = (Resizable)childComponent;
					resizable.resize(width, height);
				}
				if (childComponent instanceof Container) {
					resizeChildren(width, height, (Container<Component>) childComponent);
				}			
			}
		}
	}
}
