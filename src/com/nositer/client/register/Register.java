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
import com.google.gwt.user.client.ui.RootPanel;
import com.nositer.client.main.MainPanel;
import com.nositer.client.top.TopPanel;

public class Register implements EntryPoint {

	private static Register instance;
	private TopPanel topPanel;
	private MainPanel mainPanel;
	private LayoutContainer layoutContainer;
	private FormPanel formPanel;
	private Button saveButton;
	private Button cancelButton;
	
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
		TextField<String> firstName = new TextField<String>();  
		firstName.setFieldLabel("* Firstname");  
		firstName.setLabelStyle(getRequiredFieldStyle());
		
		TextField<String> lastName = new TextField<String>();  
		lastName.setFieldLabel("* Lastname");  
		lastName.setLabelStyle(getRequiredFieldStyle());
		
		TextField<String> login = new TextField<String>();  
		login.setFieldLabel("* Login name");  
		login.setLabelStyle(getRequiredFieldStyle());
		
		TextField<String> password = new TextField<String>();  
		password.setFieldLabel("* Password");  
		password.setLabelStyle(getRequiredFieldStyle());
		password.setPassword(true);
		
		TextField<String> passwordAgain = new TextField<String>();  
		passwordAgain.setFieldLabel("* Password Again");  
		passwordAgain.setLabelStyle(getRequiredFieldStyle());
		passwordAgain.setPassword(true);
		
		Location location = new Location();
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
		cancelButton = new Button("Cancel");
		cancelButton.addListener(Events.Select, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				Window.Location.assign("/Nositer.html");
			}
		});
		formPanel.addButton(cancelButton);  
	}

}
