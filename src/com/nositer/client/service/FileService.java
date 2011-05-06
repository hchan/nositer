package com.nositer.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.client.widget.directorytree.FolderModel;
import com.nositer.shared.GWTException;


@RemoteServiceRelativePath("ServiceResolver.gwtrpc")
public interface FileService extends RemoteService {

	List<FileModel> getFolderChildren(FileModel folder) throws GWTException;

	void createFolder(String folder) throws GWTException;

	void createFolder(String folder, GroupPlusView groupPlusView) throws GWTException;

	List<FileModel> getFolderChildren(FileModel folder,
			GroupPlusView groupPlusView) throws GWTException;

	void renameFolder(String pathName, String oldRelativeFolder,
			String newRelativeFolder) throws GWTException;

	void renameFolder(String pathName, String oldRelativeFolder,
			String newRelativeFolder, GroupPlusView groupPlusView) throws GWTException;

	void deleteFolder(FolderModel folderModel) throws GWTException;

	void deleteFolder(FolderModel folderModel, GroupPlusView groupPlusView) throws GWTException;

}