/*package com.zjzx.util;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GzServletUtil {
	public static void invokmethod(Object obj, String method,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			Method executeMth = obj.getClass().getDeclaredMethod(method,
					HttpServletRequest.class, HttpServletResponse.class);
			executeMth.setAccessible(true);
			executeMth.invoke(obj, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

	}
}
*/