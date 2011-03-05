package com.nositer.client.groups;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.ImageHelper;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.avatar.Avatar;

public class ViewGroupTabItem extends TabItem implements Resizable {

	private HtmlContainer description;
	private ContentPanel contentPanel;

	private Avatar avatar;
	private Group group;

	public ViewGroupTabItem(Group group) {
		super("View");
		this.group = group;
		init();
	}

	public void init() {
		setMonitorWindowResize(true);
		TableLayout layout = new TableLayout(1);

		contentPanel = new ContentPanel();
		contentPanel.setBodyBorder(false);
		contentPanel.setBorders(false);
		contentPanel.setLayout(layout);
		contentPanel.setHeaderVisible(false);
		contentPanel.setScrollMode(Scroll.AUTO);
		contentPanel.setId(getItemId());
		description = new HtmlContainer();
		description.setHtml(group.getDescription());
		avatar = new Avatar();
		avatar.setPathToImage(ImageHelper.getUserImagePathURL(group.getAvatarlocation()));
		LayoutContainer avatarContentPanel = new LayoutContainer();
		avatarContentPanel.add(avatar);
		contentPanel.add(avatarContentPanel);
		contentPanel.add(description);
		add(contentPanel);
	}

	@Override
	public void resize(int width, int height) {
		contentPanel.setHeight(MainPanel.getInstance().getHeight()-57);
		contentPanel.setWidth(MainPanel.getInstance().getWidth()-3);
	
		
	}

	

}
