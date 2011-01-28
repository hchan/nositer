package com.nositer.client.left;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout.VBoxLayoutAlign;

public class LeftPanel extends ContentPanel {

	private BorderLayoutData leftLayoutData;
	private ArrayList<NavigationItem> navigationItems;

	public ArrayList<NavigationItem> getNavigationItems() {
		return navigationItems;
	}

	public void setNavigationItems(ArrayList<NavigationItem> navigationItems) {
		this.navigationItems = navigationItems;
	}

	public BorderLayoutData getLeftLayoutData() {
		return leftLayoutData;
	}

	public void setLeftLayoutData(BorderLayoutData leftLayoutData) {
		this.leftLayoutData = leftLayoutData;
	}


	public LeftPanel (BorderLayoutData leftLayoutData) {	
		this.leftLayoutData = leftLayoutData;
		init();
	}

	private void init() {
		navigationItems = new ArrayList<NavigationItem>();
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
		NavigationItem viewProfile = createNavigationItem("View Profile");
		profile.add(viewProfile);
		NavigationItem editProfile = createNavigationItem("Edit Profile");
		profile.add(editProfile);
		
		
		this.add(profile);
		
		ContentPanel groups = new ContentPanel();
		groups.setHeading("Groups");	
		NavigationItem group1 = createNavigationItem("Group1");
		groups.add(group1);
		this.add(groups);
		
		
		profile.collapse();
		profile.setStyleName("x-panel-collapsed");
		this.layout();
	}

	private NavigationItem createNavigationItem(String labelStr) {
		NavigationItem retval = new NavigationItem(labelStr);
		navigationItems.add(retval);
		return retval;
	}
	
	
	
	 
}
