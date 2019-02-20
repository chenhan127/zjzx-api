package com.zjzx.util;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import net.sourceforge.pinyin4j.PinyinHelper;

public class WordUtil {
	public static String toPinyin(String str) {
		String convert = "";
		for (int j = 0, len = str.length(); j < len; j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				convert += word;
			}
		}
		return convert;
	}

	//
	// public static void main(String[] args) {
	// System.out.println(toPinyin("$"));
	// List<Record> user = Db.find("select * from user limit 20");
	//
	// }

	/**
	 * 删除Html标签
	 * 
	 * @param inputString
	 * @return
	 */
	public static String removeHtmlTag(String inputString) {
		if (inputString == null)
			return null;
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		java.util.regex.Pattern p_special;
		java.util.regex.Matcher m_special;
		try {
			// 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			// 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
			// 定义HTML标签的正则表达式
			String regEx_html = "<[^>]+>";
			// 定义一些特殊字符的正则表达式 如：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			String regEx_special = "\\&[a-zA-Z]{1,10};";

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签
			p_special = Pattern
					.compile(regEx_special, Pattern.CASE_INSENSITIVE);
			m_special = p_special.matcher(htmlStr);
			htmlStr = m_special.replaceAll(""); // 过滤特殊标签
			textStr = htmlStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return textStr;// 返回文本字符串
	}

//	public static void main(String[] args) {
//		StringBuffer sb = new StringBuffer();
//		sb.append("<p>");
//		sb.append("&emsp;&emsp;湖北半年破电信网诈案2286起&emsp;");
//		sb.append("</p>");
//		sb.append("<p>");
//		sb.append("&emsp;本报武汉7月18日电&emsp;记者刘志月实习生刘欢&emsp;");
//		sb.append("</p>");
//		sb.append("<p>");
//		sb.append("记者今天从湖北省公安厅获悉,上半年,湖北全省共破获电信诈骗案件2286起,同比上升11%；打掉团伙130个,抓获违法犯罪嫌疑人2181人。劝阻拦截12981起,避免群众损失5000余万元,紧急止付资金3.8亿余元,冻结涉案资金8亿元。");
//		sb.append("</p>");
//		String str = removeHtmlTag(sb.toString());
//		System.out.println(str);
//
//	}
}
