package com.bsoft.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存储Map格式的键值对的List容器.
 * @author Wuyong
 *
 */
public class MapListWrapper {
	private List<Map> list = new ArrayList<Map>();
	
	private Map newMap = null;

	/**
	 * 往map中存放key/value对.
	 * @param key
	 * @param value
	 */
	public void push(Object key,Object value){
		if(newMap == null){
			newMap = new HashMap();
		}
		
		if(value == null || "null".equals(value) || "-".equals(value)){
			value = "";
		}
		
		newMap.put(key, value);
	}
	
	/**
	 * map加入容器.
	 */
	public void addMap(){
		list.add(newMap);
		newMap = null;
	}
	
	/**
	 * 获取容器中的键值对.
	 * @return List
	 */
	public List<Map> getMapList(){
		return list;
	}
	
	/**
	 * 清空容器.
	 */
	public void clear(){
		list.clear();
	}
}
