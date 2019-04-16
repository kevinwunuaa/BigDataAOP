package com.bsoft.common.validator;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bsoft.common.context.ApplicationContextWrapper;

/**
 * <h1>关联性校验</h1>. 
 * 校验类别：
 * <ol>
 * <li>1、业务数据与病人信息</li>
 * <li>2、住院业务就诊流水号验证</li>
 * <li>3、门诊业务挂号流水号验证</li>
 * <li>4、明细数据与主表关联性</li>
 * </ol>
 * 
 * @author Wuyong
 * 
 */
public class RelevanceValidator extends AbstractValidator {

	/**
	 * 关联性验证.
	 * <h3>说明：</h3>
	 * <p>
	 * 关联性验证涉及数据库访问,需向spring容器注入命名为jdbcTemplate的资源
	 * </p>
	 */
	@Override
	public JSONObject validate(String value) {
		JSONObject json = getJsonResult();
		if (StringUtils.isEmpty(value)) {
			return json;
		}

		JSONObject param = getValidatorParam();

		String table = param.getString("targetTable");
		String field = param.getString("targetField");

		String sql = "select count(1) from " + table + " where " + field
				+ " = ?";
		JdbcTemplate jt = (JdbcTemplate) ApplicationContextWrapper.getApplicationContext().getBean("jdbcTemplate");
		int rows = jt.queryForInt(sql, value);

		if (rows <= 0) {
			json.put("success", false);
			json.put("info",
					String.format("在字段[%s.%s]中不存在值[%s]。", table, field, value));
		}
		return json;
	}

}
