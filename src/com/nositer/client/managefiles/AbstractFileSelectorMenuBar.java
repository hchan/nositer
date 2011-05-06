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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.widget.directorytree.AbstractFileDirectoryTreeGridContainer;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.client.widget.directorytree.FolderModel;
import com.nositer.client.widget.messagebox.AlertMessageBox;
import com.nositer.client.widget.messagebox.ConfirmMessageBox;
import com.nositer.client.widget.messagebox.InfoMessageBox;
import com.nositer.client.widget.messagebox.PromptMessageBox;
import com.nositer.shared.Global;

abstract public class AbstractFileSelectorMenuBar extends MenuBar {

	private MenuBarItem file;
	private MenuItemManageFiles createFolder;
	private MenuItemManageFiles deleteFolder;
	private MenuItemManageFiles renameFolder;

	private MenuItemManageFiles downloadFile;
	private MenuItemManageFiles showURLPath;
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
		initDeleteFolder();
		retval.add(deleteFolder);
		initRenameFolder();
		retval.add(renameFolder);

		initDownloadFile();
		retval.add(downloadFile);
		initShowURLPath();
		retval.add(showURLPath);
		return retval;
	}

	private void initShowURLPath() {
		showURLPath = new MenuItemManageFiles("Show URL Path");
		showURLPath.setFolderMenuItem(false);
		showURLPath.addListener(Events.Select, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				FileModel fileModel = (FileModel) fileDirectoryTreeGridContainer.getTree().getSelectionModel().getSelectedItem();
				doShowURLPath(fileModel);
			}
		});
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
					PromptMessageBox.show("Create Folder", "Enter the name of the folder you want to create", new Listener<MessageBoxEvent>() {
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

	private void initRenameFolder() {
		renameFolder = new MenuItemManageFiles("Rename Folder");
		renameFolder.setFolderMenuItem(true);
		renameFolder.addListener(Events.Select, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {		
				final FolderModel folderModel = (FolderModel) fileDirectoryTreeGridContainer.getTree().getSelectionModel().getSelectedItem();
				if (fileDirectoryTreeGridContainer.getSelectedFolderPanel().getSelectedFolder().getValue() != null) {
					PromptMessageBox.show("Rename Folder", "Enter the name of the folder you want <B>" + folderModel.getName() + "</B> to be renamed to", new Listener<MessageBoxEvent>() {
						@Override
						public void handleEvent(MessageBoxEvent be) {
							Button buttonClicked = be.getButtonClicked();
							if (buttonClicked.getText().equals("OK")) {
								doRenameFolder(fileDirectoryTreeGridContainer.getSelectedFolderPanel().getSelectedFolder().getValue(), be.getValue());
							}
						}
					});
				} else {
					AlertMessageBox.show("Error", "You must choose a folder", null);
				}
			}
		});
	}

	private void initDeleteFolder() {
		deleteFolder = new MenuItemManageFiles("Delete Folder");
		deleteFolder.setFolderMenuItem(true);
		deleteFolder.addListener(Events.Select, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {				
				final FolderModel folderModel = (FolderModel) fileDirectoryTreeGridContainer.getTree().getSelectionModel().getSelectedItem();
				if (fileDirectoryTreeGridContainer.getSelectedFolderPanel().getSelectedFolder().getValue() != null) {
					ConfirmMessageBox.show("Delete Folder", "You are about to delete the folder: " + folderModel.getPath() + " and all if its contents",
							new Listener<MessageBoxEvent>() {
						@Override
						public void handleEvent(MessageBoxEvent be) {
							Button buttonClicked = be.getButtonClicked();
							if (buttonClicked.getText().equals("Yes")) {
								doDeleteFolder(folderModel);
							}
						}
					});
				} else {
					AlertMessageBox.show("Error", "You must choose folder", null);
				}
			}
		});
	}


	private void doCreateFolder(final String folderName) {
		String fullFolderName = fileDirectoryTreeGridContainer.getSelectedFolderPanel().getSelectedFolder().getValue() + "/" + folderName;
		doCreateFolderService(fullFolderName, getRefreshFolderCallbackForCreate(folderName));
	}

	private void doDeleteFolder(FolderModel folderModel) {
		doDeleteFolderService(folderModel, getRefreshFolderCallbackOnParentFolder());
	}
	
	private void doRenameFolder(String oldFullFolderName, String newRelativeFolderName) {

		int lastIndexOfSlash = oldFullFolderName.lastIndexOf("/");
		String pathName = oldFullFolderName.substring(0, lastIndexOfSlash);

		String oldRelativeFolderName = oldFullFolderName.substring(lastIndexOfSlash + 1);

		doRenameFolderService(pathName, oldRelativeFolderName , newRelativeFolderName, getRefreshFolderCallbackOnParentFolder());
	}


	private AsyncCallback<Void> getRefreshFolderCallbackOnParentFolder() {
		AsyncCallback<Void> retval =  new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				AlertMessageBox.show("Warning", caught.getMessage(), null);
			}

			@Override
			public void onSuccess(Void result) {			
				FileModel fileModel = (FileModel) fileDirectoryTreeGridContainer.getTree().getSelectionModel().getSelectedItem();				
				FileModel parentFolderModel = (FileModel) fileDirectoryTreeGridContainer.getTree().getTreeStore().getParent(fileModel);
				
				fileDirectoryTreeGridContainer.setSelectedFileOrFolder(parentFolderModel,
						fileDirectoryTreeGridContainer.getTree().findNode(parentFolderModel));	
				fileDirectoryTreeGridContainer.refreshSelectedTreeNode();
			}	
		};
		return retval;
	}

	private AsyncCallback<Void> getRefreshFolderCallbackForCreate(final String folderName) {
		AsyncCallback<Void> retval =  new AsyncCallback<Void>() {
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
		return retval;
	}


	public void doDownloadFile(FileModel fileModel) {
		Window.open(getURLPath(fileModel)
				+ "?" + Global.DOWNLOAD + "=true",
				"_blank", 
				null);		
	}

	public void doShowURLPath(FileModel fileModel) {
		InfoMessageBox.show(getURLPath(fileModel));
	}

	abstract public String getURLPath(FileModel fileModel);

	abstract public void doCreateFolderService(String fullFolderName, AsyncCallback<Void> callback) ;

	abstract public void doRenameFolderService(String pathName, String oldRelativeFolderName, String newRelativeFolderName, AsyncCallback<Void> callback);

	abstract public void doDeleteFolderService(FolderModel folderModel, AsyncCallback<Void> callback);

}
