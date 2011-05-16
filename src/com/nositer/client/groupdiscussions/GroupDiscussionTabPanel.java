package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.widget.TabPanel;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.groups.GroupTabPanel.TabItemType;
import com.nositer.client.main.MainPanel;
import com.nositer.client.managefiles.GroupFileSelector;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.Resizable;


public class GroupDiscussionTabPanel extends TabPanel implements Resizable {

	@Override
	public void resize(int width, int height) {
		setHeight(MainPanel.getInstance().getHeight()-29);
		//setSize(MainPanel.getInstance().getWidth()-3, 
		//	MainPanel.getInstance().getHeight()-29);
		//setHeight(200);
		//setHeight("100%");
		//if (this.isRendered()) {
		//	GWTUtil.log("height: " + this.getHeight());
		//	GWTUtil.log("width: " + this.getWidth());
			
		//	setWidth(GroupDiscussionsContainer.DEFAULTLEFTPANELWIDTH);
			
		//}
		//else {
			//setAutoHeight(true);
			//setWidth(300);
			//setHeight(MainPanel.getInstance().getHeight() - 60);
		//}
		
		
		
		
		
	}




}
