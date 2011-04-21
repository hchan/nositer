package com.nositer.client.widget.directorytree;

import java.util.List;

import com.extjs.gxt.ui.client.data.RpcProxy;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.ServiceBroker;

public class UserFileDirectoryTreeGridContainer extends AbstractFileDirectoryTreeGridContainer {

	public UserFileDirectoryTreeGridContainer(boolean useSelectedFilePanel) {
		super(useSelectedFilePanel);
	}

	@Override
	public RpcProxy<List<FileModel>> createProxy() {
		return createUserProxy();
	}
	
	public static  RpcProxy<List<FileModel>> createUserProxy() {
		return new RpcProxy<List<FileModel>>() {  
			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<FileModel>> callback) {
				ServiceBroker.fileService.getFolderChildren((FileModel) loadConfig, callback);  
			}  			
		};  
	}

}
