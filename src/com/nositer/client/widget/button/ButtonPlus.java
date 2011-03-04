package com.nositer.client.widget.button;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class ButtonPlus extends Button {

	public ButtonPlus(String text, AbstractImagePrototype icon) {
	  super(text, icon);
	  
	  addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent be) {
				doSelect();
			}
	  });
	}
	
	public void doSelect() {
		
	}
}
