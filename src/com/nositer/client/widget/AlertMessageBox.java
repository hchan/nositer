package com.nositer.client.widget;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.MessageBox;

public class AlertMessageBox {
	public static void show (String title, String msg, Listener<MessageBoxEvent> callback) {
		MessageBox.alert(title, msg, callback);
	}

	
}
