package com.bsoft.common.context;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.bsoft.common.quartz.QuartzJob;
import com.bsoft.common.quartz.QuartzJobList;
import com.bsoft.common.util.Dom4jImpl;
import com.bsoft.common.util.JaxbUtil;
import com.bsoft.common.util.PropertyUtil;
import com.bsoft.common.validator.Validator;
import com.bsoft.common.validator.ValidatorList;

/**
 * 环境参数设置
 * @author Wuyong
 *
 */
public class EnvironmentContext {
	private static final Logger log = Logger.getLogger(EnvironmentContext.class);
	public static String VALIDATOR_PATH = null;
	public static ValidatorList validatorList = null;

	public static String JOB_PATH = null;
	public static QuartzJobList jobList = null;
	
	static{
		VALIDATOR_PATH = PropertyUtil.getPropertyValue("sys", "validatorXmlPath");
		VALIDATOR_PATH = StringUtils.isEmpty(VALIDATOR_PATH) ? "/com/bsoft/common/validator/validators.xml" : VALIDATOR_PATH;
		
		JOB_PATH = PropertyUtil.getPropertyValue("sys", "jobXmlPath");
		JOB_PATH = StringUtils.isEmpty(JOB_PATH) ? "/com/bsoft/common/quartz/jobs.xml" : JOB_PATH;
	}

	public EnvironmentContext() {
	}

	public static void reset() {
		validatorList = null;
		jobList = null;

		initValidators();
		initJobs();
	}

	public void init() {
		initValidators();
		initJobs();
	}

	private static void initValidators() {
		log.info("初始化验证器...");
		String path = EnvironmentContext.class.getResource(VALIDATOR_PATH)
				.getPath();
		File f = new File(path);
		Dom4jImpl dom = new Dom4jImpl();
		Document doc = dom.createXmlDoc(f);
		validatorList = JaxbUtil.converyToJavaBean(doc.asXML(),
				ValidatorList.class);
		if (validatorList.getValidators() == null) {
			validatorList.setValidators(new ArrayList<Validator>());
		}
	}

	private static void initJobs() {
		log.info("初始化jobs...");
		String path = EnvironmentContext.class.getResource(JOB_PATH).getPath();
		File f = new File(path);
		Dom4jImpl dom = new Dom4jImpl();
		Document doc = dom.createXmlDoc(f);
		jobList = JaxbUtil.converyToJavaBean(doc.asXML(), QuartzJobList.class);
		if (jobList.getJobs() == null) {
			jobList.setJobs(new ArrayList<QuartzJob>());
		}
	}

}
