package com.nositer.client.widget.combobox;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;
import com.nositer.client.dto.generated.Relationshipcode;

@SuppressWarnings({"rawtypes", "unchecked"}) 
public class RelationshipcodeComboBox extends ComboBoxPlus<Relationshipcode> {

	public RelationshipcodeComboBox() {		
		init();
	}

	public void init() {		
		this.setTemplate(getTemplateStr());		
		initAsLookupTable();
	}
	
	@Override
	public boolean doServiceWithRPC(int offset, int limit, String query, AsyncCallback callback) {
		ServiceBroker.lookupService.getCodes(Relationshipcode.class.getName(), offset, limit, query, callback);
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
