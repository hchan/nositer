package com.nositer.client.widget.avatar;

import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class Avatar extends LayoutContainer {
	private HtmlContainer htmlContainer;
	private String pathToImage;
	
	public HtmlContainer getHtmlContainer() {
		return htmlContainer;
	}

	public void setHtmlContainer(HtmlContainer htmlContainer) {
		this.htmlContainer = htmlContainer;
	}

	public String getPathToImage() {
		return pathToImage;
	}

	public Avatar() {
		init();
	}
	
	public Avatar(String pathToImage) {
		this.pathToImage = pathToImage;
		init();
		setPathToImage(pathToImage);
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
