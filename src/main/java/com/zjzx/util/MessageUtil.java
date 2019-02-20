/*package com.zjzx.util;

import java.io.IOException;


import com.alibaba.fastjson.JSONObject;
import com.constant.ParameterConfig;
import com.model.Message;
import com.task.AccessToken;

*//***
 * 消息发送工具
 * @author D N
 *
 *//*
public class MessageUtil {
	*//**
	 * 发送单位提醒消息
	 *//*
	
	//private static String access_token = AccessToken.getAccessToken();
	public static void sendDwtxMsg(Message message,String openid){
		 String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + AccessToken.getAccessToken();
		
		//单位提醒消息模板
		String mbid = ParameterConfig.WX_UNIT_NOTICE_ID;
		JSONObject xxObj = new JSONObject();
		//接收者openid
		xxObj.put("touser", openid);
		xxObj.put("template_id", mbid);
		xxObj.put("url", message.getUrl());
		
		JSONObject data = new JSONObject();
		xxObj.put("data", data);
		
		JSONObject first = new JSONObject();
		//消息内容
		first.put("value", message.getXxnr());
		first.put("color", "#173177");
		data.put("first", first);
		//标题
		JSONObject keyword1 = new JSONObject();
		keyword1.put("value", message.getBt());
		keyword1.put("color", "#173177");
		data.put("keyword1", keyword1);
		
		//创建人
		JSONObject keyword2 = new JSONObject();
		keyword2.put("value", message.getCjr());
		keyword2.put("color", "#173177");
		data.put("keyword2", keyword2);
		
		//创建时间
		JSONObject keyword3 = new JSONObject();
		keyword3.put("value", message.getCreatetime());
		keyword3.put("color", "#173177");
		data.put("keyword3", keyword3);
		
		JSONObject remark = new JSONObject();
		remark.put("value", "请注意查收");
		remark.put("color", "#173177");
		data.put("remark", remark);
		JSONObject resMap =	null;
				
		try {
			resMap = HttpUtil.postMsg(url, xxObj);
		System.out.println(resMap);
		String statu = resMap.getString("errmsg");
		
		if("ok".equals(statu)){
			String msgid = resMap.getString("msgid");
			System.out.println("消息发送成功。。。。。msgid="+msgid);
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			String errcode = resMap.getString("errcode");
			if("40001".equals(errcode)){
				
			}
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		String url = ParameterConfig.ACCESS_TOKEN_ONLY_URL;
		try {
			JSONObject obj = HttpUtil.getHttpJSONObj(url);
			String token = obj.getString("access_token");
			String access_token = token;//AccessToken.getAccessToken();
			
			Message msg = new Message();
			msg.setBt("测试");
			msg.setCjr("陈涵");
			msg.setXxlx("单位通知");
			msg.setXxnr("阿斯顿撒多");
			msg.setCreatetime("2017-11-11");
			msg.setCreatorid("1");
			msg.setTouserid("2");
			msg.setTousername("asd");
			msg.sendBydwtx();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
*/