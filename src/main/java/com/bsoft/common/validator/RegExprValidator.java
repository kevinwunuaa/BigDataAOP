package com.bsoft.common.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONObject;

/**
 * 正则表达式校验
 * 
 * @author Wuyong
 * 
 */
public class RegExprValidator extends AbstractValidator {
	public final static String PHONE_PATTERN = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
	public final static String EMAIL_PATTERN = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
	
	@Override
	public JSONObject validate(String value) {
		JSONObject json = getJsonResult();
		if(StringUtils.isEmpty(value)){
			return json;
		}
		String regExpr = (String) getValidatorParam().get("regExpr");
		Pattern pattern = Pattern.compile(regExpr);
		Matcher matcher = pattern.matcher(value);
		
		if(!matcher.matches()){
			json.put("success", false);
			json.put("info", String.format("值[%s]不能匹配[%s]！", new Object[]{value,regExpr}));
		}
		return json;
	}

}
