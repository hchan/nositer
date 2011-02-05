package com.nositer.client.editprofile;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.HistoryTokenHelper;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.ErrorPanel;
import com.nositer.client.widget.InfoMessageBox;
import com.nositer.shared.ServiceBroker;
@SuppressWarnings({"unchecked", "rawtypes"})
public class ChangePassword extends LayoutContainer {
	
	private FormPanel formPanel;
	private TextField<String> oldPassword;
	private TextField<String> password;
	private TextField<String> passwordAgain;
	private ErrorPanel errorPanel;
	public ChangePassword() {
		init();
	}

	public void init() {
		formPanel = new FormPanel();
		formPanel.setHeaderVisible(false);
		formPanel.setLabelWidth(200);
		formPanel.setWidth(500);
		formPanel.setBorders(false);
		formPanel.setBodyBorder(false);
		errorPanel = new ErrorPanel();
		errorPanel.setStyleAttribute("margin-bottom", "5px");
		errorPanel.hide();
		oldPassword = createPasswordField("* Old Password");		
		password = createPasswordField("* Password");
		passwordAgain = createPasswordField("* Password Again");
		
		formPanel.add(errorPanel);
		formPanel.add(oldPassword);
		formPanel.add(password);
		formPanel.add(passwordAgain);
		this.add(formPanel);
		initButtons();
	}


	

	private TextField<String> createPasswordField(String fieldLabel) {
		TextField<String> retval = new TextField<String>();
		retval.setFieldLabel(fieldLabel);  
		retval.setLabelStyle(GWTUtil.getRequiredFieldStyle());
		retval.setPassword(true);
		return retval;
	}

	public void initButtons() {
		initChangeButton();
		initCancelButton();
	}
	
	
	public void initChangeButton() {
		formPanel.setButtonAlign(HorizontalAlignment.CENTER);  
		Button button = new Button("Change");
		formPanel.addButton(button);  
	
		Listener saveListener = new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				ArrayList<String> errors = getErrors();
				if (errors.size() > 0) {
					errorPanel.setErrors(errors);
					errorPanel.show();					
				} else {
				
					AsyncCallback<Void> callback = new AsyncCallback<Void>() {
						@Override
						public void onFailure(Throwable caught) {
							errorPanel.clearErrorMessages();
							errorPanel.addErrorMessage(caught.getMessage());
							errorPanel.show();
							GWTUtil.log("", caught);
						}

						@Override
						public void onSuccess(Void result) {
							InfoMessageBox.show("Updated!", new Listener<MessageBoxEvent>() {
								@Override
								public void handleEvent(MessageBoxEvent be) {								
									History.newItem(HistoryTokenHelper.VIEWPROFILE.toString());									
								}								
							});										
						}
					};
					ServiceBroker.profileService.updatePasswordOfCurrentUser(oldPassword.getValue(), password.getValue(), callback);
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
				History.newItem(HistoryTokenHelper.VIEWPROFILE.toString());
			}
		};
		cancelButton.addListener(Events.Select, cancelListener);
	}

	

	public ArrayList<String> getErrors() {
		ArrayList<String> retval = new ArrayList<String>();
		GWTUtil.addRequiredErrorIfNecessary(oldPassword, retval);
		GWTUtil.addRequiredErrorIfNecessary(password, retval);
		if ((password.getValue() != null) && (!password.getValue().equals(passwordAgain.getValue()))) {
			retval.add("Password and Password Again are not the same");
		} 
		return retval;
	}
}
