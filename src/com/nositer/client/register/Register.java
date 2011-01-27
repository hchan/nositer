package com.nositer.client.register;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.Style.HideMode;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.nositer.client.dto.generated.User;
import com.nositer.client.main.MainPanel;
import com.nositer.client.top.TopPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.ErrorPanel;
import com.nositer.shared.ServiceBroker;

public class Register implements EntryPoint {

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
	private ErrorPanel errorPanel;

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
		topPanel = new TopPanel(topLayoutData);		
		BorderLayoutData mainLayoutData = new BorderLayoutData(LayoutRegion.CENTER);  
		mainPanel = new MainPanel(mainLayoutData);
		populateMainPanel();
		layoutContainer.add(topPanel, topLayoutData);		
		layoutContainer.add(mainPanel, mainLayoutData);	

	}

	private void populateMainPanel() {		
		initFormPanel();

		mainPanel.setLayout(new CenterLayout());
		ContentPanel registrationPanel = new ContentPanel(new TableLayout(1));
		registrationPanel.setHeading("Registration");
		errorPanel = new ErrorPanel();
		errorPanel.hide();
		registrationPanel.add(errorPanel);
		registrationPanel.add(formPanel);
		mainPanel.add(registrationPanel);

	}

	public static String getRequiredFieldStyle() {
		return "color: red; font-weight: bold";
	}

	private void initFormPanel() {


		formPanel = new FormPanel();
		formPanel.setHeaderVisible(false);

		formPanel.setLabelWidth(150);
		formPanel.setHeading("Registration");
		formPanel.setFrame(true);
		firstName = new TextField<String>();  
		firstName.setFieldLabel("* Firstname");  
		firstName.setLabelStyle(getRequiredFieldStyle());

		lastName = new TextField<String>();  
		lastName.setFieldLabel("* Lastname");  
		lastName.setLabelStyle(getRequiredFieldStyle());

		login = new TextField<String>();  
		login.setFieldLabel("* Login name");  
		login.setLabelStyle(getRequiredFieldStyle());

		password = new TextField<String>();  
		password.setFieldLabel("* Password");  
		password.setLabelStyle(getRequiredFieldStyle());
		password.setPassword(true);

		passwordAgain = new TextField<String>();  
		passwordAgain.setFieldLabel("* Password Again");  
		passwordAgain.setLabelStyle(getRequiredFieldStyle());
		passwordAgain.setPassword(true);

		location = new Location();
		formPanel.add(firstName);
		formPanel.add(lastName);
		formPanel.add(login);
		formPanel.add(password);
		formPanel.add(passwordAgain);
		formPanel.add(location);
		addButtons();
		formPanel.setWidth(500);
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
				Window.Location.assign("/Nositer.html");
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
				Window.Location.assign("/Nositer.html");
			}
		});
		formPanel.addButton(cancelButton);  
	}

	public ArrayList<String> getErrors() {
		ArrayList<String> retval = new ArrayList<String>();
		addRequiredErrorIfNecessary(firstName, retval);
		addRequiredErrorIfNecessary(lastName, retval);
		addRequiredErrorIfNecessary(login, retval);
		addRequiredErrorIfNecessary(password, retval);
		if ((password.getValue() != null) && (!password.getValue().equals(passwordAgain.getValue()))) {
			retval.add("Password and Password Again are not the same");
		} 
		if (location.getCountry().getValue().getData("countrycode").equals("CAN")) {
			addRequiredErrorIfNecessary(location.getPostalcode(), retval);
		} else {
			addRequiredErrorIfNecessary(location.getZipcode(), retval);
		}
		return retval;
	}

	private void addRequiredErrorIfNecessary(TextField<String> textField,
			ArrayList<String> retval) {
		if (textField.getValue() == null) {
			retval.add(textField.getFieldLabel().replace("* ", "") + " is required");
		}
	}

	private User createDTO() {
		User retval = new User();
		retval.setFirstname(firstName.getValue());
		retval.setLastname(lastName.getValue());
		retval.setLogin(login.getValue());
		retval.setPassword(password.getValue());
		retval.setCountrycode((String) location.getCountry().getValue().getData("countrycode"));
		return retval;
	}

}
