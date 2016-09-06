package com.mqif.action.common;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.json.JSONObject;

import com.tools.Common;
import com.tools.MqifServerActionSupport;
import com.tools.Struts2Util;
import com.tools.ueditor.ActionEnter;
import com.tools.ueditor.ConfigManager;
import com.tools.ueditor.define.ActionMap;
import com.tools.ueditor.define.State;
import com.tools.ueditor.upload.MqifBase64Uploader;

@Namespace("/common")
public class UploadAction extends MqifServerActionSupport {
	
	private static final long serialVersionUID = 1L;
	@Action(value="ueditor")
	public String ueditor() throws IOException{
		setResponseJSON(new ActionEnter(req, Struts2Util.getRealPath( "/" )).exec());
		return NONE;
	}
	
	/**
	 * 重新写一个上传
	 * @return
	 * @throws IOException
	 */
	@Action(value="upicon")
	public String upicon() throws IOException{
		JSONObject rejson = new JSONObject();
		String iconimage = req.getParameter("iconimage");
		if(Common.isNull(iconimage)){
			rejson.put("state","上传失败，无法获取图片数据！");
			setResponseJSON(rejson);
			return NONE;
		}
		//判断图片资源格式
		ConfigManager configManager = ConfigManager.getInstance(req,application.getRealPath("/"),req.getContextPath(), req.getRequestURI() );
		Map<String, Object> conf = configManager.getConfig(ActionMap.getType("uploadimage"));
		conf.put("isBase64", true);
		State state = MqifBase64Uploader.save(iconimage,conf);
		setResponseJSON(state.toJSONString());
		return NONE;
	}
}
