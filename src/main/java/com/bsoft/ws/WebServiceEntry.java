package com.bsoft.ws;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;

import com.bsoft.common.context.ApplicationContextWrapper;
import com.bsoft.common.util.XmlTool;
import com.bsoft.ws.ap.WebServiceAccessPoint;

/**
 * 单一入口通用web服务定义类.
 * <br/>
 * 注入bean为#webServiceEntry,定义url为：/services/webServiceEntry，需要用到此服务的应用，需导入com/bsoft/ws/spring-ws.xml配置.
 * <br/>
 * @author Wuyong 2019-01-04
 */
@Service("webServiceEntry")
@WebService(endpointInterface = "com.bsoft.ws.WebServiceEntry", serviceName = "webServiceEntry", portName = "webServiceEntryPort")
@SOAPBinding(style = javax.jws.soap.SOAPBinding.Style.RPC)
public class WebServiceEntry{
	/**
	 * 服务入口方法
	 * <br/>
	 * 消息格式：
	 * <br/>
	 * 请求消息格式：<br/>
	 * <ServiceRequest>
	 * 	<serviceId>服务id[实现WebServiceAccessPoint接口，且注入spring容器]</serviceId>
	 *  <body>数据载体</body>
	 * </ServiceRequest>
	 * 响应消息格式：<br/>
	 * <ServiceResponse>
	 * 	<code>消息码</code>
	 *  <message>消息内容</message>
	 *  <body>数据</body>
	 * </ServiceResponse>
	 * @param requestXml 符合预先规定格式的xml请求数据.
	 * @return xml响应消息.
	 * @throws Exception
	 */
	@WebMethod(operationName="invoke")
	public String invoke(String requestXml) {
		Map requestMap = null;
		try {
			requestMap = XmlTool.documentToMap(requestXml);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		String serviceId = (String) requestMap.get("serviceId");
		
		WebServiceAccessPoint ap = (WebServiceAccessPoint) ApplicationContextWrapper.getApplicationContext().getBean(serviceId);
		
		Object body = requestMap.get("body");
		
		Map bodyMap = null;
		
		if(body == null || body instanceof String){
			bodyMap = new HashMap();
			bodyMap.put("body", body);
		}
		
		if(body != null && body instanceof Map){
			bodyMap = (Map) body;
		}

		return ap.service(bodyMap);
	} 
}
