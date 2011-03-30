package com.nositer.client.groupsubscriptions;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.Nositer;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.groups.Groups;
import com.nositer.client.widget.radiogroup.YesNoRadioGroup;
import com.nositer.client.widget.radiogroup.YesNoRadioGroup.YesNoType;

public class MyGroupSubscription extends FormPanel {
	private GroupSubscriptionsContainer groupSubscriptionsContainer;
	private YesNoRadioGroup subscribeRadioGroup;
	private YesNoRadioGroup invisibleRadioGroup;
	private Button updateButton;

	public MyGroupSubscription(GroupSubscriptionsContainer groupSubscriptionsContainer) {
		this.groupSubscriptionsContainer = groupSubscriptionsContainer;
		init();
	}

	public void init() {
		setHeading("My Subscription Information");
		setCollapsible(true);
		setBodyBorder(false);
		if (Groups.isGroupIOwn(groupSubscriptionsContainer.getGroupPlusView())) {
			groupIOwnInit();
		} else {
			userInit();
		}
	}
	
	private void groupIOwnInit() {
		HtmlContainer htmlContainer = new HtmlContainer("You are the owner of this group");
		add(htmlContainer);
	}

	private void userInit() {
		subscribeRadioGroup = new YesNoRadioGroup("Subscribe");
		if (groupSubscriptionsContainer.getGroupPlusView().getUserid().equals(Nositer.getInstance().getUser().getId())) {
			subscribeRadioGroup.setYesNo(YesNoType.YES);
		} else {
			subscribeRadioGroup.setYesNo(YesNoType.NO);
		}
		add(subscribeRadioGroup);
		invisibleRadioGroup = new YesNoRadioGroup("Invisible");
		if (groupSubscriptionsContainer.getGroupPlusView().getInvisible() != null && 
				groupSubscriptionsContainer.getGroupPlusView().getInvisible().equals(true)) {
			invisibleRadioGroup.setYesNo(YesNoType.YES);
		} else {
			invisibleRadioGroup.setYesNo(YesNoType.NO);
		}
		add(invisibleRadioGroup);
		initUpdateButton();
		addButton(updateButton);
	}

	private void initUpdateButton() {
		setButtonAlign(HorizontalAlignment.CENTER);
		updateButton = new Button("Update");
		updateButton.addSelectionListener(new SelectionListener<ButtonEvent>() {			
			@Override
			public void componentSelected(ButtonEvent ce) {
				createOrUpdateSubscription();
			}					
		});
	}

	private void createOrUpdateSubscription() {
		AsyncCallback<Void> callback = new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		GroupPlusView groupPlusViewToUpdate =  groupSubscriptionsContainer.getGroupPlusView().clone();
		groupPlusViewToUpdate.setUserHasGroupDisable(subscribeRadioGroup.getValueAsBoolean());
		groupPlusViewToUpdate.setInvisible(invisibleRadioGroup.getValueAsBoolean());
		ServiceBroker.groupService.createOrUpdateSubscription(groupPlusViewToUpdate, callback);
	}
}
