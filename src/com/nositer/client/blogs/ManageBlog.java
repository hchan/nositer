package com.nositer.client.blogs;

import com.extjs.gxt.ui.client.widget.TabPanel;
import com.nositer.client.Nositer;
import com.nositer.client.dto.generated.Blog;
import com.nositer.client.dto.generated.GroupPlusView;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ManageBlog extends TabPanel {
	private static ManageBlog instance;
	private BlogsContainerTabItem blogsContainerTabItem;
	

	public BlogsContainerTabItem getBlogsContainerTabItem() {
		return blogsContainerTabItem;
	}

	public void setBlogsContainerTabItem(BlogsContainerTabItem blogsContainerTabItem) {
		this.blogsContainerTabItem = blogsContainerTabItem;
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
		setAutoHeight(true);
		setAutoWidth(true);
		blogsContainerTabItem = new BlogsContainerTabItem("My Blog");
		add(blogsContainerTabItem);
		
	}	
	
	
	public static boolean isBlogIOwn(Blog blog) {
		boolean retval = false;
		retval = blog.getUserid().equals(Nositer.getInstance().getUser().getId());
		return retval;
	}  

}
