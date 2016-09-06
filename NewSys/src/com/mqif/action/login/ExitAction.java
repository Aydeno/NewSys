package com.mqif.action.login;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import com.tools.MqifServerActionSupport;

@Namespace("/")
public class ExitAction extends MqifServerActionSupport {
	private static final long serialVersionUID = 1L;

	@Action(value="exit",results={
			@Result(name="login",location="exit.jsp"),
			})
	public String exit() {
		session.invalidate();
		return "exit";
	}

}
