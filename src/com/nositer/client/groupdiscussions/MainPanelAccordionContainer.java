package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.nositer.client.left.NavigationItem;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.messagebox.AlertMessageBox;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class MainPanelAccordionContainer extends ContentPanel implements Resizable {
	private ContentPanel tools;
	private NavigationItem addGroupmessageNavigationItem;
	private NavigationItem editGroupmessageNavigationItem;
	private GroupDiscussionsContainer groupDiscussionsContainer;
	private AccordionLayout accordionLayout;

	public ContentPanel getTools() {
		return tools;
	}

	public void setTools(ContentPanel tools) {
		this.tools = tools;
	}

	public NavigationItem getAddGroupmessageNavigationItem() {
		return addGroupmessageNavigationItem;
	}

	public void setAddGroupmessageNavigationItem(
			NavigationItem addGroupmessageNavigationItem) {
		this.addGroupmessageNavigationItem = addGroupmessageNavigationItem;
	}

	public NavigationItem getEditGroupmessageNavigationItem() {
		return editGroupmessageNavigationItem;
	}

	public void setEditGroupmessageNavigationItem(
			NavigationItem editGroupmessageNavigationItem) {
		this.editGroupmessageNavigationItem = editGroupmessageNavigationItem;
	}

	public GroupDiscussionsContainer getGroupDiscussionsContainer() {
		return groupDiscussionsContainer;
	}

	public void setGroupDiscussionsContainer(
			GroupDiscussionsContainer groupDiscussionsContainer) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
	}

	public AccordionLayout getAccordionLayout() {
		return accordionLayout;
	}

	public void setAccordionLayout(AccordionLayout accordionLayout) {
		this.accordionLayout = accordionLayout;
	}

	public MainPanelAccordionContainer(GroupDiscussionsContainer groupDiscussionsContainer) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
		init();
	}

	private void init() {
		this.setHeaderVisible(false);
		accordionLayout = new AccordionLayout();
		setLayout(accordionLayout);
		tools = new ContentPanel();
		tools.setHeading("Options");
		tools.collapse();
		addGroupmessageNavigationItem = new NavigationItem("Add message to this topic");
		tools.add(addGroupmessageNavigationItem);
		
		editGroupmessageNavigationItem = new NavigationItem("Edit this message");
		
		//editGroupmessageNavigationItem.disable();
		tools.add(editGroupmessageNavigationItem);
		this.add(tools);
		addDefaultListeners();
	}


	private void addDefaultListeners() {

		addGroupmessageNavigationItem.addListener(Events.OnClick, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {
				AddGroupmessage addGroupmessage = new AddGroupmessage(groupDiscussionsContainer);
				addGroupmessage.populateInsideMainPanel();
			}
		});

		editGroupmessageNavigationItem.addListener(Events.OnClick, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {
				AlertMessageBox.show("", "Hello", null);
			}
		});
		
		tools.addListener(Events.Collapse,  new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {
				groupDiscussionsContainer.getGroupDiscussionMainPanel().resize(0, 0);
			}
		});

		tools.addListener(Events.Expand,  new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {
				groupDiscussionsContainer.getGroupDiscussionMainPanel().resize(0, 0);
			}
		});
	}

	@Override
	public void resize(int width, int height) {
		int newWidth = MainPanel.getInstance().getWidth() - groupDiscussionsContainer.getGroupDiscussionLeftPanel().getWidth();
		setWidth(newWidth-10);
	}

}
