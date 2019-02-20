package com.zjzx.clientservice;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.zjzx.clientservice.factory.SmsClientServiceFallbackFactory;

@FeignClient(value="zjzx-sms",fallbackFactory=SmsClientServiceFallbackFactory.class)
public interface SmsClientService {
	@RequestMapping("/sms/sendCode")
	public JSONObject sendCode(@RequestParam("mobile") String mobile, @RequestParam("code") String code);
}
