package com.nositer.client.groupchat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

import com.extjs.gxt.ui.client.data.BaseModel;

public class ChatEvent extends BaseModel implements Serializable {

    private String login;
    private String data;
    private String grouptagname;
    private ChatEventType chatEventType;
    private TreeSet<String> logins;
    
    public ChatEvent() {
    }    
    
    public TreeSet<String> getLogins() {
		return logins;
	}

	public void setLogins(TreeSet<String> logins) {
		this.logins = logins;
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

	public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return getLogin() + ": " + getData();
    }


}
