package com.nositer.client.uploadimages;

import com.extjs.gxt.ui.client.widget.form.TextField;


public class SelectedFolderPanel extends SelectedFilePanel {

	public TextField<String> getSelectedFolder() {
		return selectedFile;
	}

	public void setSelectedFolder(TextField<String> selectedFolder) {
		this.selectedFile = selectedFolder;
	}

	public FolderModel getFolderModel() {
		return fileModel;
	}

	public void setFolderModel(FolderModel folderModel) {
		this.fileModel = folderModel;
	}

	@Override
	public void init() {		
		super.init();
		selectedFile.setFieldLabel("Selected Folder");
	}
}
