package com.nositer.client.widget.combobox;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ListView;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.generated.Postalcode;
import com.nositer.shared.ServiceBroker;

@SuppressWarnings({"rawtypes", "unchecked"}) 
public class PostalcodeComboBox extends ComboBoxPlus {

	public PostalcodeComboBox() {		
		init();
	}

	public void init() {
		RpcProxy<PagingLoadResult<Postalcode>> proxy = new RpcProxy<PagingLoadResult<Postalcode>>() {
			
			@Override
			protected void load(Object loadConfig, AsyncCallback callback) {
				BasePagingLoadConfig basePagingLoadConfig = (BasePagingLoadConfig)loadConfig;
				int limit = DEFAULTLIMIT;
				int offset = basePagingLoadConfig.get("offset");
				String query = (basePagingLoadConfig.get("query"));
				ServiceBroker.postalcodeService.getPostalcodes(offset, limit, query, callback);
			}
		};
		PagingLoader<PagingLoadResult<ModelData>> loader = getLoader(proxy); 
		ListStore<BeanModel> store = new ListStore<BeanModel>(loader);
		this.setStore(store);
		this.setTemplate(getTemplateStr());
		this.setDisplayField("CUSTOMDISPLAYFIELD");
		this.setView(new ListView<BeanModel>() {
			@Override
			protected BeanModel prepareData(BeanModel model) {
				model.set("CUSTOMDISPLAYFIELD", (String)model.get("code"));
				return super.prepareData(model);
			}			
		});
	}

	
	private native String getTemplateStr() /*-{ 
    return  [ 
    '<tpl for=".">', 
    '<div class="x-combo-list-item"><B>{code}</B> <I><small>{city}, {province}</small></I></div>', 
    '</tpl>' 
    ].join(""); 
  }-*/;  


}
