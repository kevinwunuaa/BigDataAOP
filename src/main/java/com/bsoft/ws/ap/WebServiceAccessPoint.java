package com.bsoft.ws.ap;

import java.util.Map;

/**
 * web服务节点定义.
 * @author Wuyong
 *
 */
public interface WebServiceAccessPoint {
	/**
	 * 服务入口.
	 * @param body 请求数据.
	 * @return responsexml
	 */
	public String service(Map body);
}
