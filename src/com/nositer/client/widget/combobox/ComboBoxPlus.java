package com.nositer.client.widget.combobox;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.data.HttpProxy;
import com.extjs.gxt.ui.client.data.ModelType;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.nositer.client.dto.DTO;

public class ComboBoxPlus extends ComboBox {
	public static final int DEFAULTLIMIT = 20;
	public ComboBoxPlus() {
		init();
	}
	
	private void init() {
		this.setPageSize(DEFAULTLIMIT);
		initPagingToolBar();
	}

	
	
	public static ModelType getModelType(ArrayList<String> columnNames) {
		ModelType retval = new ModelType();
		retval.setRoot("records");
		//retval.setRecordName("record");
		//retval.setTotalName("totalCount");
		for (String property : columnNames) {
			retval.addField(property);
		}
		return retval;
	}
	
	public static ModelType  getModelTypeFromClass(Class<? extends DTO> dtoClass) {
		DTO dto = GWT.create(dtoClass);
		return getModelType(dto.getColumnNames());
	}
	
	public HttpProxy getHttpProxy(String serviceName) {
		HttpProxy retval = null;
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET,  serviceName);
		retval = new HttpProxy (builder);
		return retval;
	}
	
	public void initPagingToolBar() {
		removeLastButtonFromToolBar(this.getPagingToolBar());
	
	}
	
	
	public static void removeLastButtonFromToolBar(PagingToolBar toolBar) {
		toolBar.getMessages().setDisplayMsg("");		
		toolBar.getMessages().setAfterPageText("");
		toolBar.getMessages().setEmptyMsg("");
		toolBar.getImages().setLast(null);
		toolBar.getImages().setLastDisabled(null);
		toolBar.getImages().setRefresh(null);
	}
	
	
	@Override
	protected void onBlur(ComponentEvent ce) {
		super.onBlur(ce);

		if (getValue() == null) {
			setRawValue("");
		} 
	}
}
