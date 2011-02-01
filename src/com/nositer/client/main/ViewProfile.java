package com.nositer.client.main;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.nositer.client.dto.generated.User;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.util.ImageHelper;
import com.nositer.client.widget.Avatar;

public class ViewProfile extends LayoutContainer {

	LayoutContainer quickStats;
	private LabelField firstname;	
	private LabelField lastname;
	private LabelField location;
	private LabelField email;
	private LabelField gender;
	private LabelField birthdate;
	private LabelField profession;
	private Avatar avatar;
	public void populate(User user) {

		firstname.setValue(user.getFirstname());
		lastname.setValue(user.getLastname());
		email.setValue(user.getEmail());
		String genderStr = null;
		if (user.getGendermale() != null) {
			if (user.getGendermale()) {
				genderStr = "Male";
			} else {
				genderStr = "Female";
			}
		}
		gender.setValue(genderStr);
		birthdate.setValue(GWTUtil.getFormattedDate(user.getBirthdate()));
		profession.setValue(user.getProfession());


		if (user.getPostalcode() != null) {
			location.setValue(user.getPostalcode().getCity() + ", " + user.getPostalcode().getProvince() + " " + user.getCountrycode());
		} else {
			location.setValue(user.getZipcode().getCity() + ", " + user.getZipcode().getState() + " " + user.getCountrycode());
		}


		if (user.getAvatarlocation() == null) { 
			avatar.setPathToImage(ImageHelper.UNKNOWNAVATAR);
		} else {
			avatar.setPathToImage(user.getAvatarlocation());
		}

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
		avatar = new Avatar();
		this.add(avatar);

	}

	public LabelField createProfileLabelField(String fieldLabel) {
		LabelField retval = new LabelField();
		retval.setLabelSeparator(":");
		retval.setFieldLabel(fieldLabel);
		return retval;
	}

}
