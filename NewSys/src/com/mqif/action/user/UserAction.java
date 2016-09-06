package com.mqif.action.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.mqif.entitys.user.UserExInfo;
import com.mqif.entitys.user.UserInfo;
import com.mqif.service.UserExInfoManager;
import com.mqif.service.UserInfoManager;
import com.tools.Common;
import com.tools.Constants;
import com.tools.Mqif;
import com.tools.MqifServerActionSupport;

@Namespace("/user")
public class UserAction  extends MqifServerActionSupport{
	private static final long serialVersionUID = 1L;
	@Autowired
	UserInfoManager userinfoManager;
	@Autowired
	UserExInfoManager userexinfoManager;
	private Integer p;//当前页码
	private String id;//当前传入的ID,用于编辑修改
	private List<JSONObject> jsonlist;
	private Integer nextpageno;//下一页页码
	private Boolean issurpluspage;//是否还有剩余页
	private UserInfo userInfo;
	private String seqlist;
	
	private Map<String,String> stateMap=new HashMap<String, String>();
	private Map<String,String> shopMap=new HashMap<String, String>();

	
	@Action(value="list",results={
			@Result(name="golist",location="list.jsp"),
			})
	public String user_list(){
		int pages = 1;
		if(null!=p&&!"".equals(p)&&p>1){
			pages=p;
		}
		jsonlist = userinfoManager.getList(ctx,pages," us.number,us.lv,uf.realname ");
		//是否还有剩余页
		if(userinfoManager.getSurplusPage()>0){
			issurpluspage = true;
			nextpageno=pages+1;
		}else{
			issurpluspage = false;
			nextpageno=pages;
		}
		returnInfo = Mqif.returnInfo();
		returnInfo.put("issurpluspage", issurpluspage);
		returnInfo.put("nextpageno", nextpageno);
		return "golist";
		
	}

	@Action(value="add",results={
			@Result(name="goedit",location="edit.jsp"),
			})
	public String user_add(){
		
		userInfo = new UserInfo();
		jsonlist=userinfoManager.getList(ctx);
		stateMap = Constants.USER_STATUS_TYPE;
		returnInfo = Mqif.returnInfo();
		return "goedit";
	}
	
	/**
	 * 修改
	 */
	@Action(value="edit",results={
			@Result(name="goedit",location="edit.jsp"),
			})
	public String user_edit(){
		if(Common.isNull(id)){
			returnInfo = Mqif.returnInfo(false,"ID参数有误!");
			return "goedit";
		}
		
		stateMap = Constants.USER_STATUS_TYPE;
		userInfo=userinfoManager.get(id);
		returnInfo = Mqif.returnInfo();
		return "goedit";
	}
	
	
	/**
	 * 保存
	 */
	@Action(value="save",results={
			@Result(name="goedit",location="edit.jsp"),
			})
	public String user_save(){
		if(!Common.isNull(id)){
			UserInfo usinfo = userinfoManager.get(id);
			usinfo.setNumber(userInfo.getNumber());
			usinfo.setStatus(userInfo.getStatus());
			if(!userInfo.getPassword().equals(usinfo.getPassword())){
				usinfo.setPassword(Common.getMD5(userInfo.getPassword().toString()));
			}
			UserExInfo einfo = usinfo.getUserexinfo();
			if(einfo==null){
				einfo = new UserExInfo();
			}
			einfo.setNickname(userInfo.getUserexinfo().getNickname());
			einfo.setRealname(userInfo.getUserexinfo().getRealname());
			einfo.setIdnum(userInfo.getUserexinfo().getIdnum());
			einfo.setBirthday(userInfo.getUserexinfo().getBirthday());
			einfo.setSexenum(userInfo.getUserexinfo().getSexenum());
			einfo.setMobile(userInfo.getUserexinfo().getMobile());
			einfo.setQq(userInfo.getUserexinfo().getQq());
			einfo.setEmail(userInfo.getUserexinfo().getEmail());
			usinfo.setUserexinfo(einfo);
			userinfoManager.save(usinfo);
			
			returnInfo = Mqif.returnInfo(
					true,
					"保存成功！",
					new Object[]{
							new String[]{"继续编辑","user/edit.action?id="+id},
							new String[]{"返回列表","user/list.action"}
					}
					);
		}else{
			//保存前判断
			Boolean rep  = userinfoManager.checkNumRep(userInfo.getNumber(),null);
			if(rep){
				returnInfo = Mqif.returnInfo(false,"该用户名称已经存在，请修改用户名!");
				return "goedit";
			}
			userInfo.setPassword(Common.getMD5(userInfo.getPassword().toString()));
			userInfo.getUserexinfo().setUserid(userInfo);
			userInfo.getUserconfiginfo().setUserid(userInfo);
			userInfo.getUserconfiginfo().setListpage(10);
			userinfoManager.save(userInfo);
			
			returnInfo = Mqif.returnInfo(
					true,
					"保存成功！",
					new Object[]{
							new String[]{"继续添加","user/add.action"},
							new String[]{"返回列表","user/list.action"}
					}
					);
		}

		return "goedit";
	}

	
	/**
	 * 删除
	 */
	@Action(value="remove",results={
			@Result(name="goedit",location="edit.jsp"),
			})
	public String user_remove(){
		if(Common.isNull(id)){
			returnInfo = Mqif.returnInfo(false,"ID参数有误!");
			return "goedit";
		}else{
			if("C29D5C70D6DE11E4813ABFC4959E59E3".equals(id)){
				returnInfo = Mqif.returnInfo(false,"删除失败，admin用户是不能删除的！");
				return "goedit";
			}
			Boolean res = userexinfoManager.removeUE(id);
			if(!res){
				returnInfo = Mqif.returnInfo(false,"删除失败，请重试!");
				return "goedit";
			}
			userinfoManager.delete(id);
			returnInfo = Mqif.returnInfo(true,"删除成功！","user/list.action");
			return "goedit";
		}
	}
	
	
	
	/**
	 * 旧密码
	 */
	private String oldpass;
	public String getOldpass() {return oldpass;}
	public void setOldpass(String oldpass) {this.oldpass = oldpass;}
	/**
	 * 新密码
	 */
	private String newpass;
	public String getNewpass() {return newpass;}
	public void setNewpass(String newpass) {this.newpass = newpass;}
	
	//修改密码
	@Action(value="password")
	public String password() throws IOException{
		if(!ctx.getUserinfo().getPassword().equals(oldpass)){
			JSONObject json = new JSONObject();
			json.put("status", false);
			json.put("message", "旧密码错误,请输入正确的旧密码!");
			setResponseJSON(json);
			return NONE;
		}else{
			userinfoManager.savePass(ctx.getUserinfo().getUuid(), newpass);
			ctx.getUserinfo().setPassword(newpass);
			JSONObject json = new JSONObject();
			json.put("status", true);
			json.put("message", "密码修改成功");
			setResponseJSON(json);
			return NONE;
		}
	}
	
	public Integer getP() {
		return p;
	}
	public String getId() {
		return id;
	}
	public List<JSONObject> getJsonlist() {
		return jsonlist;
	}
	public Integer getNextpageno() {
		return nextpageno;
	}
	public Boolean getIssurpluspage() {
		return issurpluspage;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public String getSeqlist() {
		return seqlist;
	}
	public void setP(Integer p) {
		this.p = p;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setJsonlist(List<JSONObject> jsonlist) {
		this.jsonlist = jsonlist;
	}
	public void setNextpageno(Integer nextpageno) {
		this.nextpageno = nextpageno;
	}
	public void setIssurpluspage(Boolean issurpluspage) {
		this.issurpluspage = issurpluspage;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public void setSeqlist(String seqlist) {
		this.seqlist = seqlist;
	}
	public Map<String, String> getStateMap() {
		return stateMap;
	}
	public void setStateMap(Map<String, String> stateMap) {
		this.stateMap = stateMap;
	}
	public Map<String, String> getShopMap() {
		return shopMap;
	}

	public void setShopMap(Map<String, String> shopMap) {
		this.shopMap = shopMap;
	}
	
	
}
