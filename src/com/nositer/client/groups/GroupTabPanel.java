package com.nositer.client.groups;

import com.extjs.gxt.ui.client.widget.TabPanel;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.Resizable;


public class GroupTabPanel extends TabPanel implements Resizable {
	private ViewGroupTabItem viewGroupTabItem;
	private EditGroupTabItem editGroupTabItem;
	
	private Group group;

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
}
