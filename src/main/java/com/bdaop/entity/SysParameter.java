package com.bdaop.entity;

/**
 * SysParameter entity. @author MyEclipse Persistence Tools
 */

public class SysParameter implements java.io.Serializable {

	// Fields

	private Integer id;
	private String llb;
	private Integer bzlb;
	private Integer xslb;
	private String dm;
	private String mc;
	private String jm;
	private String bz;
	private String kqymc;
	private String kqymcbt;
	private String tcpip;
	private String czy;
	private String czybt;
	private String rqtime;

	// Constructors

	/** default constructor */
	public SysParameter() {
	}

	/** full constructor */
	public SysParameter(String llb, Integer bzlb, Integer xslb, String dm,
			String mc, String jm, String bz, String kqymc, String kqymcbt,
			String tcpip, String czy, String czybt, String rqtime) {
		this.llb = llb;
		this.bzlb = bzlb;
		this.xslb = xslb;
		this.dm = dm;
		this.mc = mc;
		this.jm = jm;
		this.bz = bz;
		this.kqymc = kqymc;
		this.kqymcbt = kqymcbt;
		this.tcpip = tcpip;
		this.czy = czy;
		this.czybt = czybt;
		this.rqtime = rqtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLlb() {
		return this.llb;
	}

	public void setLlb(String llb) {
		this.llb = llb;
	}

	public Integer getBzlb() {
		return this.bzlb;
	}

	public void setBzlb(Integer bzlb) {
		this.bzlb = bzlb;
	}

	public Integer getXslb() {
		return this.xslb;
	}

	public void setXslb(Integer xslb) {
		this.xslb = xslb;
	}

	public String getDm() {
		return this.dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public String getMc() {
		return this.mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getJm() {
		return this.jm;
	}

	public void setJm(String jm) {
		this.jm = jm;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getKqymc() {
		return this.kqymc;
	}

	public void setKqymc(String kqymc) {
		this.kqymc = kqymc;
	}

	public String getKqymcbt() {
		return this.kqymcbt;
	}

	public void setKqymcbt(String kqymcbt) {
		this.kqymcbt = kqymcbt;
	}

	public String getTcpip() {
		return this.tcpip;
	}

	public void setTcpip(String tcpip) {
		this.tcpip = tcpip;
	}

	public String getCzy() {
		return this.czy;
	}

	public void setCzy(String czy) {
		this.czy = czy;
	}

	public String getCzybt() {
		return this.czybt;
	}

	public void setCzybt(String czybt) {
		this.czybt = czybt;
	}

	public String getRqtime() {
		return this.rqtime;
	}

	public void setRqtime(String rqtime) {
		this.rqtime = rqtime;
	}

}