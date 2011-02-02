package com.nositer.client.left;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
@SuppressWarnings({"rawtypes", "unchecked"})
public class NavigationTree extends LayoutContainer {	
	private ArrayList<NavigationItem> navigationItems;
	public NavigationTree() {
		navigationItems = new ArrayList<NavigationItem>();
	}
	public ArrayList<NavigationItem> getNavigationItems() {
		return navigationItems;
	}

	public void setNavigationItems(ArrayList<NavigationItem> navigationItems) {
		this.navigationItems = navigationItems;
	}
	
	
	public NavigationItem createNavigationItem(String labelStr) {
		final NavigationItem retval = new NavigationItem(labelStr);
		navigationItems.add(retval);
		retval.addListener(Events.OnClick, new Listener() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (retval.isSelected()) {
					select(retval);
				}
			}
		});

		return retval;
	}
	
	public void select(NavigationItem navigationItemToSelect) {
		for (NavigationItem navigationItem : navigationItems) {
			navigationItem.doUnSelected();
		}
		navigationItemToSelect.doSelected();
	}
}
