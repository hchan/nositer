package com.nositer.client.createiwantto;

import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.Resizable;


public class CreateIWantTo extends LayoutContainer implements Resizable {

	private Label iWantToLabel;
	private Label headingLabel;
	private ContentPanel contentPanel;
	
	public Label getiWantToLabel() {
		return iWantToLabel;
	}

	public void setiWantToLabel(Label iWantToLabel) {
		this.iWantToLabel = iWantToLabel;
	}

	public CreateIWantTo() {
		init();
	}
	
	public void init() {
		setLayout(new FitLayout());
		contentPanel = new ContentPanel();
		contentPanel.setBodyBorder(false);
		contentPanel.setHeaderVisible(false);
		VBoxLayout layout = new VBoxLayout();

		contentPanel.setLayout(layout);
		headingLabel = new Label("Create I Want To ...");
		headingLabel.setStyleName("formHeading");
		contentPanel.add(headingLabel, new VBoxLayoutData(new Margins (5,5,5,5)));
		iWantToLabel = new Label("I want to ");
		contentPanel.add(iWantToLabel, new VBoxLayoutData(new Margins (5,5,5,5)));
		add(contentPanel);
		resize(0,0);
	}
	
	@Override
	public void resize(int width, int height) {
		setSize(MainPanel.getInstance().getWidth(), MainPanel.getInstance().getHeight());
		contentPanel.setWidth(MainPanel.getInstance().getWidth());
		contentPanel.setHeight(MainPanel.getInstance().getHeight());
	}

}
