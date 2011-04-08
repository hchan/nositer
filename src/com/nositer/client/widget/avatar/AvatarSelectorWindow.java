package com.nositer.client.widget.avatar;

import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.nositer.client.Scope;
import com.nositer.client.widget.directorytree.AbstractFileDirectoryTreeGridContainer;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.client.widget.directorytree.GroupFileDirectoryTreeGridContainer;
import com.nositer.client.widget.directorytree.UserFileDirectoryTreeGridContainer;
import com.nositer.client.widget.fileviewer.FileViewerContainer;


public class AvatarSelectorWindow extends Window {
	private AvatarSelector avatarSelector;
	private AbstractFileDirectoryTreeGridContainer fileDirectoryTreeGridContainer;
	private ContentPanel contentPanel;
	private FileViewerContainer fileViewerContainer;
	private Button okButton;
	private Button cancelButton;



	public AvatarSelector getAvatarSelector() {
		return avatarSelector;
	}

	public void setAvatarSelector(AvatarSelector avatarSelector) {
		this.avatarSelector = avatarSelector;
	}

	public AbstractFileDirectoryTreeGridContainer getFileDirectoryTreeGridContainer() {
		return fileDirectoryTreeGridContainer;
	}

	public void setFileDirectoryTreeGridContainer(
			AbstractFileDirectoryTreeGridContainer fileDirectoryTreeGridContainer) {
		this.fileDirectoryTreeGridContainer = fileDirectoryTreeGridContainer;
	}

	public ContentPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(ContentPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public FileViewerContainer getFileViewerContainer() {
		return fileViewerContainer;
	}

	public void setFileViewerContainer(FileViewerContainer fileViewerContainer) {
		this.fileViewerContainer = fileViewerContainer;
	}

	public Button getOkButton() {
		return okButton;
	}

	public void setOkButton(Button okButton) {
		this.okButton = okButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(Button cancelButton) {
		this.cancelButton = cancelButton;
	}

	public AvatarSelectorWindow(AvatarSelector avatarSelector) {
		this.avatarSelector = avatarSelector;
		init();
	}

	private void init() {
		setLayout(new FitLayout());
		setSize(750, 500);
		setModal(true);
		setBlinkModal(true);	
		contentPanel = new ContentPanel();
		contentPanel.setHeaderVisible(false);
		BorderLayout layout = new BorderLayout();
		contentPanel.setLayout(layout);

		if (getAvatarSelector().getScope().getType().equals(Scope.Type.user)) {
			fileDirectoryTreeGridContainer = new AbstractAvatarFileDirectoryTreeGridContainer(false, this) {
				@Override
				public RpcProxy<List<FileModel>> createProxy() {
					return UserFileDirectoryTreeGridContainer.createUserProxy();
				}
			};
		} else {
			fileDirectoryTreeGridContainer = new AbstractAvatarFileDirectoryTreeGridContainer(false, this) {
				@Override
				public RpcProxy<List<FileModel>> createProxy() {
					return GroupFileDirectoryTreeGridContainer.createGroupProxy(getAvatarSelector().getScope().getGroupPlusView());
				}
			};
		}
		fileDirectoryTreeGridContainer.applyImageStoreFilter();



		fileDirectoryTreeGridContainer.setLayout(new FlowLayout(0));
		fileViewerContainer = new FileViewerContainer() {
			@Override
			protected void onResize(int width, int height) {
				AvatarSelectorWindow.this.onResize(AvatarSelectorWindow.this.getWidth(), AvatarSelectorWindow.this.getHeight());
			};
		};
		fileViewerContainer.getContentPanel().setBottomComponent(null);
		fileViewerContainer.getContentPanel().setTopComponent(null);

		setHeading("Select Avatar");
		BorderLayoutData westBorderLayoutData = new BorderLayoutData(LayoutRegion.WEST);
		westBorderLayoutData.setSize(400);
		westBorderLayoutData.setSplit(true);

		BorderLayoutData centerBorderLayoutData = new BorderLayoutData(LayoutRegion.CENTER);
		centerBorderLayoutData.setSplit(true);

		contentPanel.add(fileDirectoryTreeGridContainer, westBorderLayoutData);
		contentPanel.add(fileViewerContainer, centerBorderLayoutData);

		addButtons();		
		this.add(contentPanel);
	}

	private void addButtons() {
		okButton = new Button("OK");
		okButton.addListener(Events.Select, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				avatarSelector.getSelectedFile().setValue(fileDirectoryTreeGridContainer.getSelectedFilePanel().getSelectedFile().getValue());
				AvatarSelectorWindow.this.hide();
			}
		});
		contentPanel.addButton(okButton);
		cancelButton = new Button("Cancel");
		cancelButton.addListener(Events.Select, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				AvatarSelectorWindow.this.hide();
			}
		});
		contentPanel.addButton(cancelButton);
		contentPanel.setButtonAlign(HorizontalAlignment.CENTER);
	}

	@Override
	protected void onResize(int width, int height) {	
		int heightOffset = 70;
		super.onResize(width, height);
		fileDirectoryTreeGridContainer.getContentPanel().setSize(fileDirectoryTreeGridContainer.getWidth(), 
				height-heightOffset);
		fileViewerContainer.setHeight(height-heightOffset);
		fileViewerContainer.getContentPanel().setHeight(height-heightOffset);		
		fileViewerContainer.getSelectedFilePanel().setWidth(fileViewerContainer.getWidth() - 10);		
	}

}
