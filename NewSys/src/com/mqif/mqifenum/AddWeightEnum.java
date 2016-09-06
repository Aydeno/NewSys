package com.mqif.mqifenum;
/**
 * 加量
 * @author huangjs
 *
 */
public enum AddWeightEnum {

	ADDWEIGHT("150克/2元",0,2),
	;

	private String name;
	private int value;
	private float price;

	private AddWeightEnum(String name, Integer value,float price) {
		this.name = name;
		this.value = value;
		this.price = price;
	}

	public static float getPrice(int value) {
		for (AddWeightEnum c : AddWeightEnum.values()) {
			if (c.getValue() == value) {
				return c.getPrice();
			}
		}
		return 0;
	}
	public static String getName(int value) {
		for (AddWeightEnum c : AddWeightEnum.values()) {
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}


}