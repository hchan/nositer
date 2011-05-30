package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.HtmlEditor;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.Nositer;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Groupmessage;
import com.nositer.client.dto.generated.Grouptopic;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.messagebox.AlertMessageBox;
import com.nositer.client.widget.messagebox.InfoMessageBox;

public class CreateGrouptopic extends ContentPanel implements Resizable {

	private GroupDiscussionsContainer groupDiscussionsContainer;
	private Label theHeading;
	private FormPanel formPanel;	
	private TextField<String> name;
	private HtmlEditor description;
	private Button saveButton;

	public CreateGrouptopic(GroupDiscussionsContainer groupDiscussionsContainer) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
		formPanel = new FormPanel();
	}

	public void populateMainPanel() {
		this.setHeaderVisible(false);

		theHeading = new Label("Create New Topic and Message");
		theHeading.setStyleName("formHeading");
		groupDiscussionsContainer.getGroupDiscussionMainPanel().removeAll();
		this.add(theHeading, new MarginData(5, 0, 0, 5));

		formPanel.setBodyBorder(false);
		formPanel.setHeaderVisible(false);
		formPanel.setWidth("100%");
		formPanel.setLabelAlign(LabelAlign.TOP);
		name = new TextField<String>();
		name.setFieldLabel("Topic name");
		description = new HtmlEditor() {
			protected void afterRender() {
				resize(0,0);
				super.afterRender();
			};
		};
		description.setFieldLabel("Message");
		
		
		formPanel.add(name, new FormData("100%"));
		formPanel.add(description, new FormData("100%"));
		saveButton = new Button("Save");
		formPanel.setButtonAlign(HorizontalAlignment.CENTER);  
		formPanel.addButton(saveButton);
		this.add(formPanel, new MarginData(5, 5, 65, 0));
		groupDiscussionsContainer.getGroupDiscussionMainPanel().add(this);
		addDefaultListeners();
		resize(0,0);
	}


	private void addDefaultListeners() {
		saveButton.addListener(Events.Select, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				Grouptopic grouptopic = new Grouptopic();
				grouptopic.setUserid(Nositer.getInstance().getUser().getId());
				grouptopic.setGroupid(groupDiscussionsContainer.getGroupPlusView().getId());
				grouptopic.setName(name.getValue());

				Groupmessage groupmessage = new Groupmessage();
				groupmessage.setUserid(Nositer.getInstance().getUser().getId());
				groupmessage.setGrouptopicid(groupDiscussionsContainer.getGroupPlusView().getId());
				groupmessage.setDescription(description.getValue());

				grouptopic.getGroupmessages().add(groupmessage);
				AsyncCallback<Grouptopic> callback = new AsyncCallback<Grouptopic>() {
					@Override
					public void onFailure(Throwable caught) {
						AlertMessageBox.show("Error", caught.getMessage(), null);
						GWTUtil.log("", caught);
					}

					@Override
					public void onSuccess(final Grouptopic result) {
						InfoMessageBox.show("Saved", new Listener<MessageBoxEvent>() {
							@Override
							public void handleEvent(MessageBoxEvent be) {								
								groupDiscussionsContainer.getGroupDiscussionLeftPanel().getGroupmessagesGrid().refresh();
								GroupmessagePanel groupmessagePanel = new GroupmessagePanel(groupDiscussionsContainer, result);
								groupDiscussionsContainer.getGroupDiscussionMainPanel().removeAll();
								groupDiscussionsContainer.getGroupDiscussionMainPanel().add(groupmessagePanel);
								groupDiscussionsContainer.getGroupDiscussionMainPanel().layout();
							}								
						});										
					}
				};
				ServiceBroker.groupService.createGrouptopic(groupDiscussionsContainer.getGroupPlusView(), grouptopic, callback);
			}
		});
	}

	@Override
	public void resize(int width, int height) {
		int newWidth = MainPanel.getInstance().getWidth() - groupDiscussionsContainer.getGroupDiscussionLeftPanel().getWidth();
		this.setWidth(newWidth - 15);
		formPanel.setWidth(newWidth - 15);
		this.setHeight(MainPanel.getInstance().getHeight()-60);		
		formPanel.setHeight(MainPanel.getInstance().getHeight()-100);
		if (description.isRendered()) {
			description.setHeight(MainPanel.getInstance().getHeight()-210);
		}		
		formPanel.layout();
		layout();
		//groupDiscussionsContainer.getGroupDiscussionMainPanel().add(this);
		groupDiscussionsContainer.getGroupDiscussionMainPanel().setWidth(newWidth);
		groupDiscussionsContainer.getGroupDiscussionMainPanel().layout();
	}

	
}
