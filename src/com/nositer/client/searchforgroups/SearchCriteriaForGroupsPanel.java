package com.nositer.client.searchforgroups;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.Nositer;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.Postalcode;
import com.nositer.client.dto.generated.User;
import com.nositer.client.dto.generated.Zipcode;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.ErrorPanel;
import com.nositer.client.widget.Location;

@SuppressWarnings("unchecked")
public class SearchCriteriaForGroupsPanel extends FormPanel {

	private TextField<String> groupName;
	private NumberField radius;
	private Location location;
	private Button searchButton;
	private ErrorPanel errorPanel;
	private Float latitude = null;
	private Float longitude = null;
	
	
	public NumberField getRadius() {
		return radius;
	}

	public void setRadius(NumberField radius) {
		this.radius = radius;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	private SearchGroupsGrid searchGroupsGrid;
	
	public SearchGroupsGrid getSearchGroupsGrid() {
		return searchGroupsGrid;
	}

	public void setSearchGroupsGrid(SearchGroupsGrid searchGroupsGrid) {
		this.searchGroupsGrid = searchGroupsGrid;
	}

	public Button getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(Button searchButton) {
		this.searchButton = searchButton;
	}

	public ErrorPanel getErrorPanel() {
		return errorPanel;
	}

	public void setErrorPanel(ErrorPanel errorPanel) {
		this.errorPanel = errorPanel;
	}

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
		errorPanel = new ErrorPanel();
		//errorPanel.init();
		errorPanel.hide();
		setHeading("Group Search");
		setCollapsible(true);
		setBodyBorder(false);
		//addSearchLabel();
		setLabelWidth(107);
		setFieldWidth(200);
		groupName = new TextField<String>();
		groupName.setFieldLabel("Group name");
		location = new Location() {
			public void init() {
				super.init();
				radius = new NumberField();
				radius.setValue(50);
				radius.setFieldLabel("Radius (km)");
				LayoutContainer radiusContainer = new LayoutContainer();
				radiusContainer.setLayout(new FormLayout());
				radiusContainer.add(radius);
				add(radiusContainer, new VBoxLayoutData(new Margins(5, 0, 0, 5)));				
				setHeight(120);				
			};
		};

		location.setHeading("Location");
		location.setStyleName(groupName.getStyleName());
		location.getPostalcode().setFieldLabel("Postal code");		
		location.getZipcode().setFieldLabel("Zip code");
		//location.populate(Nositer.getInstance().getUser());
	
		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				GWTUtil.log("", caught);
			}

			@Override
			public void onSuccess(Void result) {
				location.populate(Nositer.getInstance().getUser());
			}
		};
		ServiceBroker.noopService.noop(1000, callback);
		
		searchButton = createSearchButton();

		setTopComponent(errorPanel);
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
				doSearch();
			}					
		});
		return retval;
	}


	public void doSearch() {
		searchGroupsGrid.load();
		
		SearchForGroupsContainer.getInstance().resize(0, 0);
	}

	public ArrayList<String> getErrors() {
		ArrayList<String> retval = new ArrayList<String>();
		GWTUtil.addRequiredErrorIfNecessary(groupName, retval);
		if (latitude == null) {
			retval.add("A Postal code or zipcode must is required");
		}
		GWTUtil.addRequiredErrorIfNecessary(radius, "Radius", retval);
		if (location.getCountry().getValue().getData(Location.COUNTRYCODE).equals(Location.COUNTRYCODE_CAN)) {
			GWTUtil.addRequiredErrorIfNecessary(location.getPostalcode(), retval);
		} else {
			GWTUtil.addRequiredErrorIfNecessary(location.getZipcode(), retval);
		}
		return retval;
	}

}
