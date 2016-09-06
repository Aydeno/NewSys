package com.mqif.action.login;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tools.Ctx;

public class LoginInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	
	public String intercept(ActionInvocation invocation) throws Exception {	
		if(null!=ServletActionContext.getRequest().getSession().getAttribute("Ctx")){
			Ctx ctx = (Ctx)ServletActionContext.getRequest().getSession().getAttribute("Ctx");
			if(ctx.getLoginstatus()){
				return invocation.invoke();
			}else{
				return "is_not_login";
			}
		}else{
			return "is_not_login";
		}
	}
}
