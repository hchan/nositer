package com.nositer.client.widget.combobox;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Salutationcode;

@SuppressWarnings({"rawtypes", "unchecked"}) 
public class SalutationcodeComboBox extends ComboBoxPlus<Salutationcode> {

	public SalutationcodeComboBox() {		
		init();
	}

	public void init() {		
		this.setTemplate(getTemplateStr());		
		initAsLookupTable();
	}
	
	@Override
	public boolean doServiceWithRPC(int offset, int limit, String query, AsyncCallback callback) {
		ServiceBroker.lookupService.getCodes(Salutationcode.class.getName(), offset, limit, query, callback);
		return true;
	}
	
	
	private native String getTemplateStr() /*-{ 
    return  [ 
    '<tpl for=".">', 
    '<div class="x-combo-list-item"><B>{code}</B></div>', 
    '</tpl>' 
    ].join(""); 
  }-*/;  


}
