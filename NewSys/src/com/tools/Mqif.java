package com.tools;

import org.json.JSONObject;

public class Mqif {
	
	/**
	 * status 当前操作状态 成功=true,失败=false  *必选
	 * message 提示信息 	*必选
	 * url = 强制跳转网址	 可选
	 * 
	 * button[] = 按钮组｛按钮文字,按钮跳转｝
	 * 注：可以多个，如：{{返回列表,list.jsp},{继续编辑,edit.jsp?id=1},{继续新增,add.jsp}}
	 * 
	 * button[]=null时，只有一个确认按钮
	 */
	
	public static JSONObject returnInfo(){
		JSONObject json = new JSONObject();
		json.put("status", true);
		return json;
	}
	
	public static JSONObject returnInfo(boolean status,String message){
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("message", message);
		return json;
	}
	
	public static JSONObject returnInfo(boolean status,String message,String url){
		JSONObject buttonjson = new JSONObject();
		buttonjson.put("确定","OK");
		JSONObject urljson = new JSONObject();
		urljson.put("OK", url);
		
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("message", message);
		json.put("button", buttonjson);
		json.put("url", urljson);
		return json;
	}
	
	public static JSONObject returnInfo(boolean status,String message,Object[] button){
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("message", message);
		
		JSONObject buttonjson = new JSONObject();
		JSONObject urljson = new JSONObject();
		for (int i = 0; i < button.length; i++) {
			String[] tmp = (String[])button[i];
			buttonjson.put(tmp[0], "mqif"+i);
			urljson.put("mqif"+i, tmp[1]);
		}
		json.put("button", buttonjson);
		json.put("url", urljson);
		
		
		return json;
	}
	
	
}
