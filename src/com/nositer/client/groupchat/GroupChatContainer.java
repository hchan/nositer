package com.nositer.client.groupchat;

import org.atmosphere.gwt.client.AtmosphereClient;
import org.atmosphere.gwt.client.AtmosphereGWTSerializer;
import org.atmosphere.gwt.client.AtmosphereListener;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.core.client.GWT;
import com.nositer.client.Nositer;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.Resizable;
import com.nositer.shared.Global;

public class GroupChatContainer extends LayoutContainer implements Resizable {
	//public static int DEFAULTLEFTPANELWIDTH = 400;
	private GroupPlusView groupPlusView;
	private ContentPanel contentPanel;
	private GroupChatLeftPanel groupChatLeftPanel;
	private GroupChatMainPanel groupChatMainPanel;
	private GroupChatBottomPanel groupChatBottomPanel;
	private AtmosphereClient client;

	public AtmosphereClient getClient() {
		return client;
	}

	public void setClient(AtmosphereClient client) {
		this.client = client;
	}

	public GroupChatBottomPanel getGroupChatBottomPanel() {
		return groupChatBottomPanel;
	}

	public void setGroupChatBottomPanel(GroupChatBottomPanel groupChatBottomPanel) {
		this.groupChatBottomPanel = groupChatBottomPanel;
	}

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
	
	public GroupChatMainPanel getGroupChatMainPanel() {
		return groupChatMainPanel;
	}

	public void setGroupChatMainPanel(GroupChatMainPanel groupChatMainPanel) {
		this.groupChatMainPanel = groupChatMainPanel;
	}

	public GroupChatContainer(GroupPlusView groupPlusView) {
		this.groupPlusView = groupPlusView;
		initializeAtmosphere();
		init();
	}
	
	public void initializeAtmosphere() {

		AtmosphereListener cometListener = new CometListener(this);

		AtmosphereGWTSerializer serializer = GWT.create(EventSerializer.class);
		// set a small length parameter to force refreshes
		// normally you should remove the length parameter
		client = new AtmosphereClient(Global.COMET_URL, serializer, cometListener, true);
		client.start();
		ChatEvent chatEvent = new ChatEvent();
		chatEvent.setLogin(Nositer.getInstance().getUser().getLogin());
		chatEvent.setGrouptagname(getGroupPlusView().getTagname());
		chatEvent.setChatEventType(ChatEventType.CONNECT);
		client.broadcast(chatEvent);
	}


	public void init() {
		//setLayout(new FitLayout());
		contentPanel = new ContentPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setHeaderVisible(false);
		add(contentPanel);
		
		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 100);
		westData.setSplit(true);
		groupChatLeftPanel = new GroupChatLeftPanel(this);
		contentPanel.add(groupChatLeftPanel, westData);
		
		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
		centerData.setSplit(true);
		groupChatMainPanel = new GroupChatMainPanel(this);
		contentPanel.add(groupChatMainPanel, centerData);
		
		BorderLayoutData bottomData = new BorderLayoutData(LayoutRegion.SOUTH, 50);
		groupChatBottomPanel = new GroupChatBottomPanel(this);
		contentPanel.add(groupChatBottomPanel, bottomData);
		
		resize(0,0);		
	}
	
	@Override
	public void resize(int width, int height) {	
		
		contentPanel.setHeight(MainPanel.getInstance().getHeight() - 57);
	}

}
