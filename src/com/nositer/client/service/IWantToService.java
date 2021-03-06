package com.nositer.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.nositer.client.dto.generated.Iwantto;
import com.nositer.shared.GWTException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("ServiceResolver.gwtrpc")
public interface IWantToService extends RemoteService {
	Iwantto createIWantTo(Iwantto iwantto) throws GWTException;
	ArrayList<Iwantto> getMyIWantTos() throws GWTException;
}
