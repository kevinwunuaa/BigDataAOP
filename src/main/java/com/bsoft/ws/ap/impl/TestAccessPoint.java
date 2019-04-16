package com.bsoft.ws.ap.impl;

import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.bsoft.ws.ap.WebServiceAccessPoint;

public class TestAccessPoint implements WebServiceAccessPoint {

	@Override
	public String service(Map body) {
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("UTF-8");
		Element root = doc.addElement("ServiceResponse");
		root.addElement("code").setText("200");
		root.addElement("message").setText("测试成功！");
		root.addElement("body");

		return doc.asXML();
	}

}
