package com.mqif.mqifenum;
/**
 * 口感
 * @author huangjs
 *
 */
public enum AddTextureEnum {

	SOFT("软一点",0),
	SOFTHAND("软硬适中",1),
	HAND("硬一点",2),
	COLD("冷",3),
	HOT("热",4),
	;

	private String name;
	private int value;

	private AddTextureEnum(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public static String getName(int value) {
		for (AddTextureEnum c : AddTextureEnum.values()) {
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