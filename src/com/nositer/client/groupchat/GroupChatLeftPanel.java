package com.nositer.client.groupchat;

import java.util.List;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.data.BeanModelTag;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;
import com.nositer.client.Nositer;
import com.nositer.client.dto.generated.User;
import com.nositer.client.main.MainPanel;
import com.nositer.client.top.TopPanel;
import com.nositer.client.widget.Resizable;

@SuppressWarnings("unchecked")
public class GroupChatLeftPanel extends ContentPanel implements Resizable {


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

	public GroupChatLeftPanel() {
		init();
	}

	public GroupChatLeftPanel(
			GroupChatContainer groupChatContainer) {
		this.groupChatContainer = groupChatContainer;
		init();
	}

	
	private void init() {
		this.setId(this.getClass().getName());
		this.setLayout(new FlowLayout(0));
		this.setHeaderVisible(false);
		listField = new ListField<ModelData>();
		listField.setDisplayField(User.Column.login.name());
		User user = Nositer.getInstance().getUser();	
		
	   
	 
		ListStore<ModelData> store = new ListStore<ModelData>();
		BeanModelFactory factory = BeanModelLookup.get().getFactory(User.class);
		BeanModel val = factory.createModel(user);
		store.add(val);
		listField.setStore(store);  
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

		//listField.setHeight(MainPanel.getInstance().getHeight() - 160);
		if (this.isRendered()) {
			listField.setHeight(this.getHeight());
			listField.setWidth(this.getWidth());
		}
		//groupChatContainer.getGroupChatMainPanel().resize(0,0);
	}

	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);
		resize(0,0);
	}
	
	
}
