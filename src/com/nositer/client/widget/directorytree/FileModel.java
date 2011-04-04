package com.nositer.client.widget.directorytree;


import org.swfupload.client.File;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class FileModel extends BaseModelData implements IsSerializable {
	public enum Attribute {
		size, errorMessage, date, id
	}
	
	
	protected FileModel() {

	}

	public FileModel(String name, String path) {
		setName(name);
		setPath(path);
	}

	public FileModel(File file) {
		setName(file.getName());
		set(FileModel.Attribute.id.toString(), file.getId().toString());
		set(FileModel.Attribute.size.toString(), file.getSize());
		//set(FileModel.Attribute.errorMessage.toString(), file.getMessage());
	}

	public void setName(String name) {
		set("name", name);
	}

	public void setPath(String path) {
		set("path", path);
	}

	public String getPath() {
		return get("path");
	}

	public String getName() {
		return get("name");
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof FileModel) {
			FileModel mobj = (FileModel) obj;
			String curPath = getPath();
			if (curPath == null) {
				curPath = "";
			}
			String objPath = mobj.getPath();
			if (objPath == null) {
				objPath = "";
			}
			
			return getName().equals(mobj.getName()) 
			&& curPath.equals(objPath);
		}
		return super.equals(obj);
	}

}
