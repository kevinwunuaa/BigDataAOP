package com.bsoft.common.validator;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONObject;

/**
 * 不允许为空校验
 * @author Wuyong
 *
 */
public class NotNullValidator extends AbstractValidator {
	@Override
	public JSONObject validate(String value) {
		JSONObject json = getJsonResult();
		if(StringUtils.isEmpty(value)){
			json.put("success", false);
			json.put("info", "字段不允许为空。");
		}
		return json;
	}

}
