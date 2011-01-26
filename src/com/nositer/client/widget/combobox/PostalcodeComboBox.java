package com.nositer.client.widget.combobox;

import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.HttpProxy;
import com.extjs.gxt.ui.client.data.JsonPagingLoadResultReader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelType;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.user.client.Element;
import com.nositer.client.dto.generated.Postalcode;
import com.nositer.client.service.ServiceBroker;

public class PostalcodeComboBox extends ComboBoxPlus {

	@Override
	protected void onRender(Element parent, int index) {	
		super.onRender(parent, index);
		
		HttpProxy proxy = getHttpProxy(ServiceBroker.POSTALCODEJSONSERVICE);
		
		ModelType modelType = getModelTypeFromClass(Postalcode.class);
		JsonPagingLoadResultReader<PagingLoadResult<Postalcode>> reader = new JsonPagingLoadResultReader<PagingLoadResult<Postalcode>>(modelType);
		
		PagingLoader<PagingLoadResult<ModelData>> loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy, reader);  
		ListStore<Postalcode> store = new ListStore<Postalcode>(loader);
		
		
		this.setAutoValidate(true);
		this.setForceSelection(true);	
	}

	

	
}
