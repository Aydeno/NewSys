package com.tools;

import java.util.LinkedHashMap;
import java.util.Map;

public class Constants {
	
	/**
	 * 参数设置的配置文件路径
	 */
	public static final Integer  LISTPAGEQTY =150; //页面显示数
	public static final String  SEX_NONE ="0"; //未知
	public static final String  SEX_MAN ="1"; //男
	public static final String  SEX_WOMAN ="2"; //女
	//用户状态
	public static final Map<String,String> USER_STATUS_TYPE=new LinkedHashMap<String,String>();
	public static final Map<Integer,String> SEX_TYPE=new LinkedHashMap<Integer,String>();//sex
	
	//对常量进行静态初始化
	static{
		USER_STATUS_TYPE.put("0", "待审");
		USER_STATUS_TYPE.put("1", "启用");
		USER_STATUS_TYPE.put("-1", "禁用");
		
		SEX_TYPE.put(null, "未知");
		SEX_TYPE.put(1, "男");
		SEX_TYPE.put(2, "女");
	}
}
