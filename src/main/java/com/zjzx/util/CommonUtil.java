package com.zjzx.util;

import java.util.List;
import java.util.UUID;

public class CommonUtil {
	/**
	 * 讲列表转换为in语句
	 * @return
	 */
	public static String ListToIn(List<?> list){
		String in = list.toString().replaceAll("\\[", "(").replaceAll("\\]", ")");
		return in;
	}
	
	public static String getTocken(){
		return UUID.randomUUID().toString().replace("-", "");
	}

}
