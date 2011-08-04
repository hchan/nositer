package com.nositer.client.groupchat;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;
import com.nositer.client.widget.HtmlContainerPlus;
import com.nositer.client.widget.Resizable;

public class GroupChatMainPanel extends ContentPanel implements Resizable {
	private GroupChatContainer groupChatContainer;
	private HtmlContainerPlus htmlContainerPlus;

	public HtmlContainerPlus getHtmlContainerPlus() {
		return htmlContainerPlus;
	}

	public void setHtmlContainerPlus(HtmlContainerPlus htmlContainerPlus) {
		this.htmlContainerPlus = htmlContainerPlus;
	}

	public GroupChatContainer getGroupChatContainer() {
		return groupChatContainer;
	}

	public void setGroupChatContainer(GroupChatContainer groupChatContainer) {
		this.groupChatContainer = groupChatContainer;
	}

	
	public GroupChatMainPanel() {
		init();
	}

	public GroupChatMainPanel(
			GroupChatContainer groupChatContainer) {
		this.groupChatContainer = groupChatContainer;
		init();
	}

	private void init() {
		this.setId(this.getClass().getName());
		this.setLayout(new FlowLayout(0));
		this.setHeaderVisible(false);
		htmlContainerPlus = new HtmlContainerPlus();
		htmlContainerPlus.setStyleName("textFile");
		//htmlContainerPlus.setAutoHeight(true);
		this.add(htmlContainerPlus);
		resize(0,0);
	}

	// called when the borderlayout split is resized
	@Override
	protected void onResize(int width, int height) {
		super.onResize(width, height);
		resize(0,0);
	}

	@Override
	public void resize(int width, int height) {

		//listField.setHeight(MainPanel.getInstance().getHeight() - 160);
		if (this.isRendered()) {
		
			htmlContainerPlus.setHeight(this.getHeight());
			htmlContainerPlus.setWidth(this.getWidth());
		}
		//groupChatContainer.getGroupChatMainPanel().resize(0,0);
	}

	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);
		resize(0,0);
	}
}
