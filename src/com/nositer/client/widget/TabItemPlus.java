package com.nositer.client.widget;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.nositer.client.history.HistoryManager;

@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class TabItemPlus extends TabItem implements Resizable {
	
	public TabItemPlus() {
		super();
	}
	
	public TabItemPlus(String string) {
		super(string);
	}

	public void addDefaultListeners() {
		addListener(Events.Close, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {
				HistoryManager.removeSubHistoryToken();
			}
		});
		addListener(Events.Select, new Listener() {
			@Override
			public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {
				HistoryManager.addSubHistoryToken(TabItemPlus.this.getItemId());
				resize(0,0);
			}
		});
	}
}
