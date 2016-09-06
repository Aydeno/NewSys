package com.mqif.mqifenum;
/**
 * 辣度
 * @author huangjs
 *
 */
public enum NewsCatSourceEnum {
	
	SINA("新浪网",0),
	TENCENT("腾讯",1),
	WANGYI("网易",2),
	SOUHU("搜狐",3),
	;

	private String name;
	private int value;

	private NewsCatSourceEnum(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public static String getName(int value) {
		for (NewsCatSourceEnum c : NewsCatSourceEnum.values()) {
			if (c.getValue() == value) {
				return c.getName();
			}
		}
		return null;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

	public String toString() {
		return name;
	}

}