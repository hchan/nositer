package com.nositer.client.left;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.nositer.client.main.MainPanel;
import com.nositer.client.main.ViewProfile;
import com.nositer.client.top.TopPanel;

@SuppressWarnings({"rawtypes", "unchecked"})
public class LeftPanel extends ContentPanel {
	private static LeftPanel instance;
	public static LeftPanel getInstance() {
		return instance;
	}

	public static void setInstance(LeftPanel instance) {
		LeftPanel.instance = instance;
	}

	public NavigationTree getNavigationTree() {
		return navigationTree;
	}

	public void setNavigationTree(NavigationTree navigationTree) {
		this.navigationTree = navigationTree;
	}

	private BorderLayoutData leftLayoutData;
	private NavigationTree navigationTree;

	public BorderLayoutData getLeftLayoutData() {
		return leftLayoutData;
	}

	public void setLeftLayoutData(BorderLayoutData leftLayoutData) {
		this.leftLayoutData = leftLayoutData;
	}


	public LeftPanel (BorderLayoutData leftLayoutData) {	
		this.leftLayoutData = leftLayoutData;
		init();
		instance = this;
	}

	
	private void init() {
		navigationTree = new NavigationTree();
		leftLayoutData.setSize(150);
		leftLayoutData.setCollapsible(true);
		//this.setHeaderVisible(false);
		setHeading("Navigation");
		AccordionLayout accordionLayout = new AccordionLayout();
		accordionLayout.setFill(false);

		setLayout(accordionLayout);
		ContentPanel profile = new ContentPanel();
		profile.setStyleName("navigationPanel");
		profile.setHeading("Profile");	
		NavigationItem viewProfile = navigationTree.createNavigationItem("View Profile");
		viewProfile.addListener(Events.OnClick, new Listener() {
			@Override
			public void handleEvent(BaseEvent be) {
				ViewProfile viewProfile = new ViewProfile();
				viewProfile.populate(TopPanel.getInstance().getUser());
				setMainPanel(viewProfile);
			}
		});
		
		profile.add(viewProfile);
		NavigationItem editProfile = navigationTree.createNavigationItem("Edit Profile");
		profile.add(editProfile);


		this.add(profile);

		ContentPanel groups = new ContentPanel();
		groups.setHeading("Groups");	
		NavigationItem group1 = navigationTree.createNavigationItem("Group1");
		groups.add(group1);
		this.add(groups);


		profile.collapse();
		profile.setStyleName("x-panel-collapsed");
		this.layout();
	}

	

	private void setMainPanel(ViewProfile viewProfile) {
		MainPanel.getInstance().removeAll();
		MainPanel.getInstance().add(viewProfile);
		MainPanel.getInstance().layout();
	}


}
