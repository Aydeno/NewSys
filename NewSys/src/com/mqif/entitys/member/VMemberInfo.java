package com.mqif.entitys.member;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "vmember")
public class VMemberInfo implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private String uuid;//用户ID
	private String username;//用户昵称
	private String password;//用户密码
	private Integer sex;//性别
	private String province;//省份
	private String city;//城市
	private String country;//国家
	private String realname;//真是姓名
	private String mobile;//手机号码
	private Timestamp createTime;
	private long lastupdatetime;//会员信息更新时间
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "uuid", unique = true, nullable = false,length=32)
	public String getUuid() {
		return uuid;
	}
	@Column(name = "username",length = 20)
	public String getUsername() {
		return username;
	}
	@Column(name = "sex",length=1)
	public Integer getSex() {
		return sex;
	}
	@Column(name = "province",length = 20)
	public String getProvince() {
		return province;
	}
	@Column(name = "city",length = 20)
	public String getCity() {
		return city;
	}
	@Column(name = "country",length = 20)
	public String getCountry() {
		return country;
	}
	@Column(name = "realname", length = 100)
	public String getRealname() {
		return realname;
	}

	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}
	@Column(name = "createtime")
	public Timestamp getCreateTime() {
		return createTime;
	}
	@Column(name = "lastupdatetime",columnDefinition="bigint default 0")
	public long getLastupdatetime() {
		return lastupdatetime;
	}
	@Column(name = "password",nullable = false, length = 32)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public void setLastupdatetime(long lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}
	
}
