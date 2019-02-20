package com.zjzx.util;

public class CodeUtil {
	/**
     * 
     * 随机生成验证码（数字+字母）
     *
     * @param len 邀请码长度
     * @return
     * 
     * @author ailo555
     * @date 2016年10月23日 上午9:27:09
     */
    public static String generateRandomStr(int len) {   
        //字符源，可以根据需要删减
        String generateSource = "23456789abcdefghgklmnpqrstuvwxyz";//去掉1和i ，0和o
        String rtnStr = "";
        for (int i = 0; i < len; i++) {
            //循环随机获得当次字符，并移走选出的字符
            String nowStr = String.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
            rtnStr += nowStr;
            generateSource = generateSource.replaceAll(nowStr, "");
        }
        return rtnStr;
    }
    
    public static void main(String[] args) {
		System.out.println(generateRandomStr(10));
	}

}
