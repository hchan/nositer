package com.nositer.client.createiwantto;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Iwantto;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.InfoMessageBox;
import com.nositer.client.widget.Resizable;

@SuppressWarnings({"rawtypes", "unchecked"})
public class CreateIWantTo extends LayoutContainer implements Resizable {


	private Label headingLabel;
	private ContentPanel contentPanel;
	private FormPanel formPanel;
	private TextField<String> iWantToTextField;

	public CreateIWantTo() {
		init();
	}

	public void init() {
		setLayout(new FitLayout());
		contentPanel = new ContentPanel();
		contentPanel.setBodyBorder(false);
		contentPanel.setHeaderVisible(false);
		VBoxLayout layout = new VBoxLayout();


		contentPanel.setLayout(layout);
		headingLabel = new Label("Create I Want To ...");
		headingLabel.setStyleName("formHeading");
		contentPanel.add(headingLabel, new VBoxLayoutData(new Margins (5,5,5,5)));

		formPanel = new FormPanel();
		formPanel.setLabelWidth(55);
		formPanel.setBodyBorder(false);
		formPanel.setHeaderVisible(false);
		formPanel.setLabelSeparator("");
		iWantToTextField = new TextField<String>();
		iWantToTextField.setFieldLabel("I want to ");
		formPanel.add(iWantToTextField, new FormData("100%"));
		addButtons();
		contentPanel.add(formPanel, new VBoxLayoutData());//new Margins (5,5,5,5)));
		add(contentPanel);
		resize(0,0);
	}

	private void addButtons() {
		formPanel.setButtonAlign(HorizontalAlignment.CENTER);  
		Button button = new Button("Save");
		formPanel.addButton(button);  
		Listener saveListener = new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				AsyncCallback<Iwantto> callback = new AsyncCallback<Iwantto>() {
					@Override
					public void onFailure(Throwable caught) {							
						GWTUtil.log("", caught);
					}

					@Override
					public void onSuccess(Iwantto result) {
						InfoMessageBox.show("Saved!", new Listener<MessageBoxEvent>() {
							@Override
							public void handleEvent(MessageBoxEvent be) {								
								History.newItem(HistoryToken.VIEWPROFILE.toString());									
							}								
						});										
					}
				};
				ServiceBroker.iWantToService.createIWantTo(createIWantToDTO(), callback);
			}		
		};
		button.addListener(Events.Select, saveListener);
	}

	
	private Iwantto createIWantToDTO() {		
		Iwantto retval = new Iwantto();
		retval.setDescription(iWantToTextField.getValue());
		return retval;
	}
	
	@Override
	public void resize(int width, int height) {
		setSize(MainPanel.getInstance().getWidth(), MainPanel.getInstance().getHeight());
		contentPanel.setWidth(MainPanel.getInstance().getWidth());
		contentPanel.setHeight(MainPanel.getInstance().getHeight());
		formPanel.setWidth(MainPanel.getInstance().getWidth() - 10);
	}

}
