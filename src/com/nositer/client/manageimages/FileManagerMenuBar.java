package com.nositer.client.manageimages;

import java.util.List;

import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuBar;
import com.extjs.gxt.ui.client.widget.menu.MenuBarItem;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.AlertMessageBox;
import com.nositer.client.widget.PromptMessageBox;
import com.nositer.client.widget.directorytree.FileModel;

public class FileManagerMenuBar extends MenuBar {

	private MenuBarItem file;
	private MenuItem createFolder;

	public FileManagerMenuBar() {
		Menu subMenu = new Menu();
		initCreateFolder();
		subMenu.add(createFolder);
		file = new MenuBarItem("File", subMenu);
		setBorders(true);
		this.add(file);
	}

	private void initCreateFolder() {
		createFolder = new MenuItem("Create Folder");
		createFolder.addListener(Events.Select, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (ManageImages.getInstance().getFileManager().getSelectedFolderPanel().getSelectedFolder().getValue() != null) {
					PromptMessageBox.show("Create Folder", "Enter the name of the folder", new Listener<MessageBoxEvent>() {
						@Override
						public void handleEvent(MessageBoxEvent be) {
							Button buttonClicked = be.getButtonClicked();
							if (buttonClicked.getText().equals("OK")) {
								doCreateFolder(be.getValue());
							}
						}
					});
				} else {
					AlertMessageBox.show("Error", "You must choose a parent Selected Folder", null);
				}
			}
		});
	}

	private void doCreateFolder(final String folderName) {
		String fullFolderName = ManageImages.getInstance().getFileManager().getSelectedFolderPanel().getSelectedFolder().getValue() + "/" + folderName;
		AsyncCallback<Void> callback = new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				AlertMessageBox.show("Warning", caught.getMessage(), null);
			}

			@Override
			public void onSuccess(Void result) {			

				ManageImages.getInstance().getFileManager().getTree().getStore().addStoreListener(new StoreListener<ModelData>(){

					@Override
					public void handleEvent(StoreEvent<ModelData> e) {						
						super.handleEvent(e);
						List<ModelData> models = e.getModels();
						if (models != null) {
							for (ModelData modelData : models) {
								FileModel fileModel = (FileModel) modelData;
								
								if (fileModel != null && folderName.equals(fileModel.getName())) {
									ManageImages.getInstance().getFileManager().getTree().getSelectionModel().select(fileModel, false);
									ManageImages.getInstance().getFileManager().getSelectedFolderPanel().getSelectedFolder().setValue(fileModel.getPath());
									break;
								}
							}
						}
					}
				
				});
				ManageImages.getInstance().getFileManager().refreshSelectedTreeNode();
			}		
		};
		ServiceBroker.fileService.createFolder(fullFolderName, callback);
	}
}
