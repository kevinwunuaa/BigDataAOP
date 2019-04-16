package com.bsoft.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * dom4j XML处理工具类.
 * @author Wuyong
 *
 */
public class Dom4jImpl {
	/**
	 * 根据给定路径生成Document.
	 * @param classPath 给定路径
	 * @param fileName 文件名
	 * @return {@link Document}
	 */
	public Document createXmlDoc(String classPath, String fileName) {
		return createXmlDoc(new File(ClassLoader.class.getResource(classPath)
				.getPath(), fileName));
	}

	/**
	 * 保存文档.
	 * @param doc
	 * @param xmlPath
	 * @throws IOException
	 */
	public void saveDocument(Document doc, String xmlPath) throws IOException {
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(new FileOutputStream(xmlPath), format);
		writer.write(doc);
		writer.close();
	}
	
	/**
	 * 根据文件句柄生成Document.
	 * @param f
	 * @return  {@link Document}
	 */
	public Document createXmlDoc(File f) {
		SAXReader reader = new SAXReader();

		Document document = null;
		try {
			document = reader.read(f);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return document;
	}
	
	/**
	 * 创建给定格式的请求xml文档，不通用.
	 * @return {@link Document}
	 */
	public Document CreateXmlDoc() {
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("UTF-8");
		Element root = doc.addElement("Response");
		Element eltHead = root.addElement("Header");
		eltHead.addElement("FunCode");
		eltHead.addElement("ResultCode");
		eltHead.addElement("ErrorCode");
		eltHead.addElement("ResultMsg");
		eltHead.addElement("OpTime");
		eltHead.addElement("UserID");
		root.addElement("Body");
		return doc;
	}

	/**
	 * 创建给定格式的响应型文档.
	 * @return {@link Document}
	 */
	public Document createRegistrationResponseXml() {
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("UTF-8");
		Element root = doc.addElement("Output");
		root.addElement("Return").setText("1");
		;
		root.addElement("ErrorMessage").setText("成功");
		return doc;
	}

	/**
	 * xml串转文档.
	 * @param xml
	 * @return {@link Document}
	 * @throws Exception
	 */
	public Document stringToXmlDoc(String xml) throws Exception {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			throw new Exception("转换请求的XML失败:" + e.getMessage());
		} catch (Exception e) {
			throw new Exception("转换请求的XML失败:" + e.getMessage());
		}
		return doc;
	}

	/**
	 * 把xml文件转换为map形式，其中key为有值的节点名称，并以其�?��的祖先节点为前缀，用
	 * "."相连接�?如：SubscribeServiceReq.Send_Address.Address_Info.DeviceType
	 * 
	 * @param xmldoc
	 * @return Map 转换为map返回
	 */
	public Map<String, String> xmlDocToMap(Document xmldoc) throws Exception {
		Map<String, String> rtnMap = new HashMap<String, String>();
		Element root = xmldoc.getRootElement(); // 得到根节点
		String rootName = root.getName();
		rtnMap.put("root.name", rootName);
		try {
			// 调用递归函数，得到所有最底层元素的名称和值，加入map
			convertElementToMap(root, rtnMap, rootName);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return rtnMap;
	}

	/**
	 * 递归函数，找出最下层的节点并加入到map中，由XmlDocToMap方法调用.
	 * 
	 * @param e
	 *            xml节点，包括根节点
	 * @param map
	 *            目标map
	 * @param lastname
	 *            从根节点到上面节点名称连接的字符串
	 */
	private static void convertElementToMap(Element e, Map<String, String> map,
			String lastname) throws Exception {
		try {
			if (e.attributes().size() > 0) {
				Iterator<?> it_attr = e.attributes().iterator();
				while (it_attr.hasNext()) {
					Attribute attribute = (Attribute) it_attr.next();
					String attrname = attribute.getName();
					String attrvalue = e.attributeValue(attrname);
					map.put(lastname + "." + attrname, attrvalue);
				}
			}
			List<?> children = e.elements();
			Iterator<?> it = children.iterator();
			while (it.hasNext()) {
				Element child = (Element) it.next();
				String name = lastname + "." + child.getName();
				// 如果有子节点，则递归调用
				if (child.elements().size() > 0) {
					convertElementToMap(child, map, name);
				} else {
					// 如果没有子节点，则把值加入map
					map.put(name, child.getText());
					// 如果该节点有属性，则把所有的属性值也加入map
					if (child.attributes().size() > 0) {
						Iterator<?> attr = child.attributes().iterator();
						while (attr.hasNext()) {
							Attribute attribute = (Attribute) attr.next();
							String attrname = attribute.getName();
							String attrvalue = child.attributeValue(attrname);
							map.put(name + "." + attrname, attrvalue);
						}
					}
				}
			}
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}
}
