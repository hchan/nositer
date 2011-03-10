package com.nositer.client.widget;

import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
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
import com.nositer.client.dto.generated.Postalcode;
import com.nositer.client.dto.generated.User;
import com.nositer.client.dto.generated.Zipcode;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.combobox.PostalcodeComboBox;
import com.nositer.client.widget.combobox.ZipcodeComboBox;

@SuppressWarnings("unchecked")
public class Location extends FieldSet {
	
	
	public static final String COUNTRYCODE_USA = "USA";
	public static final String COUNTRYCODE_CAN = "CAN";
	public static final String COUNTRYCODE = "countrycode";
	private PostalcodeComboBox postalcode;
	private ZipcodeComboBox zipcode;
	private LayoutContainer geographyCode;
	private RadioGroup country;
	private Radio canRadio;
	private Radio usaRadio;
	
	public Radio getCanRadio() {
		return canRadio;
	}

	public void setCanRadio(Radio canRadio) {
		this.canRadio = canRadio;
	}

	public Radio getUsaRadio() {
		return usaRadio;
	}

	public void setUsaRadio(Radio usaRadio) {
		this.usaRadio = usaRadio;
	}

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

	
	public void init() {	
		postalcode = new PostalcodeComboBox();
		postalcode.setFieldLabel("* Postal code");
		postalcode.setStyleName(GWTUtil.getRequiredFieldStyle());

		zipcode = new ZipcodeComboBox();
		zipcode.setFieldLabel("* Zip code");
		zipcode.setStyleName(GWTUtil.getRequiredFieldStyle());

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
		canRadio = new Radio();
		canRadio.setValue(true);
		canRadio.setBoxLabel("Canada");
		canRadio.setData(COUNTRYCODE, COUNTRYCODE_CAN);
		usaRadio = new Radio();
		usaRadio.setBoxLabel(COUNTRYCODE_USA);
		usaRadio.setData(COUNTRYCODE, COUNTRYCODE_USA);
		country.add(canRadio);
		country.add(usaRadio);
		add(country, new VBoxLayoutData(new Margins(5, 0, 0, 5)));
		add(geographyCode, new VBoxLayoutData(new Margins(5, 0, 0, 5)));	
		setHeight(80);
	}

	
	private void initGeographyCode() {
		geographyCode = new LayoutContainer();
		geographyCode.setLayout(new FormLayout());
		geographyCode.add(postalcode);
	}


	public void populate(User user) {
		if (user.getCountrycode().equals(Location.COUNTRYCODE_CAN)) {
			country.setValue(getCanRadio());
			BeanModel beanModel = BeanModelLookup.get().getFactory(Postalcode.class).createModel(user.getPostalcode());
			postalcode.setValue(beanModel);
			postalcode.setRawValue(user.getPostalcode().getCode());
		} else {
			country.setValue(getUsaRadio());
			BeanModel beanModel = BeanModelLookup.get().getFactory(Zipcode.class).createModel(user.getZipcode());
			zipcode.setValue(beanModel);
			zipcode.setRawValue(user.getZipcode().getCode());
		}
	}
}
