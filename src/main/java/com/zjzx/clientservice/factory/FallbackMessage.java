package com.zjzx.clientservice.factory;

import com.alibaba.fastjson.JSONObject;

public class FallbackMessage {
	public static JSONObject getMsg(String msg) {
		JSONObject resMap = new JSONObject();
		resMap.put("status", "error");
		resMap.put("msg", msg+"方法被熔断");

		return resMap;
	}
}
