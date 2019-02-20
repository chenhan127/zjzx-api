//package com.zjzx.jfinal;
//
//import java.io.File;
//import java.io.FileFilter;
//import java.io.IOException;
//import java.net.URL;
//import java.net.URLDecoder;
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.List;
//
//import com.jfinal.config.Routes;
//import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
//
//public class Scan {
//	public static final String Controllerpackage = "com.zjzx.controller";
//	public static final String Modelpackage = "com.zjzx.model";
//
//	/**
//	 * 注解扫描controller
//	 * 
//	 * @param rt
//	 */
//	public static void MappingControllor(Routes rt) {
//		// TODO Auto-generated method stub
//		List<Class<?>> allClass = ClassUtil.getClasses(Controllerpackage);
//				
//				// getClasses(Controllerpackage);
//		for (Class c : allClass) {
//			JFController ano = (JFController) c
//					.getAnnotation(JFController.class);
//			String mapkey = ano.value();
//			rt.add(mapkey, c);
//		}
//	}
//
//	/**
//	 * 注解扫描model
//	 * 
//	 * @param arp
//	 * @throws Exception
//	 */
//	public static void MappingModel(ActiveRecordPlugin arp) throws Exception {
//		// TODO Auto-generated method stub
//		List<Class<?>> allClass = ClassUtil.getClasses(Modelpackage);
//		for (Class c : allClass) {
//			try {
//				Table ano = (Table) c.getAnnotation(Table.class);
//				String mapkey = ano.value();
//				String idcolumn = ano.idColumn();
//				arp.addMapping(mapkey,idcolumn,c);
//				if (mapkey == null) {
//					throw new Exception(c.getCanonicalName() + "   未映射");
//				}
//			} catch (Exception e) {
//				//throw new Exception(c.getCanonicalName() + "    未映射");
//				System.out.println(c.getName() +"未注解");
//			}
//		}
//	}
//
////	public static void main(String[] args) {
////		MappingControllor(null);
////	}
//
//	private static List<Class<?>> getClasses(String packagename) {
//		String dirpath = packagename.replace('.', '/');
//		List<Class<?>> allClassArray = null;
//		try {
//			Enumeration<URL> dirs = Thread.currentThread()
//					.getContextClassLoader().getResources(dirpath);
//
//			while (dirs.hasMoreElements()) {
//				URL url = dirs.nextElement();
//				// 得到协议的名称
//				String protocol = url.getProtocol();
//				if ("file".equals(protocol)) {
//					System.err.println("file类型的扫描");
//					// 获取包的物理路径
//					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
//					// 以文件的方式扫描整个包下的文件 并添加到集合中
//					allClassArray = getClassByPath(filePath, packagename);
//				}
//
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return allClassArray;
//	}
//
//	private static List<Class<?>> getClassByPath(String path, String packagename) {
//		List<Class<?>> allClassArray = new ArrayList<Class<?>>();
//		File dir = new File(path);
//		if (!dir.exists() || !dir.isDirectory()) {
//			return null;
//		}
//
//		File[] dirfiles = dir.listFiles(new FileFilter() {
//			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
//			public boolean accept(File file) {
//				return (file.isDirectory())
//						|| (file.getName().endsWith(".class"));
//			}
//		});
//		for (File file : dirfiles) {
//			String classname = packagename + "." + file.getName();
//			classname = classname.substring(0, classname.lastIndexOf("."));
//			try {
//				allClassArray.add(Thread.currentThread()
//						.getContextClassLoader().loadClass(classname));
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println();
//		}
//		return allClassArray;
//	}
//
//}
