package com.nositer.client.groupdiscussions;

import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.nositer.client.dto.generated.Grouptopic;
import com.nositer.client.main.MainPanel;
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
	private LayoutContainer filler1;

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
		filler1 = new LayoutContainer();

		add(filler1);
		add(nextButton);
		resize(0,0);
	}

	@Override
	public void resize(int width, int height) {
		filler1.removeAll();
		int widthOfToolBar = MainPanel.getInstance().getWidth() - groupDiscussionsContainer.getGroupDiscussionLeftPanel().getWidth();
		filler1.add(new Label(), new MarginData(0, widthOfToolBar - 145, 0, 0));
		layout();
	}
}
