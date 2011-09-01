package com.nositer.client.groups;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.dto.generated.User;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.HistoryWidget;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.TabItemPlus;

public class GroupTabItem extends TabItemPlus implements Resizable{


	private GroupTabPanel.TabItemType tabItemType;
	private GroupTabPanel groupTabPanel;



	public GroupTabPanel.TabItemType getTabItemType() {
		return tabItemType;
	}

	public void setTabItemType(GroupTabPanel.TabItemType tabItemType) {
		this.tabItemType = tabItemType;
	}

	public GroupTabPanel getGroupTabPanel() {
		return groupTabPanel;
	}

	public void setGroupTabPanel(GroupTabPanel groupTabPanel) {
		this.groupTabPanel = groupTabPanel;
	}



	public GroupTabItem(String tabId, GroupTabPanel.TabItemType tabItemType) {
		this.tabItemType = tabItemType;
		setItemId(tabId);
		init();
	}

	public void init() {
		setText("Loading...");
		setClosable(true);

		AsyncCallback<GroupPlusView> callback = new AsyncCallback<GroupPlusView>() {
			@Override
			public void onFailure(Throwable caught) {
				GWTUtil.log("", caught);
			}
			@Override
			public void onSuccess(GroupPlusView result) {
				init(result);
			}
		};
		ServiceBroker.groupService.getGroupByTagname(getItemId(), callback);
	}




	public void init(GroupPlusView groupPlusView) {
		GroupTabItem.this.setText(groupPlusView.getName());
		groupTabPanel = new GroupTabPanel(groupPlusView);		
		groupTabPanel.setHeight(MainPanel.getInstance().getHeight()-55);
		add(groupTabPanel);
		if (tabItemType != null) {
			groupTabPanel.show(tabItemType);
		}
		layout();
	}

	@Override
	public void addDefaultListeners() {

		addListener(Events.Close, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {

			}
		});
		addListener(Events.Select, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {
				//History.newItem(HistoryToken.GROUPS + HistoryManager.SUBTOKENSEPARATOR + GroupTabItem.this.getItemId());
				if (groupTabPanel != null) {
					HistoryWidget historyWidget = (HistoryWidget) groupTabPanel.getSelectedItem();
					History.newItem(historyWidget.getNewHistory(), false);
				}
				resize(0,0);
			}
		});
	}



	@Override
	public void resize(int width, int height) {		
		//setSize(MainPanel.getInstance().getWidth()-3,
		//		MainPanel.getInstance().getHeight());
	}
}
