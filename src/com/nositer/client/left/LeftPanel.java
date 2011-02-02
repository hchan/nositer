package com.nositer.client.left;

import java.util.Iterator;

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
	private NavigationItem myGroups;
	private NavigationItem manageGroups;
	private NavigationItem myIwantTos;
	private ContentPanel iWantTo;
	private NavigationItem manageIwantTos;
	
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
		setHeading("Navigation");
		accordionLayout = new AccordionLayout();
		accordionLayout.setFill(false);
		setLayout(accordionLayout);
		
		// Profile
		profile = new ContentPanel();
		profile.setStyleName("navigationPanel");
		profile.setHeading("Profile");	
		viewProfileNavigationItem = navigationTree.createNavigationItem("View Profile");
		editProfileNavigationItem = navigationTree.createNavigationItem("Edit Profile");
		profile.add(viewProfileNavigationItem);
		profile.add(editProfileNavigationItem);
		this.add(profile);

		// Groups
		groups = new ContentPanel();
		profile.setStyleName("navigationPanel");
		groups.setHeading("Groups");	
		myGroups = navigationTree.createNavigationItem("My Groups");
		manageGroups = navigationTree.createNavigationItem("Manage Groups");
		groups.add(myGroups);
		groups.add(manageGroups);
		this.add(groups);

		// I want to ...
		iWantTo = new ContentPanel();
		iWantTo.setStyleName("navigationPanel");
		iWantTo.setHeading("I want to ...");	
		myIwantTos = navigationTree.createNavigationItem("My I want to's ...");
		manageIwantTos = navigationTree.createNavigationItem("Manage I want to's ...");
		iWantTo.add(myIwantTos);
		iWantTo.add(manageIwantTos);
		this.add(iWantTo);
		
		
		collapseAccordion();
		this.layout();
		addListeners();
	}

	private void collapseAccordion() {		
		Iterator<Component> componentIterator = this.iterator();
		while (componentIterator.hasNext()) {
			Component component = componentIterator.next();
			ContentPanel contentPanel = (ContentPanel) component;
			contentPanel.collapse();
			contentPanel.setStyleName("x-panel-collapsed");
		}
	}

	public void addListeners() {
		viewProfileNavigationItem.addListener(Events.OnClick, new Listener() {
			@Override
			public void handleEvent(BaseEvent be) {
				doViewProfile();
				History.newItem(HistoryTokenHelper.VIEWPROFILE.toString());
			}
		});
		editProfileNavigationItem.addListener(Events.OnClick, new Listener() {
			@Override
			public void handleEvent(BaseEvent be) {
				doEditProfile();
				History.newItem(HistoryTokenHelper.VIEWPROFILE.toString());
			}
		});
	}

	public void doViewProfile() {
		ViewProfileTabPanel viewProfileTabPanel = new ViewProfileTabPanel();				
		setMainPanel(viewProfileTabPanel);
	}

	public void doEditProfile() {
	}
	
	private void setMainPanel(Component component) {
		MainPanel.getInstance().removeAll();
		MainPanel.getInstance().add(component);
		MainPanel.getInstance().layout();
	}


}
