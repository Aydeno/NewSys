package com.mqif.action.mian;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.tools.Mqif;
import com.tools.MqifServerActionSupport;

@Namespace("/")
public class HomeAction extends MqifServerActionSupport {
	private static final long serialVersionUID = 1L;
	
	@Action(value="home",results={
			@Result(name="gohome",location="home.jsp")
	})
	public String mian() {
		returnInfo = Mqif.returnInfo();
		return "gohome";
	}

}
