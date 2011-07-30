package com.nositer.client.groupchat;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;

import org.atmosphere.gwt.client.AtmosphereListener;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.StatusCodeException;

public class CometListener implements AtmosphereListener {

    @Override
    public void onConnected(int heartbeat, int connectionID) {
        GWT.log("comet.connected [" +heartbeat+", "+connectionID+"]");
    }

    @Override
    public void onBeforeDisconnected() {
        //logger.log(Level.INFO, "comet.beforeDisconnected");
    }

    @Override
    public void onDisconnected() {
        GWT.log("comet.disconnected");
    }

    @Override
    public void onError(Throwable exception, boolean connected) {
        int statuscode =-1;
        if (exception instanceof StatusCodeException) {
            statuscode = ((StatusCodeException)exception).getStatusCode();
        }
        //GWT.log("comet.error [connected=" + connected + "] ("+statuscode+")", exception);
    }

    @Override
    public void onHeartbeat() {
        //GWT.log("comet.heartbeat ["+client.getConnectionID()+"]");
    }

    @Override
    public void onRefresh() {
        //GWT.log("comet.refresh ["+client.getConnectionID()+"]");
    }

    @Override
    public void onMessage(List<? extends Serializable> messages) {
        StringBuilder result = new StringBuilder();
        for(Serializable obj : messages) {
            result.append(obj.toString()).append("<br/>");
        }
        //logger.log(Level.INFO, "comet.message ["+client.getConnectionID()+"] " + result.toString());
        //Info.display("["+client.getConnectionID()+"] Received " + messages.size() + " messages", result.toString());
    }
};