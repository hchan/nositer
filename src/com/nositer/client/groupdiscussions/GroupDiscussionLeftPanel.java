package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.button.RefreshButton;

public class GroupDiscussionLeftPanel extends ContentPanel implements Resizable {


	private GroupDiscussionsContainer groupDiscussionsContainer;
	private LeftPanelAccordionContainer leftPanelAccordionContainer;
	private GroupmessagesGrid groupmessagesGrid;
	private ToolBar toolBar;
	
	public GroupDiscussionsContainer getGroupDiscussionsContainer() {
		return groupDiscussionsContainer;
	}

	public void setGroupDiscussionsContainer(
			GroupDiscussionsContainer groupDiscussionsContainer) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
	}

	public LeftPanelAccordionContainer getLeftPanelAccordionContainer() {
		return leftPanelAccordionContainer;
	}

	public void setLeftPanelAccordionContainer(
			LeftPanelAccordionContainer leftPanelAccordionContainer) {
		this.leftPanelAccordionContainer = leftPanelAccordionContainer;
	}

	public GroupmessagesGrid getGroupmessagesGrid() {
		return groupmessagesGrid;
	}

	public void setGroupmessagesGrid(GroupmessagesGrid groupmessagesGrid) {
		this.groupmessagesGrid = groupmessagesGrid;
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public void setToolBar(ToolBar toolBar) {
		this.toolBar = toolBar;
	}

	public GroupDiscussionLeftPanel() {
		init();
	}

	public GroupDiscussionLeftPanel(
			GroupDiscussionsContainer groupDiscussionsContainer) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
		init();
	}

	private void init() {
		this.setLayout(new FlowLayout(0));
		this.setHeaderVisible(false);
		groupmessagesGrid = new GroupmessagesGrid(groupDiscussionsContainer);
		leftPanelAccordionContainer = new LeftPanelAccordionContainer(groupDiscussionsContainer);
		toolBar = new ToolBar();	
		toolBar.setAlignment(HorizontalAlignment.CENTER);
		//toolBar.add(new FillToolItem());
		toolBar.add(new RefreshButton() {			
			@Override
			public void doSelect() {
				groupmessagesGrid.refresh();
			}
		});
		this.setBottomComponent(toolBar);
		this.add(leftPanelAccordionContainer);
		this.add(groupmessagesGrid);
	}

	// called when the borderlayout split is resized
	@Override
	protected void onResize(int width, int height) {
		super.onResize(width, height);
		resize(0,0);
	}

	@Override
	public void resize(int width, int height) {
		/*
		Resizable resizable = (Resizable) groupDiscussionTabPanel.getSelectedItem();
		if (resizable != null) {
			resizable.resize(0,0);
		}
		 */
		if (groupmessagesGrid.isRendered()) {
			groupmessagesGrid.setHeight(this.getHeight());
			groupmessagesGrid.setWidth(this.getWidth());
			leftPanelAccordionContainer.setWidth(this.getWidth());
		}
		groupDiscussionsContainer.getGroupDiscussionMainPanel().resize(0,0);
	}
}
