package com.nositer.client;

import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.nositer.client.left.LeftPanel;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.GWTUtil;
import static com.nositer.client.HistoryTokenHelper.*;
public class HistoryHelper {

	public static void addHistorySupport() {

		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			public void onValueChange(ValueChangeEvent<String> event) {
				String historyToken = event.getValue();
				GWTUtil.log("historyToken: " + historyToken);
				LeftPanel leftPanel = LeftPanel.getInstance();
				AccordionLayout accordionLayout = leftPanel.getAccordionLayout();
				if (historyToken.equals(HOME)) {				
					accordionLayout.setActiveItem(null);
					MainPanel.getInstance().removeAll();
				} else if (historyToken.equals(VIEWPROFILE)) {
					leftPanel.getProfile().expand();
				
					leftPanel.getNavigationTree().select(leftPanel.getViewProfileNavigationItem());
					leftPanel.doViewProfile();
				}
			}
		});

	}

}
