package com.nositer.client.groups;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.managefiles.GroupFolderSelector;
import com.nositer.client.managefiles.GroupManageFiles;
import com.nositer.client.widget.Resizable;

public class FileManagerGroupTabItem extends TabItem implements Resizable {

	private GroupManageFiles groupManageFiles;
	private GroupPlusView groupPlusView;

	public FileManagerGroupTabItem(GroupPlusView groupPlusView) {
		super("File Manager");
		this.groupPlusView = groupPlusView;
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


	private void addDefaultListeners() {
		addListener(Events.Select, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {					
				HistoryManager.addHistory(HistoryToken.MANAGEFILESGROUP + HistoryManager.SUBTOKENSEPARATOR + groupPlusView.getTagname());				
				resize(0,0);
			}
		});


	}

	@Override
	public void resize(int width, int height) {
		//contentPanel.setHeight(MainPanel.getInstance().getHeight()-57);
		//contentPanel.setWidth(MainPanel.getInstance().getWidth()-3);


	}

	
	
	
}