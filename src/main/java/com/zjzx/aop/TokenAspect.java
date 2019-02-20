package com.zjzx.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zjzx.annotation.TokenAop;
@Aspect
@Component
public class TokenAspect {
	@Autowired
	private HttpServletRequest request;
	
	@Pointcut("@annotation(tokenAop)")
    public void tokenStatistics(TokenAop tokenAop) {
    }

	@Around("tokenStatistics(tokenAop)")
    public Object doBefore(ProceedingJoinPoint joinPoint, TokenAop tokenAop) throws Throwable {
        // 记录请求到达时间
    	String userid = request.getParameter("userid");
    	String token = request.getParameter("token");
    	if(userid == null || "0".equals(userid)){
    		return null;
    	}
    	System.out.println(userid);
    	return joinPoint.proceed();
    }

    @After("tokenStatistics(tokenAop)")
    public void doAfter(TokenAop tokenAop) {
    }

}
