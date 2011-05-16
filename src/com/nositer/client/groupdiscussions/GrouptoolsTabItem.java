package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.left.NavigationItem;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.Resizable;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class GrouptoolsTabItem extends TabItem implements Resizable{

	private GroupDiscussionsContainer groupDiscussionsContainer;
	private ContentPanel contentPanel;
	private Label label;

	public GrouptoolsTabItem(GroupDiscussionsContainer groupDiscussionsContainer) {
		super();
		this.setText("Tools");
		this.groupDiscussionsContainer = groupDiscussionsContainer;
		init();
	}

	private void init() {
		setLayout(new FlowLayout(0));  
		contentPanel = new ContentPanel();

		contentPanel.setLayout(new FlowLayout(0));
		contentPanel.setHeaderVisible(false);
		label = new Label("Below are actions you can perform in regards to discussions for this group");

		contentPanel.add(label);
		NavigationItem createMessage = new NavigationItem("Create Post");
		contentPanel.add(createMessage);


		this.add(contentPanel);
		resize(0,0);
		addDefaultListeners();
	}


	
	private void addDefaultListeners() {
		addListener(Events.Select, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {				
				HistoryManager.addHistory(HistoryToken.DISCUSSIONSTOOLSGROUP + HistoryManager.SUBTOKENSEPARATOR + groupDiscussionsContainer.getGroupPlusView().getTagname());

			}
		});
	}

	@Override
	public void resize(int width, int height) {
		if (groupDiscussionsContainer.getGroupDiscussionLeftPanel() != null) {
			contentPanel.
			setWidth(groupDiscussionsContainer.
					getGroupDiscussionLeftPanel().getWidth());
		}
		GWTUtil.log("ABC");
	}

}
