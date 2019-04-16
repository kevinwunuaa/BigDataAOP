package com.bsoft.common.validator;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONObject;

/**
 * 字符串长度校验.
 * @author Wuyong
 *
 */
public class LengthValidator extends AbstractValidator {
	@Override
	public JSONObject validate(String value) {
		JSONObject json = getJsonResult();
		if(StringUtils.isEmpty(value)){
			return json;
		}
		int len = value.length();
		
		JSONObject param = getValidatorParam();
		
		Integer low = (Integer) param.get("low");
		Integer high = (Integer) param.get("high");
		
		if(len < low || len > high){
			json.put("success", false);
			json.put("info", String.format("实际长度%d,允许长度[%d,%d]。", len,low,high));
		}
		return json;
	}

}
