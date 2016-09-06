package com.mqif.entitys.user;

import java.util.Date;

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
@Table(name = "user_ex_info")
public class UserExInfo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	public UserExInfo() {
		super();
		this.sexenum = 1;
	}

	private String uuid;
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "uuid", unique = true, nullable = false,length=32)
	public String getUuid() {return uuid;}
	public void setUuid(String uuid) {this.uuid = uuid;}
	
	
	private UserInfo userid;//关联用户
	@OneToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "userid")
	public UserInfo getUserid(){return userid;}
	public void setUserid(UserInfo s){userid=s;}
	
	
	private String realname;//用户真实姓名
	@Column(name = "realname")
	public String getRealname() {return realname;}
	public void setRealname(String realname) {this.realname = realname;}
	
	
	private String idnum;//用户身份证号码
	@Column(name = "idnum")
	public String getIdnum(){return idnum;}
	public void setIdnum(String s){idnum=s;}
	
	
	private Date birthday;//出生年月
	@Column(name = "birthday")
	public Date getBirthday(){return birthday;}
	public void setBirthday(Date s){birthday=s;}
	
	
	private Integer sexenum;//性别
	@Column(name = "sex")
	public Integer getSexenum(){return sexenum;}
	public void setSexenum(Integer s){sexenum=s;}
	
	
	private String country;//国家
	@Column(name = "country")
	public String getCountry(){return country;}
	public void setCountry(String s){country=s;}
	
	
	private String province;//省份
	@Column(name = "province")
	public String getProvince(){return province;}
	public void setProvince(String s){province=s;}
	
	
	private String city;//城市
	@Column(name = "city")
	public String getCity(){return city;}
	public void setCity(String s){city=s;}
	
	
	private String nickname;//用户昵称
	@Column(name = "nickname")
	public String getNickname(){return nickname;}
	public void setNickname(String s){nickname=s;}
	
	
	private String headimgurl;//用户头像
	@Column(name = "headimgurl")
	public String getHeadimgurl(){return headimgurl;}
	public void setHeadimgurl(String s){headimgurl=s;}
	
	
	private String qq;//用户QQ
	@Column(name = "qq")
	public String getQq() {return qq;}
	public void setQq(String qq) {this.qq = qq;}
	
	
	private String mobile;//用户手机号码
	@Column(name = "mobile")
	public String getMobile(){return mobile;}
	public void setMobile(String s){mobile=s;}
	
	
	private String email;//用户邮箱
	@Column(name = "email")
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}

}
