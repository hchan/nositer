package com.nositer.client.groupchat;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.nositer.client.main.MainPanel;
import com.nositer.client.top.TopPanel;
import com.nositer.client.widget.Resizable;

public class GroupChatMainPanel extends ContentPanel implements Resizable {


	private GroupChatContainer groupChatContainer;
	private ListField<ModelData> listField;
	
	public GroupChatContainer getGroupChatContainer() {
		return groupChatContainer;
	}

	public void setGroupChatContainer(GroupChatContainer groupChatContainer) {
		this.groupChatContainer = groupChatContainer;
	}

	public ListField<ModelData> getListField() {
		return listField;
	}

	public void setListField(ListField<ModelData> listField) {
		this.listField = listField;
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
		listField = new ListField<ModelData>();
		this.add(listField);
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
		
		//listField.setHeight(MainPanel.getInstance().getHeight() - 60);
		listField.setWidth(500);
		//groupChatContainer.getGroupChatMainPanel().resize(0,0);
	}
}
