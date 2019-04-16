package com.bsoft.common.util;

import java.io.IOException;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

	public static String get(String url, Map<String, String> args) {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		CloseableHttpResponse response = null;

		String temp = "";
		try {
			URIBuilder uriBuilder = new URIBuilder(url);

			if (args != null && args.size() > 0) {
				for (Map.Entry<String, String> entry : args.entrySet()) {
					uriBuilder.addParameter(entry.getKey(), entry.getValue());
				}
			}

			HttpGet httpGet = new HttpGet(uriBuilder.build());

			/*
			 * 添加请求头信息
			 */
			// 浏览器表示
			httpGet.addHeader("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
			// 传输的类型
			httpGet.addHeader("Content-Type",
					"application/x-www-form-urlencoded");

			// 执行请求
			response = httpClient.execute(httpGet);
			// 获得响应的实体对象
			HttpEntity entity = response.getEntity();

			temp = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放连接
			if (null != response) {
				try {
					response.close();
					httpClient.close();
				} catch (IOException e) {
					System.err.println("释放连接出错");
					e.printStackTrace();
				}
			}
		}
		return temp;
	}

	public static String post(String url, String json) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = HttpClients.createDefault();
			httpPost = new HttpPost(url);
			StringEntity entity = new StringEntity(json, "utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
			response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 释放连接
			if (null != response) {
				try {
					response.close();
					httpClient.close();
				} catch (IOException e) {
					System.err.println("释放连接出错");
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		JSONObject jo = new JSONObject();
		jo.put("username", "system");
		jo.put("password", "admin@123");
		jo.put("isEncrypt", false);
		System.out.println(HttpClientUtil.post("http://localhost:8080/BigDataAOP/login.action",jo.toString()));
	}
}
