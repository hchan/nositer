package com.nositer.client.createiwantto;

import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.Resizable;


public class CreateIWantTo extends LayoutContainer implements Resizable {


	private Label headingLabel;
	private ContentPanel contentPanel;
	private FormPanel formPanel;
	private TextField<String> iWantTo;

	public CreateIWantTo() {
		init();
	}
	
	public void init() {
		setLayout(new FitLayout());
		contentPanel = new ContentPanel();
		contentPanel.setBodyBorder(false);
		contentPanel.setHeaderVisible(false);
		VBoxLayout layout = new VBoxLayout();

		
		contentPanel.setLayout(layout);
		headingLabel = new Label("Create I Want To ...");
		headingLabel.setStyleName("formHeading");
		contentPanel.add(headingLabel, new VBoxLayoutData(new Margins (5,5,5,5)));
		
		formPanel = new FormPanel();
		formPanel.setBodyBorder(false);
		formPanel.setHeaderVisible(false);
		formPanel.setLabelSeparator("");
		iWantTo = new TextField<String>();
		iWantTo.setFieldLabel("I want to ");
		formPanel.add(iWantTo, new FormData("100%"));
		contentPanel.add(formPanel, new VBoxLayoutData());//new Margins (5,5,5,5)));
		add(contentPanel);
		resize(0,0);
	}
	
	@Override
	public void resize(int width, int height) {
		setSize(MainPanel.getInstance().getWidth(), MainPanel.getInstance().getHeight());
		contentPanel.setWidth(MainPanel.getInstance().getWidth());
		contentPanel.setHeight(MainPanel.getInstance().getHeight());
		formPanel.setWidth(MainPanel.getInstance().getWidth() - 10);
	}

}
