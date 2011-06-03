package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.nositer.client.dto.generated.Grouptopic;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.button.NextButton;
import com.nositer.client.widget.button.PreviousButton;
import com.nositer.client.widget.button.RefreshButton;

public class GrouptopicToolBar extends ToolBar implements Resizable {
	private GroupDiscussionsContainer groupDiscussionsContainer;
	private Grouptopic grouptopic;
	private RefreshButton refreshButton;
	private NextButton nextButton;
	private PreviousButton previousButton;
	private Label filler1;

	public GrouptopicToolBar(GroupDiscussionsContainer groupDiscussionsContainer, Grouptopic grouptopic) {
		this.groupDiscussionsContainer = groupDiscussionsContainer;
		this.grouptopic = grouptopic;

		init();
	}

	private void init() {
		previousButton = new PreviousButton();
		nextButton = new NextButton();
		refreshButton = new RefreshButton();
		//setAlignment(HorizontalAlignment.CENTER);

		add(previousButton);
		filler1 = new Label("x");
		filler1.setStyleAttribute("margin-right", "200");
		add(filler1);
		add(nextButton);

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}
}
