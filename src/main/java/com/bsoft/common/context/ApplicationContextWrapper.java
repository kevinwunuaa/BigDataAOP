package com.bsoft.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * ApplicationContext包装器，方便未由spring容器管理的类使用容器服务.<br/>
 * 用法：将本目录下的spring-context.xml导入spring.xml即可使用该类获取spring窗口中的对象,如：
 * <import resource="classpath:/com/bsoft/common/context/spring-context.xml" />
 * @author Wuyong
 *
 */
public class ApplicationContextWrapper implements ApplicationContextAware {
	public static ApplicationContext applicationContext;

	public ApplicationContextWrapper() {
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		applicationContext = ctx;
	}

	public void init() {
		//TODO BODY
	}
}
