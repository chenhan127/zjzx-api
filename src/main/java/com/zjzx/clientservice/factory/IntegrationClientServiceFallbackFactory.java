package com.zjzx.clientservice.factory;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zjzx.clientservice.IntegrationClientService;

import feign.hystrix.FallbackFactory;
@Component
public class IntegrationClientServiceFallbackFactory implements FallbackFactory<IntegrationClientService> {

	@Override
	public IntegrationClientService create(Throwable cause) {
		// TODO Auto-generated method stub
		return new IntegrationClientService() {
			@Override
			public JSONObject addIntegration(Integer userid, Integer integration, String discription) {
				// TODO Auto-generated method stub
				return FallbackMessage.getMsg("IntegrationClientService.addIntegration被熔断");
			}
		};
	}

}
