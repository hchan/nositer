package com.nositer.client.widget.avatar;

import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.directorytree.FileDirectoryTreeGridContainer;


public class AvatarSelectorWindow extends Window {
	private FileDirectoryTreeGridContainer fileDirectoryTreeGridContainer;

	public AvatarSelectorWindow() {
		init();
	}

	private void init() {
		fileDirectoryTreeGridContainer = new FileDirectoryTreeGridContainer(true);
		fileDirectoryTreeGridContainer.setLayout(new FlowLayout(0));
		
		setHeading("Select Avatar");
		add(fileDirectoryTreeGridContainer);
		setModal(true);
		setBlinkModal(true);
		
	}
	
	@Override
	protected void onResize(int width, int height) {		
		//super.onResize(width, height);
		fileDirectoryTreeGridContainer.setSize(width, height);
		fileDirectoryTreeGridContainer.getContentPanel().setSize(width-12, height);
		
	}
	
	
}
