package com.nositer.client.creategroup;

import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.Resizable;

public class CreateGroup extends LayoutContainer implements Resizable {
	private FormPanel formPanel;

	public CreateGroup() {
		init();
	}

	public void init() {
		formPanel = new FormPanel();
		
		formPanel.setHeaderVisible(false);
		addLabel("Create Group"); 
		add(formPanel);
		resize(0, 0);
	}

	@Override
	public void resize(int width, int height) {
		formPanel.setHeight(MainPanel.getInstance().getHeight());
	}

	private void addLabel(String labelName) {
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setMargins(new Margins(10, 0, 10, 0));
		LayoutContainer layoutContainer = new LayoutContainer(flowLayout);
		Label label = new Label(labelName);
		layoutContainer.add(label);
		formPanel.add(layoutContainer);
	}
}
