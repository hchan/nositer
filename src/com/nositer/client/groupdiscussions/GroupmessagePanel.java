package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.nositer.client.dto.generated.GroupSubscriptionView;
import com.nositer.client.dto.generated.Groupmessage;
import com.nositer.client.dto.generated.Grouptopic;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.Resizable;

public class GroupmessagePanel extends ContentPanel implements Resizable {
	private GroupDiscussionsContainer groupDiscussionsContainer;
	private MainPanelAccordionContainer mainPanelAccordionContainer;
	private HtmlContainer htmlContainer;
	private Groupmessage groupmessage;
	private Label topicName;
	private Label messageInfo;
	private Label clickableUsername;
	private Grouptopic grouptopic;
	private GrouptopicToolBar grouptopicToolBar;
	
	public GroupDiscussionsContainer getGroupDiscussionsContainer() {
		return groupDiscussionsContainer;
	}

	public void setGroupDiscussionsContainer(
			GroupDiscussionsContainer groupDiscussionsContainer) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
	}

	public MainPanelAccordionContainer getMainPanelAccordionContainer() {
		return mainPanelAccordionContainer;
	}

	public void setMainPanelAccordionContainer(
			MainPanelAccordionContainer mainPanelAccordionContainer) {
		this.mainPanelAccordionContainer = mainPanelAccordionContainer;
	}

	public HtmlContainer getHtmlContainer() {
		return htmlContainer;
	}

	public void setHtmlContainer(HtmlContainer htmlContainer) {
		this.htmlContainer = htmlContainer;
	}

	public Groupmessage getGroupmessage() {
		return groupmessage;
	}

	public void setGroupmessage(Groupmessage groupmessage) {
		this.groupmessage = groupmessage;
	}

	public Label getTopicName() {
		return topicName;
	}

	public void setTopicName(Label topicName) {
		this.topicName = topicName;
	}

	public Label getMessageInfo() {
		return messageInfo;
	}

	public void setMessageInfo(Label messageInfo) {
		this.messageInfo = messageInfo;
	}

	public Label getClickableUsername() {
		return clickableUsername;
	}

	public void setClickableUsername(Label clickableUsername) {
		this.clickableUsername = clickableUsername;
	}

	public Grouptopic getGrouptopic() {
		return grouptopic;
	}

	public void setGrouptopic(Grouptopic grouptopic) {
		this.grouptopic = grouptopic;
	}

	public GrouptopicToolBar getGrouptopicToolBar() {
		return grouptopicToolBar;
	}

	public void setGrouptopicToolBar(GrouptopicToolBar grouptopicToolBar) {
		this.grouptopicToolBar = grouptopicToolBar;
	}

	public GroupmessagePanel(GroupDiscussionsContainer groupDiscussionsContainer, Groupmessage groupmessage) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
		this.groupmessage = groupmessage;

		init();
	}

	private void init() {
		this.setHeaderVisible(false);
		setBorders(false);
		setBodyBorder(false);
		FlowLayout flowLayout = new FlowLayout();
		this.setLayout(flowLayout);
		grouptopic = groupmessage.getGrouptopic();
		topicName = new Label(grouptopic.getName());
		topicName.setStyleName("formHeading");
		
		mainPanelAccordionContainer = new MainPanelAccordionContainer(groupDiscussionsContainer);
	
		add(topicName);
		add(new HtmlContainer()); // new line
		clickableUsername = new Label(groupmessage.getUser().getFirstname() + " " + groupmessage.getUser().getLastname());
		clickableUsername.setStyleName("clickableUsername");
		clickableUsername.addListener(Events.OnClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				HistoryManager.addHistory(HistoryToken.USER + HistoryManager.SUBTOKENSEPARATOR + groupmessage.getUser().getId());
			}
		});
		add(clickableUsername);
		messageInfo = new Label("message posted on " + groupmessage.getCreatedtime() + " by ");
		messageInfo.setStyleName("groupmessageInfo");


		add(messageInfo);
		add(clickableUsername);
		htmlContainer = new HtmlContainer(groupmessage.getDescription());
		add(htmlContainer, new MarginData(5, 0, 0, 0));

	}

	public void show() {
		groupDiscussionsContainer.getGroupDiscussionMainPanel().removeAll();
		groupDiscussionsContainer.getGroupDiscussionMainPanel().add(mainPanelAccordionContainer);
		groupDiscussionsContainer.getGroupDiscussionMainPanel().add(this, new MarginData(5, 5, 5, 5));

		grouptopicToolBar = 
			new GrouptopicToolBar(
					groupDiscussionsContainer, 
					grouptopic);
		groupDiscussionsContainer.getGroupDiscussionMainPanel().getBottomContainer().removeAll();

		groupDiscussionsContainer.getGroupDiscussionMainPanel().getBottomContainer().add(grouptopicToolBar);
		groupDiscussionsContainer.getGroupDiscussionMainPanel().getBottomComponent().show();

		groupDiscussionsContainer.getGroupDiscussionMainPanel().getBottomContainer().layout(true);

		groupDiscussionsContainer.getGroupDiscussionMainPanel().layout(true);
		groupDiscussionsContainer.getGroupDiscussionMainPanel().setHeight(MainPanel.getInstance().getHeight()
				-
				groupDiscussionsContainer.getGroupDiscussionMainPanel().getBottomContainer().getHeight() - 30);

		groupDiscussionsContainer.getGroupDiscussionMainPanel().resize(0, 0);
		//groupmessagePanel.setWidth(groupDiscussionsContainer.getGroupDiscussionMainPanel().getWidth() - 20);
	}


	@Override
	public void resize(int width, int height) {
		int newWidth = MainPanel.getInstance().getWidth() - groupDiscussionsContainer.getGroupDiscussionLeftPanel().getWidth();
		setWidth(newWidth - 20);

		setHeight(groupDiscussionsContainer.getGroupDiscussionMainPanel().getHeight() - 10);
		grouptopicToolBar.resize(0, 0);
	}
}
