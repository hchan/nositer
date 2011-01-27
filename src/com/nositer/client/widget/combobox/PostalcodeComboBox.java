package com.nositer.client.widget.combobox;

import java.util.List;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ListView;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.generated.Postalcode;
import com.nositer.client.util.GWTUtil;
import com.nositer.shared.ServiceBroker;

public class PostalcodeComboBox extends ComboBoxPlus {

	public PostalcodeComboBox() {		
		init();

	}

	public void init() {
		setMinChars(1);
		RpcProxy<PagingLoadResult<Postalcode>> proxy = new RpcProxy<PagingLoadResult<Postalcode>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback callback) {
				BasePagingLoadConfig basePagingLoadConfig = (BasePagingLoadConfig)loadConfig;
				int limit = DEFAULTLIMIT;//basePagingLoadConfig.get("limit");
				int offset = basePagingLoadConfig.get("offset");
				String query = (basePagingLoadConfig.get("query"));
				ServiceBroker.postalcodeService.getPostalcodes(offset, limit, query, callback);

			}
		};


		BeanModelReader reader = new BeanModelReader() {
			@Override
			protected ListLoadResult<ModelData> newLoadResult(Object loadConfig, List<ModelData> models) {
				//return super.newLoadResult(loadConfig, models);
				return new BasePagingLoadResult<ModelData>(models);
			}


		};
		final PagingLoader<PagingLoadResult<ModelData>> loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy, reader);
		loader.addLoadListener(new LoadListener() {
			@Override
			public void loaderLoad(LoadEvent le) {
				BasePagingLoadResult basePagingLoadResult = le.getData();
				int actualSize = basePagingLoadResult.getData().size();

				int sizeFromRPC = loader.getTotalCount();
				GWTUtil.log("actualSize: " + actualSize);
				GWTUtil.log("sizeFromRPC: " + sizeFromRPC);
				super.loaderLoad(le);
			}
		});

		ListStore<BeanModel> store = new ListStore<BeanModel>(loader);
		this.setStore(store);
		this.setTemplate(getTemplateStr());
		this.setForceSelection(true);	

		

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
