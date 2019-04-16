package com.bdaop.vo;

import java.util.List;

import net.sf.json.JSONObject;

/**
 * 将菜单资源整成完整的树形结构，支持层次为5层,根为0层
 * 
 * @author Wuyong
 * 
 */
public class ResourceTree {
	ResourceNode root;

	public ResourceNode getRoot() {
		return root;
	}

	public void setRoot(ResourceNode root) {
		this.root = root;
	}

	/**
	 * 资源节点列表转为树型结构.
	 * 
	 * @param nodes
	 *            节点列表
	 * @return 资源树
	 */
	public ResourceTree getResourceTree1(List<ResourceNode> nodes) {
		final int resFloors = 5;

		for (int i = resFloors - 2; i >= 0; i--) {
			for (ResourceNode n1 : nodes) {
				if (n1.getReslevel() == i) {
					for (ResourceNode n2 : nodes) {
						if (n2.getReslevel() == (i + 1)
								&& n2.getParentid().equals(n1.getResid())) {
							n1.getChildren().add(n2);
						}
					}

					if (i == 1) {
						this.setRoot(n1);
						break;
					}
				}
			}

		}

		return this;
	}

	public ResourceTree getResourceTree(List<ResourceNode> nodes) {

		for (ResourceNode n1 : nodes) {
			if(n1.getReslevel() == 1){
				this.setRoot(n1);
				continue;
			}
			
			for(ResourceNode n2 : nodes){
				if(n1.getParentid().equals(n2.getResid())){
					n2.getChildren().add(n1);
				}
			}
		}

		return this;
	}
	
	public String renderNode(ResourceNode node){
		String result = null;
		if(node.getChildren() == null || node.getChildren().size() <= 0){
			result = "<li id=\"menuitem_" + node.getResid() + "\"><a href=\"javascript:void(0)\" style=\"text-align:left\" onclick=\"addTab('" + node.getResid() 
					+ "','" + node.getResname() + "','" + node.getResurl() + "')\"><i class=\"fa fa-circle-o\"></i>" + node.getResname() + "</a></li>";
		}else{
			result = "<li class=\"treeview\"><a href=\"#\"> <i class=\"fa fa-folder\"></i> <span>" + node.getResname() + 
					"</span> <span class=\"pull-right-container\"> <i class=\"fa fa-angle-left pull-right\"></i></span></a>";
			
			result += "<ul class=\"treeview-menu\">";
			
			for(ResourceNode n : node.getChildren()){
				result += renderNode(n);
			}
			
			result +="</ul></li>";
		}
		return result;
	}
	
	public String toJson(){
		JSONObject json = JSONObject.fromObject(this);
		return json.toString();
	}
}
