package com.nositer.client.uploadimages;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGrid;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

@SuppressWarnings("rawtypes")
public class MyTreeGrid extends TreeGrid<ModelData>{

	public MyTreeGrid( TreeStore store, ColumnModel cm) {
		super(store, cm);
		
	}

	@Override
	protected AbstractImagePrototype calculateIconStyle(ModelData model) {
		AbstractImagePrototype retval = null;
		if (model instanceof FolderModel && isLeaf(model)) {
			retval = IconHelper.createPath("/public/image/emptyFolder.png");
		} else {
			retval = super.calculateIconStyle(model);
		}
		return retval;
	}
	
	
	/*
	@Override
	public com.extjs.gxt.ui.client.widget.treegrid.TreeGrid<ModelData>.TreeNode findNode(ModelData m) {
		return findNode(m);
	};
	*/
}
