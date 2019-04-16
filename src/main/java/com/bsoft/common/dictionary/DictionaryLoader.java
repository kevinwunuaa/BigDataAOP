package com.bsoft.common.dictionary;

import java.util.Map;

/**
 * 字典加载器.
 * @author Wuyong
 *
 */
public abstract class DictionaryLoader {
	/**
	 * 根据字典名加载字典.
	 * @param dicName 字典名
	 * @return 字典列表
	 */
	public abstract Map<String,String> getDictionary(String dicName);
}
