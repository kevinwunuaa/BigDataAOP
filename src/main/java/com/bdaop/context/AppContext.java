package com.bdaop.context;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.bdaop.vo.User;
import com.bsoft.common.util.PropertyUtil;

/**
 * 全局变量保存.
 * 
 * @author Wuyong
 * 
 */
public class AppContext implements ApplicationContextAware {
	/**
	 * 应用所属的域，在sys.properties中配置domain=域
	 */
	public static String DOMAIN = null;

	/**
	 * 统一权限管理用户库模式名,在sys.properties中配置uaamUserSchema=统一权限库名
	 */
	public static String UAAM_USER_SCHEMA = null;

	/**
	 * session用户常量
	 */
	public static String SESSION_USER = "com.bdaop.uaamuser";

	public static ApplicationContext applicationContext;

	static {
		DOMAIN = PropertyUtil.getPropertyValue("sys", "domain");
		if (StringUtils.isNotEmpty(DOMAIN)) {
			SESSION_USER = DOMAIN + "_" + SESSION_USER;
		}
		UAAM_USER_SCHEMA = PropertyUtil.getPropertyValue("sys",
				"uaamUserSchema");
		UAAM_USER_SCHEMA = StringUtils.isEmpty(UAAM_USER_SCHEMA) ? "ssdev"
				: UAAM_USER_SCHEMA;
	}

	private static Map<String, Object> contextMap = new HashMap<String, Object>();

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		applicationContext = ctx;
	}

	public void init() {
		// TODO BODY
	}

	/**
	 * 存储全局变量.
	 * 
	 * @param key
	 *            变量名
	 * @param value
	 *            变量值
	 */
	public static <T> void put(String key, T value) {
		contextMap.put(key, value);
	}

	/**
	 * 获取全局变量.
	 * 
	 * @param key
	 *            变量名
	 * @param clazz
	 *            变量类型
	 * @return 变量值
	 */
	public static <T> T get(String key, Class<T> clazz) {
		return (T) contextMap.get(key);
	}

	/**
	 * 清空全局变量.
	 */
	public static void clear() {
		contextMap.clear();
	}

	/**
	 * 用户是否为管理员.
	 * 
	 * @param user
	 *            用户实例
	 * @return true or false
	 */
	public static boolean isAdmin(User user) {
		return "admin".equals(user.getUsername());
	}
}
