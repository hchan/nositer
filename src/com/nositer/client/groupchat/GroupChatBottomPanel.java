package com.nositer.client.groupchat;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.HtmlEditor;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import com.nositer.client.main.MainPanel;
import com.nositer.client.top.TopPanel;
import com.nositer.client.widget.Resizable;

public class GroupChatBottomPanel extends ContentPanel implements Resizable {


	private GroupChatContainer groupChatContainer;
	private TextArea textArea;
	private Button button;
	
	public GroupChatContainer getGroupChatContainer() {
		return groupChatContainer;
	}

	public void setGroupChatContainer(GroupChatContainer groupChatContainer) {
		this.groupChatContainer = groupChatContainer;
	}

	public TextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(TextArea textArea) {
		this.textArea = textArea;
	}

	public GroupChatBottomPanel() {
		init();
	}

	public GroupChatBottomPanel(
			GroupChatContainer groupChatContainer) {
		this.groupChatContainer = groupChatContainer;
		init();
	}

	private void init() {
		this.setId(this.getClass().getName());
		this.setLayout(new RowLayout(Orientation.HORIZONTAL));
	
		this.setHeaderVisible(false);
		
		textArea = new TextArea();
	
		
	
		button = new Button("Send");
		button.setWidth(50);
		
		this.add(textArea);
		this.add(button);
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
		this.setHeight(52);
		//listField.setHeight(MainPanel.getInstance().getHeight() - 160);
		if (this.isRendered()) {
			textArea.setHeight(this.getHeight());
			textArea.setWidth(this.getWidth()-button.getWidth());
			button.setHeight(this.getHeight());
		}
		//groupChatContainer.getGroupChatMainPanel().resize(0,0);
	}

	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);
		resize(0,0);
	}
}
