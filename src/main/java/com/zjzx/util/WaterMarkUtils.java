package com.zjzx.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

 /**
 * @author 白芷
 * @Date 2017/03/12
 * @use 利用Java代码给图片加水印
 */
public class WaterMarkUtils {
	
	
	public void mark(BufferedImage bufImg, Image img, Image markImg, int width, int height, int x, int y) {
		Graphics2D g = bufImg.createGraphics();
		g.drawImage(img, 0, 0, bufImg.getWidth(), bufImg.getHeight(), null);
		g.drawImage(markImg, x, y, width, height, null);
		g.dispose();
	}
	

	// 加文字水印
		public void mark(BufferedImage bufImg, Image img, String text, Font font, Color color, int x, int y) {
			Graphics2D g = bufImg.createGraphics();
			g.drawImage(img, 0, 0, bufImg.getWidth(), bufImg.getHeight(), null);
			g.setColor(color);
			g.setFont(font);
			g.drawString(text, x, y);
			g.dispose();
		}
/**
	 * 给图片增加文字水印
	 * 
	 * @param imgPath
	 *            -要添加水印的图片路径
	 * @param outImgPath
	 *            -输出路径
	 * @param text-文字
	 * @param font
	 *            -字体
	 * @param color
	 *            -颜色
	 * @param x
	 *            -文字位于当前图片的横坐标
	 * @param y
	 *            -文字位于当前图片的竖坐标
	 */
	public void mark(String imgPath, String outImgPath, String text, Font font, Color color, int x, int y) {
		try {
			// 读取原图片信息
			File imgFile = null;
			Image img = null;
			if (imgPath != null) {
				imgFile = new File(imgPath);
			}
			if (imgFile != null && imgFile.exists() && imgFile.isFile() && imgFile.canRead()) {
				img = ImageIO.read(imgFile);
			}
			int imgWidth = img.getWidth(null);
			int imgHeight = img.getHeight(null);
			// 加水印
			BufferedImage bufImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
			mark(bufImg, img, text, font, color, x, imgHeight/2);
			// 输出图片
			FileOutputStream outImgStream = new FileOutputStream(outImgPath);
			ImageIO.write(bufImg, "jpg", outImgStream);
			outImgStream.flush();
			outImgStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	
	/**
	 * 给图片增加图片水印
	 * 
	 * @param inputImg
	 *            -源图片，要添加水印的图片
	 * @param markImg
	 *            - 水印图片
	 * @param outputImg
	 *            -输出图片(可以是源图片)
	 * @param width
	 *            - 水印图片宽度
	 * @param height
	 *            -水印图片高度
	 * @param x
	 *            -横坐标，相对于源图片
	 * @param y
	 *            -纵坐标，同上
	 */
	public void mark(String inputImg, String markImg, String outputImg, int width, int height, int x, int y) {
		// 读取原图片信息
		File inputImgFile = null;
		File markImgFile = null;
		Image img = null;
		Image mark = null;
		try {
			if (inputImg != null && markImg != null) {
				inputImgFile = new File(inputImg);
				markImgFile = new File(markImg);
			}
			if (inputImgFile != null && inputImgFile.exists() && inputImgFile.isFile() && inputImgFile.canRead()) {
 
				img = ImageIO.read(inputImgFile);
 
			}
			if (markImgFile != null && markImgFile.exists() && markImgFile.isFile() && markImgFile.canRead()) {
 
				mark = ImageIO.read(markImgFile);
 
			}
			int imgWidth = img.getWidth(null);
			int imgHeight = img.getHeight(null);
			BufferedImage bufImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
			mark(bufImg, img, mark, width, height, x, imgHeight/2);
			FileOutputStream outImgStream = new FileOutputStream(outputImg);
			ImageIO.write(bufImg, "jpg", outImgStream);
			outImgStream.flush();
			outImgStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void addIdCardMark(String srcImgPath,String ftpFileroot){
		String content = "仅 用 与 直 击 真 相  认 证";
		//String tarImgPath = "F:/images/mark/auth-mark.png";
		String tarImgPath = ftpFileroot+"/mark/auth-mark.png";
		
		 Font font = new Font("宋体", Font.PLAIN, 50);  
		 //new WaterMarkUtils().mark(srcImgPath, srcImgPath, content, font, Color.ORANGE, 0, 14);
		// new WaterMarkUtils().mark(srcImgPath, tarImgPath, "水印效果测试", font, Color.ORANGE, 0, 14);
		 new WaterMarkUtils().mark(srcImgPath, tarImgPath, srcImgPath, 493, 318, 50, 14);
	}
	
//    public static void main(String[] args) {
//        Font font = new Font("宋体", Font.PLAIN, 50);                     //水印字体
//        String srcImgPath="F:/images/a.jpg"; //源图片地址
//        String tarImgPath="F:/images/t.jpg"; //待存储的地址
//        String waterMarkContent="图片来源：http://blog.csdn.net/zjq_1314520";  //水印内容
//       /* Color color=new Color(255,255,255,128);                               //水印图片色彩以及透明度
//        new WaterMarkUtils().addWaterMark(srcImgPath, tarImgPath, waterMarkContent, color,font);*/
//    //    new WaterMarkUtils().addWaterMark(srcImgPath, tarImgPath, color, waterMarkContent,font);
//        //Font font = new Font("宋体", Font.PLAIN, 14);
//		// 原图位置, 输出图片位置, 水印文字颜色, 水印文字
//		// new MarkText4J().mark("eguidMarkText2.jpg", "eguidMarkText2.jpg", "水印效果测试", font, Color.ORANGE, 0, 14);
//		// 增加图片水印
//		//new WaterMarkUtils().mark("eguidMarkText2.jpg", "eguid.jpg", "eguidMarkText3.jpg", 40, 20, 0, 14);
//      //  new WaterMarkUtils().mark(srcImgPath, tarImgPath, "水印效果测试", font, Color.ORANGE, 0, 14);
//       // addIdCardMark(srcImgPath);
//    }
	
	
	

//	public void mark(BufferedImage bufImg, Image img, Image markImg, int width, int height, int x, int y) {
//		Graphics2D g = bufImg.createGraphics();
//		g.drawImage(img, 0, 0, bufImg.getWidth(), bufImg.getHeight(), null);
//		g.drawImage(markImg, x, y, width, height, null);
//		g.dispose();
//	}
//	
//
//	// 加文字水印
//		public void mark(BufferedImage bufImg, Image img, String text, Font font, Color color, int x, int y) {
//			Graphics2D g = bufImg.createGraphics();
//			g.drawImage(img, 0, 0, bufImg.getWidth(), bufImg.getHeight(), null);
//			g.setColor(color);
//			g.setFont(font);
//			g.drawString(text, x, y);
//			g.dispose();
//		}
///**
//	 * 给图片增加文字水印
//	 * 
//	 * @param imgPath
//	 *            -要添加水印的图片路径
//	 * @param outImgPath
//	 *            -输出路径
//	 * @param text-文字
//	 * @param font
//	 *            -字体
//	 * @param color
//	 *            -颜色
//	 * @param x
//	 *            -文字位于当前图片的横坐标
//	 * @param y
//	 *            -文字位于当前图片的竖坐标
//	 */
//	public void mark(String imgPath, String outImgPath, String text, Font font, Color color, int x, int y) {
//		try {
//			// 读取原图片信息
//			File imgFile = null;
//			Image img = null;
//			if (imgPath != null) {
//				imgFile = new File(imgPath);
//			}
//			if (imgFile != null && imgFile.exists() && imgFile.isFile() && imgFile.canRead()) {
//				img = ImageIO.read(imgFile);
//			}
//			int imgWidth = img.getWidth(null);
//			int imgHeight = img.getHeight(null);
//			// 加水印
//			BufferedImage bufImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
//			mark(bufImg, img, text, font, color, x, imgHeight/2);
//			// 输出图片
//			FileOutputStream outImgStream = new FileOutputStream(outImgPath);
//			ImageIO.write(bufImg, "jpg", outImgStream);
//			outImgStream.flush();
//			outImgStream.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//		
//	
//	/**
//	 * 给图片增加图片水印
//	 * 
//	 * @param inputImg
//	 *            -源图片，要添加水印的图片
//	 * @param markImg
//	 *            - 水印图片
//	 * @param outputImg
//	 *            -输出图片(可以是源图片)
//	 * @param width
//	 *            - 水印图片宽度
//	 * @param height
//	 *            -水印图片高度
//	 * @param x
//	 *            -横坐标，相对于源图片
//	 * @param y
//	 *            -纵坐标，同上
//	 */
//	public void mark(String inputImg, String markImg, String outputImg, int width, int height, int x, int y) {
//		// 读取原图片信息
//		File inputImgFile = null;
//		File markImgFile = null;
//		Image img = null;
//		Image mark = null;
//		try {
//			if (inputImg != null && markImg != null) {
//				inputImgFile = new File(inputImg);
//				markImgFile = new File(markImg);
//			}
//			if (inputImgFile != null && inputImgFile.exists() && inputImgFile.isFile() && inputImgFile.canRead()) {
// 
//				img = ImageIO.read(inputImgFile);
// 
//			}
//			if (markImgFile != null && markImgFile.exists() && markImgFile.isFile() && markImgFile.canRead()) {
// 
//				mark = ImageIO.read(markImgFile);
// 
//			}
//			int imgWidth = img.getWidth(null);
//			int imgHeight = img.getHeight(null);
//			BufferedImage bufImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
//			mark(bufImg, img, mark, width, height, x, imgHeight/2);
//			FileOutputStream outImgStream = new FileOutputStream(outputImg);
//			ImageIO.write(bufImg, "jpg", outImgStream);
//			outImgStream.flush();
//			outImgStream.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void addIdCardMark(String srcImgPath){
//		String content = "仅 用 与 直 击 真 相  认 证";
//		String tarImgPath = "F:/images/mark/auth-mark.png";
//		
//		 Font font = new Font("宋体", Font.PLAIN, 50);  
//		 //new WaterMarkUtils().mark(srcImgPath, srcImgPath, content, font, Color.ORANGE, 0, 14);
//		// new WaterMarkUtils().mark(srcImgPath, tarImgPath, "水印效果测试", font, Color.ORANGE, 0, 14);
//		 new WaterMarkUtils().mark(srcImgPath, tarImgPath, "F:/images/t.jpg", 493, 318, 50, 14);
//	}
//	
//    public static void main(String[] args) {
//        Font font = new Font("宋体", Font.PLAIN, 50);                     //水印字体
//        String srcImgPath="F:/images/a.jpg"; //源图片地址
//        String tarImgPath="F:/images/t.jpg"; //待存储的地址
//        String waterMarkContent="图片来源：http://blog.csdn.net/zjq_1314520";  //水印内容
//       /* Color color=new Color(255,255,255,128);                               //水印图片色彩以及透明度
//        new WaterMarkUtils().addWaterMark(srcImgPath, tarImgPath, waterMarkContent, color,font);*/
//        
//    //    new WaterMarkUtils().addWaterMark(srcImgPath, tarImgPath, color, waterMarkContent,font);
//
//        //Font font = new Font("宋体", Font.PLAIN, 14);
//		// 原图位置, 输出图片位置, 水印文字颜色, 水印文字
//		// new MarkText4J().mark("eguidMarkText2.jpg", "eguidMarkText2.jpg", "水印效果测试", font, Color.ORANGE, 0, 14);
//		// 增加图片水印
//		//new WaterMarkUtils().mark("eguidMarkText2.jpg", "eguid.jpg", "eguidMarkText3.jpg", 40, 20, 0, 14);
//      //  new WaterMarkUtils().mark(srcImgPath, tarImgPath, "水印效果测试", font, Color.ORANGE, 0, 14);
//    
//        addIdCardMark(srcImgPath);
//    }

}