package com.nositer.client.widget.directorytree;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class FolderModel extends FileModel implements IsSerializable {

	  protected FolderModel() {

	  }

	  public FolderModel(String name, String path) {
	    super(name, path);
	  }

}
