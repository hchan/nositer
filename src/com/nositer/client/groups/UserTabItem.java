package com.nositer.client.groups;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.User;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.TabItemPlus;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserTabItem extends TabItemPlus implements Resizable{
	private GroupTabPanel.TabItemType tabItemType;

	public GroupTabPanel.TabItemType getTabItemType() {
		return tabItemType;
	}

	public void setTabItemType(GroupTabPanel.TabItemType tabItemType) {
		this.tabItemType = tabItemType;
	}

	public UserTabItem(String tabId) {
		setItemId(tabId);
		addDefaultListeners();
		init();
	}

	public UserTabItem(String tabId, GroupTabPanel.TabItemType tabItemType) {
		this.tabItemType = tabItemType;
		setItemId(tabId);
		init();
	}

	public void init() {
		setText("Loading...");
		setClosable(true);

		AsyncCallback<User> callback = new AsyncCallback<User>() {
			@Override
			public void onFailure(Throwable caught) {
				GWTUtil.log("", caught);
			}
			@Override
			public void onSuccess(User result) {
				init(result);
			}
		};
		ServiceBroker.profileService.getUser(Integer.parseInt(getItemId()), callback);

	}

	public void init(User user) {
		UserTabItem.this.setText(user.getFirstname() + " " + user.getLastname());
		UserTabPanel userTabPanel = new UserTabPanel(user);
		add(userTabPanel);
		layout();
	}


	@Override
	public void addDefaultListeners() {
		addListener(Events.Close, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {
				HistoryManager.removeSubHistoryToken();
			}
		});
		addListener(Events.Select, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {
				History.newItem(HistoryToken.USER + HistoryManager.SUBTOKENSEPARATOR + UserTabItem.this.getItemId());
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
