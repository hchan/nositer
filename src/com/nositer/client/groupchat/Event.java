package com.nositer.client.groupchat;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

public class Event extends BaseModel implements Serializable {

    private String login;
    private String data;

    public Event() {

    }

    public Event(String login, String data) {
        this.login = login;
        this.data = data;
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
