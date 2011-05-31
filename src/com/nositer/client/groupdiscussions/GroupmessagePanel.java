package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.Layout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.nositer.client.dto.generated.Groupmessage;
import com.nositer.client.dto.generated.Grouptopic;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.Resizable;

public class GroupmessagePanel extends ContentPanel implements Resizable {
	private GroupDiscussionsContainer groupDiscussionsContainer;
	private HtmlContainer htmlContainer;
	private Grouptopic grouptopic;
	private Label topicName;
	private HtmlContainer messageInfo;
	
	public GroupmessagePanel(GroupDiscussionsContainer groupDiscussionsContainer, Grouptopic grouptopic) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
		this.grouptopic = grouptopic;
		
		init();
	}

	private void init() {
		this.setHeaderVisible(false);
		setBorders(false);
		setBodyBorder(false);
		FlowLayout flowLayout = new FlowLayout();
		this.setLayout(flowLayout);
		Groupmessage[] groupmessages = grouptopic.getGroupmessages().toArray(new Groupmessage[] {});
		Groupmessage groupmessage = groupmessages[0];
		topicName = new Label(grouptopic.getName());
		topicName.setStyleName("formHeading");
		add(topicName);
		messageInfo = new HtmlContainer("message posted by " + groupmessage.getUser().getFirstname() + " on " + groupmessage.getCreatedtime());
		messageInfo.setStyleName("groupmessageInfo");
		add(messageInfo);
		htmlContainer = new HtmlContainer(groupmessage.getDescription());
		add(htmlContainer, new MarginData(5, 0, 0, 0));
	}

	@Override
	public void resize(int width, int height) {
		int newWidth = MainPanel.getInstance().getWidth() - groupDiscussionsContainer.getGroupDiscussionLeftPanel().getWidth();
		setWidth(newWidth - 20);
		
		setHeight(groupDiscussionsContainer.getGroupDiscussionMainPanel().getHeight() - 10);
	}
}
