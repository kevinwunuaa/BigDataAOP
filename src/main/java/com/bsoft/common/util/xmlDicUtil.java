package com.bsoft.common.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * xml格式字典处理类.
 * @author Wuyong
 *
 */
public class xmlDicUtil {
	private static Map<String, Document> dicMap = new HashMap<String, Document>();
	
	/**
	 * 加载xml字典.
	 * 用法：在类路径sys.properties中定义存放xml字典的目录,如：xmlDicPath=/com/common/xmldic/
	 * @param dicName
	 * @return Document
	 */
	private static Document loadXmlDic(String dicName) {
		SAXReader reader = new SAXReader();
		
		String xmlDicPath = PropertyUtil.getPropertyValue("sys", "xmlDicPath");

		String fileName = dicName + ".xml";

		Document document = null;
		try {
			InputStream in = xmlDicUtil.class.getResourceAsStream(xmlDicPath + fileName);
			document = reader.read(in);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return document;
	}
	
	/**
	 * 取字典值.
	 * @param dicName
	 * @param dicCode
	 * @return String
	 */
	public static String getXmlDicValue(String dicName, String dicCode) 
	{
		Document doc = dicMap.get(dicName);
		if (doc == null) 
		{
			doc = loadXmlDic(dicName);
			dicMap.put(dicName, doc);
		}

		String xpath = "/dic/item[@key='" + dicCode + "']";
		Element e = (Element) doc.selectSingleNode(xpath);
		if (e != null) 
		{
			String value = e.attributeValue("text");
			return value;
		}
		
		return "";
	}
}
