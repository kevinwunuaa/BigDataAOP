package com.bdaop.vo;

import java.util.List;

import com.bdaop.entity.UaamRole;

/**
 * 用户VO
 * @author Wuyong
 *
 */
public class User {
	private String userid, username, personname, gender, password, cardnum,
			organizecode, status, remark;
	private List<UaamRole> roles;

	private ResourceTree resourceTree;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCardnum() {
		return cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public String getOrganizecode() {
		return organizecode;
	}

	public void setOrganizecode(String organizecode) {
		this.organizecode = organizecode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<UaamRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UaamRole> roles) {
		this.roles = roles;
	}

	public ResourceTree getResourceTree() {
		return resourceTree;
	}

	public void setResourceTree(ResourceTree resourceTree) {
		this.resourceTree = resourceTree;
	}

}
