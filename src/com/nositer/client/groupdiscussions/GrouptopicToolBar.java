package com.nositer.client.groupdiscussions;

import java.util.Set;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.WidgetComponent;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FillData;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TextBox;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Groupmessage;
import com.nositer.client.dto.generated.Grouptopic;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.button.NextButton;
import com.nositer.client.widget.button.PreviousButton;
import com.nositer.client.widget.messagebox.AlertMessageBox;
import com.nositer.client.widget.messagebox.PromptMessageBox;

public class GrouptopicToolBar extends ToolBar implements Resizable {
	private GroupDiscussionsContainer groupDiscussionsContainer;
	private Grouptopic grouptopic;
	private NextButton nextButton;
	private PreviousButton previousButton;
	private LayoutContainer groupmessageTallyContainer;
	private int cacheGroupmessageIndex;
	private Label groupmessageIndexLabel;


	public GrouptopicToolBar(GroupDiscussionsContainer groupDiscussionsContainer, Grouptopic grouptopic) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
		this.grouptopic = grouptopic;

		init();
	}

	private void init() {
		previousButton = new PreviousButton();
		nextButton = new NextButton();
		groupmessageIndexLabel = new Label();
		//setAlignment(HorizontalAlignment.CENTER);

		add(previousButton);
		groupmessageTallyContainer = new LayoutContainer();

		add(groupmessageTallyContainer);
		add(new FillToolItem());
		add(nextButton);
		addDefaultListeners();
		layout();
		resize(0,0);
	}

	private void addDefaultListeners() {
		
		previousButton.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				int newGroupmessageIndex = cacheGroupmessageIndex-1;
				ServiceBroker.groupService.getGroupmessage(grouptopic.getId(), newGroupmessageIndex, createGroupmessageCallback(newGroupmessageIndex));
			}
		});

		nextButton.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				int newGroupmessageIndex = cacheGroupmessageIndex+1;
				ServiceBroker.groupService.getGroupmessage(grouptopic.getId(), newGroupmessageIndex, createGroupmessageCallback(newGroupmessageIndex));
			}
		});
		
		groupmessageIndexLabel.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				PromptMessageBox.show("", "Enter the message index you would like to view", new Integer(cacheGroupmessageIndex).toString(), new Listener<MessageBoxEvent>()  {
					@Override
					public void handleEvent(MessageBoxEvent be) {
						int newGroupmessageIndex = 0;
						try {
							newGroupmessageIndex = new Integer(be.getValue());
							ServiceBroker.groupService.getGroupmessage(grouptopic.getId(), newGroupmessageIndex, createGroupmessageCallback(newGroupmessageIndex));
						} catch (Exception e) {
							AlertMessageBox.show("Error", be.getValue() + " is not a valid index", null);
						}
					}
				});
			}
		});
	}
	
	private AsyncCallback<Groupmessage> createGroupmessageCallback(final int newGroupmessageIndex) {
		AsyncCallback<Groupmessage> retval =  new AsyncCallback<Groupmessage>() {
			@Override
			public void onFailure(Throwable caught) {
				AlertMessageBox.show("Error", "Could not load message with index: " + newGroupmessageIndex, null);
			}

			@Override
			public void onSuccess(Groupmessage result) {
				GroupmessagePanel groupmessagePanel = new GroupmessagePanel(groupDiscussionsContainer, result);
				groupmessagePanel.show();
			}

		};
		return retval;
	}

	@Override
	public void resize(int width, int height) {
		groupmessageTallyContainer.removeAll();

		int widthOfToolBar = MainPanel.getInstance().getWidth() - groupDiscussionsContainer.getGroupDiscussionLeftPanel().getWidth();
		//groupmessageTallyContainer.add(new Label(), new MarginData(0, widthOfToolBar - 145, 0, 0));
		groupmessageTallyContainer.add(new Label(), new MarginData(0, widthOfToolBar/2 - 120, 0, 0));
		groupmessageTallyContainer.add(new Label("Message "));

		int groupmessageIndex = getIndexOfGroupmessage(grouptopic);
		groupmessageIndexLabel.setText(new Integer(groupmessageIndex).toString());
		groupmessageIndexLabel.setStyleName("tallyCount");

		groupmessageTallyContainer.add(groupmessageIndexLabel);
		groupmessageTallyContainer.add(new Label(" of " + grouptopic.getGroupmessages().size()));

		if (groupmessageIndex == grouptopic.getGroupmessages().size()) {
			nextButton.disable();
		}

		if (groupmessageIndex == 1) {
			previousButton.disable();
		}

		cacheGroupmessageIndex = groupmessageIndex;

		//groupmessageTallyContainer.add(new Label(), new MarginData(0, widthOfToolBar/3 - 70, 0, 0));
		layout();
	}

	// bad ass algorithm
	private int getIndexOfGroupmessage(Grouptopic grouptopic) {
		int retval = 0;
		int sumofNegativeIds = 0;
		for (Groupmessage groupmessage : grouptopic.getGroupmessages()) {
			int bogusgroupmessageid = groupmessage.getId();
			if (bogusgroupmessageid < 0) {
				sumofNegativeIds += bogusgroupmessageid;
			}
		}
		int numOfGroupmessages =  grouptopic.getGroupmessages().size();
		int sumOfNaturalCount = ((1+numOfGroupmessages) * numOfGroupmessages)/2;
		retval = sumOfNaturalCount - (-1 * sumofNegativeIds);
		return retval;
	}
}
