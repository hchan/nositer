package com.nositer.client.creategroup;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.HtmlEditor;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.User;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.ErrorPanel;
import com.nositer.client.widget.InfoMessageBox;
import com.nositer.client.widget.Resizable;

public class CreateGroup extends LayoutContainer implements Resizable {
	private FormPanel formPanel;
	private TextField<String> name;
	private HtmlEditor description;
	private ErrorPanel errorPanel;
	public CreateGroup() {
		init();
	}

	public void init() {
		setMonitorWindowResize(true);
		formPanel = new FormPanel();
		
		
		formPanel.setHeaderVisible(false);
		//formPanel.setLabelWidth(200);
		formPanel.setLabelAlign(LabelAlign.TOP);
		//formPanel.setBorders(false);
		formPanel.setBodyBorder(false);
		errorPanel = new ErrorPanel();
		errorPanel.setStyleAttribute("margin-bottom", "5px");
		errorPanel.hide();
		name = new TextField<String>();
		name.setFieldLabel("Name");  
		name.setLabelStyle("font-size: 14px; font-weight: bold;");
		description = new HtmlEditor();
		setDescriptionHeight();
		
		description.setFieldLabel("Description");
		description.setLabelStyle("font-size: 14px; font-weight: bold;");
		formPanel.add(errorPanel);
		addLabel("Create Group");
		formPanel.add(name, new FormData("100%"));
		formPanel.add(description, new FormData("100%"));	
		
		this.add(formPanel);
		initButtons();	
		AsyncCallback<User> callback = new AsyncCallback<User>() {
			@Override
			public void onFailure(Throwable caught) {
				GWTUtil.log("", caught);
			}

			@Override
			public void onSuccess(User result) {
				if (result != null) {
					populate(result);
				}
			}
		};
		ServiceBroker.profileService.getCurrentUser(callback);
	}
	
	private void populate(User user) {
		name.setValue(user.getNote());
		description.setValue(user.getDescription());
	}
	
	@Override
	public void resize(int width, int height) {
		formPanel.setWidth(this.getWidth());
		setDescriptionHeight();
	}
	
	private void setDescriptionHeight() {
		int heightOfComponents = 150;
		if (errorPanel.isRendered() && !errorPanel.isHidden()) {
			heightOfComponents = heightOfComponents + errorPanel.getHeight();
		}
		description.setHeight(MainPanel.getInstance().getHeight() - heightOfComponents);
	}

	

	public void initButtons() {
		initUpdateButton();
		initCancelButton();
	}


	public void initUpdateButton() {
		formPanel.setButtonAlign(HorizontalAlignment.CENTER);  
		Button button = new Button("Update");
		formPanel.addButton(button);  

		Listener saveListener = new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				ArrayList<String> errors = getErrors();
				if (errors.size() > 0) {
					errorPanel.setErrors(errors);
					errorPanel.show();		
					resize(0,0);
				} else {

					AsyncCallback<Void> callback = new AsyncCallback<Void>() {
						@Override
						public void onFailure(Throwable caught) {
							errorPanel.clearErrorMessages();
							errorPanel.addErrorMessage(caught.getMessage());
							errorPanel.show();
							resize(0,0);
							GWTUtil.log("", caught);
						}

						@Override
						public void onSuccess(Void result) {
							InfoMessageBox.show("Updated!", new Listener<MessageBoxEvent>() {
								@Override
								public void handleEvent(MessageBoxEvent be) {								
									History.newItem(HistoryToken.VIEWPROFILE.toString());									
								}								
							});										
						}
					};
					ServiceBroker.profileService.updateAboutMeOfCurrentUser(name.getValue(), description.getValue(), callback);
				}
			}
		};
		button.addListener(Events.Select, saveListener);
	}

	// Cancel
	public void initCancelButton() {
		Button cancelButton = new Button("Cancel");
		formPanel.addButton(cancelButton);  
		Listener cancelListener = new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				History.newItem(HistoryToken.VIEWPROFILE.toString());
			}
		};
		cancelButton.addListener(Events.Select, cancelListener);
	}

	@Override
	protected void onWindowResize(int width, int height) {
		super.onWindowResize(width, height);
		resize(width, height);
	}

	public ArrayList<String> getErrors() {
		ArrayList<String> retval = new ArrayList<String>();		
		return retval;
	}

	private void addLabel(String labelName) {
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setMargins(new Margins(0, 0, 10, 0));
		LayoutContainer layoutContainer = new LayoutContainer(flowLayout);
		Label label = new Label(labelName);
		layoutContainer.add(label);
		layoutContainer.setStyleName("formHeading");
		formPanel.add(layoutContainer);
	}
}
