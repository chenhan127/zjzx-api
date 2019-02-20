package com.zjzx.clientservice.zjzx_wx;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.zjzx.clientservice.zjzx_wx.fallback.WxCientServiceFallbackFactory;

@FeignClient(value="zjzx-wx",fallbackFactory=WxCientServiceFallbackFactory.class)
public interface WxCientService {
	@RequestMapping("/wx/getWxUser")
	public JSONObject getWxUser(@RequestParam("openid") String openid, @RequestParam("access_token") String access_token);


}
