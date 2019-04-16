package com.bsoft.common.util;

import java.util.Map;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.util.StringUtils;
import com.bsoft.common.context.ApplicationContextWrapper;

/**
 * uuid，字符串处理工具类.
 * author:caoyuxiang 2017年12月25日 下午3:52:44
 */
public class StringTool extends StringUtils {
	
	/**
	 * 生成请求id.
	 * @return String
	 */
	public static String getRequestid() {
		String record = "[" + UUID.randomUUID().toString().replace("-", "")
				+ "]";
		return record;
	}
	
	/**
	 * 生成uuid,去掉其中的"-".
	 * @return String
	 */
	public static String uuid(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 生成符合Oracle密码规则的随机密码
	 * @return 密码
	 */
	public static String createOraclePwd(){
		ApplicationContext actx = ApplicationContextWrapper.getApplicationContext();
		JdbcTemplate jt = (JdbcTemplate) actx.getBean("jdbcTemplate");
		String sql = "select  '!@#' || lower(dbms_random.string('x',8)) as oraclepwd from dual";
		Map<String,Object> map = jt.queryForMap(sql);
		return map.get("ORACLEPWD").toString();
	}

	/**
	 * 判断 String的object 是否有值
	 * 
	 * @param object
	 * @return boolean
	 */
	public static boolean hasTextOb(Object object) {
		if (object == null) {
			return false;
		} else {
			return !StringUtils.isEmpty(object.toString());
		}
	}
	
	/**
	 * 字符串数据拼接.
	 * @param strArray
	 * @param separator
	 * @return String
	 */
	public static String stringJoin(String []strArray,String separator){
		if(StringUtils.isEmpty(separator)){
			separator = ",";
		}
		
		int len = strArray.length - 1;
		String result = "";
		
		for(int i = 0; i < len; i ++){
			result += strArray[i] + separator;
		}
		
		result += strArray[len];
		return result;
	}
}
