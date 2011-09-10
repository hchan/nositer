package com.nositer.client.viewblog;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.nositer.client.dto.generated.User;

public class ViewBlogContainer extends LayoutContainer {
	
	private Label descriptionLabel;
	private HtmlContainer description;

	public void populate(User user) {
		
		if (user.getDescription() != null) {
			description.setHtml(user.getDescription());
		}
	}

	public ViewBlogContainer() {
		init();
	}

	private void init() {
		//this.setAutoHeight(true);
		//this.setAutoWidth(true);
		setHeight(100000);
		setWidth(100000);
		this.setLayout(new RowLayout(Orientation.VERTICAL));
		
		descriptionLabel = new Label("About me");
		descriptionLabel.setStyleName("profileDescriptionLabel");
		this.add(descriptionLabel, new RowData(-1, -1, new Margins(5)));
		description = new HtmlContainer();
		description.setStyleName("profileDescription");
		this.add(description, new RowData(-1, -1, new Margins(5)));
	}

	
}
