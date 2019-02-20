package com.zjzx.util;

import java.util.Hashtable;

public class TransProgress {
	private TransProgress(){
		
	}
	//总进度
	private Long allProgress = 0L;
	//当前进度
	private int curentProgress = 0;
	private static Hashtable<String,TransProgress> table = new Hashtable<String,TransProgress>();
	
	public static TransProgress getTransProgress(String token){
		TransProgress  tp = table.get(token);
		if(tp == null){
			tp = new TransProgress();
			table.put(token, tp);
		}
		return tp;
	}
	
	public void init(){
		this.curentProgress = 0;
		this.allProgress = 0L;
	}
	/**
	 * 设置总进度
	 * @param allProgress
	 */
	public void setAllProgress(Long allProgress){
		this.allProgress = allProgress;
	}
	/**
	 * 合并当前进度
	 * @param curentProgress
	 */
	public void appendCurentProgress(int curentProgress){
		this.curentProgress = this.curentProgress+curentProgress;
	}
	
	public int getCurentProgress(){
		return this.curentProgress;
	}
	/**
	 * 获取当前上传进度百分比
	 * @return
	 */
	public String getCurentPercent(){
		//curentProgress/allProgress;
		float res = (float)curentProgress/(float)allProgress;
		res = res *100;
		return String.format("%.2f", res);
	}
//	public static void main(String[] args) {
//		long a = 12131;
//		long b = 1212;
//		float res = (float)b/(float)a;
//		System.out.println(String.format("%.2f", res));
//	}
	  

}
