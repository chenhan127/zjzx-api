package com.zjzx.util;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.VideoAttributes;

import java.io.File;

public class FfmepgDemo {
//	public static void main(String[] args) {
//		
//        File source = new File("E:/导出视频/7.mp4");
//        File target = new File("E:/导出视频/8.mp4");
//        
//        try{
//            AudioAttributes audio = new AudioAttributes();
//            audio.setCodec("libmp3lame");
//            audio.setBitRate(new Integer(56000));
//            audio.setChannels(new Integer(1));
//            audio.setSamplingRate(new Integer(22050));
//            
//            VideoAttributes video = new VideoAttributes();
//            video.setCodec("mpeg4");
//            video.setBitRate(new Integer(800000));
//            video.setFrameRate(new Integer(15));
//            
//            EncodingAttributes attrs = new EncodingAttributes();
//            attrs.setFormat("mp4");
//            attrs.setAudioAttributes(audio);
//            attrs.setVideoAttributes(video);
//            
//            Encoder encoder = new Encoder();
//            encoder.encode(source, target, attrs);
//            System.out.println("压缩完成...");
//        }catch (EncoderException e){
//            e.printStackTrace();
//        }
// 
//    }
}
