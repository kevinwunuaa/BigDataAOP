package com.bsoft.common.validator;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 枚举值验证.
 * @author Wuyong
 *
 */
public class EnumDicValidator extends AbstractValidator {
	/**
	 * 验证方法.
	 * <br/>
	 * 枚举字典格式:
	 * <pre>
	{
		items : [{
					code : "100",
					value : "普通门诊"
				}, {
					code : "101",
					value : "专科门诊"
				}, {
					code : "102",
					value : "专家门诊"
				}, {
					code : "103",
					value : "特需门诊"
				}, {
					code : "104",
					value : "专病门诊"
				}, {
					code : "999",
					value : "其他"
				}]
	}
	</pre>
	*/

	@Override
	public JSONObject validate(String value) {
		JSONArray ja = getValidatorParam().getJSONArray("items");
		JSONObject json = getJsonResult();
		if(StringUtils.isEmpty(value)){
			return json;
		}
		
		int len = ja.size();
		boolean match = false;
		for (int i = 0; i < len; i++) {
			JSONObject item = ja.getJSONObject(i);
			String c = (String) item.get("code");
			if (c.equals(value)) {
				match = true;
				break;
			}
		}
		
		if(!match){
			json.put("success", false);
			json.put("info", String.format("[%s]不在枚举值范围！", value));
		}
		return json;
	}

}
