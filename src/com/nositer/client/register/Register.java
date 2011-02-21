package com.nositer.client.register;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Postalcode;
import com.nositer.client.dto.generated.Relationshipcode;
import com.nositer.client.dto.generated.Salutationcode;
import com.nositer.client.dto.generated.User;
import com.nositer.client.dto.generated.Zipcode;
import com.nositer.client.main.MainPanel;
import com.nositer.client.top.TopPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.ErrorPanel;
import com.nositer.client.widget.Location;
import com.nositer.client.widget.avatar.AvatarSelector;
import com.nositer.client.widget.combobox.RelationshipcodeComboBox;
import com.nositer.client.widget.combobox.SalutationcodeComboBox;
import com.nositer.client.widget.radiogroup.GenderRadioGroup;
@SuppressWarnings("unchecked")
public class Register implements EntryPoint {

	public static final String NOSITER_HOME_URL = "/Nositer.html";
	private static Register instance;
	private TopPanel topPanel;
	private MainPanel mainPanel;
	private LayoutContainer layoutContainer;
	private FormPanel formPanel;
	private Button saveButton;
	private Button cancelButton;
	private TextField<String> firstName;
	private TextField<String> lastName;
	private TextField<String> login;
	private TextField<String> password;
	private TextField<String> passwordAgain;
	private Location location;
	private TextField<String> email;
	private GenderRadioGroup genderRadioGroup;
	private TextField<String> profession;
	private DateField birthdate;
	private ErrorPanel errorPanel;
	private SalutationcodeComboBox salutation;
	private RelationshipcodeComboBox relationship;
	private AvatarSelector avatarSelector;
	
	public AvatarSelector getAvatarSelector() {
		return avatarSelector;
	}

	public void setAvatarSelector(AvatarSelector avatarSelector) {
		this.avatarSelector = avatarSelector;
	}

	public SalutationcodeComboBox getSalutation() {
		return salutation;
	}

	public void setSalutation(SalutationcodeComboBox salutation) {
		this.salutation = salutation;
	}

	public RelationshipcodeComboBox getRelationship() {
		return relationship;
	}

	public void setRelationship(RelationshipcodeComboBox relationship) {
		this.relationship = relationship;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(Button saveButton) {
		this.saveButton = saveButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(Button cancelButton) {
		this.cancelButton = cancelButton;
	}

	public TextField<String> getFirstName() {
		return firstName;
	}

	public void setFirstName(TextField<String> firstName) {
		this.firstName = firstName;
	}

	public TextField<String> getLastName() {
		return lastName;
	}

	public void setLastName(TextField<String> lastName) {
		this.lastName = lastName;
	}

	public TextField<String> getLogin() {
		return login;
	}

	public void setLogin(TextField<String> login) {
		this.login = login;
	}

	public TextField<String> getPassword() {
		return password;
	}

	public void setPassword(TextField<String> password) {
		this.password = password;
	}

	public TextField<String> getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(TextField<String> passwordAgain) {
		this.passwordAgain = passwordAgain;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public TextField<String> getEmail() {
		return email;
	}

	public void setEmail(TextField<String> email) {
		this.email = email;
	}

	public GenderRadioGroup getGenderRadioGroup() {
		return genderRadioGroup;
	}

	public void setGenderRadioGroup(GenderRadioGroup genderRadioGroup) {
		this.genderRadioGroup = genderRadioGroup;
	}

	public TextField<String> getProfession() {
		return profession;
	}

	public void setProfession(TextField<String> profession) {
		this.profession = profession;
	}

	public DateField getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(DateField birthdate) {
		this.birthdate = birthdate;
	}

	public ErrorPanel getErrorPanel() {
		return errorPanel;
	}

	public void setErrorPanel(ErrorPanel errorPanel) {
		this.errorPanel = errorPanel;
	}

	public FormPanel getFormPanel() {
		return formPanel;
	}

	public void setFormPanel(FormPanel formPanel) {
		this.formPanel = formPanel;
	}

	public static Register getInstance() {
		return instance;
	}

	public static void setInstance(Register instance) {
		Register.instance = instance;
	}

	public void onModuleLoad() {
		init();
	}

	public LayoutContainer getLayoutContainer() {
		return layoutContainer;
	}
	public void setLayoutContainer(LayoutContainer layoutContainer) {
		this.layoutContainer = layoutContainer;
	}
	public MainPanel getMainPanel() {
		return mainPanel;
	}
	public void setMainPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public TopPanel getTopPanel() {
		return topPanel;
	}
	public void setTopPanel(TopPanel topPanel) {
		this.topPanel = topPanel;
	}

	public void init() {
		Viewport viewport = new Viewport();
		viewport.setLayout(new BorderLayout());
		initLayoutContainer();		
		BorderLayoutData borderLayoutData = new BorderLayoutData(LayoutRegion.CENTER);
		viewport.add(layoutContainer, borderLayoutData);
		borderLayoutData.setMargins(new Margins(1));
		instance = this;
		RootPanel.get().add(viewport);		
	}

	public void initLayoutContainer() {
		layoutContainer = new LayoutContainer();	
		BorderLayout borderLayout = new BorderLayout();
		layoutContainer.setLayout(borderLayout);
		BorderLayoutData topLayoutData = new BorderLayoutData(LayoutRegion.NORTH);  
		topPanel = new TopPanel(topLayoutData, false);		
		BorderLayoutData mainLayoutData = new BorderLayoutData(LayoutRegion.CENTER);  
		mainPanel = new MainPanel(mainLayoutData);
		mainPanel.setScrollMode(Scroll.AUTO);
		populateMainPanel();
		layoutContainer.add(topPanel, topLayoutData);		
		layoutContainer.add(mainPanel, mainLayoutData);	

	}

	private void populateMainPanel() {		
		initFormPanel();
		addButtons();
		mainPanel.setLayout(new CenterLayout());
		ContentPanel registrationPanel = new ContentPanel(new TableLayout(1));
		registrationPanel.setWidth(500);
		registrationPanel.setHeading("Registration");
		errorPanel = new ErrorPanel();
		errorPanel.init();
		errorPanel.hide();
		registrationPanel.add(errorPanel);
		registrationPanel.add(formPanel);
		mainPanel.add(registrationPanel);

	}

	public void initFormPanel() {
		formPanel = new FormPanel();
		formPanel.setHeaderVisible(false);

		formPanel.setLabelWidth(150);
		formPanel.setHeading("Registration");
		formPanel.setFrame(true);

		addRequiredFieldLabel();				
		addRequiredFields();		
		addOptionalFieldLabel();
		addOptionalFields();
		formPanel.setWidth(500);
	}

	private void addOptionalFields() {
		avatarSelector = new AvatarSelector();
		avatarSelector.gethBoxSelectedFileLayoutData().setMargins(new Margins(0, 1, 5, 78));
		avatarSelector.getSelectAvatar().setStyleName("avatarSelectorBasicProfile");
		avatarSelector.hide();
		
		email = new TextField<String>();
		email.setFieldLabel("Email");		

		salutation = new SalutationcodeComboBox();
		salutation.setForceSelection(false);		
		salutation.setFieldLabel("Salutation");
				
		relationship = new RelationshipcodeComboBox();
		relationship.setForceSelection(false);		
		relationship.setFieldLabel("Relationship Status");
		
		genderRadioGroup = new GenderRadioGroup();

		profession = new TextField<String>();
		profession.setFieldLabel("Profession");

		birthdate = new DateField();
		birthdate.setFieldLabel("Birthdate (yyyy-MM-dd)");

		formPanel.add(avatarSelector);
		formPanel.add(email);
		formPanel.add(salutation);
		formPanel.add(relationship);
		formPanel.add(genderRadioGroup);
		formPanel.add(profession);
		formPanel.add(birthdate);
	}

	private void addRequiredFields() {
		firstName = new TextField<String>();  
		firstName.setFieldLabel("* Firstname");  
		firstName.setLabelStyle(GWTUtil.getRequiredFieldStyle());

		lastName = new TextField<String>();  
		lastName.setFieldLabel("* Lastname");  
		lastName.setLabelStyle(GWTUtil.getRequiredFieldStyle());

		login = new TextField<String>();  
		login.setFieldLabel("* Login name");  
		login.setLabelStyle(GWTUtil.getRequiredFieldStyle());

		password = new TextField<String>();  
		password.setFieldLabel("* Password");  
		password.setLabelStyle(GWTUtil.getRequiredFieldStyle());
		password.setPassword(true);

		passwordAgain = new TextField<String>();  
		passwordAgain.setFieldLabel("* Password Again");  
		passwordAgain.setLabelStyle(GWTUtil.getRequiredFieldStyle());
		passwordAgain.setPassword(true);
		location = new Location();				

		formPanel.add(firstName);
		formPanel.add(lastName);
		formPanel.add(login);
		formPanel.add(password);
		formPanel.add(passwordAgain);
		formPanel.add(location);
	}

	private void addRequiredFieldLabel() {
		FlowLayout flowLayoutRequiredFieldLabelContainer = new FlowLayout();
		flowLayoutRequiredFieldLabelContainer.setMargins(new Margins(0, 0, 10, 0));
		LayoutContainer requiredFieldLabelContainer = new LayoutContainer(flowLayoutRequiredFieldLabelContainer);
		Label requiredFieldLabel = new Label("Required Fields");
		requiredFieldLabel.setStyleName("requiredFieldLabel");
		requiredFieldLabelContainer.add(requiredFieldLabel);
		formPanel.add(requiredFieldLabelContainer);
	}

	private void addOptionalFieldLabel() {
		FlowLayout flowLayoutOptionalFieldLabelContainer = new FlowLayout();
		flowLayoutOptionalFieldLabelContainer.setMargins(new Margins(10, 0, 10, 0));
		LayoutContainer optionalFieldLabelContainer = new LayoutContainer(flowLayoutOptionalFieldLabelContainer);
		Label optionalFieldLabel = new Label("Optional Fields");
		optionalFieldLabel.setStyleName("optionalFieldLabel");
		optionalFieldLabelContainer.add(optionalFieldLabel);
		formPanel.add(optionalFieldLabelContainer);
	}

	private void addButtons() {
		formPanel.setButtonAlign(HorizontalAlignment.CENTER);  
		saveButton = new Button("Save");
		formPanel.addButton(saveButton);  

		final AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				errorPanel.clearErrorMessages();
				if (caught.getMessage().indexOf("ConstraintViolationException") != -1) {
					errorPanel.addErrorMessage("Login is already taken");
				} else {
					errorPanel.addErrorMessage(caught.getMessage());
				}
				errorPanel.show();
				GWTUtil.log("", caught);
			}

			@Override
			public void onSuccess(Boolean result) {
				Window.Location.assign(NOSITER_HOME_URL);
			}
		};
		saveButton.addListener(Events.Select, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				ArrayList<String> errors = getErrors();
				if (errors.size() > 0) {
					errorPanel.setErrors(errors);
					errorPanel.show();					

				} else {
					User user = createDTO();
					ServiceBroker.registerService.register(user, callback);
				}
			}
		});

		cancelButton = new Button("Cancel");
		cancelButton.addListener(Events.Select, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				Window.Location.assign(NOSITER_HOME_URL);
			}
		});
		formPanel.addButton(cancelButton);  
	}


	public ArrayList<String> getErrors() {
		ArrayList<String> retval = new ArrayList<String>();
		GWTUtil.addRequiredErrorIfNecessary(firstName, retval);
		GWTUtil.addRequiredErrorIfNecessary(lastName, retval);
		GWTUtil.addRequiredErrorIfNecessary(login, retval);
		GWTUtil.addRequiredErrorIfNecessary(password, retval);
		if ((password.getValue() != null) && (!password.getValue().equals(passwordAgain.getValue()))) {
			retval.add("Password and Password Again are not the same");
		} 
		if (location.getCountry().getValue().getData(Location.COUNTRYCODE).equals(Location.COUNTRYCODE_CAN)) {
			GWTUtil.addRequiredErrorIfNecessary(location.getPostalcode(), retval);
		} else {
			GWTUtil.addRequiredErrorIfNecessary(location.getZipcode(), retval);
		}
		return retval;
	}

	public User createDTO() {
		User retval = new User();
		retval.setFirstname(firstName.getValue());
		retval.setLastname(lastName.getValue());
		retval.setLogin(login.getValue());
		retval.setPassword(password.getValue());
		retval.setCountrycode((String) location.getCountry().getValue().getData(Location.COUNTRYCODE));
		if (location.getCountry().getValue().getData(Location.COUNTRYCODE).equals(Location.COUNTRYCODE_CAN)) {					
			Postalcode postalcode = location.getPostalcode().getBean();			
			retval.setPostalcode(postalcode);
		} else {
			Zipcode zipcode = location.getZipcode().getBean();			
			retval.setZipcode(zipcode);
		}
		
		if (salutation.getValue() != null) {
			Salutationcode salutationcode = salutation.getBean();
			retval.setSalutationcode(salutationcode);
		}
		
		if (relationship.getValue() != null) {
			Relationshipcode relationshipcode = relationship.getBean();
			retval.setRelationshipcode(relationshipcode);
		}
		
		retval.setEmail(email.getValue());
		if (genderRadioGroup.getGender() != null) {
			if (genderRadioGroup.getGender().equals(GenderRadioGroup.GenderType.MALE)) {
				retval.setGendermale(true);
			} else {
				retval.setGendermale(false);
			}
		}
		retval.setProfession(profession.getValue());
		retval.setBirthdate(birthdate.getValue());
		return retval;
	}

}
