package com.zjzx.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;

public class FileUtil {
	public static final String tempDir = "d:/temp";
	public static final String markRoot ="d:";
	public static final String videomarkRoot ="d\\\\:";
	public FutureObj<FileInputStream> createSlt(final InputStream fis)
			throws Exception {
		final FutureObj<FileInputStream> futureObj = new FutureObj<FileInputStream>();
		new AsyncUtil() {
			@Override
			public void runMethod() {
				// TODO Auto-generated method stub
				String tempFile = tempDir + "/" + getFileName() + ".mp4";
				try {
					FileOutputStream fos = new FileOutputStream(new File(
							tempFile));
					IOUtils.copy(fis, fos);
					IOUtils.closeQuietly(fos);
					IOUtils.closeQuietly(fis);
					String targetFile = tempDir + "/" + getFileName() + ".jpg";
					List<String> commend = new ArrayList<String>();
					commend.add("ffmpeg");
					commend.add("-i");
					commend.add(tempFile);
					commend.add("-y");
					commend.add("-f");
					commend.add("image2");
					commend.add("-ss");
					commend.add("1");
					commend.add("-t");
					commend.add("0.001");
					commend.add(targetFile);
					ProcessBuilder builder = new ProcessBuilder(commend);
					builder.start();
					FileInputStream targetfis = getFile(targetFile);
					
					System.out.println(targetfis);
					futureObj.setSuccess(true);
					futureObj.setValue(targetfis);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					futureObj.setSuccess(false);
				}
			}
		}.excute();

		return futureObj;
	}
	
	public InputStream getMarkPic(InputStream fis){
		String fileName = "d:/temp/"+getFileName();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(fileName));
			IOUtils.copy(fis, fos);
			WaterMarkUtils.addIdCardMark(fileName, markRoot);
			return new FileInputStream(new File(fileName));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public static FileInputStream getFile(String targetFile) {
		FileInputStream targetfis = null;
		try {
			targetfis = new FileInputStream(new File(targetFile));
			return targetfis;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return getFile(targetFile);
		}

	}

	public static String getFileName() {
		String uid = UUID.randomUUID().toString();
		return uid;
	}

//	public static void main(String[] args) {
//		String targetFile = tempDir + "/aaa.jpg";
//		List<String> commend = new ArrayList<String>();
//		// cutpic.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
//		// cutpic.add(upFilePath); // 视频文件路径
//		// cutpic.add("-y");
//		// cutpic.add("-f");
//		// cutpic.add("image2");
//		// cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
//		// cutpic.add("1"); // 添加起始时间为第1秒
//		// cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
//		// cutpic.add("0.001"); // 添加持续时间为1毫秒
//		// cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
//		// cutpic.add("800*600"); // 添加截取的图片大小为800*600
//		// cutpic.add(mediaPicPath); // 添加截取的图片的保存路径
//
//		commend.add("ffmpeg");
//		commend.add("-i");
//		commend.add("d:/temp/12.mp4");
//		commend.add("-y");
//		commend.add("-f");
//		commend.add("image2");
//		commend.add("-ss");
//		commend.add("1");
//		commend.add("-t");
//		commend.add("0.001");
//		commend.add("d:/aaa.jpg");
//		ProcessBuilder builder = new ProcessBuilder(commend);
//		try {
//			builder.start();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
