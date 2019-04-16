package com.bdaop.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdaop.entity.SysOrganization;
import com.bsoft.dao_support.HibernateDaoSupport;

@Service(value="baseService")
public class BaseService extends AbstractService{
	
	public SysOrganization loadOrgByCode(String orgCode){
		SysOrganization org = (SysOrganization) hDao.uniqueResult(
				"from SysOrganization t where t.dwbh = ?", orgCode);
		return org;
	}
	
	public void saveOrg(SysOrganization org, String action){
		if ("add".equals(action)) {
			org.setId(null);
			hDao.saveEntity(org);
		} else if ("update".equals(action)) {
			SysOrganization org1 = hDao.getEntity(SysOrganization.class,org.getId());
			BeanUtils.copyProperties(org, org1);
			hDao.updateEntity(org1);
		} else
			;
	}
}
