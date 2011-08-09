package com.nositer.client.groupchat;

import java.io.Serializable;
import java.util.TreeSet;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.nositer.client.dto.generated.User;

public class ChatEvent extends BaseModel implements Serializable {

	private User user;
    private String data;
    private String grouptagname;
    private ChatEventType chatEventType;
    private TreeSet<User> users; // users who are logged in to a group
    
    public ChatEvent() {
    }    
    
  
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public TreeSet<User> getUsers() {
		return users;
	}


	public void setUsers(TreeSet<User> users) {
		this.users = users;
	}


	public ChatEventType getChatEventType() {
		return chatEventType;
	}

	public void setChatEventType(ChatEventType chatEventType) {
		this.chatEventType = chatEventType;
	}

	public String getGrouptagname() {
		return grouptagname;
	}

	public void setGrouptagname(String grouptagname) {
		this.grouptagname = grouptagname;
	}


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return getUser().getLogin() + ": " + getData();
    }

	


}
