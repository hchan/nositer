package com.nositer.client.groups;

import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.UserHasGroupView;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.TabItemPlus;

public class GroupTabItem extends TabItemPlus implements Resizable{
	
	private GroupTabPanel groupTabPanel;
	private GroupTabPanel.TabItemType tabItemType;
	
	public GroupTabPanel getGroupTabPanel() {
		return groupTabPanel;
	}

	public void setGroupTabPanel(GroupTabPanel groupTabPanel) {
		this.groupTabPanel = groupTabPanel;
	}

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
		AsyncCallback<UserHasGroupView> callback = new AsyncCallback<UserHasGroupView>() {
			@Override
			public void onFailure(Throwable caught) {
				GWTUtil.log("", caught);
			}
			@Override
			public void onSuccess(UserHasGroupView result) {
				init(result);
			}
		};
		ServiceBroker.groupService.getGroupByTagname(getItemId(), callback);
	}

	public void init(UserHasGroupView userHasGroupView) {
		GroupTabItem.this.setText(userHasGroupView.getName());
		setLayout(new FitLayout());
		groupTabPanel = new GroupTabPanel(userHasGroupView);		
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
