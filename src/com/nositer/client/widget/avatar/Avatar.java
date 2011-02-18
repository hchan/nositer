package com.nositer.client.widget.avatar;

import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class Avatar extends LayoutContainer {
	private HtmlContainer htmlContainer;

	public Avatar() {
		init();
	}

	private void init() {
		htmlContainer = new HtmlContainer();
		this.setLayout(new FitLayout());
		this.add(htmlContainer);
	}

	public void setPathToImage(String pathToImage) {
		htmlContainer.setHtml("<IMG SRC='" + pathToImage + "' CLASS='avatar'/>");
	}
}
