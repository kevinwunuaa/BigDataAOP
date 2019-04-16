package com.bsoft.common.validator;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

/**
 * 取值区间校验
 * @author Wuyong
 *
 */
public class RangeValidator extends AbstractValidator {

	@Override
	public JSONObject validate(String value) {
		JSONObject json = getJsonResult();
		if(StringUtils.isEmpty(value)){
			return json;
		}

		Double d = Double.valueOf(value);
		
		JSONObject param = getValidatorParam();
		
		Double min = Double.valueOf(param.get("min").toString());
		Double max = Double.valueOf(param.get("max").toString());
		
		if(d < min || d > max){
			json.put("success", false);
			json.put("info", "值[" + d + "]超出允许范围[" + min + "," + max + "]。");
		}
		return json;
	}

}
