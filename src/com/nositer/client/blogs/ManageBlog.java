package com.nositer.client.blogs;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ManageBlog extends ContentPanel {
	private static ManageBlog instance;
	private BlogsContainer blogsContainer;
	private LayoutContainer topComponent;

	public BlogsContainer getBlogsContainer() {
		return blogsContainer;
	}

	public void setBlogsContainer(BlogsContainer blogsContainer) {
		this.blogsContainer = blogsContainer;
	}

	public static ManageBlog getInstance(boolean createIfNecessary) {
		ManageBlog retval = null;
		if (instance != null) {
			retval = instance;
		} else if (createIfNecessary) {
			retval = new ManageBlog();	
			instance = retval;
		}
		return retval;
	}

	public static void setInstance(ManageBlog instance) {
		ManageBlog.instance = instance;
	}

	public ManageBlog() {
		init();
	}

	public void init() {
		setHeaderVisible(false);
		setAutoHeight(true);
		setAutoWidth(true);
		instance = this;
		blogsContainer = new BlogsContainer();
		add(blogsContainer);
		topComponent = new LayoutContainer();
		topComponent.add(new Label("My Blog"));
		setTopComponent(topComponent);
	}	
}
