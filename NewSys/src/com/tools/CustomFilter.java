package com.tools;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.StrutsRequestWrapper;

public class CustomFilter implements Filter {
	
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String url = request.getRequestURI();
		
		if (null==decideURI(url)) {
			chain.doFilter(req, res);
		}else{
			chain.doFilter(new StrutsRequestWrapper((HttpServletRequest) req), res);
		}
	}

	
	private String decideURI(String url){
		if(url.contains("/common/ueditor.action")){
			return "ueditor";
		}else if(url.contains("/common/upicon.action")){
			return "upico";
		}else{
			return null;
		}
	}
	public void destroy() {
	}
	public void init(FilterConfig arg0) throws ServletException {
	}
}