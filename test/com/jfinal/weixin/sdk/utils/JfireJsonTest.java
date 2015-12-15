package com.jfinal.weixin.sdk.utils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class JfireJsonTest {

	/**
	 * JfireJson代理对象
	 */
	private static class JfireJsonDelegate {
		@SuppressWarnings("unchecked")
		public <T> T decode(String jsonString, Class<T> valueType) {
			if (List.class.isAssignableFrom(valueType)) {
				return (T) link.jfire.codejson.JsonTool.fromString(jsonString);
			}
			if (Map.class.isAssignableFrom(valueType)) {
				return (T) link.jfire.codejson.JsonTool.fromString(jsonString);
			}
			return link.jfire.codejson.JsonTool.read(valueType, jsonString);
		}
		
	}
	
	public static void main(String[] args) {
		String xx = "{\"errcode\":0,\"errmsg\":\"ok\",\"ticket\":\"bxLdikRXVbTPdHSM05e5u-7GF-BEfA72SVkyQIci_2MqsBUGINGsO9nUvV_9BxdQLI_Zf5D4Q7m-SXYLz8hQcw\",\"expires_in\":7200}";
		
		JfireJsonDelegate dd = new JfireJsonDelegate();
		
		Map ooo = dd.decode(xx, Map.class);
		System.out.println(ooo);
	}
}
