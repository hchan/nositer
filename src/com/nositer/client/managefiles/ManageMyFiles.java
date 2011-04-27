package com.nositer.client.managefiles;

import com.nositer.client.Scope;
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
}