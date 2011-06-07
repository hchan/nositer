package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.nositer.client.left.NavigationItem;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.Resizable;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class MainPanelAccordionContainer extends ContentPanel implements Resizable {
	private ContentPanel tools;
	private NavigationItem addGroupmessageNavigationItem;
	private GroupDiscussionsContainer groupDiscussionsContainer;

	public MainPanelAccordionContainer(GroupDiscussionsContainer groupDiscussionsContainer) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
		init();
	}

	private void init() {
		this.setHeaderVisible(false);
		setLayout(new AccordionLayout());
		tools = new ContentPanel();
		tools.setHeading("Options");
		tools.collapse();
		addGroupmessageNavigationItem = new NavigationItem("Add message to this topic");
		tools.add(addGroupmessageNavigationItem);
		this.add(tools);
		addDefaultListeners();
	}
	
	
	private void addDefaultListeners() {
		
		addGroupmessageNavigationItem.addListener(Events.OnClick, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {
				AddGroupmessage addGroupmessage = new AddGroupmessage(groupDiscussionsContainer);
				addGroupmessage.populateGroupmessagePanel();
			}
		});
	}

	@Override
	public void resize(int width, int height) {
		int newWidth = MainPanel.getInstance().getWidth() - groupDiscussionsContainer.getGroupDiscussionLeftPanel().getWidth();
		setWidth(newWidth-10);
	}

}
