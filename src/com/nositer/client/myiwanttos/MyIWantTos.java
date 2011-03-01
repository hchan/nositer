package com.nositer.client.myiwanttos;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.widget.Resizable;
import com.nositer.client.widget.TabItemPlus;

@SuppressWarnings({"rawtypes", "unchecked"})
public class MyIWantTos extends TabPanel implements Resizable {
	private TabItemPlus myIWantTosItem;
	private MyIWantTosContainer myIWantTosContainer;
	private static MyIWantTos instance;

	public MyIWantTosContainer getMyIWantTosContainer() {
		return myIWantTosContainer;
	}

	public void setMyIWantTosContainer(MyIWantTosContainer myIWantTosContainer) {
		this.myIWantTosContainer = myIWantTosContainer;
	}
	

	public static MyIWantTos getInstance(boolean createIfNecessary) {
		MyIWantTos retval = null;
		if (instance != null) {
			retval = instance;
		} else if (createIfNecessary) {
			retval = new MyIWantTos();			
		}
		return retval;
	}
	
	public static void setInstance(MyIWantTos instance) {
		MyIWantTos.instance = instance;
	}

	public TabItemPlus getMyIWantTosItem() {
		return myIWantTosItem;
	}

	public void setMyIWantTosItem(TabItemPlus myIWantTosItem) {
		this.myIWantTosItem = myIWantTosItem;
	}

	public MyIWantTos() {
		init();
	}

	
	public void init() {
		setAutoHeight(true);
		setAutoWidth(true);
		myIWantTosItem = new TabItemPlus("My I want To's") {

			@Override
			public void resize(int width, int height) {
				
			}
			
			@Override
			public void addDefaultListeners() {
			
				addListener(Events.Select, new Listener() {
					@Override
					public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {						
						HistoryManager.addHistory(HistoryToken.MYIWANTTOS.toString());
						resize(0,0);
					}
				});
			}
			
		};  
		myIWantTosItem.setClosable(false);
		myIWantTosItem.addListener(Events.Select, new Listener() {

			@Override
			public void handleEvent(BaseEvent be) {
				resize(0,0);
				
			}});
		FitLayout layout = new FitLayout();
		myIWantTosItem.setLayout(layout);
		myIWantTosContainer = new MyIWantTosContainer();
		myIWantTosItem.add(myIWantTosContainer);
		add(myIWantTosItem);
		instance = this;
	}
	
	public void showTab(String tabId) {
		TabItem tabItem = null;
		tabItem = findItem(tabId, false);
		if (tabItem == null) {
			tabItem = new IWantToTabItem(tabId);			
			add(tabItem);
		}	
		setSelection(tabItem);		
	}

	@Override
	public void resize(int width, int height) {
		Resizable resizable = null;
		if (this.getSelectedItem().getItems().get(0) instanceof Resizable) {
			resizable = (Resizable)this.getSelectedItem().getItems().get(0);
			resizable.resize(width, height);
		}
	}  
}
