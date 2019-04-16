package com.bdaop.entity;

/**
 * 机构信息表
 * @author Wuyong
 *
 */
public class UaamOrg {
	private Long id;
	private String parentid;
	private String orgcode;
	private String orgname;
	private String admindivision;
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getAdmindivision() {
		return admindivision;
	}

	public void setAdmindivision(String admindivision) {
		this.admindivision = admindivision;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
