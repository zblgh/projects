package com.famework.myframedwz.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

import com.alibaba.fastjson.JSONObject;
/**
 * ------------------------------------------------------------------
 * 版权所有 Copyright (C) 2014 杭州热讯电子商务有限公司
 *
 * 项目名称：银行接口-服务端
 *
 * 模块名称：公共操作类
 *
 * 文件名：DataUtil.java
 * 
 * 创建者： 戴耀强 （kiccleaf@163.com）
 * 
 * 创建时间：2014年5月28日-上午10:53:46
 * 
 * 描述：TODO
 *-------------------------------------------------------------------
 */
public class DataUtil {

	
	/**
	 * Base64加密
	 * @param plainText
	 * @return
	 */
	public static String encodeStr(String plainText) {
		byte[] b = plainText.getBytes();
		Base64 base64 = new Base64();
		b = base64.encode(b);
		String s = new String(b);
		return s;
	}

	/**
	 * Base64解密
	 * @param encodeStr
	 * @return
	 */
	public static String decodeStr(String encodeStr) {
		byte[] b = encodeStr.getBytes();
		Base64 base64 = new Base64();
		b = base64.decode(b);
		String s = new String(b);
		return s;
	}
	/**
	 * 获取客户端域名
	 * @param request
	 * @return
	 */
	public static String getDoMain(HttpServletRequest request) {
		//String strDoMain=request.getRequestURL().toString();//获取URL整串地址
		String strDoMain=request.getServerName().toString();//获取域名

		return strDoMain.toString();
		
	}
	/**
	 * 设置头信息页面不缓存
	 * @param response
	 */
	public static void setHeader(HttpServletResponse response) {
		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Server", "PayBank V1.0");
	}
	/**
	 * 获取完整URL 方法 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getRequestURL(HttpServletRequest request) {
		if (request == null) {
			return "";
		}
		String url = "";
		url = request.getContextPath();
		url = url + request.getServletPath();

		Enumeration names = request.getParameterNames();
		int i = 0;
		if (!"".equals(request.getQueryString())
				|| request.getQueryString() != null) {
			url = url + "?" + request.getQueryString();
		}

		if (names != null) {
			while (names.hasMoreElements()) {
				String name = (String) names.nextElement();
				if (i == 0) {
					url = url + "?";
				} else {
					url = url + "&";
				}
				i++;

				String value = request.getParameter(name);
				if (value == null) {
					value = "";
				}
				url = url + name + "=" + value;
				try {
					// java.net.URLEncoder.encode(url, "ISO-8859");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		try {
			// String enUrl = java.net.URLEncoder.encode(url, "utf-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return url;
	}

	/**
	 * 获取客户端IP
	 * @param request IP地址
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param params
	 *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendGet(String url, String params) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlName = url + "?" + params;
			URL realUrl = new URL(urlName);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.137 Safari/537.36");

			// 建立实际的连接
			conn.connect();
			// 获取所有响应头字段

			Map<String, List<String>> map = conn.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}

			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			JSONObject jsonObj = JSONObject.parseObject(in.toString());
			result=jsonObj.toString();
			/*String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}*/
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定URL发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param params
	 *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendPost(String url, String params) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			//Https链接
			//HttpsURLConnection conn=(HttpsURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.137 Safari/537.36");

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(params);
			// flush输出流的缓冲
			out.flush();

			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			result=in.readLine();
			/*
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}*/
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 向指定Https发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param params
	 *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendHttps(String url, String params) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			//URLConnection conn = realUrl.openConnection();
			//Https链接
			HttpsURLConnection conn=(HttpsURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.137 Safari/537.36");

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(params);
			// flush输出流的缓冲
			out.flush();

			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			result=in.readLine();
			
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 获取指定网页内容
	 * @param strUrl
	 * @return
	 */
	public static String GetURLstr(String strUrl) {
		
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(strUrl);
		
		String info = "";
		try {
			httpClient.executeMethod(getMethod);
			info = getMethod.getResponseBodyAsString();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return info;
	}

	   /**
	    * 
	    * @param urlAll:请求接口
	    * @param charset:字符编码
	    * @return 返回json结果
	    */
	   public static String getHttp(String urlAll,String charset){
		   BufferedReader reader = null;
		   String result = null;
		   //StringBuffer sbf = new StringBuffer();
		   String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";//模拟浏览器
		   try {
			   URL url = new URL(urlAll);
			   HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			   connection.setRequestMethod("GET");
			   connection.setReadTimeout(30000);
			   connection.setConnectTimeout(30000);
			   connection.setRequestProperty("User-agent",userAgent);
			   connection.connect();
			   InputStream is = connection.getInputStream();
			   reader = new BufferedReader(new InputStreamReader(is, charset));
			   result=reader.readLine();
			  //JSONObject jsonObj = JSONObject.fromObject(reader);
				//result=jsonObj.toString();
				/*String strRead = null;
				while ((strRead = reader.readLine()) != null) {
					sbf.append(strRead);
					sbf.append("\r\n");
				}*/
				reader.close();
				//result = sbf.toString();
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		   return result;
	   }
	/**
	 * 生成对帐订单号
	 * 
	 * @return 订单号(14014357636233798)
	 */
	public static String getOrderDZ() {
		String orderNO = DateUtil.getDateTime() + getRandom4();
		return orderNO;
	}

	/**
	 * 生成正常订单20位
	 * 
	 * @return 订单号(20140530154243033740)
	 */
	public static String getOrderNO() {
		String orderNO = DateUtil.toStringDH(new Date())
				+ getRandomInt(6);
		return orderNO;
	}
	/**
	 * 获取随机整数(0~9).
	 * @param 随机整数的长度
	 * @return 随机整数
	 */
	public static String getRandomInt(int len) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int c = random.nextInt(10);
			String strRand=Integer.toString(c);
			sb.append(strRand);
		}
		return sb.toString();
	}
	/**
	 * 获取5位随机数10000-99999之间的数
	 * @return
	 */
	public static String getRandom() {
		return String.valueOf(Math.round(Math.random()*89999+10000));
	}
	/**
	 * 获取4位随机数10000-99999之间的数
	 * @return
	 */
	public static String getRandom4() {
		return String.valueOf(Math.round(Math.random()*8999+1000));
	}
	/**
	 * 获取随机数(0~9,a~z).
	 * @param 随机数的长度
	 * @return 随机数
	 */
	public static String getRandomStr(int len) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		String strRand = null;
		for (int i = 0; i < len; i++) {
			// 得到随机产生的验证码数字
			while (i >= 0) {
				int c = random.nextInt(123);
				if ((c <= '9' && c >= '0') ||(c <= 'z' && c >= 'a')) {
					strRand = String.valueOf((char) c);
					break;
				}
			}
			// 产生四个随机数字组合在一起
			sb.append(strRand);
		}
		return sb.toString();
	}
	/**
	   * 判断是否为整数
	   *
	   * @param str 传入的字符串
	   * @return 是整数返回true,否则返回false
	   */
	public static boolean isInteger(String str) {
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
	    return pattern.matcher(str).matches();
	}
	
	/**
	   * 判断是否为浮点数，包括double和float
	   *
	   * @param str 传入的字符串
	   * @return 是浮点数返回true,否则返回false
	   */
	public static boolean isDouble(String str) {
	    Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
	    return pattern.matcher(str).matches();
	}
	/**
	 * 检测手机格式
	 * @param mobiles 手机号码
	 * @return true/false
	 */
	public static boolean isMobileNO(String mobiles){
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		System.out.println(m.matches()+"---");
		return m.matches();
		
	}
	/**
	 * 防止SQL注入,验证字符类型不能包含特殊字
	 * @param string
	 * @return
	 */
    public static boolean checkNonlicetCharacters(String string) {
        boolean flag = true;
        // 不许出现单引号
        if (string != null && string.indexOf("'") > 0) {
            flag = false;
        }

        return flag;
    }
    /**
     * 防止SQL注入
     * @param string
     * @return
     */
    public static String getValidSQLPara(String string) {
        if (string == null || string.length() == 0) {
            return string;
        }
        string=string.replaceAll("'", "");
        string=string.replaceAll(" ", "");
        return string;
    }
    public static String getValues(HttpServletRequest request,String values) {
    	String reString=request.getParameter(values);
    	if(reString!=null && reString.length()> 0){
    		reString=getValidSQLPara(reString);
    	}else{
    		reString="";
    	}
    	/*if(null != request.getParameter(values) && request.getParameter(values).toString()!="")
		{
    		reString=getValidSQLPara(request.getParameter(values));
		}*/
		return reString;
	}
    /**
     * 根据银行编号返回中文
     * @param strNo
     * @return
     */
    public static String getBankName(Integer strNo) {
    	String retBnakName="";
    	switch(strNo)
    	{
    		case 100:
    			retBnakName="中国邮政储蓄";
    			break;
    		case 102:
    			retBnakName="工商银行";
    			break;
    		case 103:
    			retBnakName="农业银行";
    			break;
    		case 104:
    			retBnakName="中国银行";
    			break;
    		case 105:
    			retBnakName="建设银行";
    			break;
    		case 301:
    			retBnakName="交通银行";
    			break;
    		case 302:
    			retBnakName="中信银行";
    			break;
    		case 6303:
    			retBnakName="中国光大银行";
    			break;
    		case 304:
    			retBnakName="华夏银行";
    			break;
    		case 305:
    			retBnakName="民生银行";
    			break;
    		case 306:
    			retBnakName="广发银行股份有限公司";
    			break;
    		case 308:
    			retBnakName="招商银行";
    			break;
    		case 309:
    			retBnakName="兴业银行";
    			break;
    		case 310:
    			retBnakName="浦东发展银行";
    			break;
    		case 510:
    			retBnakName="平安银行股份有限公司";
    			break;    			
    	}
    	return retBnakName;
	}
}
