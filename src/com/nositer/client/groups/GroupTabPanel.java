package com.nositer.client.groups;

import com.extjs.gxt.ui.client.widget.TabPanel;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.groups.GroupTabPanel.TabItemType;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.Resizable;


public class GroupTabPanel extends TabPanel implements Resizable {
	public enum TabItemType {
		VIEW, EDIT
	}
	private ViewGroupTabItem viewGroupTabItem;
	private EditGroupTabItem editGroupTabItem;
	
	private Group group;

	public ViewGroupTabItem getViewGroupTabItem() {
		return viewGroupTabItem;
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




	public Group getGroup() {
		return group;
	}




	public void setGroup(Group group) {
		this.group = group;
	}




	public GroupTabPanel(Group group) {
		super();
		this.group = group;
		init();
	}




	public void init() {
		setTabPosition(TabPosition.BOTTOM);
		viewGroupTabItem = new ViewGroupTabItem(group);
		add(viewGroupTabItem);
		resize(0,0);
		if (Groups.isGroupIOwn(group)) {
			editGroupTabItem = new EditGroupTabItem(group);
			add(editGroupTabItem);
		}
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
		}
	}
}
