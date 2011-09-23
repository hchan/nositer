package com.nositer.client.blogs;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.createoreditblog.CreateOrEditBlog;
import com.nositer.client.dto.generated.Blog;
import com.nositer.client.dto.generated.User;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.viewprofile.ViewProfile;
import com.nositer.client.viewprofile.ViewProfileContainer;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.TabItemPlus;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class EditBlogTabItem extends TabItemPlus implements Resizable {



	public EditBlogTabItem(String tabId) {
		setItemId(tabId);
		addDefaultListeners();
		init();
	}


	public void init() {
		setText("Loading...");
		setClosable(true);

		AsyncCallback<Blog> callback = new AsyncCallback<Blog>() {
			@Override
			public void onFailure(Throwable caught) {
				GWTUtil.log("", caught);
			}
			@Override
			public void onSuccess(Blog result) {
				init(result);
			}
		};
		ServiceBroker.blogService.getBlog(Integer.parseInt(getItemId()), callback);

	}

	public void init(Blog blog) {
		int lengthOfHeaderText = 10;
		String headerText = blog.getName();
		if (headerText.length() > lengthOfHeaderText) {
			headerText = headerText.substring(0, lengthOfHeaderText);
			headerText += "...";
		}
		EditBlogTabItem.this.setText(headerText);

		CreateOrEditBlog createOrEditBlog = new CreateOrEditBlog(false);
		createOrEditBlog.populate(blog);

		add(createOrEditBlog);
		layout();
	}


	@Override
	public void addDefaultListeners() {
		addListener(Events.Close, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {

			}
		});
		addListener(Events.Select, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {
				History.newItem(HistoryToken.EDITBLOG + HistoryManager.SUBTOKENSEPARATOR + EditBlogTabItem.this.getItemId());
				resize(0,0);
			}
		});
	}

	@Override
	public void resize(int width, int height) {		
		//setSize(MainPanel.getInstance().getWidth()-3,
		//		MainPanel.getInstance().getHeight());
	}
}
