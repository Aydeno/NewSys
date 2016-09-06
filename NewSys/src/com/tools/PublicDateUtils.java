/**
 * @author aiden
 * @data 2014-11-7
 * 
 */
package com.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PublicDateUtils {
	/**
	 * 将object类型转换为yyyy-MM-dd HH:mm:ss格式
	 * @param obj
	 * @return String类型的格式化结果
	 */
	public static String objDateToFormat(Object obj){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(obj==null||"".equals(obj)){
			return "";
		}
		return sdf.format(obj);
	}
	
	public static String objDateToFormat2(Object obj){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(obj==null||"".equals(obj)){
			return "";
		}
		return sdf.format(obj);
	}
	/**
	 * 获取当前时间 HH:mm
	 * @return
	 */
	public static String getCurrHourMim(){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(System.currentTimeMillis());
	}
	/**
	 * 获取当前时间 HH
	 * @return
	 */
	public static String getCurrHour(){
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		return sdf.format(System.currentTimeMillis());
	}
	/**
	 * 获取当前时间 mm
	 * @return
	 */
	public static String getCurrMim(){
		SimpleDateFormat sdf = new SimpleDateFormat("mm");
		return sdf.format(System.currentTimeMillis());
	}
	
	/**
	 * 生成15位数的会员卡号编码
	 * @return
	 */
	public static String createFiveTeenNum(){
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyMMddhhmmssSSS"); 
		String strDate = sdf.format(d);
		return strDate;
	}
	
	/**
	 * 生成12位数的会员卡号编码
	 * @return
	 */
	public static String createThreeTenNum(){
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("MMddhhmmssSSS"); 
		String strDate = sdf.format(d);
		return strDate;
	}
	
	//生成编码
	public static String createClassNum(int i){
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddmmss"); 
		String strDate = sdf.format(d);
		 strDate=strDate+addZeroForNum(""+i, 4);
		return strDate;
	}
	 /**
	  *数字不足位数左补0
	  */
	public static String addZeroForNum(String str, int strLength) {
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append("0").append(str);//左补0
				str = sb.toString();
				strLen = str.length();
			}
		}
		return str;
	}
	// Date -> Sting
	public static String dateToStr(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		String strDate = sdf.format(date);
		return strDate;
	}
	//Sting  -> Date
	public static Date strToDate(String str) throws ParseException{
		SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = format1.parse(str);
		return date;
	}
	public static Date strToDate2(String str) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = sdf.parse(str);
		return date;
	}
	//计算距离月底天数
	public static int getTwoDate() throws ParseException{
		long day=0;
		Calendar currDate = Calendar.getInstance(); 
		Calendar lastDate = Calendar.getInstance(); 
		lastDate.set(Calendar.DATE,1);//设为当前月的1号
		lastDate.add(Calendar.MONTH,1);//
		lastDate.add(Calendar.DATE,-1);//
		day=(lastDate.getTime().getTime()-currDate.getTime().getTime())/(24*60*60*1000);
		return (int)day;
	}
	
//	//获得本月第一天
	public static String getFitstDate(){
		Calendar firstDate = Calendar.getInstance(); 
		firstDate.set(Calendar.DATE,1);//设为当前月的1号
		return dateToStr(firstDate.getTime()); 

	}
	
//	//获得本月最后一天
	public static String getLastDate(){
		Calendar lastDate = Calendar.getInstance(); 
		lastDate.set(Calendar.DATE,1);//设为当前月的1号
		lastDate.add(Calendar.MONTH,1);//
		lastDate.add(Calendar.DATE,-1);//
		return dateToStr(lastDate.getTime()); 

	}
	//当然日期的星期几，数字为1-7直接，1为星期日，7为星期天
	public static int getLastWeek(){
		Date da=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(da);
		int dy=calendar.get(Calendar.DAY_OF_WEEK);
		return dy; 
	}
	
	/**
	 * 根据输入的数字返回时间，例如当前日期2014-11-07，参数2，返回2014-11-09
	 */
	public static Date getDateByParm(int d){
		Calendar calendar = Calendar.getInstance(); 
		calendar.add(Calendar.DAY_OF_MONTH,d);
		return calendar.getTime();
	}
	/**
	 *  更具时间戳生成8位数不重复的随机码
	 * @param time
	 * @return
	 */
	public static String currTimeToHex(long time){
		return Integer.toHexString((int)time);//获取当前时间的时间戳，然后转换为十六进制。
	}

}
