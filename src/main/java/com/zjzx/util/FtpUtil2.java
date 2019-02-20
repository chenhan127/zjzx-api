package com.zjzx.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.UUID;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * ftp 文件上传工具
 * 
 * @author D N
 * 
 */
public class FtpUtil2 {

	//private static final String ip = "47.101.174.173";
	private static final String ip = "172.19.163.229";
	private final static String username = "ftp_user";
	private final static String psd = "123456";
	public final static String FTP_FILE_ROOT = "f:/file";
	private FTPClient ftpClient;

	private FtpUtil2() {
		// TODO Auto-generated constructor stub
		FTPClient ftpClient = new FTPClient();
		try {
			// 连接FTP
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

	/**
	 * 切换目录
	 */
	private void cd(String dir) {
		try {
			boolean res = ftpClient.changeWorkingDirectory("/" + dir);
			if (!res) {
				boolean i = ftpClient.makeDirectory("/" + dir);
				System.out.println(i);
				ftpClient.changeWorkingDirectory("/" + dir);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void sendFile(String name, InputStream fis,FtpDo ftpDo) {

		try {
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			//OutputStream os = ftpClient.storeFileStream(name);
			
//			int bufferSize = ftpClient.getBufferSize();
//			byte[] buffer = new byte[bufferSize];
//			int n = -1;
//			while ((n = fis.read(buffer)) != -1){
//				os.write(buffer);
//				ftpDo.progress(n);
//			}
//			fis.close();
//			os.flush();
//			os.close();
			ftpClient.storeFile(name, fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String doFtp(FtpDo ftpDo){
		FtpUtil2 util = null;
		try{
			util = new FtpUtil2();
			String dir = ftpDo.cd();
			String sfx = ftpDo.setSfx();
			String uid = UUID.randomUUID().toString();
			InputStream fis = ftpDo.setFis();
			String fileName= uid+"."+sfx;
			util.cd(dir);
			util.sendFile(fileName, fis,ftpDo);
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(util !=null){
				util.close();
			}
		}
		return "";
	}
	

//	public static void main(String[] args) throws FileNotFoundException {
//		FtpUtil2.doFtp(new FtpDo() {
//			@Override
//			public String setSfx() {
//				// TODO Auto-generated method stub
//				return "mp4";
//			}
//			@Override
//			public InputStream setFis() throws Exception {
//				// TODO Auto-generated method stub
//				File file = new File("e:/test.mp3");
//				FileInputStream fis = new FileInputStream(file);
//				return fis;
//			
//			}
//			@Override
//			public String cd() {
//				// TODO Auto-generated method stub
//				return "pic";
//			}
//			@Override
//			public void progress(int progress) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		
////		FtpUtil2 i = new FtpUtil2();
////		i.cd("MP3");
////		File file = new File("e:/test.mp3");
////		i.sendFile("2.MP3", new FileInputStream(file));
//	}

	private void close() {

		try {
			if (this.ftpClient.isConnected()) {
				this.ftpClient.disconnect();
				System.out.println("链接");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
