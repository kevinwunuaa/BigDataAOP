package com.bsoft.common.validator;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import com.bsoft.common.context.ApplicationContextWrapper;
import com.bsoft.common.dictionary.DictionaryLoader;

/**
 * 字典验证.
 * @author Wuyong
 *
 */
public class DictionaryValidator extends AbstractValidator {
	
	/**
	 * 字典验证方法.
	 * <h3>说明:</h3>
	 * 字典存储有多种方式，比如数据库、文件等，具体加载字典的方式需要由使用者实现，
	 * 使用者需提供{@link DictionaryLoader#getDictionary(String)}的实现类，并注册到spring容器中，命名为dictionaryLoader即可
	 */
	@Override
	public JSONObject validate(String value) {
		String dicName = (String) getValidatorParam().get("dic");
		JSONObject json = getJsonResult();
		if(StringUtils.isEmpty(value)){
			return json;
		}
		
		ApplicationContext actx = ApplicationContextWrapper.getApplicationContext();
		
		DictionaryLoader dicLoader = (DictionaryLoader) actx.getBean("dictionaryLoader");
		
		Map dic = dicLoader.getDictionary(dicName);
		
		if(dic == null){
			json.put("success", false);
			json.put("info", String.format("配置的字典[%s]不存在！", dicName));
			return json;
		}
		
		if(!dic.containsKey(value)){
			json.put("success", false);
			json.put("info", String.format("在字典[%s]中未发现[%s]！", dicName,value));
		}
		return json;
	}

}
