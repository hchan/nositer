package com.nositer.client.blogs;

import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.nositer.client.Nositer;
import com.nositer.client.blogs.ManageBlog.TabItemType;
import com.nositer.client.dto.generated.Blog;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.groups.GroupTabItem;
import com.nositer.client.groups.GroupTabPanel;
import com.nositer.client.groups.Groups;
import com.nositer.client.groups.UserTabItem;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ManageBlog extends TabPanel {
	public enum TabItemType {
		BLOGS, EDITBLOG
	}
	
	
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

	public void showNonClosableTab(ManageBlog.TabItemType tabItemType) {
		TabItem tabItem = null;
		if (tabItemType.equals(ManageBlog.TabItemType.BLOGS)) {
			tabItem = blogsContainerTabItem;
			blogsContainerTabItem.resize(0,0);
		} 
		setSelection(tabItem);		
	}  

	public void showClosableTab(String tabId, ManageBlog.TabItemType tabItemType) {
		TabItem tabItem = null;
		tabItem = findItem(tabId, false);
		if (tabItem == null) {
			if (tabItemType.equals(ManageBlog.TabItemType.EDITBLOG)) {
				tabItem = new EditBlogTabItem(tabId);
			}
			add(tabItem);
		} 
		setSelection(tabItem);	
	}	
	
}
