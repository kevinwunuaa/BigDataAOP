package com.bdaop.entity;

/**
 * SysEmployee entity. @author MyEclipse Persistence Tools
 */

public class SysEmployee implements java.io.Serializable {

	// Fields

	private Integer id;
	private String dwbh;
	private String dm;
	private String jm;
	private String yhm;
	private String mc;
	private String xmm;
	private String xb;
	private String csrq;
	private String sfzh;
	private String lxfs;
	private String bmmc;
	private String zzmc;
	private String zt;
	private Integer mmcwcs;

	// Constructors

	/** default constructor */
	public SysEmployee() {
	}

	/** full constructor */
	public SysEmployee(String dwbh, String dm, String jm, String yhm,
			String mc, String xmm, String xb, String csrq, String sfzh,
			String lxfs, String bmmc, String zzmc, String zt, Integer mmcwcs) {
		this.dwbh = dwbh;
		this.dm = dm;
		this.jm = jm;
		this.yhm = yhm;
		this.mc = mc;
		this.xmm = xmm;
		this.xb = xb;
		this.csrq = csrq;
		this.sfzh = sfzh;
		this.lxfs = lxfs;
		this.bmmc = bmmc;
		this.zzmc = zzmc;
		this.zt = zt;
		this.mmcwcs = mmcwcs;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDwbh() {
		return this.dwbh;
	}

	public void setDwbh(String dwbh) {
		this.dwbh = dwbh;
	}

	public String getDm() {
		return this.dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public String getJm() {
		return this.jm;
	}

	public void setJm(String jm) {
		this.jm = jm;
	}

	public String getYhm() {
		return this.yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public String getMc() {
		return this.mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getXmm() {
		return this.xmm;
	}

	public void setXmm(String xmm) {
		this.xmm = xmm;
	}

	public String getXb() {
		return this.xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getCsrq() {
		return this.csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getSfzh() {
		return this.sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getLxfs() {
		return this.lxfs;
	}

	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}

	public String getBmmc() {
		return this.bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	public String getZzmc() {
		return this.zzmc;
	}

	public void setZzmc(String zzmc) {
		this.zzmc = zzmc;
	}

	public String getZt() {
		return this.zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public Integer getMmcwcs() {
		return this.mmcwcs;
	}

	public void setMmcwcs(Integer mmcwcs) {
		this.mmcwcs = mmcwcs;
	}

}