package com.nositer.client.left;

import java.util.Iterator;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.util.GWTUtil;


public class LeftPanel extends ContentPanel {
	private static LeftPanel instance;
	private BorderLayoutData leftLayoutData;
	private NavigationTree navigationTree;
	private AccordionLayout accordionLayout;
	private ContentPanel profile;
	private ContentPanel groups;
	private ContentPanel files;
	private ContentPanel iwantto;
	private ContentPanel blog;
	private ContentPanel chat;
	private NavigationItem viewProfileNavigationItem;
	private NavigationItem editBasicProfileNavigationItem;
	private NavigationItem editAboutMeNavigationItem;
	private NavigationItem groupsNavigationItem;
	private NavigationItem createGroupNavigationItem;
	private NavigationItem searchForGroupsNavigationItem;
	private NavigationItem iwanttosNavigationItem;
	private NavigationItem createIwantToNavigationItem;
	private NavigationItem changePasswordNavigationItem;
	private NavigationItem uploadFilesNavigationItem;
	private NavigationItem manageFilesNavigationItem;


	public NavigationItem getSearchForGroupsNavigationItem() {
		return searchForGroupsNavigationItem;
	}


	public void setSearchForGroupsNavigationItem(
			NavigationItem searchForGroupsNavigationItem) {
		this.searchForGroupsNavigationItem = searchForGroupsNavigationItem;
	}


	public ContentPanel getIwantto() {
		return iwantto;
	}


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


	public ContentPanel getFiles() {
		return files;
	}


	public void setFiles(ContentPanel files) {
		this.files = files;
	}


	

	public ContentPanel getIWantto() {
		return iwantto;
	}


	public void setIwantto(ContentPanel iwantto) {
		this.iwantto = iwantto;
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


	public NavigationItem getGroupsNavigationItem() {
		return groupsNavigationItem;
	}


	public void setGroupsNavigationItem(NavigationItem groupsNavigationItem) {
		this.groupsNavigationItem = groupsNavigationItem;
	}


	public NavigationItem getCreateGroupNavigationItem() {
		return createGroupNavigationItem;
	}


	public void setCreateGroupNavigationItem(
			NavigationItem createGroupNavigationItem) {
		this.createGroupNavigationItem = createGroupNavigationItem;
	}


	public NavigationItem getIwanttosNavigationItem() {
		return iwanttosNavigationItem;
	}


	public void setIwanttosNavigationItem(NavigationItem iwanttosNavigationItem) {
		this.iwanttosNavigationItem = iwanttosNavigationItem;
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


	public NavigationItem getUploadFilesNavigationItem() {
		return uploadFilesNavigationItem;
	}


	public void setUploadFilesNavigationItem(
			NavigationItem uploadFilesNavigationItem) {
		this.uploadFilesNavigationItem = uploadFilesNavigationItem;
	}


	public NavigationItem getManageFilesNavigationItem() {
		return manageFilesNavigationItem;
	}


	public void setManageFilesNavigationItem(
			NavigationItem manageFilesNavigationItem) {
		this.manageFilesNavigationItem = manageFilesNavigationItem;
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
		viewProfileNavigationItem = addNavigationItem("View Profile", profile, HistoryToken.MYPROFILE);
		editBasicProfileNavigationItem = addNavigationItem("Edit Basic Profile", profile, HistoryToken.EDITBASICPROFILE);
		changePasswordNavigationItem = addNavigationItem("Change Password", profile, HistoryToken.CHANGEPASSWORD);
		editAboutMeNavigationItem = addNavigationItem("Edit About Me", profile, HistoryToken.EDITABOUTME);
		
		// Files
		files = createNavigationContentPanel("Files", this);
		uploadFilesNavigationItem = addNavigationItem("Upload Files", files, HistoryToken.UPLOADFILES);
		manageFilesNavigationItem = addNavigationItem("Manage Files", files, HistoryToken.MANAGEFILES);		
		
		// Groups
		groups = createNavigationContentPanel("Groups", this);	
		groupsNavigationItem = addNavigationItem("View Groups", groups, HistoryToken.GROUPS);
		createGroupNavigationItem = addNavigationItem("Create Group", groups, HistoryToken.CREATEGROUP);
		searchForGroupsNavigationItem = addNavigationItem("Search", groups, HistoryToken.SEARCHFORGROUPS);
		
		// I want to ...
		iwantto = createNavigationContentPanel("I want to ...", this);	
		iwanttosNavigationItem = addNavigationItem("I want to's ...", iwantto, HistoryToken.IWANTTOS);
		createIwantToNavigationItem = addNavigationItem("Create I want to...", iwantto, HistoryToken.CREATEIWANTTO);
		
		
		// blog
		blog = createNavigationContentPanel("Blog", this);	
		
		// chat
		chat = createNavigationContentPanel("Chat", this);	
		
		collapseAccordion();
		this.layout();
		//addListeners();
	}
	
	public NavigationItem addNavigationItem(String str, ContentPanel contentPanel, HistoryToken historyToken) {
		NavigationItem retval = navigationTree.createNavigationItem(str);
		contentPanel.add(retval);
		HistoryManager.addHistoryOnClick(retval, historyToken.toString());
		return retval;
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

	/*
	public void addListeners() {		
		HistoryManager.addHistoryOnClick(viewProfileNavigationItem, HistoryToken.MYPROFILE.toString());
		HistoryManager.addHistoryOnClick(editBasicProfileNavigationItem, HistoryToken.EDITBASICPROFILE.toString());
		HistoryManager.addHistoryOnClick(changePasswordNavigationItem, HistoryToken.CHANGEPASSWORD.toString());
		HistoryManager.addHistoryOnClick(editAboutMeNavigationItem, HistoryToken.EDITABOUTME.toString());
		HistoryManager.addHistoryOnClick(uploadFilesNavigationItem, HistoryToken.UPLOADFILES.toString());
		HistoryManager.addHistoryOnClick(manageFilesNavigationItem, HistoryToken.MANAGEFILES.toString());
		HistoryManager.addHistoryOnClick(createGroupNavigationItem, HistoryToken.CREATEGROUP.toString());
		HistoryManager.addHistoryOnClick(groupsNavigationItem, HistoryToken.GROUPS.toString());
		HistoryManager.addHistoryOnClick(createIwantToNavigationItem, HistoryToken.CREATEIWANTTO.toString());
		HistoryManager.addHistoryOnClick(iwanttosNavigationItem, HistoryToken.IWANTTOS.toString());
	}
	*/
}
