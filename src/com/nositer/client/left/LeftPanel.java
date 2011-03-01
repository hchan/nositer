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
	private ContentPanel images;
	private ContentPanel iWantTo;
	private ContentPanel blog;
	private ContentPanel chat;
	private NavigationItem viewProfileNavigationItem;
	private NavigationItem editBasicProfileNavigationItem;
	private NavigationItem editAboutMeNavigationItem;
	private NavigationItem myGroupsNavigationItem;
	private NavigationItem manageGroupsNavigationItem;
	private NavigationItem createGroupNavigationItem;
	private NavigationItem myIwantTosNavigationItem;
	private NavigationItem createIwantToNavigationItem;
	private NavigationItem changePasswordNavigationItem;
	private NavigationItem uploadImagesNavigationItem;
	private NavigationItem manageImagesNavigationItem;
	
	


	public static LeftPanel getInstance() {
		return instance;
	}


	public static void setInstance(LeftPanel instance) {
		LeftPanel.instance = instance;
	}


	public BorderLayoutData getLeftLayoutData() {
		return leftLayoutData;
	}


	public void setLeftLayoutData(BorderLayoutData leftLayoutData) {
		this.leftLayoutData = leftLayoutData;
	}


	public NavigationTree getNavigationTree() {
		return navigationTree;
	}


	public void setNavigationTree(NavigationTree navigationTree) {
		this.navigationTree = navigationTree;
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


	public ContentPanel getImages() {
		return images;
	}


	public void setImages(ContentPanel images) {
		this.images = images;
	}


	

	public ContentPanel getiWantTo() {
		return iWantTo;
	}


	public void setiWantTo(ContentPanel iWantTo) {
		this.iWantTo = iWantTo;
	}


	public ContentPanel getBlog() {
		return blog;
	}


	public void setBlog(ContentPanel blog) {
		this.blog = blog;
	}


	public ContentPanel getChat() {
		return chat;
	}


	public void setChat(ContentPanel chat) {
		this.chat = chat;
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
			NavigationItem editBasicProfileNavigationItem) {
		this.editBasicProfileNavigationItem = editBasicProfileNavigationItem;
	}


	public NavigationItem getEditAboutMeNavigationItem() {
		return editAboutMeNavigationItem;
	}


	public void setEditAboutMeNavigationItem(
			NavigationItem editAboutMeNavigationItem) {
		this.editAboutMeNavigationItem = editAboutMeNavigationItem;
	}


	public NavigationItem getMyGroupsNavigationItem() {
		return myGroupsNavigationItem;
	}


	public void setMyGroupsNavigationItem(NavigationItem myGroupsNavigationItem) {
		this.myGroupsNavigationItem = myGroupsNavigationItem;
	}


	public NavigationItem getManageGroupsNavigationItem() {
		return manageGroupsNavigationItem;
	}


	public void setManageGroupsNavigationItem(
			NavigationItem manageGroupsNavigationItem) {
		this.manageGroupsNavigationItem = manageGroupsNavigationItem;
	}


	public NavigationItem getCreateGroupNavigationItem() {
		return createGroupNavigationItem;
	}


	public void setCreateGroupNavigationItem(
			NavigationItem createGroupNavigationItem) {
		this.createGroupNavigationItem = createGroupNavigationItem;
	}


	public NavigationItem getMyIwantTosNavigationItem() {
		return myIwantTosNavigationItem;
	}


	public void setMyIwantTosNavigationItem(NavigationItem myIwantTosNavigationItem) {
		this.myIwantTosNavigationItem = myIwantTosNavigationItem;
	}


	public NavigationItem getCreateIwantToNavigationItem() {
		return createIwantToNavigationItem;
	}


	public void setCreateIwantToNavigationItem(
			NavigationItem createIwantToNavigationItem) {
		this.createIwantToNavigationItem = createIwantToNavigationItem;
	}


	public NavigationItem getChangePasswordNavigationItem() {
		return changePasswordNavigationItem;
	}


	public void setChangePasswordNavigationItem(
			NavigationItem changePasswordNavigationItem) {
		this.changePasswordNavigationItem = changePasswordNavigationItem;
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
		myGroupsNavigationItem = navigationTree.createNavigationItem("My Groups");
		manageGroupsNavigationItem = navigationTree.createNavigationItem("Manage Groups");
		createGroupNavigationItem = navigationTree.createNavigationItem("Create Group");
		groups.add(myGroupsNavigationItem);
		groups.add(manageGroupsNavigationItem);
		groups.add(createGroupNavigationItem);

		// I want to ...
		iWantTo = createNavigationContentPanel("I want to ...", this);	
		myIwantTosNavigationItem = navigationTree.createNavigationItem("My I want to's ...");
		createIwantToNavigationItem = navigationTree.createNavigationItem("Create I want to...");
		iWantTo.add(myIwantTosNavigationItem);
		iWantTo.add(createIwantToNavigationItem);
		
		// blog
		blog = createNavigationContentPanel("Blog", this);	
		
		// chat
		chat = createNavigationContentPanel("Chat", this);	
		
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
		HistoryManager.addHistoryOnClick(createGroupNavigationItem, HistoryToken.CREATEGROUP.toString());
		HistoryManager.addHistoryOnClick(myGroupsNavigationItem, HistoryToken.MYGROUPS.toString());
		HistoryManager.addHistoryOnClick(createIwantToNavigationItem, HistoryToken.CREATEIWANTTO.toString());
		HistoryManager.addHistoryOnClick(myIwantTosNavigationItem, HistoryToken.MYIWANTTOS.toString());
	}
}
