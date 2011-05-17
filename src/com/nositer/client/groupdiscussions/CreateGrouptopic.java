package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.HtmlEditor;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
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
	
	
	public CreateGrouptopic(GroupDiscussionsContainer groupDiscussionsContainer) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
		formPanel = new FormPanel();
	}

	public void populateMainPanel() {
		
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
		this.add(formPanel, new MarginData(5, 5, 65, 0));
		groupDiscussionsContainer.getGroupDiscussionMainPanel().add(this);
		groupDiscussionsContainer.layout();
	}
	

	@Override
	public void resize(int width, int height) {
		
		GWTUtil.log("WIDTH:" + MainPanel.getInstance().getWidth());
		GWTUtil.log("WIDTH22:" + groupDiscussionsContainer.getGroupDiscussionLeftPanel().getGrouptoolsTabItem().getTabPanel().getWidth());
		int newWidth = MainPanel.getInstance().getWidth() - groupDiscussionsContainer.getGroupDiscussionLeftPanel().getGrouptoolsTabItem().getTabPanel().getWidth();
		this.setWidth(newWidth);
		formPanel.setWidth(newWidth - 5);
		setDescriptionHeight();
	}
	
	private void setDescriptionHeight() {
		//int heightOfComponents = 150;
		//if (errorPanel.isRendered() && !errorPanel.isHidden()) {
		//	heightOfComponents = heightOfComponents + errorPanel.getHeight();
		//}
		//description.setHeight(MainPanel.getInstance().getHeight() - heightOfComponents);
	}
}
