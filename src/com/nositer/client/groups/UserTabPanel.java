package com.nositer.client.groups;

import com.extjs.gxt.ui.client.widget.TabPanel;
import com.nositer.client.dto.generated.User;
import com.nositer.client.widget.Resizable;


public class UserTabPanel extends TabPanel implements Resizable {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserTabPanel(User user) {
		super();
		this.user = user;		
	}

	public void init() {
	
	}

	@Override
	public void resize(int width, int height) {
	
	}

}
