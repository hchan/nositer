package com.nositer.client.widget.imageviewer;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuBar;
import com.extjs.gxt.ui.client.widget.menu.MenuBarItem;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.manageimages.ManageImages;
import com.nositer.client.top.TopPanel;
import com.nositer.client.widget.messagebox.AlertMessageBox;
import com.nositer.client.widget.messagebox.InfoMessageBox;
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
		
		setAsAvatar = new MenuItem("Set as Avatar");
		setAsAvatar.addListener(Events.Select, createSetAsAvatarListener());
		subMenu.add(setAsAvatar);
		
		options = new MenuBarItem("Options", subMenu);
		setBorders(true);
		this.add(options);
	}


	
	public Listener createSetAsAvatarListener() {
		Listener retval = null;
		retval = new Listener() {
			@Override
			public void handleEvent(BaseEvent be) {
				
				if (ManageImages.getInstance().getImageViewerContainer().getSelectedFilePanel().getSelectedFile().getValue() != null) {
					AsyncCallback<Void> callback = new AsyncCallback() {

						@Override
						public void onFailure(Throwable caught) {
							AlertMessageBox.show("Error", caught.getMessage(), null);
						}

						@Override
						public void onSuccess(Object result) {
							TopPanel.getInstance().getUser().setAvatarlocation(ManageImages.getInstance().getImageViewerContainer().getSelectedFilePanel().getSelectedFile().getValue());
							ManageImages.getInstance().getFileManager().refreshSelectedTreeNode();
							InfoMessageBox.show("Avatar updated");
						}
						
					};
					ServiceBroker.profileService.updateAvatarOfCurrentUser(ManageImages.getInstance().getImageViewerContainer().getSelectedFilePanel().getSelectedFile().getValue(), callback);
				}
			}
		};
		return retval;
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
