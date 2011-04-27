package com.nositer.client.groups;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.managefiles.GroupFileManager;
import com.nositer.client.widget.Resizable;

public class FileManagerGroupTabItem extends TabItem implements Resizable {

	private GroupFileManager groupFileManager;
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
		if (groupFileManager == null) {
			groupFileManager = new GroupFileManager(groupPlusView);
			add(groupFileManager);			
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