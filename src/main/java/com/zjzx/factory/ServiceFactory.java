//package com.zjzx.factory;
//
//import com.jfinal.aop.Duang;
//import com.jfinal.plugin.activerecord.tx.Tx;
//
//public class ServiceFactory {
//	
//	public static <K> K getServiceForTx(String packge){
//		K service = null;
//		try {
//			 service =(K) Duang.duang(Class.forName(packge),Tx.class);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return service;
//	}
//	
//	public static <K> K getServiceFor(String packge){
//		K service = null;
//		try {
//			 service =(K) Duang.duang(Class.forName(packge));
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return service;
//	}
//
//}
