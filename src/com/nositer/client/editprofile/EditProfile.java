package com.nositer.client.editprofile;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.generated.User;
import com.nositer.client.register.Register;
import com.nositer.client.top.TopPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.shared.ServiceBroker;

public class EditProfile extends LayoutContainer {
	private Register register;
	private FormPanel formPanel;
	public EditProfile() {
		init();
	}

	public void init() {
		register = new Register();
		register.initFormPanel();
		formPanel = register.getFormPanel();

		TableLayout layout = new TableLayout(1);
		//layout.setHeight("100%");
		//layout.setWidth("100%");
		this.setLayout(layout);
		this.setHeight("100%");
		this.setStyleName("editProfile");
	
		formPanel.layout();
		
		this.add(formPanel);
		if (TopPanel.getInstance().getUser() != null) {
			populate(TopPanel.getInstance().getUser());
		} else {
			AsyncCallback<User> callback = new AsyncCallback<User>() {

				@Override
				public void onFailure(Throwable caught) {
					GWTUtil.log("", caught);
				}

				@Override
				public void onSuccess(User result) {
					if (result != null) {
						populate(result);
					}
				}
			};
			ServiceBroker.profileService.getCurrentUser(callback);
		}		
	}

	public void populate(User user) {
		

	}
}
