package com.mqif.action.mian;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import com.tools.MqifServerActionSupport;

@Namespace("/")
public class MianAction extends MqifServerActionSupport {
	private static final long serialVersionUID = 1L;
	
	//菜单字符串内容
	private String memhtml;
	public String getMemhtml() {return memhtml;}
	public void setMemhtml(String memhtml) {this.memhtml = memhtml;}

	@Action(value = "mian", results = { @Result(name = "gomain", location = "mian.jsp") })
	public String mian() {
		return "gomain";
	}

}
