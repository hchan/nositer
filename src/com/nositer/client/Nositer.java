package com.nositer.client;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.BorderLayoutEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.left.LeftPanel;
import com.nositer.client.main.MainPanel;
import com.nositer.client.top.TopPanel;
import com.nositer.client.util.GWTUtil;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Nositer implements EntryPoint {
	private TopPanel topPanel;
	private MainPanel mainPanel;
	private LeftPanel leftPanel;
	private LayoutContainer layoutContainer;
	private static Nositer instance;
	private BorderLayout borderLayout;

	public static Nositer getInstance() {
		return instance;
	}
	public static void setInstance(Nositer instance) {
		Nositer.instance = instance;
	}
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
		GWTUtil.hide("loadingPleaseWait");
		Viewport viewport = new Viewport();
		viewport.setLayout(new BorderLayout());
		initLayoutContainer();		
		BorderLayoutData borderLayoutData = new BorderLayoutData(LayoutRegion.CENTER);
		viewport.add(layoutContainer, borderLayoutData);
		borderLayoutData.setMargins(new Margins(1));
		instance = this;
		RootPanel.get().add(viewport);		
		HistoryManager.addHistorySupport();
	}

	
	public void initLayoutContainer() {
		layoutContainer = new LayoutContainer();	
		borderLayout = new BorderLayout();
	
		layoutContainer.setLayout(borderLayout);
		BorderLayoutData topLayoutData = new BorderLayoutData(LayoutRegion.NORTH);  
		topPanel = new TopPanel(topLayoutData);	
		BorderLayoutData leftLayoutData = new BorderLayoutData(LayoutRegion.WEST);
		leftPanel = new LeftPanel(leftLayoutData);
		BorderLayoutData mainLayoutData = new BorderLayoutData(LayoutRegion.CENTER);  
		mainPanel = new MainPanel(mainLayoutData);		
		layoutContainer.add(topPanel, topLayoutData);
		layoutContainer.add(leftPanel, leftLayoutData);
		layoutContainer.add(mainPanel, mainLayoutData);		
		addListeners();
	}
	
	private void addListeners() {
		borderLayout.addListener(Events.Collapse, new Listener<BorderLayoutEvent>() {
			@Override
			public void handleEvent(BorderLayoutEvent borderLayoutEvent) {				
				//topPanel.collapse();				
				topPanel.hide();
				MainPanel.getInstance().resizeChildren(0, 0, MainPanel.getInstance());
			}}
		);
		
		borderLayout.addListener(Events.Expand, new Listener<BorderLayoutEvent>() {
			@Override
			public void handleEvent(BorderLayoutEvent borderLayoutEvent) {
				//topPanel.expand();
				topPanel.show();
				MainPanel.getInstance().resizeChildren(0, 0, MainPanel.getInstance());
			}}
		);
	}
}

