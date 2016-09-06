package com.mqif.entitys.member;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @ClassName: VMemberLogInfo 
 * @author huangjs
 * @date 2016年6月17日 下午11:27:02 
 * 
 */
@Entity
@Table(name = "vmember_log")
public class VMemberLogInfo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String uuid;
	private String vmember_uuid;//会员ID
	private String xstore_num;//门店编码
	private Timestamp createTime;
	
	
	public VMemberLogInfo() {
		super();
	}
	public VMemberLogInfo(String vmember_uuid, String xstore_num, Timestamp createTime) {
		super();
		this.vmember_uuid = vmember_uuid;
		this.xstore_num = xstore_num;
		this.createTime = createTime;
	}
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "uuid", unique = true, nullable = false,length=32)
	public String getUuid() {
		return uuid;
	}
	@Column(length=32)
	public String getVmember_uuid() {
		return vmember_uuid;
	}
	@Column(length=32)
	public String getXstore_num() {
		return xstore_num;
	}
	@Column
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public void setVmember_uuid(String vmember_uuid) {
		this.vmember_uuid = vmember_uuid;
	}
	public void setXstore_num(String xstore_num) {
		this.xstore_num = xstore_num;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	
}
