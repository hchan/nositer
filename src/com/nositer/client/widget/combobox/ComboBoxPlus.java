package com.nositer.client.widget.combobox;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.BeanModelTag;
import com.extjs.gxt.ui.client.data.HttpProxy;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelType;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.nositer.client.dto.DTO;
import com.nositer.client.dto.generated.Postalcode;
@SuppressWarnings("rawtypes") 
public class ComboBoxPlus<D extends BeanModelTag> extends ComboBox {
	public static final int FICTITIOUSCOUNT = 1000000;
	public static final int DEFAULTLIMIT = 20;
	public ComboBoxPlus() {
		init();
	}
	
	private void init() {
		setMinChars(1);
		this.setPageSize(DEFAULTLIMIT);
		initPagingToolBar();
		this.setForceSelection(true);	
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
	
	public PagingLoader<PagingLoadResult<ModelData>> getLoader(RpcProxy<PagingLoadResult<Postalcode>> proxy) {
		PagingLoader<PagingLoadResult<ModelData>> retval = null;
		BeanModelReader reader = new BeanModelReader() {
			@Override
			protected ListLoadResult<ModelData> newLoadResult(Object loadConfig, List<ModelData> models) {
				//return super.newLoadResult(loadConfig, models);
				return new BasePagingLoadResult<ModelData>(models);
			}
		};

		retval = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy, reader);

		retval.addLoadListener(new LoadListener() {
			@Override
			public void loaderLoad(LoadEvent le) {
				BasePagingLoadResult basePagingLoadResult = le.getData();

				int actualSize = basePagingLoadResult.getData().size();

				//int sizeFromRPC = retval.getTotalCount();
				//GWTUtil.log("actualSize: " + actualSize);
				//GWTUtil.log("sizeFromRPC: " + sizeFromRPC); // probably always 0
				if (actualSize == DEFAULTLIMIT) {
					basePagingLoadResult.setTotalLength(FICTITIOUSCOUNT);
				}
				super.loaderLoad(le);
			}
		});
		return retval;
	}
	
	public D getBean() {
		D retval = null;
		BeanModel beanModel = (BeanModel) getValue();
		if (beanModel != null) {
			retval = beanModel.getBean();
		}
		return retval;
		
	}
}
