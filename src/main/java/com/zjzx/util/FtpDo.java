package com.zjzx.util;

import java.io.InputStream;

public interface FtpDo {
	/**
	 * 设置文件流
	 * 
	 * 需返回文件流
	 * @return  
	 */
	public InputStream setFis() throws Exception;
	/**
	 * 设置文件后缀
	 * 
	 * 需返回文件后缀
	 * @return
	 */
	public String setSfx();
	/**
	 * 切换目录
	 * 
	 * 需返回切换的目录
	 * @return
	 */
	public String cd();
	/**
	 * 获取当前以上传的文件进度
	 * @param progress
	 */
	public void progress(int progress);

}
