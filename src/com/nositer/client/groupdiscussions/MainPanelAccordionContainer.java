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
	private NavigationItem createGrouptopicNavigationItem;
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
		createGrouptopicNavigationItem = new NavigationItem("Change me");
		tools.add(createGrouptopicNavigationItem);
		this.add(tools);
		addDefaultListeners();
	}
	
	
	private void addDefaultListeners() {
		addListener(Events.Select, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {				
				//HistoryManager.addHistory(HistoryToken.DISCUSSIONSTOOLSGROUP + HistoryManager.SUBTOKENSEPARATOR + groupDiscussionsContainer.getGroupPlusView().getTagname());

			}
		});
		
		createGrouptopicNavigationItem.addListener(Events.OnClick, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {
				CreateGrouptopic createGrouptopic = new CreateGrouptopic(groupDiscussionsContainer);
				createGrouptopic.populateMainPanel();
			}
		});
	}

	@Override
	public void resize(int width, int height) {
		int newWidth = MainPanel.getInstance().getWidth() - groupDiscussionsContainer.getGroupDiscussionLeftPanel().getWidth();
		setWidth(newWidth-10);

	}

}
