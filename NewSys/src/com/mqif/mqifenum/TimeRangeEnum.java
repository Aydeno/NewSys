package com.mqif.mqifenum;
/**
 * 时间段枚举
 * @ClassName: TimeRangeEnum 
 * @author huangjs
 * @date 2016年5月26日 下午12:12:09
 */
public enum TimeRangeEnum {
	/**数据库保存的是，枚举顺序整型，并非设置的值，不要轻易改变枚举顺序*/
	TIME9("9:00-9:10","9",0),
	TIME10("10:00-10:10","10",1),
	TIME11("11:00-11:10","11",2),
	TIME12("12:00-12:10","12",3),
	TIME13("13:00-13:10","13",4),
	TIME14("14:00-14:10","14",5),
	TIME15("15:00-15:10","15",6),
	TIME16("16:00-16:10","16",7),
	TIME17("17:00-17:10","17",8),
	TIME18("18:00-18:10","18",9),
	TIME19("19:00-19:10","19",10),
	TIME20("20:00-20:10","20",11),
	TIME21("21:00-21:10","21",12),
	TIME22("22:00-22:10","22",13),
	TIME23("23:00-23:10","23",14),
	TIME24("24:00-0:10","24",15),
	;

	private String name;
	private String time;
	private int value;

	private TimeRangeEnum(String name,String time, Integer value) {
		this.name = name;
		this.time = time;
		this.value = value;
	}

	public static String getName(int value) {
		for (TimeRangeEnum c : TimeRangeEnum.values()) {
			if (c.getValue() == value) {
				return c.getName();
			}
		}
		return null;
	}
	
	public static TimeRangeEnum getEnum(String time) {
		for (TimeRangeEnum c : TimeRangeEnum.values()) {
			if (c.getTime().equals(time)) {
				return c;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}


}