package com.nositer.client.widget;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;

public class Avatar extends LayoutContainer {
	private HTML html;

	public Avatar() {
		init();
	}

	private void init() {
		html = new HTML();
		
		html.setStyleName("avatar");
		this.setLayout(new FitLayout());
		this.add(html);
	}

	public void setPathToImage(String pathToImage) {
		html.setHTML("<IMG SRC='" + pathToImage + "'/>");
	}
}
