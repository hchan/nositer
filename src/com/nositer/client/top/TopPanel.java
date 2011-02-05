package com.nositer.client.top;

import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.nositer.client.dto.generated.User;
import com.nositer.client.util.GWTUtil;
import com.nositer.shared.ServiceBroker;

public class TopPanel extends ContentPanel {	
	private static TopPanel instance;
	private User user;
	private Label youAreLoggedOnAs;
	private Label firstLastName;
	private Logout logout;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static TopPanel getInstance() {
		return instance;
	}

	public static void setInstance(TopPanel instance) {
		TopPanel.instance = instance;
	}

	private BorderLayoutData topLayoutData;
	private ContentPanel loggedInAs;
	private HTMLPanel welcomePanel;
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



	public TopPanel (BorderLayoutData topLayoutData) {		
		this.topLayoutData = topLayoutData;
		init();
		instance = this;
	}


	public void init() {
		setId("topPanel");
		loggedInAs = new ContentPanel();
		loggedInAs.setHeaderVisible(false);
		loggedInAs.setBorders(false);
		loggedInAs.setBodyBorder(false);
		loggedInAs.setLayout(new HBoxLayout());
		youAreLoggedOnAs = new Label("You are logged in as:");
		youAreLoggedOnAs.setStyleName("youAreLoggedOnAs");
		loggedInAs.add(youAreLoggedOnAs);
		firstLastName = new Label();
		HBoxLayoutData spacing = new HBoxLayoutData(new Margins(0, 5, 0, 0));  
		loggedInAs.add(firstLastName, spacing);	
		firstLastName.setStyleName("firstLastName");
		logout = new Logout();
		loggedInAs.add(logout);


		topLayoutData.setSize(50);
		//topLayoutData.setCollapsible(true);
		this.setHeaderVisible(false);
		welcomePanel = new HTMLPanel("Journey with us");
		welcomePanel.setStyleName("welcomeDescription");
		this.add(welcomePanel);
		this.add(loggedInAs);
		initFirstLastName();
	}

	private void initFirstLastName() {
		AsyncCallback<User> callback = new AsyncCallback<User>() {
			@Override
			public void onFailure(Throwable caught) {
				GWTUtil.log("", caught);
			}
			@Override
			public void onSuccess(User result) {
				if (result != null) {
					user = result;		
					setFirstLastName(user);	
					TopPanel.this.loggedInAs.remove(logout);
					TopPanel.this.loggedInAs.add(logout);
					TopPanel.this.layout();
				}
			}

		};
		ServiceBroker.profileService.getCurrentUser(callback);
	}

	public void setFirstLastName(User user) {
		firstLastName.setText(user.getFirstname() + " " + user.getLastname());
	}


}
