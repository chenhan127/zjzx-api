package com.zjzx.util;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.JsonKit;

public class JSONUtil {
	
	public static JSONObject parseJSON(JSONObject json){
		if(json == null){
			return new JSONObject();
		}
		String jsonStr =  JsonKit.toJson(json);
		
		return JSONObject.parseObject(jsonStr);
	}

}
