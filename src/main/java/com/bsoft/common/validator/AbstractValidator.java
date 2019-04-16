package com.bsoft.common.validator;

import net.sf.json.JSONObject;

/**
 * 抽象校验器类，实现校验参数准备，校验逻辑由实现类具体实现.
 * @author Wuyong
 *
 */
public abstract class AbstractValidator {
	/**
	 * json格式参数
	 */
	private JSONObject validatorParam;
	
	/**
	 * 是否允许校验的字段为空,默认为true
	 */
	private boolean allowBlank = true;

	public AbstractValidator() {
	}

	public AbstractValidator(JSONObject validatorParam) {
		this.validatorParam = validatorParam;
	}

	public JSONObject getValidatorParam() {
		return validatorParam;
	}

	public void setValidatorParam(JSONObject validatorParam) {
		this.validatorParam = validatorParam;
		if (this.validatorParam != null) {
			Object blank = validatorParam.get("allowBlank");
			if(blank != null){
				allowBlank = Boolean.valueOf(blank.toString());
			}
		}
	}

	public boolean isAllowBlank() {
		return allowBlank;
	}

	public void setAllowBlank(boolean allowBlank) {
		this.allowBlank = allowBlank;
	}

	/**
	 * 校验方法入口,由实现类实现.
	 * @param value
	 * @return JSONObject 校验的结果信息
	 */
	public abstract JSONObject validate(String value);

	/**
	 * 生成默认的校验结果.
	 * @return JSONObject 校验结果
	 */
	public JSONObject getJsonResult() {
		JSONObject json = new JSONObject();
		if (allowBlank) {
			json.put("success", true);
			json.put("info", "校验通过!");
		}
		else{
			json.put("success", false);
			json.put("info", "不允许为空!");
		}
		return json;
	}
}
