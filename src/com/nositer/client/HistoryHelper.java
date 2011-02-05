package com.nositer.client;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.nositer.client.editprofile.ChangePassword;
import com.nositer.client.editprofile.EditBasicProfile;
import com.nositer.client.left.LeftPanel;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.viewprofile.ViewProfileTabPanel;

import static com.nositer.client.HistoryTokenHelper.*;
public class HistoryHelper {
	public static final String HOME = "";
	
	public static void addHistorySupport() {
		doOnModuleLoadHistoryToken();
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			public void onValueChange(ValueChangeEvent<String> event) {
				String historyToken = event.getValue();
				onHistoryChanged(historyToken);
			}
			
		});

	}

	public static void doOnModuleLoadHistoryToken() {
		String historyToken = History.getToken();
		if (!historyToken.equals(HOME)) {
			onHistoryChanged(historyToken);
		}
	}

	public static void onHistoryChanged(String historyToken) {
		GWTUtil.log("historyToken: " + historyToken);
		LeftPanel leftPanel = LeftPanel.getInstance();
		AccordionLayout accordionLayout = leftPanel.getAccordionLayout();
		if (historyToken.equals(HOME)) {				
			accordionLayout.setActiveItem(null);
			MainPanel.getInstance().removeAll();
		} else if (historyToken.equals(VIEWPROFILE.toString())) {
			leftPanel.getProfile().expand();		
			leftPanel.getNavigationTree().select(leftPanel.getViewProfileNavigationItem());
			setMainPanel(new ViewProfileTabPanel());
		} else if (historyToken.equals(EDITBASICPROFILE.toString())) {
			leftPanel.getProfile().expand();		
			leftPanel.getNavigationTree().select(leftPanel.getEditBasicProfileNavigationItem());
			setMainPanel(new EditBasicProfile());
		} else if (historyToken.equals(CHANGEPASSWORD.toString())) {
			leftPanel.getProfile().expand();	
			leftPanel.getNavigationTree().select(leftPanel.getChangePasswordNavigationItem());
			setMainPanel(new ChangePassword());
		}
	}
	

	
	public static void setMainPanel(Component component) {
		MainPanel.getInstance().removeAll();
		MainPanel.getInstance().add(component);
		MainPanel.getInstance().layout(true);
	}

}
