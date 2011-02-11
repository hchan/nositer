package com.nositer.client.history;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.nositer.client.editprofile.ChangePassword;
import com.nositer.client.editprofile.EditAboutMe;
import com.nositer.client.editprofile.EditBasicProfile;
import com.nositer.client.left.LeftPanel;
import com.nositer.client.main.MainPanel;
import com.nositer.client.manageimages.ManageImages;
import com.nositer.client.uploadimages.UploadImages;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.viewprofile.ViewProfileTabPanel;

import static com.nositer.client.history.HistoryToken.*;

@SuppressWarnings({"unchecked", "rawtypes"})
public class HistoryManager {
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
		} else if (historyToken.equals(EDITABOUTME.toString())) {
			leftPanel.getProfile().expand();	
			leftPanel.getNavigationTree().select(leftPanel.getEditAboutMeNavigationItem());
			setMainPanel(new EditAboutMe());
		} else if (historyToken.equals(UPLOADIMAGES.toString())) {
			leftPanel.getImages().expand();	
			leftPanel.getNavigationTree().select(leftPanel.getUploadImagesNavigationItem());
			setMainPanel(new UploadImages());
		} else if (historyToken.equals(MANAGEIMAGES.toString())) {
			leftPanel.getImages().expand();	
			leftPanel.getNavigationTree().select(leftPanel.getManageImagesNavigationItem());
			setMainPanel(new ManageImages());
		}
		
		
	}
	

	
	public static void setMainPanel(Component component) {
		MainPanel.getInstance().removeAll();
		MainPanel.getInstance().add(component);
		MainPanel.getInstance().layout(true);
	}

	
	public static void addHistoryOnClick(Component component, final String historyToken) {
		component.addListener(Events.OnClick, new Listener() {
			@Override
			public void handleEvent(BaseEvent be) {				
				History.newItem(historyToken);
			}
		});
	}

}
