package com.mqif.entitys.cat;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 新闻分类
 * @ClassName: NewsCatnfo 
 * @author huangjs
 * @date 2016年8月27日 下午5:19:17
 */
@Entity
@Table(name = "news_cat")
public class NewsCatnfo implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private String uuid;//用户ID
	private String en_name;//英文名称
	private String zh_name;//中文名称
	private Integer source;//来源
	
	private Integer seq;//排序
	private Timestamp createTime;
	
	public NewsCatnfo() {
		super();
		this.source  = 0;//默认来源新浪网
		this.seq = 0; //排序为0
	}
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "uuid", unique = true, nullable = false,length=32)
	public String getUuid() {
		return uuid;
	}
	@Column(name = "en_name", length = 100)
	public String getEn_name() {
		return en_name;
	}
	@Column(name = "zh_name", length = 100)
	public String getZh_name() {
		return zh_name;
	}
	@Column(name = "seq",columnDefinition="int default 0")
	public Integer getSeq() {
		return seq;
	}

	@Column(name = "createtime",columnDefinition="TIMESTAMP")
	public Timestamp getCreateTime() {
		return createTime;
	}
	@Column(name = "source",columnDefinition="int(3) default 0")
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}

	public void setZh_name(String zh_name) {
		this.zh_name = zh_name;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
}
