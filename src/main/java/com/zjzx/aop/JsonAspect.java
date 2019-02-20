//package com.zjzx.aop;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import javassist.ClassClassPath;
//import javassist.ClassPool;
//import javassist.CtClass;
//import javassist.CtMethod;
//import javassist.Modifier;
//import javassist.NotFoundException;
//import javassist.bytecode.CodeAttribute;
//import javassist.bytecode.LocalVariableAttribute;
//import javassist.bytecode.MethodInfo;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.fastjson.JSONObject;
//import com.jfinal.plugin.activerecord.Record;
//import com.zjzx.service.RequestService;
//import com.zjzx.util.AsyncUtil;
//import com.zjzx.util.DateUtil;
//
//@Aspect
//@Component
//public class JsonAspect {
//	// private static final Log log = LogFactory.getLog(JsonAspect.class);
//	private static final Map<String, Log> logMap = new HashMap<String, Log>();
//	// private static final int PoolSize = 20;
//	// //带有回调机制的线程池
//	// private static final ListeningExecutorService service = MoreExecutors
//	// .listeningDecorator(Executors.newFixedThreadPool(PoolSize));
//
//	@Autowired
//	private RequestService requestService;
//
//	@Autowired
//	private HttpServletRequest request;
//
//	@Pointcut("execution(public * com.zjzx.controller.*.*(..))")
//	public void webLog() {
//	}
//
//	@Before("webLog()")
//	public void deBefore(JoinPoint joinPoint) throws Throwable {
//		Class cls = joinPoint.getTarget().getClass();
//		String key = cls.getSimpleName();
//		if ("HealthController".equals(key)) {
//			return;
//		}
//		Log log = logMap.get(key);
//		if (log == null) {
//			log = LogFactory.getLog(cls);
//			logMap.put(key, log);
//		}
//		StringBuffer sb = new StringBuffer();
//		sb.append("\r\n----------time-------"
//				+ DateUtil.convertY_M_D_H_M_S(new Date())
//				+ "--------------------------\r\n");
//
//		sb.append("----------action------" + joinPoint.getSignature().getName()
//				+ "--------------------------\r\n");
//		Object[] args = joinPoint.getArgs();// 参数
//		// 获取参数名称和值
//		Map<String, Object> nameAndArgs = getFieldsName(this.getClass(),
//				cls.getName(), joinPoint.getSignature().getName(), args);
//		sb.append("----------args------" + nameAndArgs
//				+ "--------------------------\n");
//		log.info(sb);
//		Map<String, String[]> map = request.getParameterMap();
//		Map<String, Object> params = null;
//		try {
//			params = JsonAspect.getFieldsName(this.getClass(), cls.getName(),
//					joinPoint.getSignature().getName(), joinPoint.getArgs());
//		} catch (NotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		final Record record = new Record();
//		record.set("controller", cls.getName());
//		record.set("method", joinPoint.getSignature().getName());
//		record.set("requestparam", JSONObject.toJSONString(map));
//		record.set("params", params.toString());
//		record.set("time", DateUtil.convertY_M_D_H_M(new Date()));
//		record.set("ip", getRemoteHost());
//		new AsyncUtil() {
//
//			@Override
//			public void runMethod() {
//				// TODO Auto-generated method stub
//				requestService.saveRequestMsg(record);
//			}
//		}.excute();
//
//		// service.submit(new Callable<Object>() {
//		// @Override
//		// public Object call() throws Exception {
//		// // TODO Auto-generated method stub
//		//
//		// return null;
//		// }
//		// });
//	}
//
//	public String getRemoteHost() {
//		String ipAddress = null;
//		try {
//			ipAddress = request.getHeader("x-forwarded-for");
//			if (ipAddress == null || ipAddress.length() == 0
//					|| "unknown".equalsIgnoreCase(ipAddress)) {
//				ipAddress = request.getHeader("Proxy-Client-IP");
//			}
//			if (ipAddress == null || ipAddress.length() == 0
//					|| "unknown".equalsIgnoreCase(ipAddress)) {
//				ipAddress = request.getHeader("WL-Proxy-Client-IP");
//			}
//			if (ipAddress == null || ipAddress.length() == 0
//					|| "unknown".equalsIgnoreCase(ipAddress)) {
//				ipAddress = request.getRemoteAddr();
//				if (ipAddress.equals("127.0.0.1")) {
//					// 根据网卡取本机配置的IP
//					InetAddress inet = null;
//					try {
//						inet = InetAddress.getLocalHost();
//					} catch (UnknownHostException e) {
//						e.printStackTrace();
//					}
//					ipAddress = inet.getHostAddress();
//				}
//			}
//			// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
//			if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
//																// = 15
//				if (ipAddress.indexOf(",") > 0) {
//					ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
//				}
//			}
//		} catch (Exception e) {
//			ipAddress = "";
//		}
//		// ipAddress = this.getRequest().getRemoteAddr();
//
//		return ipAddress;
//	}
//
//	public static Map<String, Object> getFieldsName(Class cls,
//			String clazzName, String methodName, Object[] args)
//			throws NotFoundException {
//		Map<String, Object> map = new HashMap<String, Object>();
//
//		ClassPool pool = ClassPool.getDefault();
//		// ClassClassPath classPath = new ClassClassPath(this.getClass());
//		ClassClassPath classPath = new ClassClassPath(cls);
//		pool.insertClassPath(classPath);
//
//		CtClass cc = pool.get(clazzName);
//		CtMethod cm = cc.getDeclaredMethod(methodName);
//		MethodInfo methodInfo = cm.getMethodInfo();
//		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
//		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
//				.getAttribute(LocalVariableAttribute.tag);
//		if (attr == null) {
//			// exception
//		}
//		// String[] paramNames = new String[cm.getParameterTypes().length];
//		int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
//		for (int i = 0; i < cm.getParameterTypes().length; i++) {
//			map.put(attr.variableName(i + pos), args[i]);// paramNames即参数名
//		}
//
//		// Map<>
//		return map;
//	}
//}
