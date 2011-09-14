package com.nositer.client.viewblog;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Blog;
import com.nositer.client.history.HistoryManager;
import com.nositer.client.history.HistoryToken;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.ErrorPanel;
import com.nositer.client.widget.Resizable;

@SuppressWarnings({"unchecked", "rawtypes"})
public class SearchBlogByIdContainer extends LayoutContainer implements Resizable {

	private FormPanel formPanel;
	private TextField <String> blogidTextField;
	private ErrorPanel errorPanel;
	private static SearchBlogByIdContainer instance;

	public static SearchBlogByIdContainer getInstance(boolean createIfNecessary) {
		SearchBlogByIdContainer retval = null;
		if (instance != null) {
			retval = instance;
		} else if (createIfNecessary) {
			retval = new SearchBlogByIdContainer();		
			instance = retval;
		}
		return retval;
	}

	public static void setInstance(SearchBlogByIdContainer instance) {
		SearchBlogByIdContainer.instance = instance;
	}	

	public SearchBlogByIdContainer() {
		init();
	}

	public void init() {		
		formPanel = new FormPanel();		
		formPanel.setHeaderVisible(false);
		//formPanel.setLabelAlign(LabelAlign.TOP);
		//formPanel.setBorders(false);
		formPanel.setBodyBorder(false);
		formPanel.setLabelWidth(150);
		errorPanel = new ErrorPanel();
		errorPanel.setStyleAttribute("margin-bottom", "5px");
		errorPanel.hide();
		blogidTextField = new TextField<String>();
		blogidTextField.setFieldLabel("Enter the blog id");  
		blogidTextField.setLabelStyle("font-size: 14px; font-weight: bold;");		
		formPanel.add(errorPanel);
		formPanel.add(blogidTextField);//, new FormData("100%"));		
		this.add(formPanel);
		initButtons();			
	}	

	@Override
	public void resize(int width, int height) {
		formPanel.setWidth(this.getWidth());
	}

	public void initButtons() {
		initSearchButton();
	}

	public void initSearchButton() {
		formPanel.setButtonAlign(HorizontalAlignment.LEFT);  
		Button button = new Button("Search");
		formPanel.addButton(button);  

		Listener saveListener = new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				ArrayList<String> errors = getErrors();
				if (errors.size() > 0) {
					errorPanel.setErrors(errors);
					errorPanel.show();		
					resize(0,0);
				} else {

					AsyncCallback<Blog> callback = new AsyncCallback<Blog>() {
						@Override
						public void onFailure(Throwable caught) {
							errorPanel.clearErrorMessages();
							errorPanel.addErrorMessage(caught.getMessage());
							errorPanel.show();
							resize(0,0);
							GWTUtil.log("", caught);
						}

						@Override
						public void onSuccess(final Blog result) {						
							History.newItem(HistoryToken.VIEWBLOG.toString() + HistoryManager.SUBTOKENSEPARATOR + result.getId());	
						}
					};
					int blogid = 0;
					try {
						blogid = Integer.parseInt(blogidTextField.getValue());
						ServiceBroker.blogService.getBlog(blogid, callback);
					} catch (Exception e) {
						errorPanel.clearErrorMessages();
						errorPanel.addErrorMessage("Invalid blog id");
						errorPanel.show();
						resize(0,0);
					}
					
					
				}
			}
		};
		button.addListener(Events.Select, saveListener);
	}

	// Cancel
	public void initCancelButton() {
		Button cancelButton = new Button("Cancel");
		formPanel.addButton(cancelButton);  
		Listener cancelListener = new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				History.newItem(HistoryToken.MYPROFILE.toString());
			}
		};
		cancelButton.addListener(Events.Select, cancelListener);
	}

	@Override
	protected void onWindowResize(int width, int height) {
		super.onWindowResize(width, height);
		resize(width, height);
	}

	public ArrayList<String> getErrors() {
		ArrayList<String> retval = new ArrayList<String>();		
		return retval;
	}
}
