package com.nositer.client.util;

import com.extjs.gxt.ui.client.widget.treegrid.TreeGrid.TreeNode;

@SuppressWarnings("rawtypes")
public class TreeNodeHelper {

	public static void refreh( TreeNode treeNode) {
		treeNode.setLeaf(false);
		treeNode.setExpanded(false);
		treeNode.setExpanded(true);
	}

}
