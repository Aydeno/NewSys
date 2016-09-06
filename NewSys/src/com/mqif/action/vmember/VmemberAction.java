package com.mqif.action.vmember;

import java.sql.Timestamp;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.mqif.entitys.member.VMemberInfo;
import com.mqif.service.VMemberManager;
import com.tools.Common;
import com.tools.Mqif;
import com.tools.MqifServerActionSupport;

@Namespace("/vmember")
public class VmemberAction extends MqifServerActionSupport{

	private static final long serialVersionUID = 1L;
	@Autowired
	private VMemberManager vmemberManager;
	private List<JSONObject> jsonlist;
	private Integer p;//当前页码
	private Integer nextpageno;//下一页页码
	private Boolean issurpluspage;//是否还有剩余页
	private VMemberInfo vmemberInfo;
	
	private String id;//当前传入的ID,用于编辑修改
	private String copid;

	@Action(value="list",results={
			@Result(name="golist",location="list.jsp"),
			})
	public String vOrder_list(){
		//取出该商城所有订单
		int pages = 1;
		if(null!=p&&!"".equals(p)&&p>1){
			pages=p;
		}
		jsonlist = vmemberManager.getList(ctx,pages," createtime desc ");
		//是否还有剩余页
		if(vmemberManager.getSurplusPage()>0){
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
	
	/**
	 * 修改
	 */
	@Action(value="edit",results={
			@Result(name="goedit",location="edit.jsp"),
			})
	public String edit(){
		if(Common.isNull(id)){
			returnInfo = Mqif.returnInfo(false,"ID参数有误!");
			return "goedit";
		}
		vmemberInfo=vmemberManager.get(id);
		returnInfo = Mqif.returnInfo();
		return "goedit";
	}
	
	
	/**
	 * 保存
	 */
	@Action(value="save",results={
			@Result(name="goedit",location="edit.jsp"),
			})
	public String save(){
		if(!Common.isNull(id)){
			VMemberInfo vmInfo = vmemberManager.get(id);
			vmInfo.setRealname(vmemberInfo.getRealname());
			vmInfo.setMobile(vmemberInfo.getMobile());
			//开始保存
			vmemberManager.save(vmInfo);
			returnInfo = Mqif.returnInfo(
					true,
					"保存成功！",
					new Object[]{
							new String[]{"继续编辑","vmember/edit.action?id="+id},
							new String[]{"返回列表","vmember/list.action"}
					}
					);
		}else{
			Boolean rep = vmemberManager.getCheckMobile(vmemberInfo.getMobile());
			if(rep){
				returnInfo = Mqif.returnInfo(false,"该手机已经被绑定!请先解除绑定");
				return "goedit";
			}
			
//			vmemberInfo.setMem_pwd(Common.getMD5(vmemberInfo.getMem_pwd().toString()));//设置密码
			//设置会员类型为：员工卡
			vmemberInfo.setSex(0);
			vmemberInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
			vmemberManager.save(vmemberInfo);
			
			returnInfo = Mqif.returnInfo(
					true,
					"保存成功！",
					new Object[]{
							new String[]{"继续添加","vmember/add.action"},
							new String[]{"返回列表","vmember/list.action"}
					});
		}

		return "goedit";
	}
	@Action(value="add",results={
			@Result(name="goedit",location="edit.jsp"),
			})
	public String user_add(){
		vmemberInfo = new VMemberInfo();
		returnInfo = Mqif.returnInfo();
		return "goedit";
	}
	
	private String strlist;
	private String operation;//动作名称
	
	public List<JSONObject> getJsonlist() {
		return jsonlist;
	}
	public void setJsonlist(List<JSONObject> jsonlist) {
		this.jsonlist = jsonlist;
	}
	
	public VMemberInfo getVmemberInfo() {
		return vmemberInfo;
	}

	public String getId() {
		return id;
	}

	public void setVmemberInfo(VMemberInfo vmemberInfo) {
		this.vmemberInfo = vmemberInfo;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getP() {return p;}
	public void setP(Integer p) {this.p = p;}
	public Integer getNextpageno() {return nextpageno;}
	public void setNextpageno(Integer nextpageno) {this.nextpageno = nextpageno;}
	public Boolean getIssurpluspage() {return issurpluspage;}
	public void setIssurpluspage(Boolean issurpluspage) {this.issurpluspage = issurpluspage;}
	public String getStrlist() {
		return strlist;
	}
	public String getOperation() {
		return operation;
	}
	public void setStrlist(String strlist) {
		this.strlist = strlist;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getCopid() {
		return copid;
	}
	public void setCopid(String copid) {
		this.copid = copid;
	}

}
