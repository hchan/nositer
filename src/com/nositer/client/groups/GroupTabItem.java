package com.nositer.client.groups;

import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.dto.generated.User;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.TabItemPlus;

public class GroupTabItem extends TabItemPlus implements Resizable{


	private GroupTabPanel.TabItemType tabItemType;



	public GroupTabItem(String tabId) {
		setItemId(tabId);
		init();
	}

	public GroupTabItem(String tabId, GroupTabPanel.TabItemType tabItemType) {
		this.tabItemType = tabItemType;
		setItemId(tabId);
		init();
	}

	public void init() {
		setText("Loading...");
		setClosable(true);
		if (tabItemType.equals(GroupTabPanel.TabItemType.SUBSCRIBER)) {
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
		} else {
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
	}

	public void init(User user) {
		GroupTabItem.this.setText(user.getFirstname() + " " + user.getLastname());
		UserTabPanel userTabPanel = new UserTabPanel(user);		
		add(userTabPanel);
		layout();
	}


	public void init(GroupPlusView groupPlusView) {
		GroupTabItem.this.setText(groupPlusView.getName());
		//setLayout(new FitLayout());
		GroupTabPanel groupTabPanel = new GroupTabPanel(groupPlusView);		
		add(groupTabPanel);
		if (tabItemType != null) {
			groupTabPanel.show(tabItemType);
		}
		layout();
	}


	@Override
	public void resize(int width, int height) {		
		//setSize(MainPanel.getInstance().getWidth()-3,
		//		MainPanel.getInstance().getHeight());
	}
}
