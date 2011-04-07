package com.nositer.client.widget.directorytree;


import org.swfupload.client.File;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class FileModel extends BaseModelData implements IsSerializable {
	public enum Attribute {
		size, errorMessage, date, id, name, path
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
		set(Attribute.name.toString(), name);
	}

	public void setPath(String path) {
		set(Attribute.path.toString(), path);
	}

	public String getPath() {
		return get(Attribute.path.toString());
	}

	public String getName() {
		return get(Attribute.name.toString());
	}
	
	public boolean isDirectory() {
		return get(Attribute.size.toString()) == null;
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
