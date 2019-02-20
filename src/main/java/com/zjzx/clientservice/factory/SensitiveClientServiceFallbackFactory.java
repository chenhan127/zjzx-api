package com.zjzx.clientservice.factory;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zjzx.clientservice.SensitiveClientService;
import com.zjzx.util.FutureObj;

import feign.hystrix.FallbackFactory;
@Component
public class SensitiveClientServiceFallbackFactory implements FallbackFactory<SensitiveClientService> {

	@Override
	public SensitiveClientService create(Throwable cause) {
		// TODO Auto-generated method stub
		return new SensitiveClientService() {
			@Override
			public JSONObject setWord(String word, Integer creatorid) {
				// TODO Auto-generated method stub
				return FallbackMessage.getMsg("SensitiveClientService.setWord被熔断");
			}
			
			@Override
			public JSONObject getWordList(Integer page, Integer size, String keyword) {
				// TODO Auto-generated method stub
				return FallbackMessage.getMsg("SensitiveClientService.getWordList被熔断");
			}
			
			@Override
			public JSONObject deleteWord(Integer recordid) {
				// TODO Auto-generated method stub
				return FallbackMessage.getMsg("SensitiveClientService.deleteWord被熔断");
			}
			
			@Override
			public FutureObj<Boolean> checkInterlocutionWord(Integer itemid) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public FutureObj<Boolean> checkArticleWord(Integer itemid) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

}
