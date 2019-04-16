package com.bdaop.entity;
// default package



/**
 * SysOrganizationId entity. @author MyEclipse Persistence Tools
 */

public class SysOrganizationId  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String dwbh;


    // Constructors

    /** default constructor */
    public SysOrganizationId() {
    }

    
    /** full constructor */
    public SysOrganizationId(Integer id, String dwbh) {
        this.id = id;
        this.dwbh = dwbh;
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
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysOrganizationId) ) return false;
		 SysOrganizationId castOther = ( SysOrganizationId ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getDwbh()==castOther.getDwbh()) || ( this.getDwbh()!=null && castOther.getDwbh()!=null && this.getDwbh().equals(castOther.getDwbh()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getDwbh() == null ? 0 : this.getDwbh().hashCode() );
         return result;
   }   





}