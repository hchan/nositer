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
import com.nositer.client.viewprofile.ViewProfile;
import com.nositer.client.viewprofile.ViewProfileContainer;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.TabItemPlus;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserTabItem extends TabItemPlus implements Resizable{
	


	public UserTabItem(String tabId) {
		setItemId(tabId);
		addDefaultListeners();
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
		
		 ViewProfileContainer viewProfileContainer = new ViewProfileContainer();
		viewProfileContainer.populate(user);
		
		
		add(viewProfileContainer);
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
