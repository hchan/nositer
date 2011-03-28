package com.nositer.client.groups;

import com.extjs.gxt.ui.client.widget.TabPanel;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.UserHasGroupView;
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
	
	private UserHasGroupView userHasGroupView;

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




	public void setUserHasGroupView(UserHasGroupView userHasGroupView) {
		this.userHasGroupView = userHasGroupView;
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




	public UserHasGroupView getUserHasGroupView() {
		return userHasGroupView;
	}




	public void setGroup(UserHasGroupView userHasGroupView) {
		this.userHasGroupView = userHasGroupView;
	}




	public GroupTabPanel(UserHasGroupView userHasGroupView) {
		super();
		this.userHasGroupView = userHasGroupView;
		init();
	}




	public void init() {
		setTabPosition(TabPosition.BOTTOM);
		viewGroupTabItem = new ViewGroupTabItem(userHasGroupView);
		add(viewGroupTabItem);
		resize(0,0);
		if (Groups.isGroupIOwn(userHasGroupView)) {
			editGroupTabItem = new EditGroupTabItem(userHasGroupView);
			add(editGroupTabItem);
		}
		subscriptionsGroupTabItem = new SubscriptionsGroupTabItem(userHasGroupView);
		add(subscriptionsGroupTabItem);
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
