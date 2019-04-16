package com.bsoft.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * properties文件工具类.
 * @author Wuyong
 *
 */
public class PropertyUtil {

	/** 日志 */
	protected static Logger logger = Logger.getLogger(PropertyUtil.class);
	
	/**
	 * 获取指定Property的属性值
	 * @param propertyName 属性名
	 * @return Property属性值
	 */
	public static String getPropertyValue(String propertyFileName, String propertyName) {
		Properties properties = loadInitFile(propertyFileName);
		String propertyValue = properties.getProperty(propertyName);
		return propertyValue;
	}
	
	/**
	 * 判断指定Property的属性值是否为传入的指定的值
	 * @param propertyName 属性名
	 * @param propertyValue 属性值
	 * @return boolean
	 */
	public static Boolean compareProperty(String propertyFileName,String propertyName, String propertyValue) {
		Properties properties = loadInitFile(propertyFileName);
		String value = properties.getProperty(propertyName);
		return (null == value) ? false : value.equals(propertyValue);
	}
	
	/**
	 * 加截Property文件
	 * @return Properties文件
	 */
	public static Properties loadInitFile(String propertyFileName){
		
		String path = PropertyUtil.class.getClassLoader().getResource(propertyFileName + ".properties").getPath(); 
		Properties properties = new Properties();
		try {
			InputStream inputStream = new FileInputStream(path);  
			properties.load(inputStream);
		} catch (IOException e) {
			logger.error("加载" + propertyFileName + ".properties文档时出现异常");
			return null;
		}
		return properties;
	}
}

