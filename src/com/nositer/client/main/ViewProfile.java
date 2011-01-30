package com.nositer.client.main;

import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.nositer.client.dto.generated.User;

public class ViewProfile extends LayoutContainer {
	private static ViewProfile instance;
	private Label firstname;
	public static ViewProfile show(User user) {
		if (instance == null) {
			createInstance();
		}
		instance.firstname.setText(user.getFirstname());
		return instance;
	}

	private static void createInstance() {
		instance = new ViewProfile();
	}

	public ViewProfile() {
		init();
	}


	private void init() {
		firstname = new Label();
		this.add(firstname);
	}

}
