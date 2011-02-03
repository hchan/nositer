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
	
	private TabItem profileTabItem;
	public TabItem getProfileTabItem() {
		return profileTabItem;
	}

	public void setProfileTabItem(TabItem profileTabItem) {
		this.profileTabItem = profileTabItem;
	}

	public ViewProfileTabPanel() {
		init();
	}
	
	public void init() {
		setAutoHeight(true);
		setAutoWidth(true);


		profileTabItem = new TabItem("My Profile");  

		profileTabItem.setClosable(false);
		
		/*
		TableLayout layout = new TableLayout(1);
		layout.setHeight("100%");
		layout.setWidth("100%");
		*/
		FitLayout layout = new FitLayout();
		profileTabItem.setLayout(layout);
	
		
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
