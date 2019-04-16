package com.bdaop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsoft.dao_support.HibernateDaoSupport;
import com.bsoft.dao_support.JdbcDaoSupport;

@Service
public abstract class AbstractService {
	@Autowired
	protected HibernateDaoSupport hDao;
	
	@Autowired
	protected JdbcDaoSupport jDao;
}
