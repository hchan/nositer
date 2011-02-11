package com.nositer.client.imagemgmt;

import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGrid.TreeNode;

@SuppressWarnings("rawtypes")
public class SelectedFolderPanel extends FormPanel {

	private TextField<String> selectedFolder;
	private FolderModel folderModel;
	
	private TreeNode treeNode;
	
	public TreeNode getTreeNode() {
		return treeNode;
	}

	public FolderModel getFolderModel() {
		return folderModel;
	}

	public void setFolderModel(FolderModel folderModel) {
		this.folderModel = folderModel;
	}

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

	public void setTreeNode(TreeNode treeNode) {
		this.treeNode = treeNode;
	}

	
}
