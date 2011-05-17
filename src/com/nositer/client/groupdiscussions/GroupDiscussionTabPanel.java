package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.widget.TabPanel;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.Resizable;


public class GroupDiscussionTabPanel extends TabPanel implements Resizable {

	@Override
	public void resize(int width, int height) {
		setHeight(MainPanel.getInstance().getHeight()-29);
	}
}
