package com.bsoft.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * xml工具类
 * 
 * @author sleep 2016-09-13
 */
public class XmlTool {

	/**
	 * String 转 org.dom4j.Document.
	 * 
	 * @param xml
	 * @return {@link Document}
	 * @throws DocumentException
	 */
	public static Document strToDocument(String xml) throws DocumentException {
		return DocumentHelper.parseText(xml);
	}

	/**
	 * org.dom4j.Document 转 com.alibaba.fastjson.JSONObject.
	 * 
	 * @param xml
	 * @return {@link JSONObject}
	 * @throws DocumentException
	 */
	public static JSONObject documentToJSONObject(String xml)
			throws DocumentException {
		return elementToJSONObject(strToDocument(xml).getRootElement());
	}

	/**
	 * org.dom4j.Element to com.alibaba.fastjson.JSONObject.
	 * 
	 * @param node
	 * @return {@link JSONObject}
	 */
	public static JSONObject elementToJSONObject(Element node) {
		JSONObject result = new JSONObject();
		
		List<Attribute> listAttr = node.attributes();
		for (Attribute attr : listAttr) {
			result.put(attr.getName(), attr.getValue());
		}

		List<Element> listElement = node.elements();
		if (!listElement.isEmpty()) {
			for (Element e : listElement) {
				if (e.attributes().isEmpty() && e.elements().isEmpty()) 
					result.put(e.getName(), e.getTextTrim());
				else {
					if (!result.containsKey(e.getName())) 
						result.put(e.getName(), new JSONArray());
					((JSONArray) result.get(e.getName()))
							.add(elementToJSONObject(e));
				}
			}
		}
		return result;
	}

	/**
	 * xml转Map.
	 * @param xml
	 * @return {@link Map}
	 * @throws DocumentException
	 */
	public static Map documentToMap(String xml) throws DocumentException {
		return elementToMap(strToDocument(xml).getRootElement());
	}

	/**
	 * 元素节点转Map.
	 * @param node
	 * @return {@link Map}
	 */
	public static Map elementToMap(Element node) {
		Map obj = new HashMap();
		List<Attribute> listAttr = node.attributes();
		for (Attribute attr : listAttr) {
			obj.put(attr.getName(), attr.getValue());
		}

	
		List<Element> listElement = node.elements();
		if (!listElement.isEmpty()) {
			for (Element e : listElement) {

				List<Element> eList = node.elements(e.getName());
				if (eList != null && eList.size() > 1) {
					if (obj.get(e.getName()) == null) {
						obj.put(e.getName(), elementTolist(eList));
					}

				} else if (!e.getTextTrim().equals("")) {
					obj.put(e.getName(), e.getTextTrim());
				} else {
					Map mape = elementToMap(e);
					if (mape == null || mape.size() == 0) {
						obj.put(e.getName(), "");
					} else {
						obj.put(e.getName(), mape);
					}

				}

			}
		} else if (!node.getTextTrim().equals("")) {
			obj.put(node.getName(), node.getTextTrim());
		}
		return obj;
	}

	/**
	 * 元素列表转List.
	 * @param eList
	 * @return List
	 */
	public static List elementTolist(List<Element> eList) {
		List list = new ArrayList();

		for (Element e : eList) {
			list.add(elementToMap(e));

		}
		return list;
	}

	public static void main(String[] args) {
		String xml = "<MapSet>" + "<MapGroup id='Sheboygan'>" + "<Map>"
				+ "<Type>MapGuideddddddd</Type>"
				+ "<SingleTile>true</SingleTile>" + "<Extension>"
				+ "<ResourceId>ddd</ResourceId>" + "</Extension>" + "</Map>"
				+ "<Map>" + "<Type>ccc</Type>" + "<SingleTile>ggg</SingleTile>"
				+ "<Extension>" + "<ResourceId>aaa</ResourceId>"
				+ "</Extension>" + "</Map>" + "<Extension />" + "</MapGroup>"
				+ "<ddd>" + "33333333" + "</ddd>" + "<ddd>" + "444" + "</ddd>"
				+ "</MapSet>";

		System.out.println(xml);

		try {
			System.out.println(documentToMap(xml));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}