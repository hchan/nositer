package com.nositer.client.widget;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.fx.FxConfig;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.Element;

public class ErrorPanel extends LayoutContainer {

	private Label header;
	private LayoutContainer errorMessages;
	private boolean hidden = false;
	private ArrayList<String> errors = null;
	public Label getHeader() {
		return header;
	}

	public void setHeader(Label header) {
		this.header = header;
	}

	public LayoutContainer getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(LayoutContainer errorMessages) {
		this.errorMessages = errorMessages;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public ErrorPanel() {
		init();
	}

	
	
	public void init() {
		errors = new ArrayList<String>();
		header = new Label("Errors");
		header.setId("errorHeader");
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
		errors.clear();
	}

	public void addErrorMessage(String str) {
		Label label = new Label("&rArr; " + str);
		label.setStyleName("errorMessages");
		errorMessages.add(label);
		errors.add(str);
	}
	
	public ArrayList<String> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<String> errors) {
		clearErrorMessages();
		for (String str : errors) {
			addErrorMessage(str);
		}
	}
	
	
	@Override
	public void hide() {
		hidden = true;
		super.hide();
	}
	
	@Override
	public void show() {
		hidden = false;
		super.show();
		this.layout();
		this.el().fadeIn(FxConfig.NONE);
	}

	public void resetHeight() {
		setHeight(25 + this.getErrors().size() * 20);
	}
}
