package com.nositer.client.viewprofile;

import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.nositer.client.dto.generated.User;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.util.ImageHelper;
import com.nositer.client.widget.Avatar;

/**
 * 
 * Contains form and avatar
 *
 */
public class Quickstats extends LayoutContainer {
	private LabelField firstname;	
	private LabelField lastname;
	private LabelField location;
	private LabelField email;
	private LabelField gender;
	private LabelField birthdate;
	private LabelField profession;
	private Avatar avatar;
	private LayoutContainer formContainer;
	public Quickstats() {
		init();
	}

	public void init() {
		FormLayout formContainerLayout = new FormLayout();
		formContainer = new LayoutContainer();
		formContainer.setLayout(formContainerLayout);
		
		firstname = createProfileLabelField("First name");
		lastname = createProfileLabelField("Last name");
		location = createProfileLabelField("Location");
		email = createProfileLabelField("Email");
		gender = createProfileLabelField("Gender");
		birthdate = createProfileLabelField("Birth Date");		
		profession = createProfileLabelField("Profession");

		formContainer.add(firstname);
		formContainer.add(lastname);		
		formContainer.add(location);
		formContainer.add(email);
		formContainer.add(gender);
		formContainer.add(birthdate);
		formContainer.add(profession);
		
		this.setLayout(new HBoxLayout());
		this.add(formContainer);
		avatar = new Avatar();
		this.add(avatar, new HBoxLayoutData(new Margins(0, 0, 0, 5)));
	}
	
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
	
	public LabelField createProfileLabelField(String fieldLabel) {
		LabelField retval = new LabelField();
		retval.setLabelSeparator(":");
		retval.setStyleName("profileLabelField");
		retval.setFieldLabel(fieldLabel);
		return retval;
	}
}
