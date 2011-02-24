package com.nositer.client.mygroups;

import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.util.GWTUtil;

public class GroupTabItem extends TabItem {
	private HtmlContainer description;
	
	public GroupTabItem(String tabId) {
		setItemId(tabId);
		init();
	}

	public void init() {
		description = new HtmlContainer();
		AsyncCallback<Group> callback = new AsyncCallback<Group>() {

			@Override
			public void onFailure(Throwable caught) {
				GWTUtil.log("", caught);
			}

			@Override
			public void onSuccess(Group result) {
				GroupTabItem.this.setText(result.getName());
				description.setHtml(result.getDescription());
				
			}
			
		};
		add(description);
		ServiceBroker.groupService.getGroupByTagname(getItemId(), callback);
	}

}
