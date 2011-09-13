package com.nositer.client.iwanttos;

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
public class IWanttos extends TabPanel implements Resizable {
	private TabItemPlus iwanttosItem;
	private IwanttosContainer iwanttosContainer;
	private static IWanttos instance;

	
	

	public TabItemPlus getIwanttosItem() {
		return iwanttosItem;
	}

	public void setIwanttosItem(TabItemPlus iwanttosItem) {
		this.iwanttosItem = iwanttosItem;
	}

	public IwanttosContainer getIwanttosContainer() {
		return iwanttosContainer;
	}

	public void setIwanttosContainer(IwanttosContainer iwanttosContainer) {
		this.iwanttosContainer = iwanttosContainer;
	}

	public static IWanttos getInstance(boolean createIfNecessary) {
		IWanttos retval = null;
		if (instance != null) {
			retval = instance;
		} else if (createIfNecessary) {
			retval = new IWanttos();
			instance = retval;
		}
		return retval;
	}
	
	public static void setInstance(IWanttos instance) {
		IWanttos.instance = instance;
	}



	public IWanttos() {
		init();
	}

	
	public void init() {
		setAutoHeight(true);
		setAutoWidth(true);
		iwanttosItem = new TabItemPlus("My I want To's") {

			@Override
			public void resize(int width, int height) {
				
			}
			
			@Override
			public void addDefaultListeners() {
			
				addListener(Events.Select, new Listener() {
					@Override
					public void handleEvent(com.extjs.gxt.ui.client.event.BaseEvent be) {						
						HistoryManager.addHistory(HistoryToken.IWANTTOS.toString());
						resize(0,0);
					}
				});
			}
			
		};  
		iwanttosItem.setClosable(false);
		iwanttosItem.addListener(Events.Select, new Listener() {

			@Override
			public void handleEvent(BaseEvent be) {
				resize(0,0);
				
			}});
		FitLayout layout = new FitLayout();
		iwanttosItem.setLayout(layout);
		iwanttosContainer = new IwanttosContainer();
		iwanttosItem.add(iwanttosContainer);
		add(iwanttosItem);
		instance = this;
	}
	
	public void showTab(String tabId) {
		TabItem tabItem = null;
		tabItem = findItem(tabId, false);
		if (tabItem == null) {
			tabItem = new IwanttoTabItem(tabId);			
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
