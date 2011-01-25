package com.nositer.client.widget;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.fx.FxConfig;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;

public class ErrorPanel extends LayoutContainer {

	private Label header;
	private LayoutContainer errorMessages;

	public ErrorPanel() {
		init();
	}

	public void init() {
		header = new Label("Errors");
		header.setStyleName("errorHeader");
		errorMessages = new LayoutContainer(new TableLayout(1));
		TableLayout layout = new TableLayout(1);  
		layout.setCellHorizontalAlign(HorizontalAlignment.CENTER);
		layout.setWidth("100%");
		layout.setHeight("100%");
		this.setStyleName("errorPanel");
		this.setLayout(layout);
		this.add(header);
		this.add(errorMessages);
		this.layout();
	}

	public void clearErrorMessages() {
		errorMessages.removeAll();
	}

	public void addErrorMessage(String str) {
		Label label = new Label("&rArr; " + str);
		label.setStyleName("errorMessages");
		errorMessages.add(label);
	}
	
	public void setErrors(ArrayList<String> errors) {
		clearErrorMessages();
		for (String str : errors) {
			addErrorMessage(str);
		}
	}
	
	
	@Override
	public void hide() {
		super.hide();
	}
	
	@Override
	public void show() {
		super.show();
		this.layout();
		this.el().fadeIn(FxConfig.NONE);
	}
}
