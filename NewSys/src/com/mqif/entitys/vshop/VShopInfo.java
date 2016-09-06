//package com.mqif.entitys.vshop;
//
//import java.io.Serializable;
//import java.sql.Timestamp;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.GenericGenerator;
//
//import com.mqif.entitys.user.UserConfigInfo;
//import com.mqif.entitys.user.UserInfo;
//
//@Entity
//@Table(name = "vshop")
//public class VShopInfo implements Serializable{
//	private static final long serialVersionUID = 1L;
//
//	private String uuid;//商城ID
//	@Id
//	@GeneratedValue(generator = "paymentableGenerator")
//	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
//	@Column(name = "uuid", unique = true, nullable = false,length=32)
//	public String getUuid() {return uuid;}
//	public void setUuid(String uuid) { this.uuid = uuid; }
//	
//	private String name;//商城名称
//	@Column(name = "name", nullable = false,length=100)
//	public String getName(){return name;}
//	public void setName(String s){name=s;}
//	
//	private String mobile;//手机号码
//	@Column(name = "mobile",length=20)
//	public String getMobile(){return mobile;}
//	public void setMobile(String s){mobile=s;}
//	
//	private UserInfo userinfo;//所属用户
//	@ManyToOne(cascade = {CascadeType.ALL})
//	@JoinColumn(name = "userid")
//	public UserInfo getUserinfo(){return userinfo;}
//	public void setUserinfo(UserInfo s){userinfo=s;}
//	
//	private Timestamp createtime;//创建时间
//	@Column(name = "createtime")
//	public Timestamp getCreatetime() {return createtime;}
//	public void setCreatetime(Timestamp createtime) {this.createtime = createtime;}
//	
//	private Set<UserConfigInfo> userconfiginfolist;
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="defaultvshopid")
//	public Set<UserConfigInfo> getUserconfiginfolist(){return userconfiginfolist;}
//	public void setUserconfiginfolist(Set<UserConfigInfo> s){userconfiginfolist=s;}
//	
//	
//	private String number;//商城编码
//	@Column(name = "number", nullable = false,length=100)
//	public String getNumber(){return number;}
//	public void setNumber(String number){this.number=number;}
//	
//	private String content;//介绍
//	@Column(name = "content",length=255)
//	public String getContent(){return content;}
//	public void setContent(String content){this.content=content;}
//	
//
//	public VShopInfo() {
//		super();
//	}
//	
//	private String address;
//	@Column(name = "address")
//	public String getAddress() {
//		return address;
//	}
//	public void setAddress(String address) {
//		this.address = address;
//	}
//	
//	private String province;
//	private String city;
//	private String town;
//	@Column(name = "province")
//	public String getProvince() {
//		return province;
//	}
//	@Column(name = "city")
//	public String getCity() {
//		return city;
//	}
//	@Column(name = "town")
//	public String getTown() {
//		return town;
//	}
//	public void setProvince(String province) {
//		this.province = province;
//	}
//	public void setCity(String city) {
//		this.city = city;
//	}
//	public void setTown(String town) {
//		this.town = town;
//	}
//	private String latlng;//纬度,经度
//	@Column(name = "latlng")
//	public String getLatlng() {
//		return latlng;
//	}
//	public void setLatlng(String latlng) {
//		this.latlng = latlng;
//	}
//	
//	private String icon;
//	@Column(name = "icon")
//	public String getIcon() {return icon;}
//	public void setIcon(String icon) {this.icon = icon;}
//	
//}
