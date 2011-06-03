package com.nositer.client.groups;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.HttpGetFileHelper;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.avatar.Avatar;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ViewGroupTabItem extends TabItem implements Resizable {

	private HtmlContainer description;
	private ContentPanel contentPanel;

	private Avatar avatar;
	private GroupPlusView groupPlusView;
	private GroupTabPanel groupTabPanel;

	public HtmlContainer getDescription() {
		return description;
	}

	public void setDescription(HtmlContainer description) {
		this.description = description;
	}

	public ContentPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(ContentPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public GroupPlusView getGroupPlusView() {
		return groupPlusView;
	}

	public void setGroupPlusView(GroupPlusView groupPlusView) {
		this.groupPlusView = groupPlusView;
	}

	public GroupTabPanel getGroupTabPanel() {
		return groupTabPanel;
	}

	public void setGroupTabPanel(GroupTabPanel groupTabPanel) {
		this.groupTabPanel = groupTabPanel;
	}

	public ViewGroupTabItem(GroupPlusView groupPlusView, GroupTabPanel groupTabPanel) {
		super("View");
		this.groupPlusView = groupPlusView;
		this.groupTabPanel = groupTabPanel;
		init();
	}

	public void init() {
		setMonitorWindowResize(true);
		TableLayout layout = new TableLayout(1);

		contentPanel = new ContentPanel();
		contentPanel.setBodyBorder(false);
		contentPanel.setBorders(false);
		contentPanel.setLayout(layout);
		contentPanel.setHeaderVisible(false);
		contentPanel.setScrollMode(Scroll.AUTO);
		contentPanel.setId(getItemId());
		description = new HtmlContainer();
		//description.setHtml(groupPlusView.getDescription());
		avatar = new Avatar();
		//avatar.setPathToImage(HttpGetFileHelper.getGroupPathURL(groupPlusView.getAvatarlocation(), groupPlusView.getId()));
		populate(groupPlusView);
		LayoutContainer avatarContentPanel = new LayoutContainer();
		avatarContentPanel.add(avatar);
		contentPanel.add(avatarContentPanel);
		contentPanel.add(description);
		add(contentPanel);
		resize(0,0);
		addDefaultListeners();
	}
	
	public void populate(GroupPlusView groupPlusView) {
		this.groupPlusView = groupPlusView;
		description.setHtml(groupPlusView.getDescription());
		avatar.setPathToImage(HttpGetFileHelper.getGroupPathURL(groupPlusView.getAvatarlocation(), groupPlusView.getId()));
	}
	
	
	public void populate(Group group) {		
		description.setHtml(group.getDescription());
		avatar.setPathToImage(HttpGetFileHelper.getGroupPathURL(group.getAvatarlocation(), group.getId()));
	}
	
	
	private void addDefaultListeners() {
		addListener(Events.Select, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {
				HistoryManager.addHistory(HistoryToken.GROUPS + HistoryManager.SUBTOKENSEPARATOR + groupPlusView.getTagname());
				resize(0,0);
			}
		});
	}

	@Override
	public void resize(int width, int height) {
		contentPanel.setHeight(MainPanel.getInstance().getHeight()-57);
		contentPanel.setWidth(MainPanel.getInstance().getWidth()-3);
	}

	

}
