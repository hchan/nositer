package com.nositer.client.widget.radiogroup;

import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;

public class GenderRadioGroup extends RadioGroup {
	public static final String MALE = "MALE";
	public static final String FEMALE = "FEMALE";
	public static final String GENDERKEY = "GENDERKEY";
	private Radio maleRadio;
	private Radio femaleRadio;
	
	public GenderRadioGroup() {
		this.setFieldLabel("Gender");
		maleRadio = new Radio();
		maleRadio.setBoxLabel("Male");
		maleRadio.setData(GENDERKEY, MALE);
		
		femaleRadio = new Radio();
		femaleRadio.setBoxLabel("Female");
		femaleRadio.setData(GENDERKEY, FEMALE);
		
		add(maleRadio);
		add(femaleRadio);
	}
	
	public String getGender() {
		String retval = null;
		if (getValue() != null) {
			retval = getValue().getData(GENDERKEY);
		}
		return retval;
	}
}
