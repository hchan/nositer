package com.nositer.client.blogs;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.managefiles.GroupManageFiles;
import com.nositer.client.widget.HistoryWidget;
import com.nositer.client.widget.Resizable;

public class FileManagerGroupTabItem extends TabItem implements Resizable, HistoryWidget {

	private GroupManageFiles groupManageFiles;
	private GroupPlusView groupPlusView;
	private GroupTabPanel groupTabPanel;

	public GroupManageFiles getGroupManageFiles() {
		return groupManageFiles;
	}
	public void setGroupManageFiles(GroupManageFiles groupManageFiles) {
		this.groupManageFiles = groupManageFiles;
	}
	public GroupPlusView getGroupPlusView() {
		return groupPlusView;
	}
	public void setGroupPlusView(GroupPlusView groupPlusView) {
		this.groupPlusView = groupPlusView;
	}
	public GroupTabPanel getGroupTabPanel() {
		return groupTabPanel;
	}
	public void setGroupTabPanel(GroupTabPanel groupTabPanel) {
		this.groupTabPanel = groupTabPanel;
	}
	public FileManagerGroupTabItem(GroupPlusView groupPlusView, GroupTabPanel groupTabPanel) {
		super("File Manager");
		this.groupPlusView = groupPlusView;
		this.groupTabPanel = groupTabPanel;
		init();
	}
	public void init() {
		initFileManager();
		addDefaultListeners();
	}

	public void initFileManager() {
		if (groupManageFiles == null) {
			groupManageFiles = new GroupManageFiles(groupPlusView);
			add(groupManageFiles);			
		}
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addDefaultListeners() {
		addListener(Events.Select, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {					
				HistoryManager.addHistory(getNewHistory());				
				resize(0,0);
			}
		});


	}

	@Override
	public void resize(int width, int height) {
		//contentPanel.setHeight(MainPanel.getInstance().getHeight()-57);
		//contentPanel.setWidth(MainPanel.getInstance().getWidth()-3);


	}
	@Override
	public String getNewHistory() {
		return HistoryToken.MANAGEFILESGROUP + HistoryManager.SUBTOKENSEPARATOR + groupPlusView.getTagname();
	}

	
	
	
}