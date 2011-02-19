package com.nositer.client.widget.avatar;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
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
import com.nositer.client.widget.directorytree.FileDirectoryTreeGridContainer;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.client.widget.imageviewer.ImageViewerContainer;


public class AvatarSelectorWindow extends Window {
	private AvatarSelector avatarSelector;
	private FileDirectoryTreeGridContainer fileDirectoryTreeGridContainer;
	private ContentPanel contentPanel;
	private ImageViewerContainer imageViewerContainer;
	private Button okButton;
	private Button cancelButton;
	
	

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
		
		fileDirectoryTreeGridContainer = new FileDirectoryTreeGridContainer(true) {
			@Override
			protected void onResize(int width, int height) {
				AvatarSelectorWindow.this.onResize(AvatarSelectorWindow.this.getWidth(), AvatarSelectorWindow.this.getHeight());
			};
			
			@Override
			public void doFileModelClick(FileModel fileModel) {
				imageViewerContainer.setImage(fileModel);
				imageViewerContainer.getSelectedFilePanel().getSelectedFile().setValue(fileModel.getPath());
			}
		};
		
		fileDirectoryTreeGridContainer.setLayout(new FlowLayout(0));
		imageViewerContainer = new ImageViewerContainer() {
			@Override
			protected void onResize(int width, int height) {
				AvatarSelectorWindow.this.onResize(AvatarSelectorWindow.this.getWidth(), AvatarSelectorWindow.this.getHeight());
			};
		};
		imageViewerContainer.getContentPanel().setBottomComponent(null);
		imageViewerContainer.getContentPanel().setTopComponent(null);
		
		setHeading("Select Avatar");
		BorderLayoutData westBorderLayoutData = new BorderLayoutData(LayoutRegion.WEST);
		westBorderLayoutData.setSize(400);
		westBorderLayoutData.setSplit(true);
		
		BorderLayoutData centerBorderLayoutData = new BorderLayoutData(LayoutRegion.CENTER);
		centerBorderLayoutData.setSplit(true);

		contentPanel.add(fileDirectoryTreeGridContainer, westBorderLayoutData);
		contentPanel.add(imageViewerContainer, centerBorderLayoutData);
		
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
		imageViewerContainer.setHeight(height-heightOffset);
		imageViewerContainer.getContentPanel().setHeight(height-heightOffset);		
		imageViewerContainer.getSelectedFilePanel().setWidth(imageViewerContainer.getWidth() - 10);		
	}
		
}
