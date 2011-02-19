package com.nositer.client.widget.avatar;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGrid.TreeNode;
import com.nositer.client.widget.directorytree.FileDirectoryTreeGridContainer;
import com.nositer.client.widget.directorytree.FolderModel;


@SuppressWarnings("rawtypes")
public class AvatarSelector extends LayoutContainer {		
	protected TextField<String> selectedFile;
	protected FolderModel fileModel;	
	protected TreeNode treeNode;
	protected Label selectAvatar;
	protected Button button;
	
	
	public Label getSelectAvatar() {
		return selectAvatar;
	}

	public void setSelectAvatar(Label selectAvatar) {
		this.selectAvatar = selectAvatar;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}

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

	public AvatarSelector() {
		init();
	}
	

	public void init() {
		this.setLayout(new HBoxLayout());
		HBoxLayoutData hBoxLayoutData = new HBoxLayoutData();
		hBoxLayoutData.setMargins(new Margins(0, 1, 0, 10));
		this.setWidth(400);
		selectAvatar = new Label("Select Avatar:");
		selectAvatar.setStyleName("avatarSelector");
		selectedFile = new TextField<String>();
		selectedFile.setWidth(200);
		selectedFile.setEnabled(false);
		selectedFile.setStyleName("selectedFile");
		
		initButton();
		
		
		this.add(selectAvatar);
		this.add(selectedFile, hBoxLayoutData);
		this.add(button);
	}
	
	public void initButton() {
		button = new Button();
		button.setIcon(IconHelper.createPath("/public/image/avataricon.png"));
		button.addListener(Events.Select, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				AvatarSelectorWindow avatarSelectorWindow = new AvatarSelectorWindow(AvatarSelector.this);				
				avatarSelectorWindow.show();
			}
		});
	}

	public void setTreeNode(TreeNode treeNode) {
		this.treeNode = treeNode;
	}

}
