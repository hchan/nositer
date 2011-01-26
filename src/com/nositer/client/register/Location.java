package com.nositer.client.register;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import com.nositer.client.widget.combobox.PostalcodeComboBox;

public class Location extends FieldSet {
	private PostalcodeComboBox postalcode;
	private TextField<String> zipcode;
	private LayoutContainer geographyCode;
	private RadioGroup country;
	public RadioGroup getCountry() {
		return country;
	}

	public void setCountry(RadioGroup country) {
		this.country = country;
	}

	public PostalcodeComboBox getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(PostalcodeComboBox postalcode) {
		this.postalcode = postalcode;
	}

	public TextField<String> getZipcode() {
		return zipcode;
	}

	public void setZipcode(TextField<String> zipcode) {
		this.zipcode = zipcode;
	}

	public LayoutContainer getGeographyCode() {
		return geographyCode;
	}

	public void setGeographyCode(LayoutContainer geographyCode) {
		this.geographyCode = geographyCode;
	}

	public Location() {
		init();
	}

	
	private void init() {	
		postalcode = new PostalcodeComboBox();
		postalcode.setFieldLabel("* Postal code");
		postalcode.setStyleName(Register.getRequiredFieldStyle());

		zipcode = new TextField<String>();
		zipcode.setFieldLabel("* Zip code");
		zipcode.setStyleName(Register.getRequiredFieldStyle());

		initGeographyCode();


		setStyleName("registerLocation");
		setLayout(new VBoxLayout());		
		setHeading("* Location");
		country = new RadioGroup();

		country.addListener(Events.Change, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				FieldEvent fieldEvent = (FieldEvent)be;
				RadioGroup radioGroup = (RadioGroup) fieldEvent.getSource();
				geographyCode.removeAll();
				if (radioGroup.getValue().getData("countrycode").equals("CAN")) {
					geographyCode.add(postalcode);
				} else {
					geographyCode.add(zipcode);
				}
				geographyCode.layout();
			}
		});
		Radio can = new Radio();
		can.setValue(true);
		can.setBoxLabel("Canada");
		can.setData("countrycode", "CAN");
		Radio usa = new Radio();
		usa.setBoxLabel("USA");
		usa.setData("countrycode", "USA");
		country.add(can);
		country.add(usa);
		add(country, new VBoxLayoutData(new Margins(5, 0, 0, 5)));
		add(geographyCode, new VBoxLayoutData(new Margins(5, 0, 0, 5)));
		setHeight(80);
	}

	
	private void initGeographyCode() {
		geographyCode = new LayoutContainer();
		geographyCode.setLayout(new FormLayout());
		geographyCode.add(postalcode);
	}
}
