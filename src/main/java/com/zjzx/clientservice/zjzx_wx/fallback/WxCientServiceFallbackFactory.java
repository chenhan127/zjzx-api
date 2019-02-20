package com.zjzx.clientservice.zjzx_wx.fallback;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zjzx.clientservice.factory.FallbackMessage;
import com.zjzx.clientservice.zjzx_wx.WxCientService;

import feign.hystrix.FallbackFactory;

@Component
public class WxCientServiceFallbackFactory implements FallbackFactory<WxCientService>{

	@Override
	public WxCientService create(Throwable cause) {
		// TODO Auto-generated method stub
		return new WxCientService() {

			@Override
			public JSONObject getWxUser(String openid,String access_token) {
				// TODO Auto-generated method stub
				return FallbackMessage.getMsg("WxCientService.getWxUser");
			}
		};
	}

}
