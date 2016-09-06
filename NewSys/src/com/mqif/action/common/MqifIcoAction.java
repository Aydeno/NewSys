package com.mqif.action.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import com.tools.MqifServerActionSupport;

@Namespace("/common")
public class MqifIcoAction extends MqifServerActionSupport {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 系统图标列表
	 */
	private List<String> iconlist;
	public List<String> getIconlist() {return iconlist;}
	public void setIconlist(List<String> iconlist) {this.iconlist = iconlist;}
	

	@Action(value="mqif_ico",results={
			@Result(name="go",location="mqif_ico.jsp")
	})
	public String mqif_ico() {
		iconlist = new ArrayList<String>();
		String path = "image/client/icon";
		File dir = new File(req.getServletContext().getRealPath("/"+path));
		File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile() && file.toString().endsWith(".png")) {
                iconlist.add(path+"/"+file.getName());
            }
        }
		return "go";
	}
	
	@Action(value="mqif_upload",results={
			@Result(name="go",location="mqif_upload.jsp")
	})
	public String mqif_upload() {
		return "go";
	}
}
