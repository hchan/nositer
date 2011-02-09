package com.nositer.client.imagemgmt;

import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;

public class SelectedFolderPanel extends FormPanel {

	private TextField<String> selectedFolder;
	
	public TextField<String> getSelectedFolder() {
		return selectedFolder;
	}

	public void setSelectedFolder(TextField<String> selectedFolder) {
		this.selectedFolder = selectedFolder;
	}

	public SelectedFolderPanel () {
		init();
	}

	public void init() {
		this.setHeaderVisible(false);
		this.setLabelWidth(100);
		this.setWidth("100%");
		selectedFolder = new TextField<String>();
		selectedFolder.setFieldLabel("Selected Folder");
		selectedFolder.setEnabled(false);
		selectedFolder.setStyleName("selectedFolder");
		this.add(selectedFolder, new FormData("100%"));
	}
}
