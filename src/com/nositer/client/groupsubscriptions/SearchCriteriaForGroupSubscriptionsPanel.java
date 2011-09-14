package com.nositer.client.groupsubscriptions;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.LoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.GroupSubscriptionView;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.ErrorPanel;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class SearchCriteriaForGroupSubscriptionsPanel extends FormPanel {
	private GroupSubscriptionsContainer groupSubscriptionsContainer;
	private TextField<String> lastname;	
	private Button searchButton;
	private ErrorPanel errorPanel;


	private GroupSubscriptionsGrid searchGroupsGrid;
	
	public GroupSubscriptionsGrid getSearchGroupsGrid() {
		return searchGroupsGrid;
	}

	public void setSearchGroupsGrid(GroupSubscriptionsGrid searchGroupsGrid) {
		this.searchGroupsGrid = searchGroupsGrid;
	}

	public Button getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(Button searchButton) {
		this.searchButton = searchButton;
	}

	public ErrorPanel getErrorPanel() {
		return errorPanel;
	}

	public void setErrorPanel(ErrorPanel errorPanel) {
		this.errorPanel = errorPanel;
	}

	public TextField<String> getLastname() {
		return lastname;
	}

	public void setLastname(TextField<String> groupName) {
		this.lastname = groupName;
	}

	public SearchCriteriaForGroupSubscriptionsPanel(GroupSubscriptionsContainer groupSubscriptionsContainer) {
		this.groupSubscriptionsContainer = groupSubscriptionsContainer;
		init();
	}

	
	public void init() {
		//setHeaderVisible(false);
		errorPanel = new ErrorPanel();
		//errorPanel.init();
		errorPanel.hide();
		setHeading("Subscribers (Click to hide/show filter options)");
		getHeader().sinkEvents(Event.ONCLICK);
		getHeader().addListener(Events.OnClick, new Listener() {

			@Override
			public void handleEvent(BaseEvent event) {
				if (SearchCriteriaForGroupSubscriptionsPanel.this.isCollapsed()) {
					SearchCriteriaForGroupSubscriptionsPanel.this.expand();
				} else {
					SearchCriteriaForGroupSubscriptionsPanel.this.collapse();
				}
			}}
		);
		setCollapsible(true);
		setBodyBorder(false);
		// foaddSearchLabel();
		setLabelWidth(107);
		setFieldWidth(200);
		lastname = new TextField<String>();
		lastname.setFieldLabel("Last name (starts with)");
	
		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				GWTUtil.log("", caught);
			}

			@Override
			public void onSuccess(Void result) {
				//location.populate(TopPanel.getInstance().getUser());
			}
		};
		ServiceBroker.noopService.noop(0, callback);

		searchButton = createSearchButton();

		setTopComponent(errorPanel);
		add(lastname);
	
		setButtonAlign(HorizontalAlignment.CENTER);
		addButton(searchButton);



	}

	private Button createSearchButton() {
		Button retval = new Button("Search");
		retval.addSelectionListener(new SelectionListener<ButtonEvent>() {			
			@Override
			public void componentSelected(ButtonEvent ce) {
				doSearch();
			}					
		});
		return retval;
	}


	public void doSearch() {
		PagingLoadConfig config = new BasePagingLoadConfig();
		config.set(GroupSubscriptionsGrid.USERCRITERIA, new Boolean(true));
		config.set(GroupSubscriptionView.Column.lastname.toString(), lastname.getValue());
		searchGroupsGrid.getLoader().load(config);		
		groupSubscriptionsContainer.resize(0, 0);
	}

	public ArrayList<String> getErrors() {
		ArrayList<String> retval = new ArrayList<String>();
		GWTUtil.addRequiredErrorIfNecessary(lastname, retval);		
		return retval;
	}

}
