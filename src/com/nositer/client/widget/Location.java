package com.nositer.client.widget;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import com.nositer.client.register.Register;
import com.nositer.client.widget.combobox.PostalcodeComboBox;
import com.nositer.client.widget.combobox.ZipcodeComboBox;

public class Location extends FieldSet {
	
	
	public static final String COUNTRYCODE_USA = "USA";
	public static final String COUNTRYCODE_CAN = "CAN";
	public static final String COUNTRYCODE = "countrycode";
	private PostalcodeComboBox postalcode;
	private ZipcodeComboBox zipcode;
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


	public ZipcodeComboBox getZipcode() {
		return zipcode;
	}

	public void setZipcode(ZipcodeComboBox zipcode) {
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

		zipcode = new ZipcodeComboBox();
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
				if (radioGroup.getValue().getData(COUNTRYCODE).equals(COUNTRYCODE_CAN)) {
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
		can.setData(COUNTRYCODE, COUNTRYCODE_CAN);
		Radio usa = new Radio();
		usa.setBoxLabel(COUNTRYCODE_USA);
		usa.setData(COUNTRYCODE, COUNTRYCODE_USA);
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