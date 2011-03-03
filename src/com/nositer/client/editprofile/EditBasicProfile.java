package com.nositer.client.editprofile;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Postalcode;
import com.nositer.client.dto.generated.Relationshipcode;
import com.nositer.client.dto.generated.Salutationcode;
import com.nositer.client.dto.generated.User;
import com.nositer.client.dto.generated.Zipcode;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.main.MainPanel;
import com.nositer.client.register.Register;
import com.nositer.client.top.TopPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.ErrorPanel;
import com.nositer.client.widget.Location;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.messagebox.InfoMessageBox;
import com.nositer.client.widget.radiogroup.GenderRadioGroup.GenderType;

@SuppressWarnings({"unchecked", "rawtypes"})
public class EditBasicProfile extends LayoutContainer implements Resizable {
	private Register register;
	private FormPanel formPanel;

	public EditBasicProfile() {
		init();
	}

	public void init() {

		register = new Register();
		register.setErrorPanel(new ErrorPanel());
		register.getErrorPanel().hide();
		this.add(register.getErrorPanel());
		register.initFormPanel();
		
		formPanel = register.getFormPanel();
		register.getAvatarSelector().show();
	
		
		initButtons();
		TableLayout layout = new TableLayout(1);
		//layout.setHeight("100%");
		//layout.setWidth("100%");
		this.setLayout(layout);
		this.setHeight("100%");
		this.setStyleName("editBasicProfile");
		formPanel.remove(register.getLogin());
		formPanel.remove(register.getPassword());
		formPanel.remove(register.getPasswordAgain());
		formPanel.layout();

		this.add(formPanel);
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


	public void populate(User user) {

		register.getFirstName().setValue(user.getFirstname());
		register.getLastName().setValue(user.getLastname());
		if (user.getCountrycode().equals(Location.COUNTRYCODE_CAN)) {
			register.getLocation().getCountry().setValue(register.getLocation().getCanRadio());
			BeanModel beanModel = BeanModelLookup.get().getFactory(Postalcode.class).createModel(user.getPostalcode());
			register.getLocation().getPostalcode().setValue(beanModel);
			register.getLocation().getPostalcode().setRawValue(user.getPostalcode().getCode());
		} else {
			register.getLocation().getCountry().setValue(register.getLocation().getUsaRadio());
			BeanModel beanModel = BeanModelLookup.get().getFactory(Zipcode.class).createModel(user.getZipcode());
			register.getLocation().getZipcode().setValue(beanModel);
			register.getLocation().getZipcode().setRawValue(user.getZipcode().getCode());
		}

		if (user.getSalutationcode() != null) {
			BeanModel salutationModel = BeanModelLookup.get().getFactory(Salutationcode.class).createModel(user.getSalutationcode());
			register.getSalutation().setValue(salutationModel);
			register.getSalutation().setRawValue(user.getSalutationcode().getCode());
		}

		if (user.getRelationshipcode() != null) {
			BeanModel relationshipModel = BeanModelLookup.get().getFactory(Relationshipcode.class).createModel(user.getRelationshipcode());
			register.getRelationship().setValue(relationshipModel);
			register.getRelationship().setRawValue(user.getRelationshipcode().getCode());
		}
		
		register.getEmail().setValue(user.getEmail());
		if (user.getGendermale() != null) {
			if (user.getGendermale()) {
				register.getGenderRadioGroup().setGender(GenderType.MALE);
			} else {
				register.getGenderRadioGroup().setGender(GenderType.FEMALE);
			}
		}
		register.getProfession().setValue(user.getProfession());
		register.getBirthdate().setValue(user.getBirthdate());		

	}

	public void initButtons() {
		initUpdateButton();
		initCancelButton();
	}


	public void initUpdateButton() {
		formPanel.setButtonAlign(HorizontalAlignment.CENTER);  
		Button button = new Button("Update");
		formPanel.addButton(button);  
		register.getFormPanel().layout();
		Listener saveListener = new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				ArrayList<String> errors = getErrors();
				if (errors.size() > 0) {
					register.getErrorPanel().setErrors(errors);
					register.getErrorPanel().show();					
				} else {
					final User user = createDTO();
					AsyncCallback<Void> callback = new AsyncCallback<Void>() {
						@Override
						public void onFailure(Throwable caught) {
							register.getErrorPanel().clearErrorMessages();
							register.getErrorPanel().addErrorMessage(caught.getMessage());
							register.getErrorPanel().show();
							GWTUtil.log("", caught);
						}

						@Override
						public void onSuccess(Void result) {
							TopPanel.getInstance().setUser(user);
							TopPanel.getInstance().setFirstLastName(user);
							InfoMessageBox.show("Updated!", new Listener<MessageBoxEvent>() {
								@Override
								public void handleEvent(MessageBoxEvent be) {								
									History.newItem(HistoryToken.VIEWPROFILE.toString());									
								}								
							});							
						}
					};
					ServiceBroker.profileService.updateCurrentUserForEditBasicProfile(user, callback);
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

	private User createDTO() {
		User retval = register.createDTO();
		retval.setAvatarlocation(register.getAvatarSelector().getSelectedFile().getValue());
		return retval;
	}

	public ArrayList<String> getErrors() {
		ArrayList<String> retval = new ArrayList<String>();
		GWTUtil.addRequiredErrorIfNecessary(register.getFirstName(), retval);
		GWTUtil.addRequiredErrorIfNecessary(register.getLastName(), retval);

		if (register.getLocation().getCountry().getValue().getData(Location.COUNTRYCODE).equals(Location.COUNTRYCODE_CAN)) {
			GWTUtil.addRequiredErrorIfNecessary(register.getLocation().getPostalcode(), retval);
		} else {
			GWTUtil.addRequiredErrorIfNecessary(register.getLocation().getZipcode(), retval);
		}
		return retval;
	}

	@Override
	public void resize(int width, int height) {
		setSize(MainPanel.getInstance().getWidth(), MainPanel.getInstance().getHeight());
		
	}
}
