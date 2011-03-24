package com.nositer.client.groups;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.nositer.client.createoreditgroup.CreateOrEditGroup;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.UserHasGroupView;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.widget.Resizable;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class EditGroupTabItem extends TabItem implements Resizable {

	private CreateOrEditGroup createOrEditGroup;
	private UserHasGroupView userHasGroupView;

	public EditGroupTabItem(UserHasGroupView userHasGroupView) {
		super("Edit");
		this.userHasGroupView = userHasGroupView;
		init();
	}

	public void init() {
		createOrEditGroup = new CreateOrEditGroup(false) {
			@Override
			public int getHeightOffset() {
				return 300;
			};
		};		
		createOrEditGroup.populate(userHasGroupView);
		add(createOrEditGroup);
		addDefaultListeners();
	}

	
	private void addDefaultListeners() {
		addListener(Events.Select, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {
				HistoryManager.addHistory(HistoryToken.EDITGROUP + HistoryManager.SUBTOKENSEPARATOR + userHasGroupView.getTagname());
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