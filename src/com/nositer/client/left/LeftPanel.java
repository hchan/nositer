package com.nositer.client.left;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;

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

	




}
