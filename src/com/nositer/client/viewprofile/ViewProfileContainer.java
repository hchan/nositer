package com.nositer.client.viewprofile;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.nositer.client.dto.generated.User;

public class ViewProfileContainer extends LayoutContainer {
	private Quickstats quickStats;
	private Note note;
	private Label descriptionLabel;
	private HtmlContainer description;

	public void populate(User user) {
		quickStats.populate(user);	
		note.populate(user);
		if (user.getDescription() != null) {
			description.setHtml(user.getDescription());
		}
	}

	public ViewProfileContainer() {
		init();
	}

	private void init() {
		//this.setAutoHeight(true);
		//this.setAutoWidth(true);
		setHeight(100000);
		setWidth(100000);
		this.setLayout(new RowLayout(Orientation.VERTICAL));
		quickStats = new Quickstats();
		this.add(quickStats, new RowData(-1, -1, new Margins(5)));
		note = new Note();
		this.add(note, new RowData(-1, -1, new Margins(5)));
		descriptionLabel = new Label("About me");
		descriptionLabel.setStyleName("profileDescriptionLabel");
		this.add(descriptionLabel, new RowData(-1, -1, new Margins(5)));
		description = new HtmlContainer();
		description.setStyleName("profileDescription");
		this.add(description, new RowData(-1, -1, new Margins(5)));
	}

	
}
