package com.mqif.service;

import java.util.List;

import org.hibernate.SQLQuery;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.mqif.entitys.cat.NewsCatnfo;
import com.mqif.template.services.EntityManager;
import com.tools.Common;

@Service
public class NewsCatInfoManager extends EntityManager<NewsCatnfo,String>{
	
	private List<JSONObject> jsonlist;
	private JSONObject json;
	private Object[] obj;
	private List<Object[]> tempList;
	private SQLQuery query;
	
	
	public net.sf.json.JSONArray allNewsCatJsonArray(){
		 net.sf.json.JSONArray js = new  net.sf.json.JSONArray();
		 net.sf.json.JSONObject json = null;
		StringBuffer sql = new StringBuffer("SELECT seq , en_name , zh_name FROM news_cat order by seq");
		
		query = entityDao.getSession().createSQLQuery(sql.toString());
		tempList = query.list();
		for (int i = 0; i < tempList.size(); i++) {
			obj = tempList.get(i);
			json = new  net.sf.json.JSONObject();
			json.put("seq", obj[0]);
			json.put("channel", obj[1]);
			json.put("zh_name", obj[2]);
			js.add(json);
		}
		
		return js;
	}
	
	public String checkIsExist(String en_name){
		
		StringBuffer sql = new StringBuffer("SELECT uuid FROM news_cat where en_name = ?");
		query = entityDao.getSession().createSQLQuery(sql.toString());
		query.setParameter(0, en_name);
		List<String> obj = query.list();
		if(obj.size()>0){
			if(!Common.isNull(obj.get(0))){
				return obj.get(0);
			}
		}
		return null;
	}
	
}
