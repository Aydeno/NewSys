package com.tools;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 * Struts2工具类
 */
public class Struts2Util {

	// -- 取得Request/Response/Session的简化函数 --//
	/**
	 * 写入ctx
	 */
	public static void setCtx(Ctx ctx) {
		getSession().setAttribute("Ctx",ctx);
		getSession().setMaxInactiveInterval(900);//登陆成功后改为15分钟，
	}
	
	/**
	 * 取得ctx
	 */
	public static Ctx getCtx() {
		if(null!=getSession().getAttribute("Ctx")){
			return (Ctx)getSession().getAttribute("Ctx");
		}else{
			return null;
		}
	}
	

	
	/**
	 * 全部Session
	 */
	public static void clearAllSession() {
		getSession().setAttribute("Member",null);
		getSession().setAttribute("Client",null);
		getSession().setMaxInactiveInterval(0);//退出清空
	}

	
	/** 获得HttpSession的简化函数.
	 */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * 获得HttpSession的简化函数.
	 */
	public static HttpSession getSession(boolean isNew) {
		return ServletActionContext.getRequest().getSession(isNew);
	}

	/**
	 * 获得HttpSession中Attribute的简化函数.
	 */
	public static Object getSessionAttribute(String name) {
		HttpSession session = getSession(false);
		return (session != null ? session.getAttribute(name) : null);
	}

	/**
	 * 设置HttpRequest中Attribute的简化函数.
	 */
	public static void setRequestAttribute(String key, Object value) {
		getRequest().setAttribute(key, value);
	}

	/**
	 * 获得HttpRequest的简化函数.
	 */
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获得HttpRequest中Parameter的简化方法.
	 */
	public static String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	/**
	 * 获得HttpResponse的简化函数.
	 */
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	/**
	 * 获得(ServletContext)Application的简化函数.
	 */
	public static ServletContext getApplication() {
		 ServletContext context = ServletActionContext.getServletContext();
		 return context;
	}
	/**
	 * 获得getRealPath
	 */
	public static String getRealPath(String folderPath) {
		return ServletActionContext.getServletContext().getRealPath(folderPath);
	}
	
	
	public static boolean isNullorSpace(Object object) {
		boolean b = false;
		if ("".equals(object) || object == null) {
			b = true;
		}
		return b;

	}
	
}









