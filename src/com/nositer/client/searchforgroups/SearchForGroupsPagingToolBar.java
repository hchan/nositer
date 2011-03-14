package com.nositer.client.searchforgroups;

import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;

public class SearchForGroupsPagingToolBar extends PagingToolBar {

	public SearchForGroupsPagingToolBar(int pageSize) {
		super(pageSize);
	}

	public Button getRefreshButton() {
		return refresh;
	}
}
