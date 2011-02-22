package com.nositer.client.left;

import java.util.Iterator;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;


public class LeftPanel extends ContentPanel {
	private static LeftPanel instance;
	private BorderLayoutData leftLayoutData;
	private NavigationTree navigationTree;
	private AccordionLayout accordionLayout;
	private ContentPanel profile;
	private ContentPanel groups;
	private NavigationItem viewProfileNavigationItem;
	private NavigationItem editBasicProfileNavigationItem;
	private NavigationItem editAboutMeNavigationItem;
	private NavigationItem myGroups;
	private NavigationItem manageGroups;
	private NavigationItem createGroup;
	private NavigationItem myIwantTos;
	private ContentPanel iWantTo;
	private NavigationItem manageIwantTos;
	private NavigationItem changePasswordNavigationItem;
	private ContentPanel images;
	private NavigationItem uploadImagesNavigationItem;
	private NavigationItem manageImagesNavigationItem;
	
	public NavigationItem getCreateGroup() {
		return createGroup;
	}

	public void setCreateGroup(NavigationItem createGroup) {
		this.createGroup = createGroup;
	}

	public NavigationItem getMyGroups() {
		return myGroups;
	}

	public void setMyGroups(NavigationItem myGroups) {
		this.myGroups = myGroups;
	}

	public NavigationItem getManageGroups() {
		return manageGroups;
	}

	public void setManageGroups(NavigationItem manageGroups) {
		this.manageGroups = manageGroups;
	}

	public NavigationItem getMyIwantTos() {
		return myIwantTos;
	}

	public void setMyIwantTos(NavigationItem myIwantTos) {
		this.myIwantTos = myIwantTos;
	}

	public ContentPanel getiWantTo() {
		return iWantTo;
	}

	public void setiWantTo(ContentPanel iWantTo) {
		this.iWantTo = iWantTo;
	}

	public NavigationItem getManageIwantTos() {
		return manageIwantTos;
	}

	public void setManageIwantTos(NavigationItem manageIwantTos) {
		this.manageIwantTos = manageIwantTos;
	}

	public ContentPanel getImages() {
		return images;
	}

	public void setImages(ContentPanel images) {
		this.images = images;
	}

	public NavigationItem getUploadImagesNavigationItem() {
		return uploadImagesNavigationItem;
	}

	public void setUploadImagesNavigationItem(
			NavigationItem uploadImagesNavigationItem) {
		this.uploadImagesNavigationItem = uploadImagesNavigationItem;
	}

	public NavigationItem getManageImagesNavigationItem() {
		return manageImagesNavigationItem;
	}

	public void setManageImagesNavigationItem(
			NavigationItem manageImagesNavigationItem) {
		this.manageImagesNavigationItem = manageImagesNavigationItem;
	}

	public NavigationItem getEditAboutMeNavigationItem() {
		return editAboutMeNavigationItem;
	}

	public void setEditAboutMeNavigationItem(
			NavigationItem editAboutMeNavigationItem) {
		this.editAboutMeNavigationItem = editAboutMeNavigationItem;
	}

	public NavigationItem getChangePasswordNavigationItem() {
		return changePasswordNavigationItem;
	}

	public void setChangePasswordNavigationItem(
			NavigationItem changePasswordNavigationItem) {
		this.changePasswordNavigationItem = changePasswordNavigationItem;
	}

	public NavigationItem getViewProfileNavigationItem() {
		return viewProfileNavigationItem;
	}

	public void setViewProfileNavigationItem(
			NavigationItem viewProfileNavigationItem) {
		this.viewProfileNavigationItem = viewProfileNavigationItem;
	}

	public NavigationItem getEditBasicProfileNavigationItem() {
		return editBasicProfileNavigationItem;
	}

	public void setEditBasicProfileNavigationItem(
			NavigationItem editProfileNavigationItem) {
		this.editBasicProfileNavigationItem = editProfileNavigationItem;
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
		profile = createNavigationContentPanel("Profile", this);	
		viewProfileNavigationItem = navigationTree.createNavigationItem("View Profile");
		editBasicProfileNavigationItem = navigationTree.createNavigationItem("Edit Basic Profile");
		changePasswordNavigationItem = navigationTree.createNavigationItem("Change Password");
		editAboutMeNavigationItem = navigationTree.createNavigationItem("Edit About Me");
		profile.add(viewProfileNavigationItem);
		profile.add(editBasicProfileNavigationItem);
		profile.add(changePasswordNavigationItem);
		profile.add(editAboutMeNavigationItem);
		
		
		// Images
		images = createNavigationContentPanel("Images", this);
		uploadImagesNavigationItem = navigationTree.createNavigationItem("Upload Images");
		manageImagesNavigationItem = navigationTree.createNavigationItem("Manage Images");
		images.add(uploadImagesNavigationItem);
		images.add(manageImagesNavigationItem);
		
		// Groups
		groups = createNavigationContentPanel("Groups", this);	
		myGroups = navigationTree.createNavigationItem("My Groups");
		manageGroups = navigationTree.createNavigationItem("Manage Groups");
		createGroup = navigationTree.createNavigationItem("Create Group");
		groups.add(myGroups);
		groups.add(manageGroups);
		groups.add(createGroup);

		// I want to ...
		iWantTo = createNavigationContentPanel("I want to ...", this);	
		myIwantTos = navigationTree.createNavigationItem("My I want to's ...");
		manageIwantTos = navigationTree.createNavigationItem("Manage I want to's ...");
		iWantTo.add(myIwantTos);
		iWantTo.add(manageIwantTos);
		
		
		
		collapseAccordion();
		this.layout();
		addListeners();
	}
	
	public ContentPanel createNavigationContentPanel(String heading, LayoutContainer container) {
		ContentPanel retval = new ContentPanel();
		retval.setStyleName("navigationPanel");
		retval.setHeading(heading);
		container.add(retval);
		return retval;
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
		HistoryManager.addHistoryOnClick(viewProfileNavigationItem, HistoryToken.VIEWPROFILE.toString());
		HistoryManager.addHistoryOnClick(editBasicProfileNavigationItem, HistoryToken.EDITBASICPROFILE.toString());
		HistoryManager.addHistoryOnClick(changePasswordNavigationItem, HistoryToken.CHANGEPASSWORD.toString());
		HistoryManager.addHistoryOnClick(editAboutMeNavigationItem, HistoryToken.EDITABOUTME.toString());
		HistoryManager.addHistoryOnClick(uploadImagesNavigationItem, HistoryToken.UPLOADIMAGES.toString());
		HistoryManager.addHistoryOnClick(manageImagesNavigationItem, HistoryToken.MANAGEIMAGES.toString());
		HistoryManager.addHistoryOnClick(createGroup, HistoryToken.CREATEGROUP.toString());
		HistoryManager.addHistoryOnClick(myGroups, HistoryToken.MYGROUPS.toString());
	}
}
