package com.jfinal.weixin.sdk.utils;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// gson 将map序列化成json字符串的时候不会讲int转为浮点
// 将json字符串转反序列化为map时，int会变成浮点型
public class GsonTest {

	static Gson gson = new GsonBuilder().create();
	
	public static void main(String[] args) {
		int id = 111;
		String name = "张三疯";
		
		Map<String, Map<String, Object>> groupData = new HashMap<String, Map<String, Object>>();
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("id", id);
		mapData.put("name", name);
		groupData.put("group", mapData);
		
		System.out.println(gson.toJson(groupData));
		
		
		String jsonStr = "{\"group\":{\"id\":111,\"name\":\"张三疯\"}}";
		
		@SuppressWarnings("rawtypes")
		Map xx = gson.fromJson(jsonStr, Map.class);
		System.out.println(xx);
	}
}
