package com.nositer.client.service;

import java.util.List;

import com.extjs.gxt.ui.client.data.RemoteSortTreeLoadConfig;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.shared.GWTException;

public interface FileServiceAsync {

	void getFolderChildren(FileModel folder, AsyncCallback<List<FileModel>> callback) throws GWTException;

	void getFolderChildren(FileModel folder, GroupPlusView groupPlusView, AsyncCallback<List<FileModel>> callback) throws GWTException;

	void createFolder(String folder, AsyncCallback<Void> callback) throws GWTException;
	
	void createFolder(String folder, GroupPlusView groupPlusView, AsyncCallback<Void> callback) throws GWTException;

	void renameFolder(String pathName, String oldRelativeFolder, String newRelativeFolder, AsyncCallback<Void> callback) throws GWTException;
	
	void renameFolder(String pathName, String oldRelativeFolder, String newRelativeFolder, GroupPlusView groupPlusView, AsyncCallback<Void> callback) throws GWTException;
}
