package com.bsoft.common.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * easyui tree结构.
 * 包含属性:id,text,state(取值：open,closed)，attributes(额外属性),checked,children.
 * @author Wuyong
 * 
 */
public class TreeNode {
	/** id */
	private String id;
	
	/** 显示值 */
	private String text;
	
	/** 展开/关闭状态 */
	private String state;
	
	/** 其他自定义的属性 */
	private Map attributes;
	
	/** 选中状态 */
	private Boolean checked;
	
	/** 子结点 */
	List<TreeNode> children = new ArrayList<TreeNode>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Map getAttributes() {
		return attributes;
	}

	public void setAttributes(Map attributes) {
		this.attributes = attributes;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

}
