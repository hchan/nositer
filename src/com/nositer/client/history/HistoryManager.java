package com.nositer.client.history;


import static com.nositer.client.history.HistoryToken.*;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.Nositer;
import com.nositer.client.Scope;
import com.nositer.client.ServiceBroker;
import com.nositer.client.createiwantto.CreateIWantTo;
import com.nositer.client.createoreditgroup.CreateOrEditGroup;
import com.nositer.client.dto.generated.User;
import com.nositer.client.editprofile.ChangePassword;
import com.nositer.client.editprofile.EditAboutMe;
import com.nositer.client.editprofile.EditBasicProfile;
import com.nositer.client.groups.GroupTabPanel;
import com.nositer.client.groups.Groups;
import com.nositer.client.iwanttos.IWanttos;
import com.nositer.client.left.LeftPanel;
import com.nositer.client.main.MainPanel;
import com.nositer.client.managefiles.ManageFiles;
import com.nositer.client.searchforgroups.SearchForGroupsContainer;
import com.nositer.client.uploadfiles.UploadFiles;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.viewprofile.ViewProfile;

@SuppressWarnings({"unchecked", "rawtypes"})
public class HistoryManager {
	public static final String HOME = "";
	public static final String SUBTOKENSEPARATOR = "-";

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

	public static void onHistoryChanged(final String historyToken) {
		GWTUtil.log("historyToken: " + historyToken);
		LeftPanel leftPanel = LeftPanel.getInstance();
		AccordionLayout accordionLayout = leftPanel.getAccordionLayout();
		if (historyToken.equals(HOME)) {				
			accordionLayout.setActiveItem(null);
			MainPanel.getInstance().removeAll();
		} else {
			AsyncCallback<User> callback = new AsyncCallback<User>() {
				@Override
				public void onFailure(Throwable caught) {
					GWTUtil.log("", caught);
				}
				@Override
				public void onSuccess(User result) {
					if (result != null) {
						Nositer.getInstance().setUser(result);		
						onHistoryChangedForTokensOtherThanHome(historyToken); 
					}
				}
			};
			ServiceBroker.profileService.getCurrentUser(callback);
		}
	}

	public static void onHistoryChangedForTokensOtherThanHome(String historyToken) {
		LeftPanel leftPanel = LeftPanel.getInstance();
		if (historyToken.equals(VIEWPROFILE.toString())) {
			leftPanel.getProfile().expand();		
			leftPanel.getNavigationTree().select(leftPanel.getViewProfileNavigationItem());
			setMainPanel(new ViewProfile());
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
		} else if (historyToken.equals(UPLOADFILES.toString())) {
			leftPanel.getFiles().expand();	
			leftPanel.getNavigationTree().select(leftPanel.getUploadFilesNavigationItem());
			setMainPanel(new UploadFiles(new Scope(Scope.Type.user)));
		} else if (historyToken.equals(MANAGEFILES.toString())) {
			leftPanel.getFiles().expand();	
			leftPanel.getNavigationTree().select(leftPanel.getManageFilesNavigationItem());
			setMainPanel(new ManageFiles());
		} else if (historyToken.equals(CREATEGROUP.toString())) {
			leftPanel.getGroups().expand();	
			leftPanel.getNavigationTree().select(leftPanel.getCreateGroupNavigationItem());
			setMainPanel(new CreateOrEditGroup(true));
		} else if (historyToken.equals(GROUPS.toString())) {
			leftPanel.getGroups().expand();	
			leftPanel.getNavigationTree().select(leftPanel.getGroupsNavigationItem());
			Groups.getInstance(true).showNonClosableTab(Groups.TabItemType.GROUPS);
			setMainPanel(Groups.getInstance(true));
		}  else if (historyToken.equals(SEARCHFORGROUPS.toString())) {
			leftPanel.getGroups().expand();	
			leftPanel.getNavigationTree().select(leftPanel.getSearchForGroupsNavigationItem());
			Groups.getInstance(true).showNonClosableTab(Groups.TabItemType.SEARCHFORGROUPS);
			setMainPanel(Groups.getInstance(true));
		} else if (historyToken.startsWith(GROUPS.toString() + SUBTOKENSEPARATOR)) {
			leftPanel.getGroups().expand();	
			leftPanel.getNavigationTree().select(leftPanel.getGroupsNavigationItem());
			Groups.getInstance(true).showClosableTab(getSubHistoryToken(), GroupTabPanel.TabItemType.VIEW);
			setMainPanel(Groups.getInstance(true));
		} else if (historyToken.equals(CREATEIWANTTO.toString())) {
			leftPanel.getIWantto().expand();	
			leftPanel.getNavigationTree().select(leftPanel.getCreateIwantToNavigationItem());
			setMainPanel(new CreateIWantTo());
		} else if (historyToken.equals(IWANTTOS.toString())) {
			leftPanel.getIWantto().expand();	
			leftPanel.getNavigationTree().select(leftPanel.getIwanttosNavigationItem());
			setMainPanel(IWanttos.getInstance(true));
		} else if (historyToken.startsWith(EDITGROUP.toString() + SUBTOKENSEPARATOR)) {
			leftPanel.getGroups().expand();	
			leftPanel.getNavigationTree().select(leftPanel.getGroupsNavigationItem());
			Groups.getInstance(true).showClosableTab(getSubHistoryToken(), GroupTabPanel.TabItemType.EDIT);
			setMainPanel(Groups.getInstance(true));
		} else if (historyToken.startsWith(SUBSCRIPTIONSGROUP.toString() + SUBTOKENSEPARATOR)) {
			leftPanel.getGroups().expand();	
			leftPanel.getNavigationTree().select(leftPanel.getGroupsNavigationItem());
			Groups.getInstance(true).showClosableTab(getSubHistoryToken(), GroupTabPanel.TabItemType.SUBSCRIPTIONS);
			setMainPanel(Groups.getInstance(true));
		}  else if (historyToken.startsWith(GROUPSSUBSCRIBER.toString() + SUBTOKENSEPARATOR)) {
			leftPanel.getGroups().expand();	
			leftPanel.getNavigationTree().select(leftPanel.getGroupsNavigationItem());
			Groups.getInstance(true).showClosableTab(getSubHistoryToken(), GroupTabPanel.TabItemType.SUBSCRIBER);
			setMainPanel(Groups.getInstance(true));
		}
	}



	public static void setMainPanel(Component component) {
		Component curComponent = null;
		if (MainPanel.getInstance().getItemCount() == 1) {
			curComponent = MainPanel.getInstance().getItem(0);
		}
		if (!component.equals(curComponent)) {
			MainPanel.getInstance().removeAll();
			MainPanel.getInstance().add(component);
			MainPanel.getInstance().layout(true);
		}
	}


	public static void addHistoryOnClick(Component component, final String historyToken) {
		component.addListener(Events.OnClick, new Listener() {
			@Override
			public void handleEvent(BaseEvent be) {				
				History.newItem(historyToken);
			}
		});
	}

	public static void addSubHistoryToken(String subHistoryToken) {		
		String historyToken = History.getToken();
		String newHistoryToken = historyToken.replaceFirst(SUBTOKENSEPARATOR + ".*", "");
		newHistoryToken += SUBTOKENSEPARATOR + subHistoryToken;
		History.newItem(newHistoryToken);		
	}

	public static String getSubHistoryToken() {
		String retval = null;
		retval = History.getToken();
		retval = retval.replaceAll(".*" + SUBTOKENSEPARATOR, "");
		return retval;
	}

	public static void removeSubHistoryToken() {
		String historyToken = History.getToken();
		String newHistoryToken = historyToken.replaceFirst(SUBTOKENSEPARATOR + ".*", "");
		History.newItem(newHistoryToken);		
	}

	public static void addHistory(String historyToken) {
		String currHistoryToken = History.getToken();
		if (!historyToken.equals(currHistoryToken)) {
			History.newItem(historyToken);		
		}
	}
}
