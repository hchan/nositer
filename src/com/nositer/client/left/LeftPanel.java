package com.nositer.client.left;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.History;
import com.nositer.client.HistoryTokenHelper;
import com.nositer.client.main.MainPanel;
import com.nositer.client.top.TopPanel;
import com.nositer.client.viewprofile.ViewProfile;
import com.nositer.client.viewprofile.ViewProfileTabPanel;

@SuppressWarnings({"rawtypes", "unchecked"})
public class LeftPanel extends ContentPanel {
	private static LeftPanel instance;
	private BorderLayoutData leftLayoutData;
	private NavigationTree navigationTree;
	private AccordionLayout accordionLayout;
	private ContentPanel profile;
	private ContentPanel groups;
	private NavigationItem viewProfileNavigationItem;
	private NavigationItem editProfileNavigationItem;
	
	public NavigationItem getViewProfileNavigationItem() {
		return viewProfileNavigationItem;
	}

	public void setViewProfileNavigationItem(
			NavigationItem viewProfileNavigationItem) {
		this.viewProfileNavigationItem = viewProfileNavigationItem;
	}

	public NavigationItem getEditProfileNavigationItem() {
		return editProfileNavigationItem;
	}

	public void setEditProfileNavigationItem(
			NavigationItem editProfileNavigationItem) {
		this.editProfileNavigationItem = editProfileNavigationItem;
	}

	public AccordionLayout getAccordionLayout() {
		return accordionLayout;
	}

	public void setAccordionLayout(AccordionLayout accordionLayout) {
		this.accordionLayout = accordionLayout;
	}

	public ContentPanel getProfile() {
		return profile;
	}

	public void setProfile(ContentPanel profile) {
		this.profile = profile;
	}

	public ContentPanel getGroups() {
		return groups;
	}

	public void setGroups(ContentPanel groups) {
		this.groups = groups;
	}

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

	
	public void init() {
		navigationTree = new NavigationTree();
		leftLayoutData.setSize(150);
		leftLayoutData.setCollapsible(true);
		//this.setHeaderVisible(false);
		setHeading("Navigation");
		accordionLayout = new AccordionLayout();
		accordionLayout.setFill(false);

		setLayout(accordionLayout);
		profile = new ContentPanel();
		profile.setStyleName("navigationPanel");
		profile.setHeading("Profile");	
		viewProfileNavigationItem = navigationTree.createNavigationItem("View Profile");
		viewProfileNavigationItem.addListener(Events.OnClick, new Listener() {
			@Override
			public void handleEvent(BaseEvent be) {
				doViewProfile();
				History.newItem(HistoryTokenHelper.VIEWPROFILE);
			}
		});
		
		profile.add(viewProfileNavigationItem);
		editProfileNavigationItem = navigationTree.createNavigationItem("Edit Profile");
		profile.add(editProfileNavigationItem);


		this.add(profile);

		groups = new ContentPanel();
		groups.setHeading("Groups");	
		NavigationItem group1 = navigationTree.createNavigationItem("Group1");
		groups.add(group1);
		this.add(groups);


		profile.collapse();
		profile.setStyleName("x-panel-collapsed");
		
		
		this.layout();
	}

	public void doViewProfile() {
		ViewProfileTabPanel viewProfileTabPanel = new ViewProfileTabPanel();				
		setMainPanel(viewProfileTabPanel);
	}

	private void setMainPanel(Component component) {
		MainPanel.getInstance().removeAll();
		MainPanel.getInstance().add(component);
		MainPanel.getInstance().layout();
	}


}
