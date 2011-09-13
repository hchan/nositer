package com.nositer.client.viewblog;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Blog;
import com.nositer.client.dto.generated.User;
import com.nositer.client.util.GWTUtil;

public class ViewBlogContainer extends LayoutContainer {

	private Label name;
	private HtmlContainer description;
	private static ViewBlogContainer instance;



	public static ViewBlogContainer getInstance(boolean createIfNecessary) {
		ViewBlogContainer retval = null;
		if (instance != null) {
			retval = instance;
		} else if (createIfNecessary) {
			retval = new ViewBlogContainer();		
			instance = retval;
		}
		return retval;
	}

	public static void setInstance(ViewBlogContainer instance) {
		ViewBlogContainer.instance = instance;
	}

	public void populate(Integer blogid) {
		AsyncCallback<Blog> callback = new AsyncCallback<Blog>() {
			@Override
			public void onFailure(Throwable caught) {
				GWTUtil.log("", caught);
			}
			@Override
			public void onSuccess(Blog result) {
				if (result != null) {					
					init(result);
				}
			}			
		};
		instance = this;
		ServiceBroker.blogService.getBlog(blogid, callback);
	}

	public ViewBlogContainer() {
		init();
	}

	public void init(Blog result) {
		description.setHtml(result.getDescription());
		name.setText(result.getName());
	}
	
	private void init() {
		//this.setAutoHeight(true);
		//this.setAutoWidth(true);
		setHeight(100000);
		setWidth(100000);
		this.setLayout(new RowLayout(Orientation.VERTICAL));

		name = new Label();
		name.setStyleName("blogName");
		this.add(name, new RowData(-1, -1, new Margins(5)));
		description = new HtmlContainer();
		description.setStyleName("profileDescription");
		this.add(description, new RowData(-1, -1, new Margins(5)));
	}


}
