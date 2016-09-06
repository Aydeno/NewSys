//package com.mqif.action.vshop;
//
//
//import java.sql.Timestamp;
//import java.util.List;
//
//import org.apache.struts2.convention.annotation.Action;
//import org.apache.struts2.convention.annotation.Namespace;
//import org.apache.struts2.convention.annotation.Result;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.mqif.entitys.vshop.VShopInfo;
//import com.mqif.service.UserInfoManager;
//import com.mqif.service.VShopManager;
//import com.tools.Common;
//import com.tools.Mqif;
//import com.tools.MqifServerActionSupport;
//
//@Namespace("/vshop")
//public class VshopAction extends MqifServerActionSupport {
//	private static final long serialVersionUID = 1L;
//	@Autowired
//	private VShopManager vshopManager;
//	@Autowired
//	UserInfoManager userinfoManager;
//	private List<JSONObject> jsonlist;
//	private VShopInfo vshopInfo;
//
//	private Integer p;//当前页码
//	private Integer nextpageno;//下一页页码
//	private Boolean issurpluspage;//是否还有剩余页
//
//	@Action(value="vshop_list",results={
//			@Result(name="goedit",location="vshop_list.jsp")
//	})
//	public String list(){
//		//取出该商城所有订单
//		int pages = 1;
//		if(null!=p&&!"".equals(p)&&p>1){
//			pages=p;
//		}
//		jsonlist = vshopManager.getList(ctx,pages," sp.number,sp.name ");
//		//是否还有剩余页
//		if(vshopManager.getSurplusPage()>0){
//			issurpluspage = true;
//			nextpageno=pages+1;
//		}else{
//			issurpluspage = false;
//			nextpageno=pages;
//		}
//		returnInfo = Mqif.returnInfo();
//		returnInfo.put("issurpluspage", issurpluspage);
//		returnInfo.put("nextpageno", nextpageno);
//		return "golist";
//	}
//	@Action(value="vshop_add",results={
//			@Result(name="goedit",location="vshop_edit.jsp")
//	})
//	public String vshop_add(){
//		vshopInfo = new VShopInfo();
//		returnInfo = Mqif.returnInfo();
//		return "goedit";
//	}
//	/**
//	 * 修改
//	 */
//	@Action(value="vshop_edit",results={
//			@Result(name="goedit",location="vshop_edit.jsp")
//	})
//	public String vshop_edit(){
//		if(Common.isNull(id)){
//			returnInfo = Mqif.returnInfo(false,"ID参数有误!");
//			return "goedit";
//		}
//		vshopInfo = vshopManager.get(id);
//		returnInfo = Mqif.returnInfo();
//		return "goedit";
//	}
//	/**
//	 * 修改
//	 */
//	@Action(value="edit",results={
//			@Result(name="goedit",location="vshop.jsp")
//	})
//	public String edit(){
//		vshopInfo = vshopManager.get(ctx.getUserinfo().getUserconfiginfo().getDefaultvshopid().getUuid());
//		returnInfo = Mqif.returnInfo();
//		return "goedit";
//	}
//
//
//	/**
//	 * 保存
//	 */
//	@Action(value="vshop_save",results={
//			@Result(name="goedit",location="vshop_edit.jsp")
//	})
//	public String vshop_save(){
//		//校验商城编码是否已经存在
//		Boolean rep = vshopManager.checkNumRep(vshopInfo.getNumber(), id);
//		if(rep){
//			returnInfo = Mqif.returnInfo(false,"商城编码已经存在!");
//			return "goedit";
//		}
//		
//		if(!Common.isNull(id)){
//			VShopInfo vsinfo = vshopManager.get(id);
//
//			vsinfo.setNumber(vshopInfo.getNumber());
//			vsinfo.setName(vshopInfo.getName());
//			vsinfo.setContent(vshopInfo.getContent());
////			vsinfo.setQq(vshopInfo.getQq());
////			vsinfo.setEmail(vshopInfo.getEmail());
//			vsinfo.setMobile(vshopInfo.getMobile());
////			vsinfo.setOrderonnoff(vshopInfo.getOrderonnoff());
////			vsinfo.setVdepotid(vshopInfo.getVdepotid());
////			vsinfo.setOfflinedepotid(vshopInfo.getOfflinedepotid());
//			vsinfo.setAddress(vshopInfo.getAddress());
//			vsinfo.setProvince(vshopInfo.getProvince());
//			vsinfo.setCity(vshopInfo.getCity());
//			vsinfo.setTown(vshopInfo.getTown());
//			vsinfo.setLatlng(vshopInfo.getLatlng().replace(" ", ""));
//			vsinfo.setIcon(vshopInfo.getIcon());
////			vsinfo.setCustomerid(vshopInfo.getCustomerid());
////			vsinfo.setAppkey(vshopInfo.getAppkey());
////			vsinfo.setAppsecret(vshopInfo.getAppsecret());
////			vsinfo.setIswholesalers(vshopInfo.getIswholesalers());
//			//开始保存
//			vshopManager.save(vsinfo);
//			returnInfo = Mqif.returnInfo(
//					true,
//					"保存成功！",
//					new Object[]{
//							new String[]{"继续编辑","vshop/vshop_edit.action?id="+id},
//							new String[]{"返回列表","vshop/vshop_list.action"}
//					}
//					);
//		}else{
//			vshopInfo.setCreatetime(new Timestamp(System.currentTimeMillis()));
//			vshopInfo.setUserinfo(ctx.getUserinfo());
//			vshopInfo.getLatlng().replace(" ", "");
//			vshopManager.save(vshopInfo);
//			returnInfo = Mqif.returnInfo(
//					true,
//					"保存成功！",
//					new Object[]{
//							new String[]{"继续编辑","vshop/vshop_edit.action?id="+id},
//							new String[]{"返回列表","vshop/vshop_list.action"}
//					}
//					);
//		}
//
//		return "goedit";
//	}
//	/**
//	 * 保存
//	 */
//	@Action(value="save",results={
//			@Result(name="goedit",location="vshop.jsp")
//	})
//	public String save(){
//		//校验商城编码是否已经存在
//		Boolean rep = vshopManager.checkNumRep(vshopInfo.getNumber(), id);
//		if(rep){
//			returnInfo = Mqif.returnInfo(false,"商城编码已经存在!");
//			return "goedit";
//		}
//		if(!Common.isNull(id)){
//
//			VShopInfo vsinfo = vshopManager.get(id);
//
//			vsinfo.setNumber(vshopInfo.getNumber());
//			vsinfo.setName(vshopInfo.getName());
//			vsinfo.setContent(vshopInfo.getContent());
//			vsinfo.setProvince(vshopInfo.getProvince());
//			vsinfo.setCity(vshopInfo.getCity());
//			vsinfo.setTown(vshopInfo.getTown());
//			vsinfo.setIcon(vshopInfo.getIcon());
//			//开始保存
//			vshopManager.save(vsinfo);
//			returnInfo = Mqif.returnInfo(
//					true,
//					"保存成功！",
//					new Object[]{
//							new String[]{"确定","vshop/edit.action"}
//					}
//					);
//		}
//
//		return "goedit";
//	}
//
//
//	private String id;//当前传入的ID,用于编辑修改
//	public String getId(){return id;}
//	public void setId(String id){this.id=id;}
//	public VShopInfo getVshopInfo() {
//		return vshopInfo;
//	}
//	public void setVshopInfo(VShopInfo vshopInfo) {
//		this.vshopInfo = vshopInfo;
//	}
//	public List<JSONObject> getJsonlist() {
//		return jsonlist;
//	}
//	public Integer getP() {
//		return p;
//	}
//	public Integer getNextpageno() {
//		return nextpageno;
//	}
//	public Boolean getIssurpluspage() {
//		return issurpluspage;
//	}
//	public void setJsonlist(List<JSONObject> jsonlist) {
//		this.jsonlist = jsonlist;
//	}
//	public void setP(Integer p) {
//		this.p = p;
//	}
//	public void setNextpageno(Integer nextpageno) {
//		this.nextpageno = nextpageno;
//	}
//	public void setIssurpluspage(Boolean issurpluspage) {
//		this.issurpluspage = issurpluspage;
//	}
//
//}
