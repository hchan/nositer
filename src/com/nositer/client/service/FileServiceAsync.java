package com.nositer.client.service;

import java.util.List;

import com.extjs.gxt.ui.client.data.RemoteSortTreeLoadConfig;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.widget.directorytree.FileModel;

public interface FileServiceAsync {

	void getImageFolderChildren(FileModel folder, AsyncCallback<List<FileModel>> callback);

	void getImageFolderChildren(FileModel folder, GroupPlusView groupPlusView, AsyncCallback<List<FileModel>> callback);

	void createFolder(String folder, AsyncCallback<Void> callback);
	
	void createFolder(String folder, GroupPlusView groupPlusView, AsyncCallback<Void> callback);

}
