package com.nositer.client.groups;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.nositer.client.creategroup.CreateGroup;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.ImageHelper;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.avatar.Avatar;

public class EditGroupTabItem extends TabItem implements Resizable {

	private CreateGroup createGroup;
	private Group group;

	public EditGroupTabItem(Group group) {
		super("Edit");
		this.group = group;
		init();
	}

	public void init() {
		createGroup = new CreateGroup();
		
		add(createGroup);
	}

	@Override
	public void resize(int width, int height) {
		//contentPanel.setHeight(MainPanel.getInstance().getHeight()-57);
		//contentPanel.setWidth(MainPanel.getInstance().getWidth()-3);
	
		
	}

	

}