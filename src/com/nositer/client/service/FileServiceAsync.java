package com.nositer.client.service;

import java.util.List;

import com.extjs.gxt.ui.client.data.RemoteSortTreeLoadConfig;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.widget.directorytree.FileModel;

public interface FileServiceAsync {

	void getImageFolderChildren(FileModel folder, AsyncCallback<List<FileModel>> callback);

	void getImageFolderChildren(FileModel folder, Group group, AsyncCallback<List<FileModel>> callback);

	void createFolder(String folder, AsyncCallback<Void> callback);
	
	void createFolder(String folder, Group group, AsyncCallback<Void> callback);

}
