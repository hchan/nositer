package com.nositer.client.top;

import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.nositer.client.Nositer;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.User;
import com.nositer.client.util.GWTUtil;

public class TopPanel extends ContentPanel {	
	protected static TopPanel instance;
	private Label youAreLoggedOnAs;
	private Label firstLastName;
	private Logout logout;

	public static void setInstance(TopPanel instance) {
		TopPanel.instance = instance;
	}

	public static TopPanel getInstance() {
		return instance;
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
		this(topLayoutData, false);

	}


	public TopPanel(BorderLayoutData topLayoutData, boolean noUser) {
		this.topLayoutData = topLayoutData;
		init(noUser);
		instance = this;
	}

	public void init(boolean noUser) {
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
		if (!noUser) {
			this.add(loggedInAs);
			adjustComponents();
		}

	}

	public void adjustComponents() {
		setFirstLastName(Nositer.getInstance().getUser());	
		TopPanel.this.loggedInAs.remove(logout);
		TopPanel.this.loggedInAs.add(logout);
		TopPanel.this.layout();
	}

	private void initFirstLastNameNotUsed() {
		AsyncCallback<User> callback = new AsyncCallback<User>() {
			@Override
			public void onFailure(Throwable caught) {
				GWTUtil.log("", caught);
			}
			@Override
			public void onSuccess(User result) {
				GWTUtil.log("Inside success of initFirstLastName()");
				if (result != null) {
					Nositer.getInstance().setUser(result);		
					adjustComponents();
				}
			}
		};
		ServiceBroker.profileService.getCurrentUser(callback);
	}

	/*
	public void initUser() {
		AsyncCallback<User> callback = new AsyncCallback<User>() {
			@Override
			public void onFailure(Throwable caught) {
				GWTUtil.log("", caught);
			}
			@Override
			public void onSuccess(User result) {
				GWTUtil.log("Inside success of initFirstLastName()");
				if (result != null) {
					user = result;		
				}
			}
		};
		ServiceBroker.profileService.getCurrentUser(callback);
	}
	 */

	public void setFirstLastName(User user) {
		firstLastName.setText(user.getFirstname() + " " + user.getLastname());
	}


}
