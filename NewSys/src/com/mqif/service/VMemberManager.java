package com.mqif.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.mqif.entitys.member.VMemberInfo;
import com.mqif.template.services.EntityManager;
import com.tools.Common;
import com.tools.Constants;
import com.tools.Ctx;
import com.tools.PublicDateUtils;

@Service
public class VMemberManager extends EntityManager<VMemberInfo,String>{
	private List<JSONObject> jsonlist;
	private JSONObject json;
	private Object[] obj;
	private List<Object[]> tempList;
	private SQLQuery query;


	/**
	 * 客户端查询会员是否存在
	 * @param vshopid
	 * @param wechatid
	 * @return
	 */
	public String getVMemberUUID(String username){
		StringBuffer sql = new StringBuffer("select uuid from vmember where username=?");
		query = entityDao.getSession().createSQLQuery(sql.toString());
		query.setParameter(0, username);
		List<String> obj = query.list();
		if(obj.size()>0){
			if(!Common.isNull(obj.get(0))){
				return obj.get(0);
			}
		}
		return null;
	}

	/**
	 * 客户端查询会员是否存在
	 * @param vshopid
	 * @param wechatid
	 * @return
	 */
	public VMemberInfo getVMemberInfo(String username){
		String uuid = null;
		StringBuffer sql = new StringBuffer("select uuid from vmember where username=? or mobile = ?");
		query = entityDao.getSession().createSQLQuery(sql.toString());
		query.setParameter(0, username);
		query.setParameter(1, username);
		List tempList = query.list();
		for(int i=0; i<tempList.size(); i++){
			uuid = tempList.get(i).toString();
		}
		if(!Common.isNull(uuid)){
			return this.get(uuid);
		}else{
			return null;
		}
	}
	
	/**
	 * 接口查询会员是否存在
	 * @param mobile
	 * @return
	 */
	public Boolean getCheckMobile(String mobile){
		StringBuffer sql = new StringBuffer("select count(uuid) from vmember where mobile=?");
		query = entityDao.getSession().createSQLQuery(sql.toString());
		query.setParameter(0, mobile);
		if(Integer.parseInt(query.list().get(0).toString())>0){
			return true;
		}
		return false;
	}

	/**
	 * 当前页之后,剩余几页
	 */
	private Integer surplusPage=0;
	public Integer getSurplusPage(){
		return surplusPage;
	}

	public List<JSONObject> getList(Ctx ctx){
		load(ctx,1,null);
		return jsonlist;
	}

	public List<JSONObject> getList(Ctx ctx,Integer page){
		load(ctx,page,null);
		return jsonlist;
	}

	public List<JSONObject> getList(Ctx ctx,Integer page,String orderByField){
		load(ctx,page,orderByField);
		return jsonlist;
	}

	/**
	 * pageNo 当前页码
	 * orderByField={id desc,time asc}
	 */
	private void load(Ctx ctx,Integer pageNo,String orderByField){
		String tableNameStr = "vmember";
		if(null!=orderByField&&!"".equals(orderByField)){
			orderByField=" order by "+orderByField;
		}else{
			orderByField="";
		}
		if(pageNo<1){
			pageNo=1;
		}

		//统计总记录数
		StringBuffer sql = new StringBuffer("select count(uuid) from "+tableNameStr+" ");

		query = entityDao.getSession().createSQLQuery(sql.toString());
		Integer countSize = Integer.parseInt(query.list().get(0).toString());
		Integer countPageSize = (int)Math.ceil(((double)countSize)/Constants.LISTPAGEQTY);//计算总页数
		if(countPageSize>1&&pageNo>countPageSize){//当前页大于总页数时,取最后一页
			pageNo=countPageSize;
		}

		//记录数起	每页记录数 * 批次页数
		int start_limit = (pageNo*Constants.LISTPAGEQTY)-Constants.LISTPAGEQTY;
		//计算剩余页数
		surplusPage=countPageSize-pageNo;

		//开始序号index
		int indexNo = start_limit+1;
		//查询
		jsonlist = new ArrayList<JSONObject>();

		sql = new StringBuffer("select uuid,username,sex,createTime from vmember "+orderByField+" limit "+start_limit+","+Constants.LISTPAGEQTY+"");

		query = entityDao.getSession().createSQLQuery(sql.toString());
		tempList = query.list();
		for(int i=0; i<tempList.size(); i++){
			obj = (Object[])tempList.get(i);
			json = new JSONObject();
			json.put("index", indexNo++);
			json.put("uuid", obj[0]);
			json.put("username", obj[1]);
			if(Common.isNull(obj[2])){
				json.put("sex","未知");
			}else{
				json.put("sex", Constants.SEX_TYPE.get(obj[2]));
			}
			json.put("createtime",PublicDateUtils.objDateToFormat(obj[3]));
			jsonlist.add(json);
		}
	}
	
	public void removeByUuid(String uuid){
		query = entityDao.getSession().createSQLQuery("delete from vmember where uuid=?");
		query.setParameter(0, uuid);
		query.executeUpdate();
	}
	
}
