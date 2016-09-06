package com.mqif.action.login;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.tools.Constants;
import com.tools.Ctx;
import com.mqif.entitys.user.UserInfo;
import com.mqif.service.UserInfoManager;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	
	//自动注解 EntityManager 的继承类
	@Autowired
	private UserInfoManager userinfomanager;

	@Action(value="login",results={
			@Result(name="gologin",location="login.jsp"),
			@Result(name="gomain",location="/mian.action",type="redirect")
	})
	public String login() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Ctx ctx = null;
		if(null==session.getAttribute("Ctx")){
			System.out.println("CTX空！");
			ctx = new Ctx();
			session.setAttribute("Ctx", ctx);
		}else{
			ctx = (Ctx)session.getAttribute("Ctx");
		}
		if(ctx.getLoginstatus()){
			return "gomain";
		}
		if(null!=session.getAttribute("ErrorQty")){
			Integer errorqty = (Integer)session.getAttribute("ErrorQty");
			if(errorqty>2){
				showcode=true;
			}else{
				showcode=false;
			}
		}else{
			showcode=false;
		}
		return "gologin";
	}

	private JSONObject setLoginCode(JSONObject json,int qty) {
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setMaxInactiveInterval(120);//改为2分钟，
		Object validateCode = session.getAttribute("validateCode");
		if(null!=validateCode&&!"".equals(validateCode)){
			showcode=true;
			json.put("jsshowcode", true);
			session.setAttribute("ErrorQty", 99);
			return json;
		}
		if(null!=session.getAttribute("ErrorQty")){
			Integer errorqty = (Integer)session.getAttribute("ErrorQty");
			session.setAttribute("ErrorQty", errorqty+qty);
			if(errorqty>2){
				showcode=true;
				json.put("jsshowcode", true);
				if(null==validateCode||"".equals(validateCode)){
					session.setAttribute("validateCode","mqif");
				}
			}else{
				showcode=false;
				json.put("jsshowcode", false);
			}
		}else{
			showcode=false;
			json.put("jsshowcode", false);
			session.setAttribute("ErrorQty", 1);
		}
		return json;
	}

	@Action(value="gologin")
	public String gologin() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		JSONObject json = new JSONObject();
		json.put("Code", false);
		if (null == password || "".equals(password) || null == username || "".equals(username)) {
			json.put("status", false);
			json.put("message", "用户名密码不能为空！");
			json=setLoginCode(json, 0);
			setResponseJSON(json);
			return NONE;
		}
		Object validateCode = session.getAttribute("validateCode");
		if(null!=validateCode){
			json=setLoginCode(json,99);
			if (null == code || "".equals(code)) {
				json.put("status", false);
				json.put("message", "验证码不能为空！");
				json=setLoginCode(json, 0);
				setResponseJSON(json);
				return NONE;
			}
			if(!code.toUpperCase().equals(validateCode.toString().toUpperCase())){
				json.put("status", false);
				json.put("message", "验证码错误！");
				json=setLoginCode(json, 0);
				setResponseJSON(json);
				return NONE;
			}
		}
		UserInfo userInfo = userinfomanager.getUserinfo(username);
		if(null==userInfo||null==userInfo.getNumber()){
			json.put("status", false);
			json.put("message", "该用户不存在！");
			json=setLoginCode(json, 1);
			setResponseJSON(json);
			return NONE;
		}
		if(!userInfo.getPassword().equals(password)){
			json.put("status", false);
			json.put("message", "用户密码错误！");
			json=setLoginCode(json, 1);
			setResponseJSON(json);
			return NONE;
		}
		if(userInfo.getStatus()!=1){
			json.put("status", false);
			json.put("message", "您的帐号状态（"+Constants.USER_STATUS_TYPE.get(userInfo.getStatus().toString())+"）禁止登陆！");
			json=setLoginCode(json, 1);
			setResponseJSON(json);
			return NONE;
		}
		//自动级联（联查信息）
		Ctx ctx = new Ctx();
		userInfo = userinfomanager.get(userInfo.getUuid());
		ctx.setUserinfo(userInfo);
		ctx.setLoginstatus(true);
		//设置session
		//session.invalidate();
		session.setMaxInactiveInterval(-1);
		session.setAttribute("Ctx", ctx);
		json.put("status", true);
		json.put("message", "登陆成功！");
		setResponseJSON(json);
		return NONE;
	}
	
	private void setResponseJSON(JSONObject responseJSON){
		ServletActionContext.getResponse().setContentType("application/json");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		try {
			ServletActionContext.getResponse().getWriter().println(responseJSON.toString());
			System.out.println(responseJSON.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Boolean showcode;
	public Boolean getShowcode() {return showcode;}
	public void setShowcode(Boolean s) {showcode=s;}

	private String code;
	public String getCode() {return code;}
	public void setCode(String code) {this.code=code;}

	private String username;
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username=username;}

	private String password;
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password=password;}

}
