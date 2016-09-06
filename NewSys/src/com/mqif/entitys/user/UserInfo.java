package com.mqif.entitys.user;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user")
public class UserInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	public UserInfo(){
		super();
		this.userexinfo  = new UserExInfo();
	}
	
	private String uuid;
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "uuid", unique = true, nullable = false,length=32)
	public String getUuid() {return uuid;}
	public void setUuid(String uuid) {this.uuid = uuid;}
	
	
	private String number;
	@Column(name = "number",unique = true, nullable = false,length=20)
	public String getNumber() {return number;}
	public void setNumber(String number) {this.number = number;}
	
	
	private Integer status;
	@Column(name = "status",nullable = false, length = 1)
	public Integer getStatus() {return status;}
	public void setStatus(Integer status) {this.status = status;}
	
	
	private String password;
	@Column(name = "password",nullable = false, length = 32)
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	
	
	private Integer lv;
	@Column(name = "lv", length = 1)
	public Integer getLv() {return lv;}
	public void setLv(Integer lv) {this.lv = lv;}
	
	
	private UserConfigInfo userconfiginfo;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="userid")
	public UserConfigInfo getUserconfiginfo() {return userconfiginfo;}
	public void setUserconfiginfo(UserConfigInfo userconfiginfo) {this.userconfiginfo = userconfiginfo;}
	
	
	private UserExInfo userexinfo;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="userid")
	public UserExInfo getUserexinfo() {return userexinfo;}
	public void setUserexinfo(UserExInfo userexinfo) {this.userexinfo = userexinfo;}
	
	
//	private Set<VShopInfo> myvshoplist;//用户拥有的商城
//	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="userinfo")
//	public Set<VShopInfo> getMyvshoplist() {return myvshoplist;}
//	public void setMyvshoplist(Set<VShopInfo> myvshoplist) {this.myvshoplist=myvshoplist;}
	
}
