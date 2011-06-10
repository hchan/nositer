package com.nositer.client.widget.messagebox;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.MessageBox.MessageBoxType;

public class PromptMessageBox {
	public static void show (String title, String msg, Listener<MessageBoxEvent> callback) {
		MessageBox.prompt(title, msg, callback);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void show (String title, String msg, final String textFieldValue, Listener<MessageBoxEvent> callback) {
		final MessageBox messageBox = new MessageBox();

		messageBox.setTitle(title);

		messageBox.setMessage(msg);
		messageBox.setType(MessageBoxType.PROMPT);
		messageBox.setButtons(Dialog.OKCANCEL);
		if (callback != null) {
			messageBox.addCallback(callback);
		}

		Dialog dialog = messageBox.getDialog();
		dialog.addListener(Events.Activate, new Listener() {
			@Override
			public void handleEvent(BaseEvent be) {
				messageBox.getTextBox().setValue(textFieldValue);
				messageBox.getTextBox().selectAll();
			}
		});
		dialog.show();
	}
}
