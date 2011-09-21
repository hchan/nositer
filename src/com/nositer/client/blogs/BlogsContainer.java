package com.nositer.client.blogs;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.nositer.client.main.MainPanel;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.button.RefreshButton;


public class BlogsContainer extends LayoutContainer implements Resizable {

	private ContentPanel contentPanel;
	private BlogsGrid blogsGrid;
	private ToolBar toolBar;
	
	public ContentPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(ContentPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public BlogsGrid getBlogsGrid() {
		return blogsGrid;
	}

	public void setBlogsGrid(BlogsGrid blogsGrid) {
		this.blogsGrid = blogsGrid;
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public void setToolBar(ToolBar toolBar) {
		this.toolBar = toolBar;
	}

	public BlogsContainer() {
		init();
	}

	public void init() {
		setLayout(new FitLayout());
		setMonitorWindowResize(true);
		blogsGrid = new BlogsGrid(); 
		toolBar = new ToolBar();			
		toolBar.add(new FillToolItem());
		toolBar.add(new RefreshButton() {			
			@Override
			public void doSelect() {
				blogsGrid.refresh();
			}
		});
		contentPanel = new ContentPanel();  
		contentPanel.setFrame(true);  
		contentPanel.setCollapsible(true);  
		contentPanel.setAnimCollapse(false);  
		contentPanel.setButtonAlign(HorizontalAlignment.CENTER);  
		contentPanel.setHeaderVisible(false);
		
		contentPanel.setLayout(new FitLayout());  
		contentPanel.add(blogsGrid);
		contentPanel.setBottomComponent(toolBar);
		
		setAutoHeight(true);
		setAutoWidth(true);
		resize(0,0);
		add(contentPanel);  
	}

	@Override
	protected void onWindowResize(int width, int height) {
		resize(width, height);
	}
	
	@Override
	public void resize(int width, int height) {		
		contentPanel.setSize(MainPanel.getInstance().getWidth()-4, MainPanel.getInstance().getHeight()-20);
		//blogsGrid.setSize(MainPanel.getInstance().getWidth()-15, MainPanel.getInstance().getHeight()-30);
	}
}
