package com.nositer.client.top;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.util.GWTUtil;
import com.nositer.shared.ServiceBroker;
@SuppressWarnings({"rawtypes", "unchecked"})
public class Logout extends LayoutContainer {
	private Label logoutLabel;

	public Logout() {
		logoutLabel = new Label("Log out");
		logoutLabel.setStyleName("logout");

		logoutLabel.addListener(Events.OnClick, new Listener() {

			@Override
			public void handleEvent(BaseEvent be) {
				logout();
			}
		});
		add(logoutLabel);
	}

	public void logout() {
		AsyncCallback<Void> callback = new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				GWTUtil.log("", caught);
				Window.Location.reload();
			}

			@Override
			public void onSuccess(Void result) {
				Window.Location.reload();
			}

		};
		ServiceBroker.profileService.logout(callback);
	}
}
