package com.nositer.client.manageimages;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuBar;
import com.extjs.gxt.ui.client.widget.menu.MenuBarItem;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.nositer.shared.Global;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ImageViewerMenuBar extends MenuBar {

	private MenuBarItem options;
	private MenuItem viewActualSize;
	private MenuItem view50percent;
	private MenuItem view25percent;
	private MenuItem viewAvatarSize;
	private MenuItem setAsAvatar;
	
	
	public ImageViewerMenuBar() {
		Menu subMenu = new Menu();
		viewActualSize = new MenuItem("View Actual Size");
		viewActualSize.addListener(Events.Select, createImageViewListener(null));
		subMenu.add(viewActualSize);
		
		view50percent = new MenuItem("View 50% of Image Viewer");
		view50percent.addListener(Events.Select, createImageViewListener("50%"));
		subMenu.add(view50percent);
		
		view25percent = new MenuItem("View 25% of Image Viewer");
		view25percent.addListener(Events.Select, createImageViewListener("25%"));
		subMenu.add(view25percent);
		
		viewAvatarSize = new MenuItem("View Avatar Size (" + Global.AVATAR_WIDTHHEIGHT + "px x " + Global.AVATAR_WIDTHHEIGHT + "px)");
		viewAvatarSize.addListener(Events.Select, createImageViewListener(Global.AVATAR_WIDTHHEIGHT + "px"));
		subMenu.add(viewAvatarSize);
		
		options = new MenuBarItem("Options", subMenu);
		setBorders(true);
		this.add(options);
	}


	
	public Listener createImageViewListener(final String percent) {		
		Listener retval = null;
		retval = new Listener() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (ManageImages.getInstance().getImageViewerContainer().getSelectedFilePanel().getSelectedFile().getValue() != null) {
					ManageImages.getInstance().getImageViewerContainer().setImage(
							ManageImages.getInstance().getImageViewerContainer().getSelectedFilePanel().getSelectedFile().getValue(),
							percent);
				}
			}
		};
		return retval;
	}
}
