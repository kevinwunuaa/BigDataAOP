package com.bdaop.entity;

/**
 * 资源表
 * @author Wuyong
 *
 */
public class UaamResource {
	private String restype, resname, resurl, resdomain, remark, status;
	private Integer resid, parentid, reslevel, resorder;

	public Integer getResid() {
		return resid;
	}

	public void setResid(Integer resid) {
		this.resid = resid;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
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

	public Integer getResorder() {
		return resorder;
	}

	public void setResorder(Integer resorder) {
		this.resorder = resorder;
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

}
