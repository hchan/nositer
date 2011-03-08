package com.nositer.client.searchforgroups;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.nositer.client.widget.Location;

public class SearchCriteriaForGroupsPanel extends FormPanel {

	private TextField<String> groupName;
	private Location location;
	private Button searchButton;
	
	public TextField<String> getGroupName() {
		return groupName;
	}

	public void setGroupName(TextField<String> groupName) {
		this.groupName = groupName;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public SearchCriteriaForGroupsPanel() {
		init();
	}

	public void init() {
		//setHeaderVisible(false);
		setHeading("Group Search");
		setCollapsible(true);
		setBodyBorder(false);
		//addSearchLabel();
		setLabelWidth(107);
		setFieldWidth(200);
		groupName = new TextField<String>();
		groupName.setFieldLabel("Group name");
		location = new Location();
		location.setHeading("Location");
		location.setStyleName(groupName.getStyleName());
		location.getPostalcode().setFieldLabel("Postal code");		
		location.getZipcode().setFieldLabel("Zip code");
		searchButton = createSearchButton();
		
		add(groupName);
		add(location);
		
		setButtonAlign(HorizontalAlignment.CENTER);
		addButton(searchButton);
	}

	private Button createSearchButton() {
		Button retval = new Button("Search");
		retval.addSelectionListener(new SelectionListener<ButtonEvent>() {			
			@Override
			public void componentSelected(ButtonEvent ce) {
				// TODO Auto-generated method stub				
			}					
		});
		return retval;
	}



}
