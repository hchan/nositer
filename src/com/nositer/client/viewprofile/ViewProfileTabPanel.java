package com.nositer.client.viewprofile;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.generated.User;
import com.nositer.client.top.TopPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.shared.ServiceBroker;

public class ViewProfileTabPanel extends TabPanel {
	public ViewProfileTabPanel() {
		setAutoHeight(true);
		setAutoWidth(true);


		final TabItem profileTabItem = new TabItem("Profile");  

		profileTabItem.setClosable(false);
		TableLayout tableLayout = new TableLayout(1);
		tableLayout.setHeight("100%");
		tableLayout.setWidth("100%");
		profileTabItem.setLayout(tableLayout);
	
		
		profileTabItem.setScrollMode(Scroll.AUTO);
		profileTabItem.addListener(Events.Select, new Listener<ComponentEvent>() {  
			public void handleEvent(ComponentEvent be) {  
				final ViewProfile viewProfile = new ViewProfile();
				if (TopPanel.getInstance().getUser() != null) {
					viewProfile.populate(TopPanel.getInstance().getUser());
					profileTabItem.add(viewProfile);
					ViewProfileTabPanel.this.layout();
				} else {
					AsyncCallback<User> callback = new AsyncCallback<User>() {

						@Override
						public void onFailure(Throwable caught) {
							GWTUtil.log("", caught);
						}

						@Override
						public void onSuccess(User result) {
							if (result != null) {
								TopPanel.getInstance().setUser(result);
								viewProfile.populate(TopPanel.getInstance().getUser());
								profileTabItem.add(viewProfile);
								ViewProfileTabPanel.this.layout();
							}
						}
					};
					ServiceBroker.profileService.getCurrentUser(callback);
				}
			}  
		});  
		add(profileTabItem);
	}  
}
