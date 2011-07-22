package com.nositer.client.groupchat;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.nositer.client.main.MainPanel;
import com.nositer.client.top.TopPanel;
import com.nositer.client.widget.Resizable;

public class GroupChatBottomPanel extends ContentPanel implements Resizable {


	private GroupChatContainer groupChatContainer;
	private TextField<String> textField;
	
	public GroupChatContainer getGroupChatContainer() {
		return groupChatContainer;
	}

	public void setGroupChatContainer(GroupChatContainer groupChatContainer) {
		this.groupChatContainer = groupChatContainer;
	}

	

	public TextField<String> getTextField() {
		return textField;
	}

	public void setTextField(TextField<String> textField) {
		this.textField = textField;
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
		this.setLayout(new FlowLayout(0));
		this.setHeaderVisible(false);
		textField = new TextField<String>();
		this.add(textField);
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
		this.setHeight(1);
		//listField.setHeight(MainPanel.getInstance().getHeight() - 60);
		//groupChatContainer.getGroupChatMainPanel().resize(0,0);
	}
}
