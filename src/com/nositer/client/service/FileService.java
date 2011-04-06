package com.nositer.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.shared.GWTException;


@RemoteServiceRelativePath("ServiceResolver.gwtrpc")
public interface FileService extends RemoteService {


	public List<FileModel> getImageFolderChildren(FileModel folder);

	public void createFolder(String folder) throws GWTException;

	public List<FileModel> getImageFolderChildren(FileModel folder, Group group);

	public void createFolder(String folder, Group group);

}