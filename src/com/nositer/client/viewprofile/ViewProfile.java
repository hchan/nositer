package com.nositer.client.viewprofile;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
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
	private HtmlContainer description;

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
		
		if (user.getDescription() != null) {
			description.setHtml(user.getDescription());
		}
	}

	public ViewProfile() {
		init();
	}


	private void init() {
		//this.setAutoHeight(true);
		//this.setAutoWidth(true);
		setHeight(100000);
		setWidth(100000);
		this.setLayout(new RowLayout(Orientation.VERTICAL));
		LayoutContainer quickStatsWithAvatar = createQuickStatsWithAvatar();
		this.add(quickStatsWithAvatar, new RowData(-1, -1, new Margins(5)));
		description = new HtmlContainer();
		this.add(description, new RowData(-1, -1, new Margins(5)));
	}

	private LayoutContainer createQuickStatsWithAvatar() {
		LayoutContainer retval = new LayoutContainer();
		retval.setLayout(new HBoxLayout());
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
		retval.add(quickStats);
		avatar = new Avatar();
		retval.add(avatar);
		return retval;
	}

	public LabelField createProfileLabelField(String fieldLabel) {
		LabelField retval = new LabelField();
		retval.setLabelSeparator(":");
		retval.setStyleName("profileLabelField");
		retval.setFieldLabel(fieldLabel);
		return retval;
	}

}
