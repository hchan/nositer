package com.nositer.client.register;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.nositer.client.dto.generated.User;
import com.nositer.client.main.MainPanel;
import com.nositer.client.service.ServiceBroker;
import com.nositer.client.top.TopPanel;
import com.nositer.client.util.GWTUtil;

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
		mainPanel.add(formPanel);
	}

	public static String getRequiredFieldStyle() {
		return "color: red; font-weight: bold";
	}

	private void initFormPanel() {
		formPanel = new FormPanel();
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
				if (!(password.getValue().equals(passwordAgain.getValue()))) {
					// TODO need better alert
					Window.alert("Password and Password Again are not the same");
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
