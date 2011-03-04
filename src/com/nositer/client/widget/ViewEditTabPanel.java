package com.nositer.client.widget;

import com.extjs.gxt.ui.client.widget.TabPanel;

public class ViewEditTabPanel extends TabPanel {
	private TabItemPlus viewTabItem;
	private TabItemPlus editTabItem;

	public TabItemPlus getViewTabItem() {
		return viewTabItem;
	}

	public void setViewTabItem(TabItemPlus viewTabItem) {
		this.viewTabItem = viewTabItem;
	}

	public TabItemPlus getEditTabItem() {
		return editTabItem;
	}

	public void setEditTabItem(TabItemPlus editTabItem) {
		this.editTabItem = editTabItem;
	}

	public ViewEditTabPanel() {
		init();
	}

	public void init() {
		setTabPosition(TabPosition.BOTTOM);
		viewTabItem = new TabItemPlus("View") {
			@Override
			public void resize(int width, int height) {				
			}
			@Override
			public void addDefaultListeners() {};
		};

		editTabItem = new TabItemPlus("Edit") {
			@Override
			public void resize(int width, int height) {				
			}
			@Override
			public void addDefaultListeners() {};
		};
		add(viewTabItem);
		add(editTabItem);
	}
}
