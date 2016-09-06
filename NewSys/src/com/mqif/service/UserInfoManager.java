package com.mqif.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.mqif.entitys.user.UserInfo;
import com.mqif.template.services.EntityManager;
import com.tools.Constants;
import com.tools.Ctx;

@Service
public class UserInfoManager extends EntityManager<UserInfo,String>{
	@SuppressWarnings("unchecked")
	public UserInfo getUserinfo(String userNum){
		List<Object[]> tempList ;
		UserInfo user = new UserInfo();
		SQLQuery query = entityDao.getSession().createSQLQuery("select uuid,number,password,status,lv from user where number = ? ");
		query.setParameter(0, userNum);
		tempList = query.list();
		for(int i=0; i<tempList.size(); i++){
			Object obj[] = (Object[])tempList.get(i);
			user.setUuid(obj[0].toString());
			user.setNumber(obj[1].toString());
			user.setPassword(obj[2].toString());
			user.setStatus(Integer.valueOf(obj[3].toString()));
		}
		return user;
	}
	
	private List<JSONObject> jsonlist;
	private JSONObject json;
	private Object[] obj;
	private List<Object[]> tempList;
	private SQLQuery query;
	
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
		String tableNameStr = "user";
		
		if(null!=orderByField&&!"".equals(orderByField)){
			orderByField=" order by "+orderByField;
		}else{
			orderByField="";
		}
		if(pageNo<1){
			pageNo=1;
		}
		String whereStr = "";
		if(null!=whereStr&&!"".equals(whereStr)){
			whereStr=" and "+whereStr;
		}
		
		//统计总记录数
		StringBuffer sql = new StringBuffer("select count(*) from "+tableNameStr+" ");
		
		query = entityDao.getSession().createSQLQuery(sql.toString());
		Integer countSize = Integer.valueOf(query.list().get(0).toString());
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
	
		sql = new StringBuffer("select us.uuid,us.number,us.lv,us.status,uf.realname,uf.nickname,uf.mobile,uf.qq,uf.email ");
		sql.append(" from user us left join user_ex_info uf on us.uuid = uf.userid "+orderByField+" limit "+start_limit+","+Constants.LISTPAGEQTY+"");
		
		query = entityDao.getSession().createSQLQuery(sql.toString());
		tempList = query.list();
		for(int i=0; i<tempList.size(); i++){
				obj = (Object[])tempList.get(i);
				json = new JSONObject();
				json.put("index", indexNo++);
				json.put("uuid", obj[0]);
				json.put("number", obj[1]);//PublicDateUtils.objDateToFormat(obj[1])
//				json.put("lv", Constants.USER_LV_TYPE.get(obj[2].toString()));
				json.put("status", Constants.USER_STATUS_TYPE.get(obj[3].toString()));
				json.put("realname", obj[4]);
				json.put("nickname", obj[5]);
				json.put("mobile", obj[6]);
				json.put("qq", obj[7]);
				json.put("email", obj[8]);
				jsonlist.add(json);
			}
		}
	
	//检查编码是否重复
		public Boolean checkNumRep(String number,String userid){
			Boolean rep = false;
			StringBuffer sql = new StringBuffer("select count(*) from user where number=? ");
			if(userid!=null){
				sql.append(" and uuid!='").append(userid).append("' ");
			}
			SQLQuery query = entityDao.getSession().createSQLQuery(sql.toString());
			query.setParameter(0, number);
			List tempList = query.list();
			if(Integer.valueOf(tempList.get(0).toString())>0){
				rep = true;
			}
			return rep;
			
		}
		
		public void updateDefaultShop(String defaultShopid,String userid){
			StringBuffer sql = new StringBuffer("update user_config set defaultvshopid=? where userid=? ");
			query = entityDao.getSession().createSQLQuery(sql.toString());
			query.setParameter(0, defaultShopid);
			query.setParameter(1, userid);
			query.executeUpdate();
		}
		
		//修改密码
		public void savePass(String userid,String pass){
			StringBuffer sql = new StringBuffer("update user set password=? where uuid=? ");
			query = entityDao.getSession().createSQLQuery(sql.toString());
			query.setParameter(0, pass);
			query.setParameter(1, userid);
			query.executeUpdate();
		}
}
