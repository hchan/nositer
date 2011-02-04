package com.nositer.client.widget.radiogroup;

import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;

public class GenderRadioGroup extends RadioGroup {

	public static final String GENDERKEY = "GENDERKEY";
	private Radio maleRadio;
	private Radio femaleRadio;

	public Radio getMaleRadio() {
		return maleRadio;
	}

	public void setMaleRadio(Radio maleRadio) {
		this.maleRadio = maleRadio;
	}

	public Radio getFemaleRadio() {
		return femaleRadio;
	}

	public void setFemaleRadio(Radio femaleRadio) {
		this.femaleRadio = femaleRadio;
	}

	public GenderRadioGroup() {
		this.setFieldLabel("Gender");
		maleRadio = new Radio();
		maleRadio.setBoxLabel("Male");
		maleRadio.setData(GENDERKEY, GenderType.MALE);

		femaleRadio = new Radio();
		femaleRadio.setBoxLabel("Female");
		femaleRadio.setData(GENDERKEY, GenderType.FEMALE);

		add(maleRadio);
		add(femaleRadio);
	}

	public void setGender(GenderType genderType) {
		if (genderType != null) {
			if (genderType.equals(GenderType.MALE)) {
				setValue(maleRadio);
			} else {
				setValue(femaleRadio);
			}
		}
	}

	public GenderType getGender() {
		GenderType retval = null;
		if (getValue() != null) {
			retval = getValue().getData(GENDERKEY);
		}
		return retval;
	}

	public enum GenderType {
		MALE, FEMALE
	}
}
