package com.nositer.client.widget;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;
import com.nositer.client.util.ImageHelper;

public class Unknownavatar extends LayoutContainer {
	public Unknownavatar() {
		init();
	}

	private void init() {
		HTML html = new HTML();
		html.setHTML("<IMG SRC='" + ImageHelper.UNKNOWNAVATAR + "'/>");
		html.setStyleName("unknownavatar");
		this.setLayout(new FitLayout());
		this.add(html);
	}

}
