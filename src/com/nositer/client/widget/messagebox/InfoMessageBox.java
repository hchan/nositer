package com.nositer.client.widget.messagebox;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.MessageBox;

public class InfoMessageBox {
	public static void show (String str) {
		MessageBox msgBox = new MessageBox();
		msgBox.setMessage(str);
		msgBox.show();
	}

	public static void show (String str, Listener<MessageBoxEvent> listener) {
		MessageBox msgBox = new MessageBox();
		msgBox.addCallback(listener);
		msgBox.setMessage(str);
		msgBox.show();		
	}
}
