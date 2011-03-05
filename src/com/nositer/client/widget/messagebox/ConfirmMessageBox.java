package com.nositer.client.widget.messagebox;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.MessageBox;

public class ConfirmMessageBox {
	public static void show (String title, String msg, Listener<MessageBoxEvent> callback) {
		MessageBox.confirm(title, msg, callback);
	}	
}