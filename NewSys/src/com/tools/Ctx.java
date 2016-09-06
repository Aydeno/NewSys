package com.tools;

import com.mqif.entitys.user.UserInfo;



public class Ctx {
	private UserInfo userinfo;
	private boolean loginstatus;
	
	public UserInfo getUserinfo(){return userinfo;}
	public boolean getLoginstatus(){return loginstatus;}
	
	public void setUserinfo(UserInfo userinfo){this.userinfo=userinfo;}
	public void setLoginstatus(boolean loginstatus){this.loginstatus=loginstatus;}

	public Ctx(){
		userinfo=null;
		loginstatus=false;
	}
}