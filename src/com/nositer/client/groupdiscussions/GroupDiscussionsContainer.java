package com.nositer.client.groupdiscussions;

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

public class GroupDiscussionsContainer extends LayoutContainer implements Resizable {
	public static int DEFAULTLEFTPANELWIDTH = 300;
	private GroupPlusView groupPlusView;
	private GroupDiscussionLeftPanel groupDiscussionLeftPanel;
	private GroupDiscussionMainPanel groupDiscussionMainPanel; 
	private ContentPanel contentPanel;
	
	
	public GroupPlusView getGroupPlusView() {
		return groupPlusView;
	}

	public void setGroupPlusView(GroupPlusView groupPlusView) {
		this.groupPlusView = groupPlusView;
	}

	public GroupDiscussionLeftPanel getGroupDiscussionLeftPanel() {
		return groupDiscussionLeftPanel;
	}

	public void setGroupDiscussionLeftPanel(
			GroupDiscussionLeftPanel groupDiscussionLeftPanel) {
		this.groupDiscussionLeftPanel = groupDiscussionLeftPanel;
	}

	public GroupDiscussionMainPanel getGroupDiscussionMainPanel() {
		return groupDiscussionMainPanel;
	}

	public void setGroupDiscussionMainPanel(
			GroupDiscussionMainPanel groupDiscussionMainPanel) {
		this.groupDiscussionMainPanel = groupDiscussionMainPanel;
	}

	public ContentPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(ContentPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public GroupDiscussionsContainer(GroupPlusView groupPlusView) {
		this.groupPlusView = groupPlusView;
		init();
	}

	public void init() {
		//setLayout(new FitLayout());
		setLayout(new FlowLayout(0));
		contentPanel = new ContentPanel();
	
		
		
		contentPanel.setHeaderVisible(false);
		BorderLayout borderLayout = new BorderLayout();
		contentPanel.setLayout(borderLayout);
		groupDiscussionLeftPanel = new GroupDiscussionLeftPanel(this);
		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST);//, DEFAULTLEFTPANELWIDTH);
		westData.setSplit(true);
		westData.setMargins(new Margins(0, 2, 0, 0));
		westData.setSize(DEFAULTLEFTPANELWIDTH);
		
		
		contentPanel.add(groupDiscussionLeftPanel, westData);
		
		groupDiscussionMainPanel = new GroupDiscussionMainPanel(this);
		groupDiscussionMainPanel.setLayout(new FlowLayout(0));  
		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
		centerData.setSplit(true);
		contentPanel.add(groupDiscussionMainPanel, centerData);
		add(contentPanel);
		resize(0,0);
		
		
	}
	
	@Override
	public void resize(int width, int height) {	
		contentPanel.setHeight(MainPanel.getInstance().getHeight() - 55);
		groupDiscussionMainPanel.resize(0,0);
	}

}
