package com.zjzx.clientservice.factory;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zjzx.clientservice.SmsClientService;

import feign.hystrix.FallbackFactory;
@Component
public class SmsClientServiceFallbackFactory implements FallbackFactory<SmsClientService>{

	@Override
	public SmsClientService create(Throwable cause) {
		// TODO Auto-generated method stub
		return new SmsClientService() {
			
			@Override
			public JSONObject sendCode(String mobile, String code) {
				// TODO Auto-generated method stub
				return FallbackMessage.getMsg("SmsClientService.sendCode熔断");
			}
		};
	}

}
