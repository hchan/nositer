package com.nositer.client.left;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;

public class NavigationTree extends LayoutContainer {
	public static final String NAVIGATIONTREE_ITEM_OFF = "navigationTreeItemOff";
	public static final String NAVIGATIONTREE_ITEM_ON = "navigationTreeItemOn";
	public static final String EXPANDPREFIX = "&rArr;&nbsp;";
	public static final String COLLAPSEPREFIX = "&dArr;&nbsp;";
	
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

	public NavigationTree(String labelStr) {
		this.labelStr = labelStr;
		setStyleName(NAVIGATIONTREE_ITEM_OFF);
		setBorders(true);
		label = new Label(EXPANDPREFIX + labelStr);
		add(label);
		addDefaultListeners();
	}

	private void addDefaultListeners() {
		addListener(Events.OnClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				selected = !selected;
				if (selected) {
					removeStyleName(NAVIGATIONTREE_ITEM_OFF);
					setStyleName(NAVIGATIONTREE_ITEM_ON);
					setBorders(true);
					label.setText(COLLAPSEPREFIX + labelStr);
					doSelected();
				} else {
					removeStyleName(NAVIGATIONTREE_ITEM_ON);
					setStyleName(NAVIGATIONTREE_ITEM_OFF);
					setBorders(true);
					label.setText(EXPANDPREFIX + labelStr);
					doUnSelected();
				}
			}
		});
	}

	public void doSelected() {

	}

	public void doUnSelected() {

	}
}
