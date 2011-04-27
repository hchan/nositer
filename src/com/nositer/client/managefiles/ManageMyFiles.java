package com.nositer.client.managefiles;

import com.nositer.client.Scope;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.fileviewer.FileViewerContainer;

public class ManageMyFiles extends AbstractManageFiles {
	
	private static ManageMyFiles instance;
	public static ManageMyFiles getInstance() {
		return instance;
	}

	public static void setInstance(ManageMyFiles instance) {
		ManageMyFiles.instance = instance;
	}

	public ManageMyFiles() {
		super();
		instance = this;
	}

	@Override
	public FileViewerContainer createFileViewerContainer() {		 
		return new FileViewerContainer(new Scope(Scope.Type.user)) {
			@Override
			protected void onResize(int width, int height) {
				resize(0,0);
			};
		};
	}

	@Override
	public AbstractFolderSelector createFolderSelector() {
		return new UserFolderSelector(getFileViewerContainer()) {
			@Override
			protected void onResize(int width, int height) {
				resize(0,0);
			};
		};
	}
	
	
	@Override
	public void resize(int width, int height) {
		folderSelector.getContentPanel().setSize(folderSelector.getWidth(), 
				folderSelector.getHeight());
		
		fileViewerContainer.setHeight(MainPanel.getInstance().getHeight()-13);
		fileViewerContainer.getContentPanel().setHeight(fileViewerContainer.getHeight());
		//fileViewerContainer.getSelectedFilePanel().getSelectedFile().setWidth(fileViewerContainer.getWidth());
		
		contentPanel.setSize(MainPanel.getInstance().getWidth(),
				MainPanel.getInstance().getHeight());
		fileViewerContainer.getSelectedFilePanel().setWidth(fileViewerContainer.getWidth() - 10);
		
	}

}