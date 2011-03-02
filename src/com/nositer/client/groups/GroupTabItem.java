package com.nositer.client.groups;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout.VBoxLayoutAlign;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.util.ImageHelper;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.TabItemPlus;
import com.nositer.client.widget.avatar.Avatar;

public class GroupTabItem extends TabItemPlus implements Resizable{
	private HtmlContainer description;
	private ContentPanel contentPanel;
	private Avatar avatar;

	public GroupTabItem(String tabId) {
		setItemId(tabId);
		init();
	}

	public void init() {
		setText("Loading...");
		setClosable(true);		
		VBoxLayout layout = new VBoxLayout(VBoxLayoutAlign.CENTER);
		setLayout(new FitLayout());
		contentPanel = new ContentPanel();
		contentPanel.setBodyBorder(false);
		contentPanel.setBorders(false);
		contentPanel.setLayout(layout);
		contentPanel.setHeaderVisible(false);

		setMonitorWindowResize(true);
		
		description = new HtmlContainer();
		avatar = new Avatar();
		AsyncCallback<Group> callback = new AsyncCallback<Group>() {

			@Override
			public void onFailure(Throwable caught) {
				GWTUtil.log("", caught);
			}

			@Override
			public void onSuccess(Group result) {
				GroupTabItem.this.setText(
						result.getName());
				description.setHtml(result.getDescription());
				avatar.setPathToImage(ImageHelper.getUserImagePathURL(result.getAvatarlocation()));
				resize(0,0);
			}

		};
		LayoutContainer avatarContentPanel = new LayoutContainer();
		avatarContentPanel.add(avatar);
		contentPanel.add(avatarContentPanel);
		contentPanel.add(description);
		add(contentPanel);

		ServiceBroker.groupService.getGroupByTagname(getItemId(), callback);
		
		
	}




	@Override
	protected void onWindowResize(int width, int height) {
		resize(0,0);
	}

	@Override
	public void resize(int width, int height) {
		contentPanel.setHeight(MainPanel.getInstance().getHeight());
		contentPanel.setWidth(MainPanel.getInstance().getWidth());
		setHeight(MainPanel.getInstance().getHeight());
		setWidth(MainPanel.getInstance().getWidth());
	}

}
