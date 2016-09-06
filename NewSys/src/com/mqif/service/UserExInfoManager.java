package com.mqif.service;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;

import com.mqif.entitys.user.UserExInfo;
import com.mqif.template.services.EntityManager;

@Service
public class UserExInfoManager extends EntityManager<UserExInfo,String>{

	public Boolean removeUE(String userid){
		SQLQuery query = entityDao.getSession().createSQLQuery("delete from user_ex_info where userid=?");
		query.setParameter(0, userid);
		int res = query.executeUpdate();
		if(res>0){
			query = entityDao.getSession().createSQLQuery("delete from user_config where userid=?");
			query.setParameter(0, userid);
			res = query.executeUpdate();
			if(res>0){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
}
