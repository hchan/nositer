package com.nositer.client.service;

import java.util.List;

import com.extjs.gxt.ui.client.data.RemoteSortTreeLoadConfig;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.shared.GWTException;

/**
 * Example <code>RemoteService</code>.
 */
@RemoteServiceRelativePath("ServiceResolver.gwtrpc")
public interface FileService extends RemoteService {

  /**
   * Returns the children of the given parent.
   * 
   * @param folder the parent folder
   * @return the children
   */
  public List<FileModel> getImageFolderChildren(FileModel folder);

  /**
   * Returns the children of the given parent.
   * 
   * @param loadConfig the load config
   * @return the children
   */
  public List<FileModel> getImageFolderChildren(RemoteSortTreeLoadConfig loadConfig);
  
  public void createFolder(String folder) throws GWTException;

}