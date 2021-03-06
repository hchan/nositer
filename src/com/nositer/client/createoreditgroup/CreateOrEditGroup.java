package com.nositer.client.createoreditgroup;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.HtmlEditor;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.Scope;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.User;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.groups.EditGroupTabItem;
import com.nositer.client.groups.Groups;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.ErrorPanel;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.avatar.AvatarSelector;
import com.nositer.client.widget.messagebox.InfoMessageBox;

public class CreateOrEditGroup extends LayoutContainer implements Resizable {

	private Integer groupid;
	private boolean create = false;
	private FormPanel formPanel;
	private TextField<String> name;
	private TextField<String> tagname;
	private HtmlEditor description;
	private ErrorPanel errorPanel;
	private AvatarSelector avatarSelector;
	private EditGroupTabItem editGroupTabItem;

	
	public EditGroupTabItem getEditGroupTabItem() {
		return editGroupTabItem;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public boolean isCreate() {
		return create;
	}

	public void setCreate(boolean create) {
		this.create = create;
	}

	public FormPanel getFormPanel() {
		return formPanel;
	}

	public void setFormPanel(FormPanel formPanel) {
		this.formPanel = formPanel;
	}

	public TextField<String> getName() {
		return name;
	}

	public void setName(TextField<String> name) {
		this.name = name;
	}

	public TextField<String> getTagname() {
		return tagname;
	}

	public void setTagname(TextField<String> tagname) {
		this.tagname = tagname;
	}

	public HtmlEditor getDescription() {
		return description;
	}

	public void setDescription(HtmlEditor description) {
		this.description = description;
	}

	public ErrorPanel getErrorPanel() {
		return errorPanel;
	}

	public void setErrorPanel(ErrorPanel errorPanel) {
		this.errorPanel = errorPanel;
	}

	public AvatarSelector getAvatarSelector() {
		return avatarSelector;
	}

	public void setAvatarSelector(AvatarSelector avatarSelector) {
		this.avatarSelector = avatarSelector;
	}

	public CreateOrEditGroup(boolean create) {
		this.create = create;
		init();
	}

	public void init() {	
		formPanel = new FormPanel();


		formPanel.setHeaderVisible(false);
		//formPanel.setLabelWidth(200);
		formPanel.setLabelAlign(LabelAlign.TOP);
		//formPanel.setBorders(false);
		formPanel.setBodyBorder(false);
		errorPanel = new ErrorPanel();
		errorPanel.setStyleAttribute("margin-bottom", "5px");
		errorPanel.hide();
		tagname = new TextField<String>();
		tagname.setFieldLabel("* Tag Name");
		tagname.setLabelStyle("font-size: 14px; font-weight: bold; color: red");

		name = new TextField<String>();
		name.setFieldLabel("* Name");
		name.setLabelStyle("font-size: 14px; font-weight: bold; color: red");

		avatarSelector = new AvatarSelector(new Scope(Scope.Type.group));

		description = new HtmlEditor();
		setDescriptionHeight();

		description.setFieldLabel("Description");
		description.setLabelStyle("font-size: 14px; font-weight: bold;");
		formPanel.add(errorPanel);
		if (create) {
			addLabel("Create Group");
		} else {
			addLabel("Edit Group");
		}
		formPanel.add(tagname, new FormData("100%"));
		formPanel.add(name, new FormData("100%"));		
		if (!isCreate()) {
			FormData formDataAvatarSelector = new FormData();
			formDataAvatarSelector.setMargins(new Margins(5, 0, 5, 0));
			formPanel.add(avatarSelector, formDataAvatarSelector);			
		}
		formPanel.add(description, new FormData("100%"));	

		this.add(formPanel);
		initButtons();	
		/*
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
		 */
	}


	@Override
	public void resize(int width, int height) {
		formPanel.setWidth(this.getWidth());
		setDescriptionHeight();
	}

	public void setDescriptionHeight() {
		int heightOffset = getHeightOffset();
		if (errorPanel.isRendered() && !errorPanel.isHidden()) {
			heightOffset = heightOffset + errorPanel.getHeight();
		}
		description.setHeight(MainPanel.getInstance().getHeight() - heightOffset);
	}

	public int getHeightOffset() {
		int retval = 0;
		if (isCreate()) {
			retval = 225;
		} else {
			retval = 250;
		}		
		return retval;
	}


	public void initButtons() {
		initSaveButton();
		initCancelButton();
	}


	public void initSaveButton() {
		formPanel.setButtonAlign(HorizontalAlignment.CENTER);  
		Button button = new Button("Save");
		formPanel.addButton(button);  

		Listener<BaseEvent> saveListener = new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				ArrayList<String> errors = getErrors();
				if (errors.size() > 0) {
					errorPanel.setErrors(errors);
					errorPanel.show();		
					resize(0,0);
				} else {

					AsyncCallback<Group> callback = new AsyncCallback<Group>() {
						@Override
						public void onFailure(Throwable caught) {
							errorPanel.clearErrorMessages();
							errorPanel.addErrorMessage(caught.getMessage());
							errorPanel.show();
							resize(0,0);
							GWTUtil.log("", caught);
						}

						@Override
						public void onSuccess(final Group result) {
							InfoMessageBox.show("Saved!", new Listener<MessageBoxEvent>() {
								@Override
								public void handleEvent(MessageBoxEvent be) {		
									if (editGroupTabItem != null) {
										editGroupTabItem.getGroupTabPanel().getViewGroupTabItem().populate(result);
									}
									HistoryManager.addHistory(HistoryToken.GROUPS.toString());
									HistoryManager.addHistory(HistoryToken.GROUPS.toString() + HistoryManager.SUBTOKENSEPARATOR + result.getTagname());											
								}								
							});										
						}
					};
					if (isCreate()) {
						ServiceBroker.groupService.createGroup(createGroupDTO(), callback);
					} else {
						ServiceBroker.groupService.updateGroup(createGroupDTO(), callback);
					}
				}
			}
		};
		button.addListener(Events.Select, saveListener);
	}


	public void populate(GroupPlusView groupPlusView) {
		groupid = groupPlusView.getId();
		name.setValue(groupPlusView.getName());
		tagname.setValue(groupPlusView.getTagname());
		description.setValue(groupPlusView.getDescription());
		avatarSelector.getScope().setGroupPlusView(groupPlusView);
		avatarSelector.getSelectedFile().setValue(groupPlusView.getAvatarlocation());
	}

	private Group createGroupDTO() {
		Group retval = new Group();
		retval.setId(groupid);
		retval.setName(name.getValue());
		retval.setTagname(tagname.getValue());
		retval.setDescription(description.getValue());
		retval.setAvatarlocation(avatarSelector.getSelectedFile().getValue());
		return retval;
	}

	// Cancel
	public void initCancelButton() {
		Button cancelButton = new Button("Cancel");
		formPanel.addButton(cancelButton);  
		Listener<BaseEvent> cancelListener = new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				HistoryManager.addHistory(HistoryToken.GROUPS.toString());		
			}
		};
		cancelButton.addListener(Events.Select, cancelListener);
	}



	public ArrayList<String> getErrors() {
		ArrayList<String> retval = new ArrayList<String>();		
		GWTUtil.addRequiredErrorIfNecessary(tagname, retval);
		GWTUtil.addRequiredErrorIfNecessary(name, retval);				
		return retval;
	}

	private void addLabel(String labelName) {
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setMargins(new Margins(0, 0, 10, 0));
		LayoutContainer layoutContainer = new LayoutContainer(flowLayout);
		Label label = new Label(labelName);
		layoutContainer.add(label);
		layoutContainer.setStyleName("formHeading");
		formPanel.add(layoutContainer);
	}

	public void setEditGroupTabItem(EditGroupTabItem editGroupTabItem) {
		this.editGroupTabItem = editGroupTabItem;
	}
}
