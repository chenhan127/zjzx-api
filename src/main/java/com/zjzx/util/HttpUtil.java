package com.zjzx.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class HttpUtil {

	/**
	 * 初始化一个HttpsURLConnection
	 * 
	 * @param urlStr
	 * @param requestMethod
	 *            "GET" 或者 "POST" 方法
	 * @return HttpsURLConnection
	 * @throws IOException
	 */
	public static HttpsURLConnection initHttpsConnection(String urlStr,
			String requestMethod) throws IOException {
		// 创建SSLContext对象，并使用我们指定的信任管理器初始化
		TrustManager[] tm = { new MyX509TrustManager() };
		SSLSocketFactory ssf = null;
		try {
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			ssf = sslContext.getSocketFactory();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}

		URL url = new URL(urlStr);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

		conn.setSSLSocketFactory(ssf);
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod(requestMethod);
		conn.setRequestProperty("content-type",
				"application/x-www-form-urlencoded");

		return conn;
	}

	public static HttpsURLConnection initHttpsConnectionKeepAlive(
			String urlStr, String requestMethod) {
		// 创建SSLContext对象，并使用我们指定的信任管理器初始化
		TrustManager[] tm = { new MyX509TrustManager() };
		SSLSocketFactory ssf = null;
		try {
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			ssf = sslContext.getSocketFactory();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}

		System.setProperty("http.keepalive", "true"); // default: true
		System.setProperty("http.maxConnections", "3"); // default: 5

		// HTTP header that influences connection persistence is:
		// Connection: close
		// If the "Connection" header is specified with the value "close" in
		// either the request
		// or the response header fields, it indicates that the connection
		// should not be considered
		// 'persistent' after the current request/response is complete.

		HttpsURLConnection conn = null;
		try {
			URL url = new URL(urlStr);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("Connection", "Keep-Alive"); // Connection
																	// 头部
			conn.setRequestProperty("content-type",
					"application/x-www-form-urlencoded");
		} catch (IOException e) {
			try {
				// http://docs.oracle.com/javase/8/docs/technotes/guides/net/http-keepalive.html
				// When the application encounters a HTTP 400 or 500 response,
				// it may ignore the IOException and then may issue another HTTP
				// request.
				// In this case, the underlying TCP connection won't be
				// Kept-Alive because
				// the response body is still there to be consumed, so the
				// socket connection is not cleared,
				// therefore not available for reuse. What the application needs
				// to do is
				// call HttpURLConnection.getErrorStream() after catching the
				// IOException ,
				// read the response body, then close the stream.
				int respCode = ((HttpURLConnection) conn).getResponseCode();
				InputStream es = ((HttpURLConnection) conn).getErrorStream();
				int ret = 0;
				byte[] buf = new byte[1024];
				while ((ret = es.read(buf)) > 0) { // read the response body
					// processBuf(buf);
				}
				es.close(); // close the errorstream

			} catch (IOException ex) {
			}
		}

		return conn;
	}

	/**
	 * 初始化一个HttpsURLConnection
	 * 
	 * @param urlStr
	 * @param requestMethod
	 *            "GET" 或者 "POST" 方法
	 * @return HttpsURLConnection
	 * @throws IOException
	 */
	public static HttpURLConnection initHttpConnection(String urlStr,
			String requestMethod) throws IOException {

		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod(requestMethod);
		conn.setRequestProperty("content-type",
				"application/x-www-form-urlencoded");

		return conn;
	}

	/**
	 * 从HttpsURLConnection读取返回的内容
	 * 
	 * @param conn
	 * @param characterCode
	 *            编码： "utf-8"， "gbk" 等等
	 * @return
	 * @throws IOException
	 */
	public static String getHttpsContent(HttpsURLConnection conn,
			String characterCode) throws IOException {
		InputStream inputStream = conn.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(
				inputStream, characterCode);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String str = null;
		StringBuffer buffer = new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
		}
		// 释放资源
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		conn.disconnect();

		return buffer.toString();
	}

	/**
	 * 从HttpsURLConnection读取返回的内容
	 * 
	 * @param conn
	 * @param characterCode
	 *            编码： "utf-8"， "gbk" 等等
	 * @return
	 * @throws IOException
	 */
	public static String getHttpsContentKeepAlive(HttpsURLConnection conn,
			String characterCode) throws IOException {
		StringBuffer buffer = new StringBuffer();

		try {
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, characterCode);
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;

			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			// conn.disconnect(); // keepalive 不能disconnect, InputStream
			// close就行了。
		} catch (IOException e) {
			// http://docs.oracle.com/javase/8/docs/technotes/guides/net/http-keepalive.html
			// When the application encounters a HTTP 400 or 500 response,
			// it may ignore the IOException and then may issue another HTTP
			// request.
			// In this case, the underlying TCP connection won't be Kept-Alive
			// because
			// the response body is still there to be consumed, so the socket
			// connection is not cleared,
			// therefore not available for reuse. What the application needs to
			// do is
			// call HttpURLConnection.getErrorStream() after catching the
			// IOException ,
			// read the response body, then close the stream.
			int respCode = ((HttpURLConnection) conn).getResponseCode();
			InputStream es = ((HttpURLConnection) conn).getErrorStream();
			int ret = 0;
			byte[] buf = new byte[1024];
			while ((ret = es.read(buf)) > 0) { // read the response body
				// processBuf(buf);
			}
			es.close(); // close the errorstream

		}

		return buffer.toString();
	}

	/**
	 * 从HttpsURLConnection读取返回的内容
	 * 
	 * @param conn
	 * @param characterCode
	 *            编码： "utf-8"， "gbk" 等等
	 * @return
	 * @throws IOException
	 */
	public static String getHttpContent(HttpURLConnection conn,
			String characterCode) throws IOException {
		InputStream inputStream = conn.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(
				inputStream, characterCode);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String str = null;
		StringBuffer buffer = new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
		}
		// 释放资源
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		conn.disconnect();

		return buffer.toString();
	}

	/**
	 * 向HttpsURLConnection连接发送内容
	 * 
	 * @param conn
	 * @param content
	 * @param characterCode
	 *            编码： "utf-8"， "gbk" 等等
	 * @throws IOException
	 */
	public static void writeHttpsContent(HttpsURLConnection conn,
			String content, String characterCode) throws IOException {
		if (content != null && conn != null) {
			OutputStream os = conn.getOutputStream();
			os.write(content.getBytes(characterCode));
			os.flush();
			os.close();
		}
	}
	/**
	 * HTTP 请求获取json数据
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static JSONObject getHttpJSONObj(String url) throws IOException{
		
	//	HttpsURLConnection conn = HttpUtil.initHttpsConnection(url, "POST");
		HttpURLConnection conn  = HttpUtil.initHttpConnection(url, "GET");
		String result = HttpUtil.getHttpContent(conn, "utf-8");
		try{
			
			return JSONObject.parseObject(result);
		}catch (Exception e) {
			// TODO: handle exception
			JSONObject resMap = new JSONObject();
			System.out.println(result);
			resMap.put("htm", result);
			return resMap;
		}
		
	}
	
	public static JSONObject getHttpJSONObj(String url,JSONObject params) throws IOException{
		if(url.indexOf("?")<=0){
			url = url+"?1=1";
		}
		for(Entry<String, Object> set:params.entrySet()){
			url = url +"&"+set.getKey()+"="+set.getValue();
		}
		return getHttpJSONObj(url);
		
	}
	
	
	/****
	 * http 请求获取JSONArray
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static JSONArray getHttpJSONArray(String url) throws IOException{
		
		HttpsURLConnection conn = HttpUtil.initHttpsConnection(url, "POST");
		
		String result = HttpUtil.getHttpsContent(conn, "utf-8");
		return JSONArray.parseArray(result);
	}
	
	public static JSONObject postMsg(String url,JSONObject json) throws IOException{
		HttpsURLConnection conn = HttpUtil.initHttpsConnectionKeepAlive(url, "POST");
		HttpUtil.writeHttpsContent(conn, json.toString(), "utf-8");	// 写 post 数据
		String result = HttpUtil.getHttpsContent(conn, "utf-8");
		return JSONObject.parseObject(result);
		
		
	}
	
//	public static void main(String[] args) throws IOException {
////		String url = "http://212.64.1.189:8080/zjzx/user/getCode?mobile=18556528201";
//		String url = "http://localhost:8080/zjzx-controller/template/saveTemplate";
//		JSONObject params = new JSONObject();
//		params.put("userid", "13");
//		JSONObject record = new JSONObject();
//		record.put("title", "测试");
//		record.put("discription", "测试sd");
//		record.put("content", "测试sd");
//		record.put("type", "1");
//		params.put("record", record.toString());
//		JSONObject resMap = HttpUtil.getHttpJSONObj(url, params);
//		System.out.println(resMap.toJSONString());
//		
//	}

}
