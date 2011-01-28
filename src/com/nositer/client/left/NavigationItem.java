package com.nositer.client.left;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;

public class NavigationItem extends LayoutContainer {
	public static final String LABELPREFIX = "&rArr;&nbsp;";
	public static final String NAVIGATION_ITEM_OFF = "navigationItemOff";
	public static final String NAVIGATION_ITEM_ON = "navigationItemOn";
	private Label label;
	private boolean selected = false;
	private String labelStr;
	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getLabelStr() {
		return labelStr;
	}

	public void setLabelStr(String labelStr) {
		this.labelStr = labelStr;
	}

	public NavigationItem(String labelStr) {
		this.labelStr = labelStr;
		setStyleName(NAVIGATION_ITEM_OFF);
		setBorders(true);
		label = new Label(LABELPREFIX + labelStr);
		add(label);
		addDefaultListeners();
	}

	private void addDefaultListeners() {
		addListener(Events.OnClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				selected = !selected;
				if (selected) {					
					doSelected();
				} else {				
					doUnSelected();
				}
			}
		});
	}

	public void doSelected() {
		selected = true;
		removeStyleName(NAVIGATION_ITEM_OFF);
		setStyleName(NAVIGATION_ITEM_ON);
		setBorders(true);
	}

	public void doUnSelected() {
		selected = false;
		removeStyleName(NAVIGATION_ITEM_ON);
		setStyleName(NAVIGATION_ITEM_OFF);
		setBorders(true);
	}
}
