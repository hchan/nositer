package com.nositer.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.generated.Iwantto;

public interface IWantToServiceAsync {

	void createIWantTo(Iwantto iwantto, AsyncCallback<Iwantto> callback);

}
