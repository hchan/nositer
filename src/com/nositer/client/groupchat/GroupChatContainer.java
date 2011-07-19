package com.nositer.client.groupchat;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.Resizable;

public class GroupChatContainer extends LayoutContainer implements Resizable {
	//public static int DEFAULTLEFTPANELWIDTH = 400;
	private GroupPlusView groupPlusView;
	private ContentPanel contentPanel;
	private GroupChatLeftPanel groupChatLeftPanel;
	
	public GroupPlusView getGroupPlusView() {
		return groupPlusView;
	}

	public void setGroupPlusView(GroupPlusView groupPlusView) {
		this.groupPlusView = groupPlusView;
	}	

	public GroupChatLeftPanel getGroupChatLeftPanel() {
		return groupChatLeftPanel;
	}

	public void setGroupChatLeftPanel(GroupChatLeftPanel groupChatLeftPanel) {
		this.groupChatLeftPanel = groupChatLeftPanel;
	}

	public ContentPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(ContentPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public GroupChatContainer(GroupPlusView groupPlusView) {
		this.groupPlusView = groupPlusView;
		init();
	}

	public void init() {
		//setLayout(new FitLayout());
		setLayout(new FlowLayout(0));
		contentPanel = new ContentPanel();
		contentPanel.setHeaderVisible(false);
		groupChatLeftPanel = new GroupChatLeftPanel(this);
		contentPanel.add(groupChatLeftPanel);
		add(contentPanel);
		resize(0,0);
		
		
	}
	
	@Override
	public void resize(int width, int height) {	
		
		contentPanel.setHeight(MainPanel.getInstance().getHeight() - 55);
	}

}
