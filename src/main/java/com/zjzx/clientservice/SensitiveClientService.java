package com.zjzx.clientservice;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.zjzx.clientservice.factory.SensitiveClientServiceFallbackFactory;
import com.zjzx.util.FutureObj;
@FeignClient(value="zjzx-system",fallbackFactory=SensitiveClientServiceFallbackFactory.class)
public interface SensitiveClientService {
	
	@RequestMapping("/sensitive/setWord")
	public JSONObject setWord(@RequestParam("word")String word,@RequestParam("creatorid") Integer creatorid);
	
	@RequestMapping("/sensitive/checkArticleWord")
	public FutureObj<Boolean> checkArticleWord(final Integer itemid);
	
	@RequestMapping("/sensitive/checkInterlocutionWord")
	public FutureObj<Boolean> checkInterlocutionWord(final Integer itemid);

	
	@RequestMapping("/sensitive/getWordList")
	public JSONObject getWordList(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("keyword") String keyword);
	
	@RequestMapping("/sensitive/deleteWord")
	public JSONObject deleteWord(Integer recordid);
}
