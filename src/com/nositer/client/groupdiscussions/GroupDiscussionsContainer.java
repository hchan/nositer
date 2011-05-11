package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.Resizable;

public class GroupDiscussionsContainer extends LayoutContainer implements Resizable {
	private GroupPlusView groupPlusView;
	private GroupDiscussionLeftPanel groupDiscussionLeftPanel; 
	private ContentPanel contentPanel;
	
	
	public GroupDiscussionsContainer(GroupPlusView groupPlusView) {
		this.groupPlusView = groupPlusView;
		init();
	}

	public void init() {
		setLayout(new FitLayout());
		contentPanel = new ContentPanel();
		contentPanel.setHeaderVisible(false);
		BorderLayout borderLayout = new BorderLayout();
		contentPanel.setLayout(borderLayout);
		groupDiscussionLeftPanel = new GroupDiscussionLeftPanel(this);
		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 100);
		westData.setSplit(true);
		westData.setMargins(new Margins(0, 2, 0, 0));
		contentPanel.add(groupDiscussionLeftPanel, westData);
		
		ContentPanel center = new ContentPanel();
		center.add(new Button("HI"));
		contentPanel.add(center, new BorderLayoutData(LayoutRegion.CENTER, 400));
		add(contentPanel);
		//add(new Button("Hi"));
		//setAutoHeight(true);
		//setAutoWidth(true);
		//setStateful(false);
		resize(0,0);
		
	}
	
	@Override
	public void resize(int width, int height) {	
		// hackery to get the Collapse/Expand Working
		contentPanel.setSize(MainPanel.getInstance().getWidth()-5, MainPanel.getInstance().getHeight() - 55);
		if (contentPanel.getTopComponent() != null && contentPanel.getTopComponent().isRendered()) {
			contentPanel.setSize(MainPanel.getInstance().getWidth()-6, MainPanel.getInstance().getHeight()-55);
		}	
		contentPanel.setSize(MainPanel.getInstance().getWidth()-5, MainPanel.getInstance().getHeight() - 55);		
	}

}
