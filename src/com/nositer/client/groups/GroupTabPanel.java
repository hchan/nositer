package com.nositer.client.groups;

import com.extjs.gxt.ui.client.widget.TabPanel;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.groups.GroupTabPanel.TabItemType;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.Resizable;


public class GroupTabPanel extends TabPanel implements Resizable {
	public enum TabItemType {
		VIEW, EDIT, SUBSCRIPTIONS
	}
	private ViewGroupTabItem viewGroupTabItem;
	private EditGroupTabItem editGroupTabItem;
	private SubscriptionsGroupTabItem subscriptionsGroupTabItem;
	private FilesGroupTabItem filesGroupTabItem;
	private UploadGroupTabItem uploadGroupTabItem;
	
	private GroupPlusView groupPlusView;

	public ViewGroupTabItem getViewGroupTabItem() {
		return viewGroupTabItem;
	}




	public SubscriptionsGroupTabItem getSubscriptionsGroupTabItem() {
		return subscriptionsGroupTabItem;
	}




	public void setSubscriptionsGroupTabItem(
			SubscriptionsGroupTabItem subscriptionsGroupTabItem) {
		this.subscriptionsGroupTabItem = subscriptionsGroupTabItem;
	}




	public void setGroupPlusView(GroupPlusView groupPlusView) {
		this.groupPlusView = groupPlusView;
	}




	public void setViewGroupTabItem(ViewGroupTabItem viewGroupTabItem) {
		this.viewGroupTabItem = viewGroupTabItem;
	}




	public EditGroupTabItem getEditGroupTabItem() {
		return editGroupTabItem;
	}




	public void setEditGroupTabItem(EditGroupTabItem editGroupTabItem) {
		this.editGroupTabItem = editGroupTabItem;
	}

	public GroupPlusView getGroupPlusView() {
		return groupPlusView;
	}

	public void setGroup(GroupPlusView groupPlusView) {
		this.groupPlusView = groupPlusView;
	}

	public GroupTabPanel(GroupPlusView groupPlusView) {
		super();
		this.groupPlusView = groupPlusView;
		init();
	}

	public void init() {
		setTabPosition(TabPosition.BOTTOM);
		viewGroupTabItem = new ViewGroupTabItem(groupPlusView);
		add(viewGroupTabItem);
		subscriptionsGroupTabItem = new SubscriptionsGroupTabItem(groupPlusView);
		add(subscriptionsGroupTabItem);
		if (Groups.isGroupIOwn(groupPlusView)) {
			editGroupTabItem = new EditGroupTabItem(groupPlusView);
			add(editGroupTabItem);
		}
		resize(0,0);
	}

	@Override
	public void resize(int width, int height) {
		setSize(MainPanel.getInstance().getWidth()-3, 
				MainPanel.getInstance().getHeight()-29);	
	}

	public void show(TabItemType tabItemType) {
		if (tabItemType.equals(TabItemType.VIEW)) {
			setSelection(viewGroupTabItem);
		} else if (tabItemType.equals(TabItemType.EDIT)) {
			setSelection(editGroupTabItem);
		} else if (tabItemType.equals(TabItemType.SUBSCRIPTIONS)) {
			setSelection(subscriptionsGroupTabItem);
		}
	}
}
