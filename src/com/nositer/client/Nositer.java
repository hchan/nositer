package com.nositer.client;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Nositer implements EntryPoint {

	private TopPanel topPanel;
	private LeftPanel leftPanel;
	private MainPanel mainPanel;
	private LayoutContainer layoutContainer;
	
	public LayoutContainer getLayoutContainer() {
		return layoutContainer;
	}
	public void setLayoutContainer(LayoutContainer layoutContainer) {
		this.layoutContainer = layoutContainer;
	}
	public MainPanel getMainPanel() {
		return mainPanel;
	}
	public void setMainPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	
	public TopPanel getTopPanel() {
		return topPanel;
	}
	public void setTopPanel(TopPanel topPanel) {
		this.topPanel = topPanel;
	}
	public LeftPanel getLeftPanel() {
		return leftPanel;
	}
	public void setLeftPanel(LeftPanel leftPanel) {
		this.leftPanel = leftPanel;
	}
	public void onModuleLoad() {
		
		LayoutContainer layoutContainer = new LayoutContainer();
		BorderLayout borderLayout = new BorderLayout();
		layoutContainer.setLayout(borderLayout);
		
		BorderLayoutData topPanelData = new BorderLayoutData(LayoutRegion.NORTH, 200);  
		topPanel = new TopPanel(this);		
		layoutContainer.add(topPanel, topPanelData);
		
		BorderLayoutData leftPanelData = new BorderLayoutData(LayoutRegion.WEST, 100);  
		leftPanel = new LeftPanel(this);
		layoutContainer.add(leftPanel, leftPanelData);
		
		
		BorderLayoutData eastPanelData = new BorderLayoutData(LayoutRegion.EAST, 100);  
		ContentPanel eastPanel = new ContentPanel();
		layoutContainer.add(eastPanel, eastPanelData);
		
		BorderLayoutData southPanelData = new BorderLayoutData(LayoutRegion.SOUTH, 100);  
		ContentPanel southPanel = new ContentPanel();
		layoutContainer.add(southPanel, southPanelData);
		
		BorderLayoutData mainPanelData = new BorderLayoutData(LayoutRegion.CENTER);  
		mainPanelData.setMargins(new Margins(5,5,5,5));  
		mainPanelData.setSplit(true);
		mainPanelData.setCollapsible(true);
		mainPanel = new MainPanel(this);
		layoutContainer.add(mainPanel, mainPanelData);
		layoutContainer.setStyleAttribute("padding", "10px");  
	
	
		RootPanel.get().add(layoutContainer);
	}

}
