package com.nositer.client.register;

import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Register implements EntryPoint {

	@Override
	public void onModuleLoad() {
		Viewport viewport = new Viewport();
		viewport.setLayout(new CenterLayout());
		FormPanel formPanel = new FormPanel();
		formPanel.setHeading("Registration");
		formPanel.setFrame(true);
		TextField<String> firstName = new TextField<String>();  
		firstName.setFieldLabel("* Name");  
		firstName.setLabelStyle("color: red");
		formPanel.add(firstName);
		viewport.add(formPanel);
		RootPanel.get().add(viewport);
	}

}
