package com.nositer.client.widget.combobox;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Postalcode;

@SuppressWarnings({"rawtypes", "unchecked"}) 
public class PostalcodeComboBox extends ComboBoxPlus<Postalcode> {

	public PostalcodeComboBox() {		
		init();
	}

	public void init() {		
		this.setTemplate(getTemplateStr());		
		initAsLookupTable();
	}
	
	@Override
	public boolean doServiceWithRPC(int offset, int limit, String query, AsyncCallback callback) {
		//ServiceBroker.postalcodeService.getPostalcodes(offset, limit, query, callback);
		ServiceBroker.lookupService.getCodes(Postalcode.class.getName(), offset, limit, query, callback);
		return true;
	}
	
	
	private native String getTemplateStr() /*-{ 
    return  [ 
    '<tpl for=".">', 
    '<div class="x-combo-list-item"><B>{code}</B> <I><small>{city}, {province}</small></I></div>', 
    '</tpl>' 
    ].join(""); 
  }-*/;  


}
