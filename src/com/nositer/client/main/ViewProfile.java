package com.nositer.client.main;

import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import com.nositer.client.dto.generated.User;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.Unknownavatar;
import com.nositer.shared.ServiceBroker;

public class ViewProfile extends LayoutContainer {
	private static ViewProfile instance;
	LayoutContainer quickStats;
	private LabelField firstname;	
	private LabelField lastname;
	private LabelField location;
	private LabelField email;
	private LabelField gender;
	private LabelField birthdate;
	private LabelField profession;
	public static ViewProfile show(User user) {
		if (instance == null) {
			createInstance();
		}
		instance.firstname.setValue(user.getFirstname());
		instance.lastname.setValue(user.getLastname());
		instance.email.setValue(user.getEmail());
		String gender = null;
		if (user.getGendermale() != null) {
			if (user.getGendermale()) {
				gender = "Male";
			} else {
				gender = "Female";
			}
		}
		instance.gender.setValue(gender);
		instance.birthdate.setValue(GWTUtil.getFormattedDate(user.getBirthdate()));
		instance.profession.setValue(user.getProfession());
		

		if (user.getPostalcode() != null) {
			instance.location.setValue(user.getPostalcode().getCity() + ", " + user.getPostalcode().getProvince() + " " + user.getCountrycode());
		} else {
			instance.location.setValue(user.getZipcode().getCity() + ", " + user.getZipcode().getState() + " " + user.getCountrycode());
		}
		return instance;
	}

	private static void createInstance() {
		instance = new ViewProfile();
	}

	public ViewProfile() {
		init();
	}


	private void init() {
		this.setLayout(new HBoxLayout());
		quickStats = new LayoutContainer();
		FormLayout quickStatsLayout = new FormLayout();
		quickStats.setLayout(quickStatsLayout);
		
		firstname = createProfileLabelField("First name");
		lastname = createProfileLabelField("Last name");
		location = createProfileLabelField("Location");
		email = createProfileLabelField("Email");
		gender = createProfileLabelField("Gender");
		birthdate = createProfileLabelField("Birth Date");		
		profession = createProfileLabelField("Profession");
		
		quickStats.add(firstname);
		quickStats.add(lastname);		
		quickStats.add(location);
		quickStats.add(email);
		quickStats.add(gender);
		quickStats.add(birthdate);
		quickStats.add(profession);
		this.add(quickStats);
		this.add(new Unknownavatar());
		
	}

	public LabelField createProfileLabelField(String fieldLabel) {
		LabelField retval = new LabelField();
		retval.setLabelSeparator(":");
		retval.setFieldLabel(fieldLabel);
		return retval;
	}
	   
}
