package com.tools;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.tencent.common.Util;
public abstract class Common {
	public static long updateOrderTime = 0;
	public static void setUpdateOrderTime(long time) {
		updateOrderTime=time;
	}
	public static long updateOutTime = 0;
	public static void setUpdateOutTime(long time) {
		updateOutTime=time;
	}
	public static long updateTimeMillis = 0;
	public static void setUpdateTimeMillis(long time) {
		updateTimeMillis=time;
	}
	
	/**
	 * 获得客户端真实IP
	 */
	public static String getRemortIP(HttpServletRequest request) {
		String realIP = request.getHeader("x-forwarded-for");
		String ip = request.getRemoteAddr();
		if(realIP==null||realIP.length()==0){
			if(ip.equals("0:0:0:0:0:0:0:1%0")){
				return "127.0.0.1";
			}
			return ip;
		}else{
			if(realIP.equals("0:0:0:0:0:0:0:1%0")){
				return "127.0.0.1";
			}
			return realIP;
		}
	}

	/**
	 * 获得一个UUID 
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 统一json格式
	 */
	public static JSONObject mqifStatus(Boolean status,String message){
		JSONObject retjson = new JSONObject();
		retjson.put("status", status);
		retjson.put("message", message);
		return retjson;
	}
	public static JSONObject mqifStatus(Boolean status){
		JSONObject retjson = new JSONObject();
		retjson.put("status", status);
		return retjson;
	}

	/**
	 * 判断字符串是否空
	 */
	public static boolean isNull(Object obj){
		if(null==obj){
			return true;
		}
		if("".equals(obj.toString().trim())){
			return true;
		}
		return false;
	}


	/**
	 * 判断字符串是否数字
	 * ^[-+]?[0-9]+(\.[0-9]+)?$
	 */
	public static boolean isNumber(String str){
		if(null==str||"".equals(str.trim())){
			return false;
		}
		Pattern pattern = Pattern.compile("^[-+]?[0-9]+(\\.[0-9]+)?$");
		Matcher isNum = pattern.matcher(str);
		if(!isNum.matches()) {
			return false;
		}
		return true;
	}
	/**
	 * 判断字符串是否数字:带2位小数
	 * ^([1-9][\\d]{0,7}|0)(\\.[\\d]{1,2})?$
	 */
	public static boolean isAmount(String str){
		if(null==str||"".equals(str.trim())){
			return false;
		}
		Pattern pattern = Pattern.compile("^([1-9][\\d]{0,7}|0)(\\.[\\d]{1,2})?$");
		Matcher isNum = pattern.matcher(str);
		if(!isNum.matches()) {
			return false;
		}
		return true;
	}
	/**
	 * 判断字符串是否正整数
	 */
	public static boolean isInt(String str){
		if(null==str||"".equals(str.trim())){
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if(!isNum.matches()) {
			return false;
		}
		return true;
	}
	/**
	 * 判断字符串是否英文加数字和减号
	 * 只能输入由数字和26个英文字母组成的字符串："^[A-Za-z0-9]+$"。
	 */
	public static boolean isEnNuSub(String str){
		if(null==str||"".equals(str.trim())){
			return false;
		}
		Pattern pattern = Pattern.compile("^[A-Za-z0-9-.]+$");
		Matcher is = pattern.matcher(str);
		if(!is.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断字符串是否英文加数字
	 * 只能输入由数字和26个英文字母组成的字符串："^[A-Za-z0-9]+$"。
	 */
	public static boolean isEnNu(String str){
		if(null==str||"".equals(str.trim())){
			return false;
		}
		Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
		Matcher is = pattern.matcher(str);
		if(!is.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断字符串是中文英文数字
	 */
	public static boolean isCh(String str){
		if(null==str||"".equals(str.trim())){
			return false;
		}
		Pattern pattern = Pattern.compile("^[\u4E00-\u9FA5A-Za-z0-9_]+$");
		Matcher is = pattern.matcher(str);
		if(!is.matches()) {
			return false;
		}
		return true;
	}
	/**
	 * 判断字符串是否是手机号码
	 */
	public static boolean isMobile(String str){
		if(null==str||"".equals(str.trim())){
			return false;
		}
		Pattern pattern = Pattern.compile("^(1[3|4|5|7|8][0-9])\\d{8}$");
		Matcher is = pattern.matcher(str);
		if(!is.matches()) {
			return false;
		}
		return true;
	}
	/**
	 * 判断字符串是否是邮箱
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str){
		if(null==str||"".equals(str.trim())){
			return false;
		}
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$");
		Matcher is = pattern.matcher(str);
		if(!is.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 日期相差天数
	 */
	public static int daysBetween(Date formDate, Date toDate) {
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(formDate);
		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
		aCalendar.setTime(toDate);
		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
		return day1 - day2;
	}
	
	/**
	 * 日期转字符串
	 * sp:yyyyMMddHH24mmss
	 */
	public static String getNowTime(String sdf) {
		SimpleDateFormat sd = new SimpleDateFormat(sdf);
		return sd.format(new Date(System.currentTimeMillis()));
	}
	
	/**
	 * 日期转字符串
	 * sp:yyyyMMddHH24mmss
	 */
	public static String longToStr(long time,String sdf) {
		SimpleDateFormat sd = new SimpleDateFormat(sdf);
		return sd.format(new Date(time));
	}
	/**
	 * 日期转字符串
	 * sp:yyyyMMddHH24mmss
	 */
	public static String dateToStr(Date date,String sdf) {
		SimpleDateFormat sd = new SimpleDateFormat(sdf);
		return sd.format(date);
	}
	/**
	 * 字符串转日期
	 * sp:yyyyMMddHH24mmss
	 */
	public static Date strToDate(String str,String sdf) {
		SimpleDateFormat sd = new SimpleDateFormat(sdf);
		try {
			return sd.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 生成随机数字
	 */
	public static int getRandom(int form, int to) {
		int temp = 0;
		try {
			if (form > to) {
				temp = new Random().nextInt(form - to);
				return temp + to;
			} else {
				temp = new Random().nextInt(to - form);
				return temp + form;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp + form;
	}
	/**
	 * POST
	 * posturl:网址
	 * params:传参
	 * charset:编码
	 */
	public static String doPost(String posturl,Map<String, String> params,String charset){
		return doPost(posturl, params, charset, null);
	}
	/**
	 * POST
	 * posturl:网址
	 * params:传参
	 * charset:编码
	 */
	public static String doPost(String posturl,Map<String, String> params,String charset,String contentType){
		if(null==charset||"".equals(charset)){
			charset="UTF-8";
		}
		String parmsStr = "";
		// 设置请求内容
		Iterator<String> it = params.keySet().iterator();
		String key;
		String value;
		while(it.hasNext()){ 
			key=it.next();
			value=params.get(key);
			parmsStr+="&"+key+"="+value;
		}
		return doPost(posturl, parmsStr, charset,null);
	}
	
	/**
	 * POST
	 * posturl:网址
	 * params:传参
	 * charset:编码
	 */
	public static String doPost(String posturl,String postStr,String charset){
		return doPost(posturl, postStr, charset, null);
	}
	
	/**
	 * POST
	 * posturl:网址
	 * params:传参
	 * charset:编码
	 */
	public static String doPost(String posturl,String postStr,String charset,String contentType){
		System.out.println("发送："+postStr);
		if(isNull(charset)){
			charset="UTF-8";
		}
		if(isNull(contentType)){
			contentType="application/x-www-form-urlencoded";
		}
		StringBuffer sb = new StringBuffer();
		try {
			// 建立连接 
			URL url = new URL(posturl);
			HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();

			// 设置连接属性 
			httpConn.setDoOutput(true);// 使用 URL 连接进行输出 
			httpConn.setDoInput(true);// 使用 URL 连接进行输入 
			httpConn.setUseCaches(false);// 忽略缓存 
			httpConn.setRequestMethod("POST");// 设置URL请求方法
			httpConn.setRequestProperty("Content-Type", contentType);
			httpConn.connect();
			// 建立输出流
			OutputStreamWriter outputStream = new OutputStreamWriter(httpConn.getOutputStream(), charset);
			outputStream.write(postStr);
			outputStream.flush();
			outputStream.close();
			// 获得响应状态 
			int responseCode = httpConn.getResponseCode();
			if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功 
				// 当正确响应时处理数据 
				String readLine;
				BufferedReader responseReader;
				// 处理响应流，必须与服务器响应流输出的编码一致 
				responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),charset));
				while ((readLine = responseReader.readLine()) != null) { 
					sb.append(readLine);
				} 
				responseReader.close();
			}
		} catch (Exception ex) { 
			ex.printStackTrace();
		}
		System.out.println("POST接收:"+sb);
		return sb.toString();
	}

	/**
	 * POST
	 * posturl:网址
	 * params:传参
	 * charset:编码
	 */
	public static String doGet(String geturl,String charset){
		if(null==charset||"".equals(charset)){
			charset="UTF-8";
		}
		StringBuffer sb = new StringBuffer();
		HttpURLConnection httpConn = null;
		try {
			// 建立连接 
			URL url = new URL(geturl);
			httpConn = (HttpURLConnection)url.openConnection();
			httpConn.setUseCaches(false);// 忽略缓存 
			httpConn.connect();
			// 获得响应状态 
			int responseCode = httpConn.getResponseCode();
			if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功 
				// 当正确响应时处理数据 
				String readLine;
				BufferedReader responseReader;
				// 处理响应流，必须与服务器响应流输出的编码一致 
				responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),charset));
				while ((readLine = responseReader.readLine()) != null) { 
					sb.append(readLine);
				} 
				responseReader.close();
			}
			httpConn.disconnect();
		} catch (Exception ex) { 
			ex.printStackTrace();
			if(null!=httpConn){
				httpConn.disconnect();
			}
		}
		return sb.toString();
	}

	/**
	 * 字符串公式计算
	 * @param str
	 * @return
	 */
	public static float count(String str) {
		CountRpn analyer = new CountRpn(str);
		BigDecimal b = new BigDecimal(analyer.getRes());
		float f = b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		return f;
	}
	/**
	 * class对象转换为JSON
	 */
	public static JSONObject classToJSON(Object obj){
		Object info1 = obj;
		Object info2;
		JSONObject json1 = new JSONObject();
		JSONObject json2;
		JSONArray jsonList;
		Field[] fields1 = info1.getClass().getDeclaredFields();
		Field[] fields2;
		Object value1,value2;
		String name1,name2,type1,type2;
		Field field1,field2;
		List<Object> list;
		Set<Object> set;
		for (int i = 0; i < fields1.length; i++) {
			field1 = fields1[i];
			makeAccessible(field1);

			name1 = field1.getName();
			type1 = field1.getGenericType().toString();
			try {
				value1 = field1.get(info1);
				if(value1!=null){
					if(name1.toUpperCase().endsWith("INFO")){
						json2 = new JSONObject();
						info2 = field1.get(info1);
						fields2 = info2.getClass().getDeclaredFields();
						for (int j = 0; j < fields2.length; j++) {
							field2 = fields2[j];
							makeAccessible(field2);
							name2 = field2.getName();
							type2 = field2.getGenericType().toString();
							if(!name2.toUpperCase().endsWith("INFO")&&!name2.toUpperCase().endsWith("LIST")&&!name2.equals("serialVersionUID")){
								Method m = info2.getClass().getMethod("get"+name2.replaceFirst(name2.substring(0, 1),name2.substring(0, 1).toUpperCase()));
								value2 = m.invoke(info2);
								if(value2!=null){
									json2.put(name2,value2);
								}
							}
						}
						json1.put(name1,json2);
					}else if(name1.toUpperCase().endsWith("LIST")){
						if(type1.startsWith("java.util.Set")){
							set = (Set<Object>)value1;
							jsonList = new JSONArray();
							for (Object objtmp:set) {
								json2 = new JSONObject();
								info2 = objtmp;
								fields2 = info2.getClass().getDeclaredFields();
								for (int k = 0; k < fields2.length; k++) {
									field2 = fields2[k];
									makeAccessible(field2);
									name2 = field2.getName();
									type2 = field2.getGenericType().toString();
									if(!name2.toUpperCase().endsWith("INFO")&&!name2.toUpperCase().endsWith("LIST")&&!name2.equals("serialVersionUID")){
										Method m = info2.getClass().getMethod("get"+name2.replaceFirst(name2.substring(0, 1),name2.substring(0, 1).toUpperCase()));
										value2 = m.invoke(info2);
										if(value2!=null){
											json2.put(name2,value2);
										}
									}
								}
								jsonList.put(json2);
							}
							json1.put(name1,jsonList);
						}else if(type1.startsWith("java.util.List")){
							list = (List<Object>)value1;
							jsonList = new JSONArray();
							for (int j = 0; j < list.size(); j++) {
								json2 = new JSONObject();
								info2 = list.get(j);
								fields2 = info2.getClass().getDeclaredFields();
								for (int k = 0; k < fields2.length; k++) {
									field2 = fields2[k];
									makeAccessible(field2);
									name2 = field2.getName();
									type2 = field2.getGenericType().toString();
									if(!name2.toUpperCase().endsWith("INFO")&&!name2.toUpperCase().endsWith("LIST")&&!name2.equals("serialVersionUID")){
										Method m = info2.getClass().getMethod("get"+name2.replaceFirst(name2.substring(0, 1),name2.substring(0, 1).toUpperCase()));
										value2 = m.invoke(info2);
										if(value2!=null){
											json2.put(name2,value2);
										}
									}
								}
								jsonList.put(json2);
							}
							json1.put(name1,jsonList);
						}
					}else{
						json1.put(name1,value1);
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				json1=null;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				json1=null;
			} catch (SecurityException e) {
				e.printStackTrace();
				json1=null;
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				json1=null;
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				json1=null;
			}
		}
		return json1;
	}
	/**
	 * 强制转换fileld可访问.
	 */
	protected static void makeAccessible(final Field field) {
		if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			field.setAccessible(true);
		}
	}


	/**
	 * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
	 * @param lng1
	 * @param lat1
	 * @param lng2
	 * @param lat2
	 * @return
	 */
	public static double getDistance(double lng1, double lat1, double lng2, double lat2){
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
		s = s * 6378137;
		s = Math.round(s * 10000) / 10000;
		return s;
	}
	private static double rad(double d){
		return d * Math.PI / 180;
	}

	
	/**
	 * MAP to XML
	 */
	public static String getMapToXml(Map<String,String> map,Boolean isCDATA) {
		String xml = "";
		// 设置请求内容
		Iterator<String> it = map.keySet().iterator();
		String key;
		String value;
		while(it.hasNext()){ 
			key=it.next();
			value=map.get(key);
			if(isCDATA){
				xml+="<"+key+"><![CDATA["+value+"]]></"+key+">";
			}else{
				xml+="<"+key+">"+value+"</"+key+">";
			}
		}
		return "<xml>"+xml+"</xml>";
	}
	
	/**
	 * XML to MAP
	 */
    public static Map<String,Object> getXmlToMap(String xmlString) {
    	Map<String, Object> map = new HashMap<String, Object>();
        //这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        InputStream is;
        Document document = null;
		try {
			builder = factory.newDocumentBuilder();
			is =  Util.getStringStream(xmlString);
			document = builder.parse(is);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return map;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return map;
		} catch (SAXException e) {
			e.printStackTrace();
			return map;
		} catch (IOException e) {
			e.printStackTrace();
			return map;
		}

        //获取到document里面的全部结点
        NodeList allNodes = document.getFirstChild().getChildNodes();
        Node node;
        int i=0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if(node instanceof Element){
                map.put(node.getNodeName(),node.getTextContent());
            }
            i++;
        }
        return map;
    }
	
	
	/**
	 * MD5
	 */
	public static String getMD5(String str) {
        String resultString = null;
        try {
            resultString = str;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(resultString.getBytes("UTF-8"));
            resultString = byteArrayToHexString(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
	}
	private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }
    /**
     * 生成34位的凭证
     * @return
     */
	public static String getTicket(){
		UUID uud = UUID.randomUUID();
		return uud.toString().toUpperCase();
	}
	
	/**
	 * 获取当前时间戳 
	 * int类型
	 * 单位：秒
	 * @return
	 */
	public static int getCurrentTimeSecond(){
		return (int)(System.currentTimeMillis()/1000);
	}
	
	public static String getURL(HttpServletRequest req){
		return getURL(req, req.getParameterMap(), null);
	}
	public static String getURL(HttpServletRequest req,Map<String, String[]> map){
		return getURL(req, map, null);
	}
	public static String getURL(HttpServletRequest req,String[] exclude_parameters){
		return getURL(req, req.getParameterMap(), exclude_parameters);
	}
	public static String getURL(HttpServletRequest req,Map<String, String[]> map,String[] exclude_parameters){
		String url = req.getRequestURL().toString();
		//待重组
		//url = url.replace(":8080","");
		url = url.replace(".action", ".html");
		boolean iss;
		String qs = "";
		Iterator<Map.Entry<String, String[]>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String[]> entry = it.next();
			for (int i = 0; i < entry.getValue().length; i++) {
				iss = false;
				if(null!=exclude_parameters){
					for (int j = 0; j < exclude_parameters.length; j++) {
						if(entry.getKey().equals(exclude_parameters[j])){
							iss=true;
						}
					}
				}
				if(!iss){
					qs += "&"+entry.getKey()+"="+entry.getValue()[i];
				}
			}
		}
		if(qs.length()>0){
			url += "?"+qs.substring(1);
		}
		System.out.println("重组URL:"+url);
		return url;
	}
	/**
	 * 保留url中的参数
	 * @param req
	 * @param parameters 要保留的参数名数组
	 * @return
	 */
	public static String getRetainURLpama(HttpServletRequest req,String[] parameters){
		String url = req.getRequestURL().toString();
		//待重组
		//url = url.replace(":8080","");
		url = url.replace(".action", ".html");
		boolean iss;
		String qs = "";
		Iterator<Map.Entry<String, String[]>> it = req.getParameterMap().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String[]> entry = it.next();
			for (int i = 0; i < entry.getValue().length; i++) {
				iss = false;
				if(null!=parameters){
					for (int j = 0; j < parameters.length; j++) {
						if(entry.getKey().equals(parameters[j])){
							iss=true;
						}
					}
				}
				if(iss){
					qs += "&"+entry.getKey()+"="+entry.getValue()[i];
				}
			}
		}
		if(qs.length()>0){
			url += "?"+qs.substring(1);
		}
		System.out.println("重组URLddd:"+url);
		return url;
	}
}