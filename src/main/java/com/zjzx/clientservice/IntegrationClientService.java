package com.zjzx.clientservice;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.zjzx.clientservice.factory.IntegrationClientServiceFallbackFactory;

@FeignClient(value="zjzx-user",fallbackFactory=IntegrationClientServiceFallbackFactory.class)
public interface IntegrationClientService {
	@RequestMapping("/integration/addIntegration")
	public JSONObject addIntegration(@RequestParam("userid") Integer userid,@RequestParam("integration") Integer integration,
			@RequestParam("discription") String discription);
}
