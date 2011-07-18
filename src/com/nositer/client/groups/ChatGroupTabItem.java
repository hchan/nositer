package com.nositer.client.groups;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.widget.HistoryWidget;
import com.nositer.client.widget.Resizable;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ChatGroupTabItem extends TabItem implements Resizable, HistoryWidget {


	private GroupPlusView groupPlusView;
	private GroupTabPanel groupTabPanel;


	public GroupPlusView getGroupPlusView() {
		return groupPlusView;
	}

	public void setGroupPlusView(GroupPlusView groupPlusView) {
		this.groupPlusView = groupPlusView;
	}

	public GroupTabPanel getGroupTabPanel() {
		return groupTabPanel;
	}

	public void setGroupTabPanel(GroupTabPanel groupTabPanel) {
		this.groupTabPanel = groupTabPanel;
	}

	public ChatGroupTabItem(GroupPlusView groupPlusView, GroupTabPanel groupTabPanel) {
		super("Chat");
		this.groupPlusView = groupPlusView;
		this.groupTabPanel = groupTabPanel;
		init();
	}

	public void init() {
		
		addDefaultListeners();
	}

	
	private void addDefaultListeners() {
		addListener(Events.Select, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {
				HistoryManager.addHistory(getNewHistory());
				resize(0,0);
			}
		});
	}

	@Override
	public void resize(int width, int height) {
		//contentPanel.setHeight(MainPanel.getInstance().getHeight()-57);
		//contentPanel.setWidth(MainPanel.getInstance().getWidth()-3);
	
		
	}

	@Override
	public String getNewHistory() {
		return HistoryToken.CHATGROUP + HistoryManager.SUBTOKENSEPARATOR + groupPlusView.getTagname();
	}

	

}