package com.mqif.action.api;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.mqif.entitys.cat.NewsCatnfo;
import com.mqif.entitys.member.VMemberInfo;
import com.mqif.service.NewsCatInfoManager;
import com.mqif.service.VMemberManager;
import com.opensymphony.xwork2.ActionSupport;
import com.tencent.common.MD5;
import com.tools.BarCode;
import com.tools.Common;


@Namespace("/api")
public class ApiAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	protected HttpServletRequest req;
	protected HttpServletResponse res;
	
	private static String signKey="G6Tq3vyQNZEKKk3Yw6lrLOOL0ArwnfOr";
	
	@Autowired
	VMemberManager vmemberManager;
	@Autowired
	NewsCatInfoManager newsCatInfoManager;
	
	public ApiAction() {
		super();
		this.req = ServletActionContext.getRequest();
		this.res = ServletActionContext.getResponse();
	}
	
	protected String mobile;
	public String getMobile() {return mobile;}
	public void setMobile(String mobile) {this.mobile = mobile;}
	
	protected String sign;
	public String getSign() {return sign;}
	public void setSign(String sign) {this.sign = sign;}
	
	protected void setResponseJSON(net.sf.json.JSONObject responseJSON){
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		try {
			res.getWriter().println(responseJSON.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String barcode;
	public String getBarcode() {return barcode;}
	public void setBarcode(String barcode) {this.barcode = barcode;}
	
	private String url;
	public String getUrl() {return url;}
	public void setUrl(String url) {this.url = url;}
	private String code;
	public String getCode() {return code;}
	public void setCode(String code) {this.code = code;}

	
	private ByteArrayInputStream inputstream;
	public ByteArrayInputStream getInputstream(){return inputstream;}
	public void setInputstream(ByteArrayInputStream inputstream){this.inputstream = inputstream;}
	
	@Action(value="barcode",results={
			@Result(name="barcode",type="stream",params={
					"contentType","image/gif",
					"inputName","inputstream",
					"bufferSize","4096"}),
	})
	public String barcode() {
		BarCode barcode = new BarCode(code,"CODE128");
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageOutputStream imageOut;
		try {
			imageOut = ImageIO.createImageOutputStream(output);
			ImageIO.write(barcode.getIamge(), "gif", imageOut);
			imageOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		inputstream = new ByteArrayInputStream(output.toByteArray());
		return "barcode";
	}
	/**
	 * 用户禁止授权提示页面
	 * @return
	 */
//	@Action(value="disagree",results={
//			@Result(name="disagree",location="../wx/disagree.jsp")
//			})
//	public String disagree(){
//		return "disagree";
//	}
	
	
	
	@Action("userregist")
	public String userregist(){
		net.sf.json.JSONObject json = new net.sf.json.JSONObject();
		net.sf.json.JSONObject repjson = new net.sf.json.JSONObject();
		String username = null;
		String password = null;
		
		
		String reqContent = null;
		InputStream in;
		BufferedReader rd;
		try {
			in = req.getInputStream();
			rd = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");// 直线分隔符
			while (tempLine != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
				tempLine = rd.readLine();
			}
			reqContent = tempStr.toString();
			rd.close();
			in.close();
			
		} catch (IOException e) {
			System.out.println("网络故障:"+e);
			e.printStackTrace();
		}
		
		if(Common.isNull(reqContent)){
			repjson.put("return_code", "FAIL");
			repjson.put("return_msg", "缺少请求内容");
			setResponseJSON(repjson);
			return NONE;
		}
		
		net.sf.json.JSONObject reqJson = net.sf.json.JSONObject.fromObject(reqContent);
		
		
		if(!reqJson.containsKey("username")){//用户名
			repjson.put("return_code", "FAIL");
			repjson.put("return_msg", "缺少username参数");
			setResponseJSON(repjson);
			return NONE;
		}else{
			username = reqJson.getString("username");
		}
		
		if(!reqJson.containsKey("password")){//密码
			repjson.put("return_code", "FAIL");
			repjson.put("return_msg", "缺少password参数");
			setResponseJSON(repjson);
			return NONE;
		}else{
			password = reqJson.getString("password");
		}
		
		if(Common.isNull(username)){
			json.put("return_code", "FAIL");
			json.put("return_msg", "请输入用户名！");
			setResponseJSON(json);
			return NONE;
		}
		if(Common.isNull(password)){
			json.put("return_code", "FAIL");
			json.put("return_msg", "请输入登陆密码！");
			setResponseJSON(json);
			return NONE;
		}
		String uuid = vmemberManager.getVMemberUUID(username);
		if(null!=uuid){
			json.put("return_code", "FAIL");
			json.put("return_msg", "该用户名已经存在，请更换用户名");
			setResponseJSON(json);
			return NONE;
		}
		
		VMemberInfo minfo = new VMemberInfo();
		minfo.setUsername(username);
		minfo.setPassword(MD5.MD5Encode(password));
		minfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
		minfo.setLastupdatetime(System.currentTimeMillis());
		minfo.setSex(0);
		vmemberManager.save(minfo);
		
		net.sf.json.JSONObject userJson = new net.sf.json.JSONObject();
		userJson.put("uuid", minfo.getUuid());
		userJson.put("username", minfo.getUsername());
		userJson.put("sex", minfo.getSex());
		userJson.put("realname", minfo.getRealname());
		
		repjson.put("return_code", "SUCCESS");
		repjson.put("return_msg","用户注册成功");
		repjson.put("user_info", userJson);
		setResponseJSON(repjson);
		
		return NONE;
	}
	
	/**
	 * user用户登陆接口
	 * @return
	 */
	@Action("userlogin")
	public String userlogin(){
		
		net.sf.json.JSONObject repjson = new net.sf.json.JSONObject();
		String reqContent = null;
		InputStream in;
		BufferedReader rd;
		try {
			in = req.getInputStream();
			rd = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");// 直线分隔符
			while (tempLine != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
				tempLine = rd.readLine();
			}
			reqContent = tempStr.toString();
			rd.close();
			in.close();
			
		} catch (IOException e) {
			System.out.println("网络故障:"+e);
			e.printStackTrace();
		}
		
		if(Common.isNull(reqContent)){
			repjson.put("return_code", "FAIL");
			repjson.put("return_msg", "缺少请求内容");
			setResponseJSON(repjson);
			return NONE;
		}
		
		net.sf.json.JSONObject reqJson = net.sf.json.JSONObject.fromObject(reqContent);
		String sign = null;
		String username = null;
		String password = null;
		
		if(!reqJson.containsKey("sign")){//签名不存在
			repjson.put("return_code", "FAIL");
			repjson.put("return_msg", "缺少sign参数");
			setResponseJSON(repjson);
			return NONE;
		}else{
			sign = reqJson.getString("sign");
		}
		
		if(!reqJson.containsKey("username")){//登陆用户名不存在
			repjson.put("return_code", "FAIL");
			repjson.put("return_msg", "缺少username参数");
			setResponseJSON(repjson);
			return NONE;
		}else{
			username = reqJson.getString("username");
		}
		
		if(!reqJson.containsKey("password")){//登陆密码不存在
			repjson.put("return_code", "FAIL");
			repjson.put("return_msg", "缺少password参数");
			setResponseJSON(repjson);
			return NONE;
		}else{
			password = reqJson.getString("password");
		}
		
		
		String localsign = MD5.MD5Encode(username+password+signKey);
		System.out.println("login localsign:"+localsign);
		if(!localsign.equalsIgnoreCase(sign)){
			repjson.put("return_code", "FAIL");
			repjson.put("return_msg", "签名错误");
			setResponseJSON(repjson);
			return NONE;
		}
		
		VMemberInfo minfo = vmemberManager.getVMemberInfo(username);
		
		if(null==minfo){
			repjson.put("return_code", "FAIL");
			repjson.put("return_msg", username+"用户不存在");
			setResponseJSON(repjson);
			return NONE;
		}
		
		password = MD5.MD5Encode(password);
		if(!password.equals(minfo.getPassword())){
			repjson.put("return_code", "FAIL");
			repjson.put("return_msg", username+"用户密码错误");
			setResponseJSON(repjson);
			return NONE;
			
		}
		
		minfo.setLastupdatetime(System.currentTimeMillis());
		vmemberManager.save(minfo);//保存用户最后登陆时间
		
		net.sf.json.JSONObject userJson = new net.sf.json.JSONObject();
		userJson.put("uuid", minfo.getUuid());
		userJson.put("username", minfo.getUsername());
		userJson.put("sex", minfo.getSex());
		userJson.put("realname", minfo.getRealname());
		
		repjson.put("return_code", "SUCCESS");
		repjson.put("return_msg","用户登陆成功");
		repjson.put("user_info", userJson);
		setResponseJSON(repjson);
		
		return NONE;
	}
	
	
	/**
	 * 获取新闻分类
	 * @return
	 */
	@Action("catlist")
	public String catlist(){
		net.sf.json.JSONObject repjson = new net.sf.json.JSONObject();
		net.sf.json.JSONArray js = newsCatInfoManager.allNewsCatJsonArray();
		repjson.put("return_code", "SUCCESS");
		repjson.put("return_msg","查询成功");
		repjson.put("newscat_js", js);
		setResponseJSON(repjson);
		
		return NONE;
	}
	
	@Action("catlistinfo")
	public String catlistinfo(){
		
		String channel  = req.getParameter("channel");//渠道
		net.sf.json.JSONObject repjson = new net.sf.json.JSONObject();
		String tmpchannel = newsCatInfoManager.checkIsExist(channel);;
		if(Common.isNull(tmpchannel)){
			tmpchannel = "news_toutiao";
		}
		String getMsg = Common.doGet(NewsURLTools.SINA_NEWS_API+tmpchannel, null);
		net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(getMsg);
		
		repjson.put("return_code", "SUCCESS");
		repjson.put("return_msg","查询成功");
		repjson.put("newsinfo", json);
		setResponseJSON(repjson);
		
		return NONE;
	}
	@Action("saveNewsCatInfo")
	public String saveNewsCatInfo(){
		NewsCatnfo catInfo = new NewsCatnfo();
		catInfo.setEn_name("Aydneo11112222");
		catInfo.setZh_name("黄解决22");
		catInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
		catInfo.setUuid("402881ee56d703f50156d7066f160001");
		newsCatInfoManager.save(catInfo);
		
		System.out.println("uuid:"+catInfo.getUuid());
		return NONE;
	}
}
