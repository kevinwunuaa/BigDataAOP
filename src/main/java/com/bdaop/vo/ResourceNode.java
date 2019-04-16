package com.bdaop.vo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 资源节点
 * @author Wuyong
 *
 */
public class ResourceNode implements Comparator<ResourceNode>{
	private String resid, parentid, restype, resname, resurl, resdomain,
			remark, status;
	private Integer reslevel,resorder;
	
	private List<ResourceNode> children = new ArrayList<ResourceNode>();

	public String getResid() {
		return resid;
	}

	public void setResid(String resid) {
		this.resid = resid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getRestype() {
		return restype;
	}

	public void setRestype(String restype) {
		this.restype = restype;
	}

	public String getResname() {
		return resname;
	}

	public void setResname(String resname) {
		this.resname = resname;
	}

	public String getResurl() {
		return resurl;
	}

	public void setResurl(String resurl) {
		this.resurl = resurl;
	}

	public String getResdomain() {
		return resdomain;
	}

	public void setResdomain(String resdomain) {
		this.resdomain = resdomain;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getReslevel() {
		return reslevel;
	}

	public void setReslevel(Integer reslevel) {
		this.reslevel = reslevel;
	}
	
	public Integer getResorder() {
		return resorder;
	}

	public void setResorder(Integer resorder) {
		this.resorder = resorder;
	}

	public List<ResourceNode> getChildren() {
		return children;
	}

	public void setChildren(List<ResourceNode> children) {
		this.children = children;
	}

	@Override
	public int compare(ResourceNode node1, ResourceNode node2) {
		return Integer.compare(node1.getResorder(), node2.getResorder());
	}
}
