package com.nositer.client.widget.radiogroup;

import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;

public class YesNoRadioGroup extends RadioGroup {

	private static final String KEY = "KEY";
	private Radio yesRadio;
	private Radio noRadio;



	public YesNoRadioGroup(String fieldLabel) {
		this.setFieldLabel(fieldLabel);
		yesRadio = new Radio();
		yesRadio.setBoxLabel("Yes");
		yesRadio.setData(KEY, YesNoType.YES);

		noRadio = new Radio();
		noRadio.setBoxLabel("No");
		noRadio.setData(KEY, YesNoType.NO);

		add(yesRadio);
		add(noRadio);
	}

	public void setYesNo(YesNoType yesNoType) {
		if (yesNoType != null) {
			if (yesNoType.equals(YesNoType.YES)) {
				setValue(yesRadio);
			} else {
				setValue(noRadio);
			}
		}
	}

	public YesNoType getYesNo() {
		YesNoType retval = null;
		if (getValue() != null) {
			retval = getValue().getData(KEY);
		}
		return retval;
	}

	public boolean getValueAsBoolean() {
		boolean retval = false;
		retval = getYesNo().equals(YesNoType.YES);
		return retval;
	}
	
	public enum YesNoType {
		YES, NO
	}
}
