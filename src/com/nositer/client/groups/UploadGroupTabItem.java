package com.nositer.client.groups;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.nositer.client.Scope;
import com.nositer.client.Scope.Type;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.uploadfiles.UploadFiles;
import com.nositer.client.widget.Resizable;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class UploadGroupTabItem extends TabItem implements Resizable {

	private UploadFiles uploadFiles;
	private GroupPlusView groupPlusView;
	private GroupTabPanel groupTabPanel;

	public UploadFiles getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(UploadFiles uploadFiles) {
		this.uploadFiles = uploadFiles;
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

	public UploadGroupTabItem(GroupPlusView groupPlusView, GroupTabPanel groupTabPanel) {
		super("Upload");
		this.groupPlusView = groupPlusView;
		this.groupTabPanel = groupTabPanel;
		init();
	}

	public void init() {
		//initSWF();
		addDefaultListeners();
	}

	public void initSWF() {
		if (uploadFiles == null) {
			Scope scope = new Scope(Type.group);
			scope.setGroupPlusView(groupPlusView);
			uploadFiles = new UploadFiles(scope);
			add(uploadFiles);			
		}
	}


	private void addDefaultListeners() {
		addListener(Events.Select, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {					
				HistoryManager.addHistory(HistoryToken.UPLOADGROUP + HistoryManager.SUBTOKENSEPARATOR + groupPlusView.getTagname());				
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