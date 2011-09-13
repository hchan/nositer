package com.nositer.client.viewblog;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;

public class ViewBlog extends LayoutContainer {
	private static ViewBlog instance;

	public static ViewBlog getInstance(boolean createIfNecessary) {
		ViewBlog retval = null;
		if (instance != null) {
			retval = instance;
		} else if (createIfNecessary) {
			retval = new ViewBlog();	
			instance = retval;
		}
		return retval;
	}

	public static void setInstance(ViewBlog instance) {
		ViewBlog.instance = instance;
	}	
	
	public ViewBlog() {				
		init();
	}
	
	public void init() {
		setAutoHeight(true);
		setAutoWidth(true);
		setLayout(new FlowLayout());
	}

	public void showBlog(String subHistoryToken) {
		removeAll();
		add(ViewBlogContainer.getInstance(true));
		ViewBlogContainer.getInstance(false).populate(Integer.parseInt(subHistoryToken));
		layout();
	}  
		
	public void showSearch() {
		
	}
}
