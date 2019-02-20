package com.zjzx.util;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
public class FtpUtil {
	private final static String ip = "212.64.1.189";
	// private final static String ip = "127.0.0.1";
	private final static String username = "user_ftp";
	private final static String psd = "123456";
	
	public final static String FTP_FILE_ROOT = "f:/server/file";
	
	private FTPClient ftpClient;
	
	public void open(){
		  FTPClient ftpClient = new FTPClient();
		    try {
	                //连接FTP
	                ftpClient.connect(ip);
	            } catch (SocketException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	 
	            try {
	                ftpClient.login(username, psd);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	 
	            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
	               System.out.println("未连接到FTP，用户名或密码错误");
	            } else {
	               System.out.println("ftp連接成功-----");
	            }
	            this.ftpClient = ftpClient;
	}
	public  FTPClient getFtpClient(){
		return this.ftpClient;
	}
	
	public  void close(){
		
		try {
			if(this.ftpClient.isConnected()){
				this.ftpClient.disconnect();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
