package com.nositer.client.managefiles;

import java.util.List;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuBar;
import com.extjs.gxt.ui.client.widget.menu.MenuBarItem;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.widget.directorytree.AbstractFileDirectoryTreeGridContainer;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.client.widget.messagebox.AlertMessageBox;
import com.nositer.client.widget.messagebox.PromptMessageBox;

abstract public class AbstractFileSelectorMenuBar extends MenuBar {

	private MenuBarItem file;
	private MenuItemManageFiles createFolder;
	private MenuItemManageFiles downloadFile;
	private AbstractFileDirectoryTreeGridContainer fileDirectoryTreeGridContainer;
	private Menu menu;

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public AbstractFileSelectorMenuBar(AbstractFileDirectoryTreeGridContainer fileDirectoryTreeGridContainer) {
		this.fileDirectoryTreeGridContainer = fileDirectoryTreeGridContainer;
		menu = createMenu();		
		file = new MenuBarItem("File", menu);
		setBorders(true);
		this.add(file);
	}

	public Menu createMenu() {
		Menu retval = new Menu();
		initCreateFolder();
		retval.add(createFolder);
		initDownloadFile();
		retval.add(downloadFile);
		return retval;
	}

	private void initDownloadFile() {
		downloadFile = new MenuItemManageFiles("Download");
		downloadFile.setFolderMenuItem(false);
		downloadFile.addListener(Events.Select, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				FileModel fileModel = (FileModel) fileDirectoryTreeGridContainer.getTree().getSelectionModel().getSelectedItem();
				doDownloadFile(fileModel);
			}
		});
	}

	private void initCreateFolder() {
		createFolder = new MenuItemManageFiles("Create Folder");
		createFolder.setFolderMenuItem(true);
		createFolder.addListener(Events.Select, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {				
				if (fileDirectoryTreeGridContainer.getSelectedFolderPanel().getSelectedFolder().getValue() != null) {
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
		String fullFolderName = fileDirectoryTreeGridContainer.getSelectedFolderPanel().getSelectedFolder().getValue() + "/" + folderName;
		AsyncCallback<Void> callback = new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				AlertMessageBox.show("Warning", caught.getMessage(), null);
			}

			@Override
			public void onSuccess(Void result) {			
				//fileDirectoryTreeGridContainer.getTree().getStore().removeAllListeners();
				fileDirectoryTreeGridContainer.getTree().getStore().addStoreListener(new StoreListener<ModelData>(){

					@Override
					public void handleEvent(StoreEvent<ModelData> e) {						
						super.handleEvent(e);
						List<ModelData> models = e.getModels();
						if (models != null) {
							for (ModelData modelData : models) {
								FileModel fileModel = (FileModel) modelData;								
								if (fileModel != null && folderName.equals(fileModel.getName())) {								
									fileDirectoryTreeGridContainer.setSelectedFileOrFolder(fileModel,
											fileDirectoryTreeGridContainer.getTree().findNode(fileModel));									
								}
							}
						}
					}

				}
				);
				fileDirectoryTreeGridContainer.refreshSelectedTreeNode();
			}	
		};
		doCreateFolderService(fullFolderName, callback);		
	}

	abstract public void doCreateFolderService(String fullFolderName,
			AsyncCallback<Void> callback) ;

	abstract public void doDownloadFile(FileModel fileModel);
}
