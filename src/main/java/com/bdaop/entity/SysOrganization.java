package com.bdaop.entity;
// default package



/**
 * SysOrganization entity. @author MyEclipse Persistence Tools
 */

public class SysOrganization  implements java.io.Serializable {


    // Fields    

	 private Integer id;
     private String dwbh;
     private String dwmc;
     private String dwyb;
     private String dwdz;
     private String dwbgsdh;
     private String dwjjzxdh;
     private String dwfwzxdh;
     private String dwjb;
     private String dwjbsj;
     private String dwpsbz;
     private String dwfr;
     private String dwfrlxdh;
     private String dwfrsfzh;
     private String dwurl;
     private String dwlogo;
     private String dwemail;
     private String tcpip;
     private String czy;
     private String czybt;
     private String rqtime;


    // Constructors

    /** default constructor */
    public SysOrganization() {
    }

	/** minimal constructor */
    public SysOrganization(Integer id) {
        this.id = id;
    }
    
    /** full constructor */
    public SysOrganization(Integer id,String dwbh, String dwmc, String dwyb, String dwdz, String dwbgsdh, String dwjjzxdh, String dwfwzxdh, String dwjb, String dwjbsj, String dwpsbz, String dwfr, String dwfrlxdh, String dwfrsfzh, String dwurl, String dwlogo, String dwemail, String tcpip, String czy, String czybt, String rqtime) {
        this.id = id;
        this.dwbh = dwbh;
        this.dwmc = dwmc;
        this.dwyb = dwyb;
        this.dwdz = dwdz;
        this.dwbgsdh = dwbgsdh;
        this.dwjjzxdh = dwjjzxdh;
        this.dwfwzxdh = dwfwzxdh;
        this.dwjb = dwjb;
        this.dwjbsj = dwjbsj;
        this.dwpsbz = dwpsbz;
        this.dwfr = dwfr;
        this.dwfrlxdh = dwfrlxdh;
        this.dwfrsfzh = dwfrsfzh;
        this.dwurl = dwurl;
        this.dwlogo = dwlogo;
        this.dwemail = dwemail;
        this.tcpip = tcpip;
        this.czy = czy;
        this.czybt = czybt;
        this.rqtime = rqtime;
    }

   
    // Property accessors

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDwbh() {
		return dwbh;
	}

	public void setDwbh(String dwbh) {
		this.dwbh = dwbh;
	}

    public String getDwmc() {
        return this.dwmc;
    }

	public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getDwyb() {
        return this.dwyb;
    }
    
    public void setDwyb(String dwyb) {
        this.dwyb = dwyb;
    }

    public String getDwdz() {
        return this.dwdz;
    }
    
    public void setDwdz(String dwdz) {
        this.dwdz = dwdz;
    }

    public String getDwbgsdh() {
        return this.dwbgsdh;
    }
    
    public void setDwbgsdh(String dwbgsdh) {
        this.dwbgsdh = dwbgsdh;
    }

    public String getDwjjzxdh() {
        return this.dwjjzxdh;
    }
    
    public void setDwjjzxdh(String dwjjzxdh) {
        this.dwjjzxdh = dwjjzxdh;
    }

    public String getDwfwzxdh() {
        return this.dwfwzxdh;
    }
    
    public void setDwfwzxdh(String dwfwzxdh) {
        this.dwfwzxdh = dwfwzxdh;
    }

    public String getDwjb() {
        return this.dwjb;
    }
    
    public void setDwjb(String dwjb) {
        this.dwjb = dwjb;
    }

    public String getDwjbsj() {
        return this.dwjbsj;
    }
    
    public void setDwjbsj(String dwjbsj) {
        this.dwjbsj = dwjbsj;
    }

    public String getDwpsbz() {
        return this.dwpsbz;
    }
    
    public void setDwpsbz(String dwpsbz) {
        this.dwpsbz = dwpsbz;
    }

    public String getDwfr() {
        return this.dwfr;
    }
    
    public void setDwfr(String dwfr) {
        this.dwfr = dwfr;
    }

    public String getDwfrlxdh() {
        return this.dwfrlxdh;
    }
    
    public void setDwfrlxdh(String dwfrlxdh) {
        this.dwfrlxdh = dwfrlxdh;
    }

    public String getDwfrsfzh() {
        return this.dwfrsfzh;
    }
    
    public void setDwfrsfzh(String dwfrsfzh) {
        this.dwfrsfzh = dwfrsfzh;
    }

    public String getDwurl() {
        return this.dwurl;
    }
    
    public void setDwurl(String dwurl) {
        this.dwurl = dwurl;
    }

    public String getDwlogo() {
        return this.dwlogo;
    }
    
    public void setDwlogo(String dwlogo) {
        this.dwlogo = dwlogo;
    }

    public String getDwemail() {
        return this.dwemail;
    }
    
    public void setDwemail(String dwemail) {
        this.dwemail = dwemail;
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