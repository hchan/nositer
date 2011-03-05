package com.nositer.client.groups;

import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.TabItemPlus;

public class GroupTabItem extends TabItemPlus implements Resizable{
	
	private GroupTabPanel groupTabPanel;
	public GroupTabItem(String tabId) {
		setItemId(tabId);
		init();
	}

	public void init() {
		setText("Loading...");
		setClosable(true);
		AsyncCallback<Group> callback = new AsyncCallback<Group>() {
			@Override
			public void onFailure(Throwable caught) {
				GWTUtil.log("", caught);
			}
			@Override
			public void onSuccess(Group result) {
				init(result);
			}
		};
		ServiceBroker.groupService.getGroupByTagname(getItemId(), callback);
	}

	public void init(Group group) {
		GroupTabItem.this.setText(group.getName());
		setLayout(new FitLayout());
		groupTabPanel = new GroupTabPanel(group);
		
		add(groupTabPanel);
		layout();
	}


	@Override
	public void resize(int width, int height) {		
		//setSize(MainPanel.getInstance().getWidth()-3,
		//		MainPanel.getInstance().getHeight());
	}
}