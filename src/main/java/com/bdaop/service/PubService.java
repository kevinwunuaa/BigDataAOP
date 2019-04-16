package com.bdaop.service;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdaop.entity.PubDocument;
import com.bsoft.common.pagination.PageObject;
import com.bsoft.dao_support.HibernateDaoSupport;

@Service(value = "pubService")
public class PubService {
	@Autowired
	HibernateDaoSupport his;
	
	public PageObject docList(String keyText,Integer pageNumber,Integer pageSize){
		String countSql = "select count(1) from hw01wd t where 1 = 1";
		String sql = "select * from hw01wd t where 1 = 1";

		if (StringUtils.isNotEmpty(keyText)) {
			sql += " t.wdmc like '%" + keyText + "%'";
			countSql += " t.wdmc like '%" + keyText + "%'";
		}
		
		sql += " order by t.id desc";
		BigInteger total = (BigInteger) his.uniqueResultBySql(countSql);

		List<PubDocument> logList = his.findPageBySql(PubDocument.class, sql, pageNumber, pageSize);

		PageObject page = new PageObject();
		page.setTotal(total.intValue());
		page.setRows(logList);
		
		return page;
	}
}
