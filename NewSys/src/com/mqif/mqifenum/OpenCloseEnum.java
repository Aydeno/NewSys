package com.mqif.mqifenum;

public enum OpenCloseEnum {
	/**数据库保存的是，枚举顺序整型，并非设置的值，不要轻易改变枚举顺序*/
	CLOSE("关闭",0),
	OPEN("启用",1),
	;

	private String name;
	private int value;

	private OpenCloseEnum(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public static String getName(int value) {
		for (OpenCloseEnum c : OpenCloseEnum.values()) {
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


}