package com.nositer.client.widget;

import com.extjs.gxt.ui.client.widget.HtmlContainer;

public class HtmlContainerPlus extends HtmlContainer {
	private String htmlHistory;

	public HtmlContainerPlus() {
		super();
		this.htmlHistory = "";
	}
	
	public String getHtmlHistory() {
		return htmlHistory;
	}

	public void setHtmlHistory(String htmlHistory) {
		this.htmlHistory = htmlHistory;
	}
	
	

}
