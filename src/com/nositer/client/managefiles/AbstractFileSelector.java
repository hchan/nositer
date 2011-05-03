package com.nositer.client.managefiles;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.nositer.client.widget.directorytree.AbstractFileDirectoryTreeGridContainer;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.client.widget.directorytree.FolderModel;
import com.nositer.client.widget.fileviewer.FileViewerContainer;
import com.nositer.shared.FileNameUtil;

@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
abstract public class AbstractFileSelector extends AbstractFileDirectoryTreeGridContainer {
	protected AbstractFileSelectorMenuBar abstractFileSelectorMenuBar;
	protected FileViewerContainer fileViewerContainer;
	private Menu contextMenu;

	public AbstractFileSelector(){

	}

	public AbstractFileSelector(FileViewerContainer fileViewerContainer) {
		super(false);
		this.fileViewerContainer = fileViewerContainer;
	}

	@Override
	protected void init() {
		super.init();
		setLayout(new FlowLayout(0));  
		abstractFileSelectorMenuBar = createFileSelectorMenuBar();
		getContentPanel().setTopComponent(abstractFileSelectorMenuBar);
		contextMenu = new Menu();
		populateContextMenu();
		tree.setContextMenu(contextMenu);
	}

	abstract public AbstractFileSelectorMenuBar createFileSelectorMenuBar();

	@Override
	public void doFileModelClick(FileModel fileModel) {
		fileViewerContainer.setLoading(fileModel);
		if (FileNameUtil.isImageFile(fileModel.getName())) {
			fileViewerContainer.setImage(fileModel);
		} else if (FileNameUtil.isTextFile(fileModel.getName())) {
			fileViewerContainer.setText(fileModel);
		} else {
			fileViewerContainer.setUnknownFile(fileModel);
		}
		fileViewerContainer.getSelectedFilePanel().getSelectedFile().setValue(fileModel.getPath());
	}


	public void populateContextMenu() {
		contextMenu = abstractFileSelectorMenuBar.getMenu();
		contextMenu.addListener(Events.BeforeShow , new Listener() {

			@Override
			public void handleEvent(BaseEvent e) {
				MenuEvent menuEvent = (MenuEvent)e;
				FileModel fileModel = (FileModel) getTree().getSelectionModel().getSelectedItem();
				setSelectedFileOrFolder(fileModel, getTree().findNode(fileModel));

				for (Component component : contextMenu.getItems()) {
					MenuItemManageFiles menuItemManageFiles = (MenuItemManageFiles) component;
					if (fileModel instanceof FolderModel) {
						if (menuItemManageFiles.isFolderMenuItem()) {
							menuItemManageFiles.show();
						} else {
							menuItemManageFiles.hide();
						}
					} else {
						if (menuItemManageFiles.isFolderMenuItem()) {
							menuItemManageFiles.hide();
						} else {
							menuItemManageFiles.show();
						}
					}
				}
				
			}});
	}
}
