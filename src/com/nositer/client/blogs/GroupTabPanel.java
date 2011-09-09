package com.nositer.client.blogs;

import com.extjs.gxt.ui.client.widget.TabPanel;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.Resizable;


public class GroupTabPanel extends TabPanel implements Resizable {
	public enum TabItemType {
		VIEW, EDIT, SUBSCRIPTIONS, DISCUSSIONS, CHAT, SUBSCRIBER, UPLOAD, FILEMANAGER
	}
	private ViewGroupTabItem viewGroupTabItem;
	private EditGroupTabItem editGroupTabItem;
	private SubscriptionsGroupTabItem subscriptionsGroupTabItem;
	private DiscussionsGroupTabItem discussionsGroupTabItem;
	private ChatGroupTabItem chatGroupTabItem;
	private UploadGroupTabItem uploadGroupTabItem;
	private FileManagerGroupTabItem fileManagerGroupTabItem;
	
	private GroupPlusView groupPlusView;

	public UploadGroupTabItem getUploadGroupTabItem() {
		return uploadGroupTabItem;
	}

	public void setUploadGroupTabItem(UploadGroupTabItem uploadGroupTabItem) {
		this.uploadGroupTabItem = uploadGroupTabItem;
	}

	public ViewGroupTabItem getViewGroupTabItem() {
		return viewGroupTabItem;
	}
	
	public SubscriptionsGroupTabItem getSubscriptionsGroupTabItem() {
		return subscriptionsGroupTabItem;
	}

	public void setSubscriptionsGroupTabItem(
			SubscriptionsGroupTabItem subscriptionsGroupTabItem) {
		this.subscriptionsGroupTabItem = subscriptionsGroupTabItem;
	}
	
	
	
	

	public DiscussionsGroupTabItem getDiscussionsGroupTabItem() {
		return discussionsGroupTabItem;
	}

	public void setDiscussionsGroupTabItem(
			DiscussionsGroupTabItem discussionsGroupTabItem) {
		this.discussionsGroupTabItem = discussionsGroupTabItem;
	}

	public ChatGroupTabItem getChatGroupTabItem() {
		return chatGroupTabItem;
	}

	public void setChatGroupTabItem(ChatGroupTabItem chatGroupTabItem) {
		this.chatGroupTabItem = chatGroupTabItem;
	}

	public void setGroupPlusView(GroupPlusView groupPlusView) {
		this.groupPlusView = groupPlusView;
	}

	public void setViewGroupTabItem(ViewGroupTabItem viewGroupTabItem) {
		this.viewGroupTabItem = viewGroupTabItem;
	}
	
	public EditGroupTabItem getEditGroupTabItem() {
		return editGroupTabItem;
	}

	public void setEditGroupTabItem(EditGroupTabItem editGroupTabItem) {
		this.editGroupTabItem = editGroupTabItem;
	}

	public GroupPlusView getGroupPlusView() {
		return groupPlusView;
	}

	public void setGroup(GroupPlusView groupPlusView) {
		this.groupPlusView = groupPlusView;
	}

	public GroupTabPanel(GroupPlusView groupPlusView) {
		super();
		this.groupPlusView = groupPlusView;
		init();
	}

	public void init() {
		setTabPosition(TabPosition.BOTTOM);
		viewGroupTabItem = new ViewGroupTabItem(groupPlusView, this);
		add(viewGroupTabItem);
		subscriptionsGroupTabItem = new SubscriptionsGroupTabItem(groupPlusView, this);
		add(subscriptionsGroupTabItem);
		discussionsGroupTabItem = new DiscussionsGroupTabItem(groupPlusView, this);
		add(discussionsGroupTabItem);
		chatGroupTabItem = new ChatGroupTabItem(groupPlusView, this);
		add(chatGroupTabItem);
		if (Blogs.isGroupIOwn(groupPlusView)) {
			editGroupTabItem = new EditGroupTabItem(groupPlusView, this);
			uploadGroupTabItem = new UploadGroupTabItem(groupPlusView, this);
			fileManagerGroupTabItem = new FileManagerGroupTabItem(groupPlusView, this);
			add(editGroupTabItem);
			add(uploadGroupTabItem);
			add(fileManagerGroupTabItem);
		}
		resize(0,0);
	}

	@Override
	public void resize(int width, int height) {
		setSize(MainPanel.getInstance().getWidth()-3, 
				MainPanel.getInstance().getHeight()-29);	
	}

	public void show(TabItemType tabItemType) {	
		if (tabItemType.equals(TabItemType.VIEW)) {
			setSelection(viewGroupTabItem);
		} else if (tabItemType.equals(TabItemType.EDIT)) {
			setSelection(editGroupTabItem);
		} else if (tabItemType.equals(TabItemType.SUBSCRIPTIONS)) {
			setSelection(subscriptionsGroupTabItem);
		} else if (tabItemType.equals(TabItemType.UPLOAD)) {
			setSelection(uploadGroupTabItem);
		} else if (tabItemType.equals(TabItemType.FILEMANAGER)) {
			setSelection(fileManagerGroupTabItem);
		} else if (tabItemType.equals(TabItemType.DISCUSSIONS)) {
			setSelection(discussionsGroupTabItem);
		} else if (tabItemType.equals(TabItemType.CHAT)) {
			setSelection(chatGroupTabItem);
		} 
	}
}
