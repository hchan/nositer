package com.nositer.client.widget;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.fx.FxConfig;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.Element;

public class WarningPanel extends LayoutContainer {

	private Label header;
	private LayoutContainer warningMessages;
	private boolean hidden = false;
	
	public Label getHeader() {
		return header;
	}

	public void setHeader(Label header) {
		this.header = header;
	}

	

	public LayoutContainer getWarningMessages() {
		return warningMessages;
	}

	public void setWarningMessages(LayoutContainer warningMessages) {
		this.warningMessages = warningMessages;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public WarningPanel() {
		//init();
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		init();
	}
	
	public void init() {
		header = new Label("Warnings");
		header.setStyleName("warningHeader");
		warningMessages = new LayoutContainer(new TableLayout(1));
		TableLayout layout = new TableLayout(1);  
		layout.setCellHorizontalAlign(HorizontalAlignment.CENTER);
		layout.setWidth("100%");
		layout.setHeight("100%");
		this.setStyleName("warningPanel");
		this.setLayout(layout);
		this.add(header);
		this.add(warningMessages);
		this.layout();
	}

	public void clearWarningMessages() {
		warningMessages.removeAll();
	}

	public void addWarningMessage(String str) {
		Label label = new Label("&rArr; " + str);
		label.setStyleName("warningMessages");
		warningMessages.add(label);
	}
	
	public void setWarnings(ArrayList<String> warnings) {
		clearWarningMessages();
		for (String str : warnings) {
			addWarningMessage(str);
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
}
