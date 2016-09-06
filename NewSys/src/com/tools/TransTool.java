package com.tools;

public class TransTool {
	//用户状态转换
	public static String transUserState(String status){
		if(status.equals("0")){
			return Constants.USER_STATUS_TYPE.get("0");
		}else if(status.equals("1")){
			return Constants.USER_STATUS_TYPE.get("1");
		}else if(status.equals("-1")){
			return Constants.USER_STATUS_TYPE.get("-1");
		}else{
			return null;
		}
	}
}
