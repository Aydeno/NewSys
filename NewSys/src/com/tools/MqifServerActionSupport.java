package com.tools;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("cruddefault")
@InterceptorRefs(value={@InterceptorRef("crudStack")})
public class MqifServerActionSupport extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	protected  HttpServletRequest req;
	protected  HttpServletResponse res;
	protected  ServletContext application;
	protected  HttpSession session;
	
	public MqifServerActionSupport() {
		super();
		req = ServletActionContext.getRequest();
		res =ServletActionContext.getResponse();
		application = ServletActionContext.getServletContext();
		session = ServletActionContext.getRequest().getSession();
		
		if(null!=session.getAttribute("Ctx")){
			this.ctx=(Ctx)session.getAttribute("Ctx");
		}else{
			this.ctx=null;
		}
		returnInfo = Mqif.returnInfo();
	}
	
	protected  void setResponseJSON(JSONArray responseJSON){
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		try {
			res.getWriter().println(responseJSON.toString());
			System.out.println(responseJSON.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected  void setResponseJSON(JSONObject responseJSON){
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		try {
			res.getWriter().println(responseJSON.toString());
			System.out.println(responseJSON.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected  void setResponseJSON(String jsonStr){
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		try {
			res.getWriter().println(jsonStr);
			System.out.println(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * CTX
	 */
	protected Ctx ctx;
	public Ctx getCtx() {return ctx;}
	public void setCtx(Ctx ctx){
		this.ctx=ctx;
		session.setAttribute("Ctx",ctx);
	}
	
	/**
	 * 返回的公共状态与提示信息
	 */
	protected JSONObject returnInfo;
	public JSONObject getReturnInfo(){return returnInfo;}
	public void setReturnInfo(JSONObject returnInfo){this.returnInfo=returnInfo;}
	

	

	/**
	 * 清除Session
	 */
	public  void clearSession() {
		session.setAttribute("Ctx",null);
		session.setMaxInactiveInterval(0);//退出清空
	}


	/**
	 * 获得HttpSession的简化函数.
	 */
	public  HttpSession getSession(boolean isNew) {
		return ServletActionContext.getRequest().getSession(isNew);
	}

	/**
	 * 获得HttpSession中Attribute的简化函数.
	 */
	public  Object getSessionAttribute(String name) {
		HttpSession session = getSession(false);
		return (session != null ? session.getAttribute(name) : null);
	}

	/**
	 * 设置HttpRequest中Attribute的简化函数.
	 */
	public  void setRequestAttribute(String key, Object value) {
		req.setAttribute(key, value);
	}
	
	/**
	 * 获得getRealPath
	 */
	public  String getRealPath(String folderPath) {
		return ServletActionContext.getServletContext().getRealPath(folderPath);
	}
	
	
	
	
	
}
