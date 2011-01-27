package com.nositer.client.top;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.nositer.client.Nositer;
import com.nositer.client.dto.generated.User;
import com.nositer.client.util.GWTUtil;
import com.nositer.shared.ServiceBroker;

public class TopPanel extends ContentPanel {
	private Nositer nositer;
	private BorderLayoutData topLayoutData;
	private ContentPanel loggedInAs;
	public ContentPanel getLoggedInAs() {
		return loggedInAs;
	}

	public void setLoggedInAs(ContentPanel loggedInAs) {
		this.loggedInAs = loggedInAs;
	}

	public BorderLayoutData getTopLayoutData() {
		return topLayoutData;
	}

	public void setTopLayoutData(BorderLayoutData topLayoutData) {
		this.topLayoutData = topLayoutData;
	}

	public Nositer getNositer() {
		return nositer;
	}

	public void setNositer(Nositer nositer) {
		this.nositer = nositer;
	}

	public TopPanel (BorderLayoutData topLayoutData) {		
		this.topLayoutData = topLayoutData;
		init();
	}

	private void init() {
		setId("topPanel");
		loggedInAs = new ContentPanel();
		loggedInAs.setHeaderVisible(false);
		loggedInAs.setBorders(false);
		loggedInAs.setBodyBorder(false);
		AsyncCallback<User> callback = new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				GWTUtil.log("", caught);
			}

			@Override
			public void onSuccess(User result) {
				if (result != null) {
					loggedInAs.setLayout(new HBoxLayout());
					Label youAreLoggedOnAs = new Label("You are logged on as:");
					youAreLoggedOnAs.setStyleName("youAreLoggedOnAs");
					loggedInAs.add(youAreLoggedOnAs);
					Label firstLastName = new Label(result.getFirstname() + " " + result.getLastname());
					firstLastName.setStyleName("firstLastName");
					loggedInAs.add(firstLastName);
					TopPanel.this.layout();
				}
			}
		};
		ServiceBroker.profileService.getCurrentUser(callback);
		topLayoutData.setSize(50);
		//topLayoutData.setCollapsible(true);
		this.setHeaderVisible(false);
		HTMLPanel htmlPanel = new HTMLPanel("Journey with us");
		htmlPanel.setStyleName("welcomeDescription");
		this.add(htmlPanel);
		this.add(loggedInAs);
	}
}
