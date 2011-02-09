package com.nositer.client.widget.combobox;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Zipcode;

@SuppressWarnings({"rawtypes", "unchecked"}) 
public class ZipcodeComboBox extends ComboBoxPlus<Zipcode> {

	public ZipcodeComboBox() {		
		init();
	}

	public void init() {		
		this.setTemplate(getTemplateStr());		
		initAsLookupTable();
	}
	
	@Override
	public boolean doServiceWithRPC(int offset, int limit, String query, AsyncCallback callback) {
		ServiceBroker.zipcodeService.getZipcodes(offset, limit, query, callback);
		return true;
	}
	
	
	private native String getTemplateStr() /*-{ 
    return  [ 
    '<tpl for=".">', 
    '<div class="x-combo-list-item"><B>{code}</B> <I><small>{city}, {state}</small></I></div>', 
    '</tpl>' 
    ].join(""); 
  }-*/;  


}
