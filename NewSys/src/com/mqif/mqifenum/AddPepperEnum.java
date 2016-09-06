package com.mqif.mqifenum;
/**
 * 辣度
 * @author huangjs
 *
 */
public enum AddPepperEnum {
	
	ADDFEW("少少辣",0),
	ADDMIDDLE("有点辣",1),
	ADDLARGE("灰常辣",2),
	ADDENLARGE("崩溃辣",3),
	;

	private String name;
	private int value;

	private AddPepperEnum(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public static String getName(int value) {
		for (AddPepperEnum c : AddPepperEnum.values()) {
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