package com.mqif.entitys.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "user_config")
public class UserConfigInfo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String uuid;
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "uuid", unique = true, nullable = false,length=32)
	public String getUuid() {return uuid;}
	public void setUuid(String uuid) {this.uuid = uuid;}
	
	
	private UserInfo userid;//关联用户名
	@OneToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "userid")
	public UserInfo getUserid(){return userid;}
	public void setUserid(UserInfo s){userid=s;}
	
	
	private int listpage;//列表页每页显示数量
	@Column(name = "listpage")
	public Integer getListpage(){return listpage;}
	public void setListpage(Integer s){listpage=s;}
	
	
//	private VShopInfo defaultvshopid;//默认管理商城
//	@ManyToOne(cascade = CascadeType.ALL, optional = true)
//	@JoinColumn(name = "defaultvshopid")
//	public VShopInfo getDefaultvshopid(){return defaultvshopid;}
//	public void setDefaultvshopid(VShopInfo defaultvshopid){this.defaultvshopid=defaultvshopid;}
	
}