package com.nositer.client.searchforgroups;

import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;

public class SearchCriteriaForGroupsPanel extends FormPanel {

	private TextField<String> groupName;
	
	public SearchCriteriaForGroupsPanel() {
		init();
	}

	public void init() {
		//setHeaderVisible(false);
		setHeading("Group Search");
		setCollapsible(true);
		setBodyBorder(false);
		//addSearchLabel();
		groupName = new TextField<String>();
		groupName.setFieldLabel("Group name");
		add(groupName);
	}
	
	@Deprecated
	// use setHeading
	private void addSearchLabel() {
		FlowLayout flowLayoutRequiredFieldLabelContainer = new FlowLayout();
		flowLayoutRequiredFieldLabelContainer.setMargins(new Margins(0, 0, 10, 0));
		LayoutContainer requiredFieldLabelContainer = new LayoutContainer(flowLayoutRequiredFieldLabelContainer);
		Label requiredFieldLabel = new Label("Search");
		requiredFieldLabel.setStyleName("requiredFieldLabel");
		requiredFieldLabelContainer.add(requiredFieldLabel);
		add(requiredFieldLabelContainer);
	}

}
