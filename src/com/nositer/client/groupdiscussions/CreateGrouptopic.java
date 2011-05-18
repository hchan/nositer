package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.HtmlEditor;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.FillData;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.Resizable;

public class CreateGrouptopic extends ContentPanel implements Resizable {

	private GroupDiscussionsContainer groupDiscussionsContainer;
	private Label theHeading;
	private FormPanel formPanel;	
	private TextField<String> name;
	private HtmlEditor description;
	private Button saveButton;
	
	public CreateGrouptopic(GroupDiscussionsContainer groupDiscussionsContainer) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
		formPanel = new FormPanel();
	}

	public void populateMainPanel() {
		this.setHeaderVisible(false);
		
		theHeading = new Label("Create New Topic");
		theHeading.setStyleName("formHeading");
		groupDiscussionsContainer.getGroupDiscussionMainPanel().removeAll();
		this.add(theHeading, new MarginData(5, 0, 0, 5));
		
		formPanel.setBodyBorder(false);
		formPanel.setHeaderVisible(false);
		formPanel.setWidth("100%");
		formPanel.setLabelAlign(LabelAlign.TOP);
		name = new TextField<String>();
		name.setFieldLabel("Topic name");
		description = new HtmlEditor();
		description.setFieldLabel("Message");
		formPanel.add(name, new FormData("100%"));
		formPanel.add(description, new FormData("100%"));
		saveButton = new Button("Save");
		formPanel.addButton(saveButton);
		this.add(formPanel, new MarginData(5, 5, 65, 0));
		groupDiscussionsContainer.getGroupDiscussionMainPanel().add(this);
		resize(0,0);
	}
	

	@Override
	public void resize(int width, int height) {
		
		GWTUtil.log("WIDTH:" + MainPanel.getInstance().getWidth());
		GWTUtil.log("WIDTH22:" + groupDiscussionsContainer.getGroupDiscussionLeftPanel().getGrouptoolsTabItem().getTabPanel().getWidth());
		int newWidth = MainPanel.getInstance().getWidth() - groupDiscussionsContainer.getGroupDiscussionLeftPanel().getGrouptoolsTabItem().getTabPanel().getWidth();
		GWTUtil.log("newWidth: " + newWidth);
		this.setWidth(newWidth - 15);
		
		formPanel.setWidth(newWidth - 15);
		setDescriptionHeight();
		formPanel.layout();
		
		this.setWidth(newWidth - 10);
		
		
		layout();
		//groupDiscussionsContainer.getGroupDiscussionMainPanel().add(this);
		groupDiscussionsContainer.getGroupDiscussionMainPanel().setWidth(newWidth);
		groupDiscussionsContainer.getGroupDiscussionMainPanel().layout();
	}
	
	private void setDescriptionHeight() {
		
		setHeight(MainPanel.getInstance().getHeight()-60);
		description.setHeight(getHeight()-150);
		//int heightOfComponents = 150;
		//if (errorPanel.isRendered() && !errorPanel.isHidden()) {
		//	heightOfComponents = heightOfComponents + errorPanel.getHeight();
		//}
		//description.setHeight(MainPanel.getInstance().getHeight() - heightOfComponents);
	}
}
