package com.nositer.client;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.nositer.client.left.LeftPanel;
import com.nositer.client.main.MainPanel;
import com.nositer.client.top.TopPanel;


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
		init();
	}

	public void init() {
		Viewport viewport = new Viewport();
		viewport.setLayout(new BorderLayout());
		initLayoutContainer();		
		BorderLayoutData borderLayoutData = new BorderLayoutData(LayoutRegion.CENTER);
		viewport.add(layoutContainer, borderLayoutData);
		borderLayoutData.setMargins(new Margins(1));
		RootPanel.get().add(viewport);
	}



	public void initLayoutContainer() {
		layoutContainer = new LayoutContainer();
	
		BorderLayout borderLayout = new BorderLayout();
		layoutContainer.setLayout(borderLayout);
		BorderLayoutData topLayoutData = new BorderLayoutData(LayoutRegion.NORTH);  
		topPanel = new TopPanel(this, topLayoutData);	
		BorderLayoutData leftLayoutData = new BorderLayoutData(LayoutRegion.WEST);
		leftPanel = new LeftPanel(this, leftLayoutData);
		BorderLayoutData mainLayoutData = new BorderLayoutData(LayoutRegion.CENTER);  
		mainPanel = new MainPanel(this, mainLayoutData);
		
		layoutContainer.add(topPanel, topLayoutData);
		layoutContainer.add(leftPanel, leftLayoutData);
		layoutContainer.add(mainPanel, mainLayoutData);	
			
	}
}

