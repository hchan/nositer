package com.nositer.client.manageimages;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.nositer.client.uploadimages.FileDirectoryTreeGridContainer;
import com.nositer.client.widget.Resizable;

public class ManageImages extends LayoutContainer implements Resizable {
	private FileDirectoryTreeGridContainer fileDirectoryTreeGridContainer;
	public ManageImages() {
		init();
	}
	
	public void init() {
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		fileDirectoryTreeGridContainer = new FileDirectoryTreeGridContainer();
		add(fileDirectoryTreeGridContainer, new BorderLayoutData(LayoutRegion.WEST));
	}
	
	@Override
	public void resize(int width, int height) {
		
	}

}
