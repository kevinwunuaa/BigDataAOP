package com.bsoft.common.validator;

import net.sf.json.JSONObject;

/**
 * 校验管理类.
 * @author Wuyong
 *
 */
public class validatorManager {
	/**
	 * 统一校验入口方法.
	 * @param validatorType 校验器类型
	 * @param value 校验目标值
	 * @param json 校验参数
	 * @return JSONObject校验结果
	 */
	public static JSONObject validate(String validatorType,String value,JSONObject json){
		String startChar = validatorType.substring(0,1).toUpperCase();
		
		String className = "com.bsoft.common.validator." + startChar +  validatorType.substring(1) + "Validator";
		
		AbstractValidator validator = null;
		try {
			validator = (AbstractValidator) Class.forName(className).newInstance();
			validator.setValidatorParam(json);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return validator.validate(value);
	}
}
