package com.nositer.client.register;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import com.nositer.client.left.LeftPanel;
import com.nositer.client.main.MainPanel;
import com.nositer.client.top.TopPanel;

public class Register implements EntryPoint {

	private static Register instance;
	private TopPanel topPanel;
	private MainPanel mainPanel;
	private LayoutContainer layoutContainer;
	private FormPanel formPanel;

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

	private Viewport viewport;
	public void init() {
		viewport = new Viewport() {
			@Override
			protected void onRender(Element parent, int index) {
			
				super.onRender(parent, index);
				viewport.setLayout(new BorderLayout());
				initLayoutContainer();		
				BorderLayoutData borderLayoutData = new BorderLayoutData(LayoutRegion.CENTER);
				viewport.add(layoutContainer, borderLayoutData);
				borderLayoutData.setMargins(new Margins(1));
			}
		};
		
		
		instance = this;
		RootPanel.get().add(viewport);		
	}

	public void initLayoutContainer() {
		layoutContainer = new LayoutContainer() {
			@Override
			protected void onRender(Element parent, int index) {
			
				super.onRender(parent, index);
				BorderLayout borderLayout = new BorderLayout();
				layoutContainer.setLayout(borderLayout);
				BorderLayoutData topLayoutData = new BorderLayoutData(LayoutRegion.NORTH);  
				topPanel = new TopPanel(topLayoutData);		
				BorderLayoutData mainLayoutData = new BorderLayoutData(LayoutRegion.CENTER);  
				
				layoutContainer.add(topPanel, topLayoutData);		
				populateMainPanel(mainLayoutData);
				layoutContainer.add(mainPanel, mainLayoutData);	
			}
		};
	

	}

	private void populateMainPanel(BorderLayoutData mainLayoutData) {		
		mainPanel = new MainPanel(mainLayoutData) {
			@Override
			protected void onRender(Element parent, int pos) {
			
				super.onRender(parent, pos);
				initFormPanel();
				mainPanel.add(formPanel);
			};
		};
	
		
	}

	private String getRequiredFieldStyle() {
		return "color: red; font-weight: bold";
	}

	private void initFormPanel() {
		formPanel = new FormPanel() {
			@Override
			protected void onRender(Element target, int index) {
				super.onRender(target, index);  
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

				FieldSet location = new FieldSet();


				location.setStyleName("registerLocation");
				location.setHeading("* Location");



				LayoutContainer locationLayoutContainer = new LayoutContainer() {
					@Override
					protected void onRender(Element parent, int index) {
						super.onRender(parent, index);  
						RadioGroup country = new RadioGroup();
						Radio can = new Radio();
						can.setBoxLabel("Canada");
						can.setValue(true);
						Radio usa = new Radio();
						usa.setBoxLabel("USA");

						country.add(can);
						country.add(usa);
						add(country, new VBoxLayoutData(new Margins(5, 0, 0, 10)));
					}
				};
				locationLayoutContainer.setLayout(new VBoxLayout());
				locationLayoutContainer.setAutoHeight(true);

				location.add(locationLayoutContainer);

				formPanel.add(firstName);
				formPanel.add(lastName);
				formPanel.add(login);
				formPanel.add(password);
				formPanel.add(passwordAgain);
				formPanel.add(location);
				formPanel.setWidth(500);
			};
		};
		
	}

}
