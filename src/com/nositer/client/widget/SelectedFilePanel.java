package com.nositer.client.widget;

import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGrid.TreeNode;
import com.nositer.client.uploadimages.FolderModel;

@SuppressWarnings("rawtypes")
public class SelectedFilePanel extends FormPanel {

	protected TextField<String> selectedFile;
	protected FolderModel fileModel;
	
	private TreeNode treeNode;
	
	public TreeNode getTreeNode() {
		return treeNode;
	}

	public TextField<String> getSelectedFile() {
		return selectedFile;
	}


	public void setSelectedFile(TextField<String> selectedFile) {
		this.selectedFile = selectedFile;
	}

	public FolderModel getFileModel() {
		return fileModel;
	}

	public void setFileModel(FolderModel fileModel) {
		this.fileModel = fileModel;
	}


	public SelectedFilePanel () {
		init();
	}

	public void init() {
		this.setHeaderVisible(false);
		this.setLabelWidth(100);
		this.setWidth("100%");
		selectedFile = new TextField<String>();
		selectedFile.setFieldLabel("Selected File");
		selectedFile.setEnabled(false);
		selectedFile.setStyleName("selectedFile");
		this.add(selectedFile, new FormData("100%"));
	}

	public void setTreeNode(TreeNode treeNode) {
		this.treeNode = treeNode;
	}

	
}
