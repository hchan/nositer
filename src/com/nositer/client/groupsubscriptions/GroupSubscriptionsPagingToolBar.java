package com.nositer.client.groupsubscriptions;

import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;

public class GroupSubscriptionsPagingToolBar extends PagingToolBar {

	public GroupSubscriptionsPagingToolBar(int pageSize) {
		super(pageSize);
	}

	public Button getRefreshButton() {
		return refresh;
	}
}
