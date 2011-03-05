package com.nositer.client.groups;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.nositer.client.creategroup.CreateGroup;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.widget.Resizable;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class EditGroupTabItem extends TabItem implements Resizable {

	private CreateGroup createGroup;
	private Group group;

	public EditGroupTabItem(Group group) {
		super("Edit");
		this.group = group;
		init();
	}

	public void init() {
		createGroup = new CreateGroup() {
			@Override
			public int getHeightOffset() {
				return 300;
			};
		};		
		add(createGroup);
		addDefaultListeners();
	}

	
	private void addDefaultListeners() {
		addListener(Events.Select, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {
				HistoryManager.addHistory(HistoryToken.EDITGROUP + HistoryManager.SUBTOKENSEPARATOR + group.getTagname());
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