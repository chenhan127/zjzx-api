package com.zjzx.aop;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zjzx.annotation.EmptyCheck;
@Aspect
@Component
public class EmptyCheckAspect {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	
	@Pointcut("@annotation(emptyCheck)")
    public void tokenStatistics(EmptyCheck emptyCheck) {
		
    }

    @Around("tokenStatistics(emptyCheck)")
    public Object doBefore(ProceedingJoinPoint  joinPoint, EmptyCheck emptyCheck) throws Throwable {
       String [] params = emptyCheck.value();
       JSONObject resMap = new JSONObject();
       resMap.put("status", "error");
       boolean flag = true;
       for(String param:params){
    	   Object value = request.getParameter(param);
    	   if(value == null || "".equals(value)){
    		   flag = false;
    		   resMap.put(param, "不能为空");
    	   }
       }
       if(!flag){
    	   response.setCharacterEncoding("utf-8");
    	   response.getWriter().write(resMap.toJSONString());
    	   return null;
       }
       return joinPoint.proceed();
    }

  /*  @After("tokenStatistics(tokenAop)")
    public void doAfter(TokenAop EmptyCheck emptyCheck {
    }*/
}
