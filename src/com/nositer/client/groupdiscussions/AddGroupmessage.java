package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.HtmlEditor;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
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

public class AddGroupmessage extends ContentPanel implements Resizable, GroupmessageContainer {

	private GroupDiscussionsContainer groupDiscussionsContainer;
	private Label theHeading;
	private FormPanel formPanel;	
	private HtmlEditor description;
	private Label filler;
	private TextArea referenceMessage;
	private Button saveButton;
	private Groupmessage groupmessage;

	public GroupDiscussionsContainer getGroupDiscussionsContainer() {
		return groupDiscussionsContainer;
	}

	public void setGroupDiscussionsContainer(
			GroupDiscussionsContainer groupDiscussionsContainer) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
	}

	public Label getTheHeading() {
		return theHeading;
	}

	public void setTheHeading(Label theHeading) {
		this.theHeading = theHeading;
	}

	public FormPanel getFormPanel() {
		return formPanel;
	}

	public void setFormPanel(FormPanel formPanel) {
		this.formPanel = formPanel;
	}

	public HtmlEditor getDescription() {
		return description;
	}

	public void setDescription(HtmlEditor description) {
		this.description = description;
	}

	public Label getFiller() {
		return filler;
	}

	public void setFiller(Label filler) {
		this.filler = filler;
	}

	public TextArea getReferenceMessage() {
		return referenceMessage;
	}

	public void setReferenceMessage(TextArea referenceMessage) {
		this.referenceMessage = referenceMessage;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(Button saveButton) {
		this.saveButton = saveButton;
	}

	public Groupmessage getGroupmessage() {
		return groupmessage;
	}

	public void setGroupmessage(Groupmessage groupmessage) {
		this.groupmessage = groupmessage;
	}

	public AddGroupmessage(GroupDiscussionsContainer groupDiscussionsContainer) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
		formPanel = new FormPanel();
	}

	public void populateInsideMainPanel() {
		this.setHeaderVisible(false);

		theHeading = new Label("Add Message");
		theHeading.setStyleName("formHeading");
		//groupDiscussionsContainer.getGroupDiscussionMainPanel().removeAll();
		this.add(theHeading, new MarginData(5, 0, 0, 5));

		formPanel.setBodyBorder(false);
		formPanel.setHeaderVisible(false);
		formPanel.setWidth("100%");
		formPanel.setLabelAlign(LabelAlign.TOP);

		description = new HtmlEditor() {
			protected void afterRender() {
				resize(0,0);
				super.afterRender();
			};
		};
		description.setFieldLabel("Message");

		formPanel.add(description, new FormData("100%"));
		
		filler = new Label();
		formPanel.add(filler, new FormData("100%"));
		
		referenceMessage = new TextArea()  {
			protected void afterRender() {
				resize(0,0);
				super.afterRender();
			};
		};

		referenceMessage.setFieldLabel("Reference Message (copy and paste what you need below to your new message above)");
		
		formPanel.add(referenceMessage, new FormData("100%"));
		
		saveButton = new Button("Save");
		formPanel.setButtonAlign(HorizontalAlignment.CENTER);  
		formPanel.addButton(saveButton);
		this.add(formPanel);

		for (Component component : groupDiscussionsContainer.getGroupDiscussionMainPanel().getItems()) {
			if (component instanceof GroupmessageContainer) {
				GroupmessageContainer groupmessageContainer = (com.nositer.client.groupdiscussions.GroupmessageContainer) component;
				populate(groupmessageContainer.getGroupmessage());
				groupDiscussionsContainer.getGroupDiscussionMainPanel().remove(component);
				groupDiscussionsContainer.getGroupDiscussionMainPanel().add(this);
				groupDiscussionsContainer.getGroupDiscussionMainPanel().layout();
				break;
			}
		}

		groupDiscussionsContainer.getGroupDiscussionMainPanel().getBottomComponent().hide();
		groupDiscussionsContainer.getGroupDiscussionMainPanel().setHeight(MainPanel.getInstance().getHeight());
		//groupDiscussionsContainer.getGroupDiscussionMainPanel().layout(true);
		
		resize(0,0);
	}

	public void populate(Groupmessage groupmessage) {
		this.groupmessage = groupmessage;
		String newReferenceMessageValue = "Topic: " + groupmessage.getGrouptopic().getName() + "\n"; 
		newReferenceMessageValue += "Posted on: " + groupmessage.getCreatedtime() + "\n";
		newReferenceMessageValue += "Posted by: " + groupmessage.getUser().getFirstname() + " " + groupmessage.getUser().getLastname() + "\n\n";
		
		newReferenceMessageValue +=	groupmessage.getDescription();
		referenceMessage.setValue(newReferenceMessageValue);
		addDefaultListeners();
	}
	
	private void addDefaultListeners() {
		saveButton.addListener(Events.Select, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				

				Groupmessage newGroupmessage = new Groupmessage();
				newGroupmessage.setUserid(Nositer.getInstance().getUser().getId());
				newGroupmessage.setGrouptopicid(groupmessage.getGrouptopic().getId());
				newGroupmessage.setDescription(description.getValue());

				
				AsyncCallback<Groupmessage> callback = new AsyncCallback<Groupmessage>() {
					@Override
					public void onFailure(Throwable caught) {
						AlertMessageBox.show("Error", caught.getMessage(), null);
						GWTUtil.log("", caught);
					}

					@Override
					public void onSuccess(final Groupmessage result) {
						InfoMessageBox.show("Saved", new Listener<MessageBoxEvent>() {
							@Override
							public void handleEvent(MessageBoxEvent be) {								
								GroupmessagePanel groupmessagePanel = new GroupmessagePanel(groupDiscussionsContainer, result);
								groupmessagePanel.show();
								groupDiscussionsContainer.getGroupDiscussionLeftPanel().getGroupmessagesGrid().refresh();								
							}								
						});										
					}
				};
				ServiceBroker.groupService.addGroupmessage(groupDiscussionsContainer.getGroupPlusView(), newGroupmessage, callback);
			}
		});
	}

	@Override
	public void resize(int width, int height) {
		int newWidth = MainPanel.getInstance().getWidth() - groupDiscussionsContainer.getGroupDiscussionLeftPanel().getWidth();
		this.setWidth(newWidth - 10);
		formPanel.setWidth(newWidth - 10);
		
		this.setHeight(MainPanel.getInstance().getHeight()-60);	
		int heightMainPanelAccordionContainer = 0;
		
		for (Component component : groupDiscussionsContainer.getGroupDiscussionMainPanel().getItems()) {
			if (component instanceof MainPanelAccordionContainer) {
				MainPanelAccordionContainer mainPanelAccordionContainer = (MainPanelAccordionContainer)component;
				heightMainPanelAccordionContainer = mainPanelAccordionContainer.getHeight();
				break;
			}
		}
		int addGroupMessageHeight = MainPanel.getInstance().getHeight() - heightMainPanelAccordionContainer;
		
		formPanel.setHeight(addGroupMessageHeight - 90);
		if (description.isRendered()) {
			description.setHeight(addGroupMessageHeight/2-100);
		}		
		if (referenceMessage.isRendered()) {
			referenceMessage.setHeight(addGroupMessageHeight/2-150);
		}
		formPanel.layout();
		layout();
		//groupDiscussionsContainer.getGroupDiscussionMainPanel().add(this);
		groupDiscussionsContainer.getGroupDiscussionMainPanel().setWidth(newWidth);
		groupDiscussionsContainer.getGroupDiscussionMainPanel().layout();
	}


}
