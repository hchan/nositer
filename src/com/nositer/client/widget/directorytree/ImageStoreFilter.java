package com.nositer.client.widget.directorytree;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreFilter;
import com.nositer.shared.FileNameUtil;
import com.nositer.shared.Global;

@SuppressWarnings("rawtypes")
public class ImageStoreFilter implements StoreFilter {

	@Override
	public boolean select(Store store, ModelData parent, ModelData item,
			String property) {
		boolean retval = false;
		FileModel fileModel = (FileModel) item;
		if (fileModel.getPath().startsWith(Global.PRIVATEFOLDER)) {
			retval = false;
		} else {
			if (fileModel.isDirectory()) {
				retval = true;
			} else {
				if (FileNameUtil.isImageFile(fileModel.getName())) {
					retval = true;
				}
			}
		}
		return retval;
	}
}
