package com.zjzx.util.videoutil;

import it.sauronsoftware.jave.Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.channels.FileChannel;

import com.alibaba.fastjson.JSONObject;

public class VideoUtil {

	public static JSONObject getVideoMsg(String filePath) {
		File source = new File(filePath);

		Encoder encoder = new Encoder();

		FileChannel fc = null;

		String size = "";
		JSONObject resMap = new JSONObject();

		try {

			it.sauronsoftware.jave.MultimediaInfo m = encoder.getInfo(source);

			long ls = m.getDuration();
			String time = ls / 60000 + "分" + (ls) / 1000 + "秒！";
			System.out.println("此视频时长为:" + ls / 60000 + "分" + (ls) / 1000
					+ "秒！");
			resMap.put("time", time);

			// 视频帧宽高

			System.out.println("此视频高度为:" + m.getVideo().getSize().getHeight());
			int height = m.getVideo().getSize().getHeight();
			resMap.put("height", height);
			System.out.println("此视频宽度为:" + m.getVideo().getSize().getWidth());

			int width = m.getVideo().getSize().getWidth();
			resMap.put("width", width);
			String fmt = m.getFormat();
			System.out.println("此视频格式为:" + fmt);
			resMap.put("fmt", fmt);
			FileInputStream fis = new FileInputStream(source);

			fc = fis.getChannel();

			BigDecimal fileSize = new BigDecimal(fc.size());

			size = fileSize.divide(new BigDecimal(1048576), 2,
					RoundingMode.HALF_UP) + "MB";

			resMap.put("size", size);

			System.out.println("此视频大小为" + size);
			return resMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (null != fc) {

				try {

					fc.close();

				} catch (IOException e) {

					e.printStackTrace();

				}

			}

		}

	}
	
	
	/*
	 * public static void main(String[] args) {
	 * 
	 * }
	 */

}
