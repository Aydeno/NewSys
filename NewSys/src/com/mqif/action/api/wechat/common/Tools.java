package com.mqif.action.api.wechat.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.tools.Common;
import com.tools.Mqif;

/**
 * 微信常用工具类
 */
public class Tools {

	/**
	 * 微信APPID 获取
	 */
	public static JSONObject getWechat_Post(String code) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("appid", WXCommon.appID);
		map.put("secret",WXCommon.appSecret);
		map.put("code", code);
		map.put("grant_type", "authorization_code");
		String reStr = Common.doPost("https://api.weixin.qq.com/sns/oauth2/access_token", map, null);
		if(Common.isNull(reStr)){
			System.out.println("接收数据异常！！！");
			return null;
		}
		JSONObject wcJson = new JSONObject(reStr);
		if(wcJson.isNull("openid")){
			System.out.println("接收数据异常1，没有返回openid！！！");
			return null;
		}
		if(Common.isNull(wcJson.getString("openid"))){
			System.out.println("接收数据异常2，没有返回openid！！！");
			return null;
		}
		System.out.println("获取到的Openid:"+wcJson.getString("openid"));
		return wcJson;
	}
	
	/**
	 * 微信用户信息 获取
	 */
	public static JSONObject getWechat_UserInfo(String access_token,String openid) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("access_token", access_token);
		map.put("openid",openid);
		map.put("lang", "zh_CN");
		String reStr = Common.doPost("https://api.weixin.qq.com/sns/userinfo", map, null);
		if(Common.isNull(reStr)){
			System.out.println("接收数据异常！！！");
			return null;
		}
		return new JSONObject(reStr);
	}
	
	/**
	 * 微信用户信息 获取
	 */
	public static JSONObject getWechat_UserInfo(String access_token,String openid,String lang) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("access_token", access_token);
		map.put("openid",openid);
		map.put("lang", lang);
		String reStr = Common.doPost("https://api.weixin.qq.com/sns/userinfo", map, null);
		if(Common.isNull(reStr)){
			System.out.println("接收数据异常！！！");
			return null;
		}
		return new JSONObject(reStr);
	}
	/**
	 * 微信用户信息 获取
	 */
	public static JSONObject getWechat_UserInfoEx(String access_token,String openid) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("access_token", access_token);
		map.put("openid",openid);
		String reStr = Common.doPost("https://api.weixin.qq.com/cgi-bin/user/info", map, null);
		if(Common.isNull(reStr)){
			System.out.println("接收数据异常！！！");
			return null;
		}
		return new JSONObject(reStr);
	}

	/**
	 * 微信签名生成
	 */
	public static String getSign(Map map) {
		//map取出Key,按字典排序
		Collection<String> keyset = map.keySet();
		List<String> keylist = new ArrayList<String>(keyset);
		Collections.sort(keylist);

		//按字典取出值拼接成新字符串
		String sign = "";
		for (int i = 0; i < keylist.size(); i++) {
			if(!Common.isNull(map.get(keylist.get(i)))){
				sign+=keylist.get(i)+"="+map.get(keylist.get(i))+"&";
			}
		}
		if(!Common.isNull(sign)){
			sign+="key="+WXCommon.key;
			return Common.getMD5(sign).toUpperCase();
		}else{
			return null;
		}
	}
	public static String getSign(JSONObject json) {
		//map取出Key,按字典排序
		Collection<String> keyset = json.keySet();
		List<String> keylist = new ArrayList<String>(keyset);
		Collections.sort(keylist);

		//按字典取出值拼接成新字符串
		String sign = "";
		for (int i = 0; i < keylist.size(); i++) {
			sign+=keylist.get(i)+"="+json.get(keylist.get(i))+"&";
		}
		if(!Common.isNull(sign)){
			sign+="key="+WXCommon.key;
			return Common.getMD5(sign).toUpperCase();
		}else{
			return null;
		}
	}


	/**
	 * 微信统一下单
	 */
	public static JSONObject createWechatOrder(HttpServletRequest req,String wechatid,String orderNum,float amount) {
		JSONObject rejson = new JSONObject();
		//生成一个随机数
		String nonce_str = Common.dateToStr(new Date(), "yyyyMMddHH24mmss")+Common.getRandom(10000,99999);
		nonce_str=Common.getMD5(nonce_str);

		//构建订单所需表单
		Map<String,String> postMap = new TreeMap<String,String>();
		postMap.put("appid",WXCommon.appID);
		postMap.put("mch_id",WXCommon.mchID);
		postMap.put("nonce_str", nonce_str);//随机数
		postMap.put("body", orderNum);//商品或支付单简要描述 32长度(放订单编码)
		//postMap.put("attach", vorderInfo.getUuid());//附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
		postMap.put("out_trade_no", orderNum);//商户系统内部的订单号,32个字符内
		postMap.put("total_fee", (int)(amount*100)+"");//总金额，整数，分为单位
		postMap.put("spbill_create_ip", Common.getRemortIP(req));//APP和网页支付提交用户端ip
		postMap.put("time_start", Common.getNowTime("yyyyMMddHH24mm"));//订单生成时间
		postMap.put("time_expire", Common.longToStr(System.currentTimeMillis()+(24*60*60*1000),"yyyyMMddHH24mm"));//订单失效时间
		postMap.put("notify_url","http://test.otoway.com/api/req.html");//通知地址: 结果回调URL
		postMap.put("trade_type", "JSAPI");//交易类型: 取值如下：JSAPI，NATIVE，APP，WAP
		postMap.put("openid",wechatid);//rade_type=JSAPI，此参数必传
		String sign = getSign(postMap);
		postMap.put("sign", sign);//签名

		String reStr = Common.doPost(WXCommon.ORDER_API, Common.getMapToXml(postMap,true), null);
		System.out.println(reStr);
		Map<String,Object> remap = Common.getXmlToMap(reStr);
		if(!remap.containsKey("return_code")){
			rejson.put("status", false);
			rejson.put("info", "参数错误1！！！");
			return rejson;
		}
		if(!remap.get("return_code").toString().equals("SUCCESS")){
			rejson.put("status", false);
			String errStr = "错误！";
			if(remap.containsKey("return_msg")){
				errStr+=remap.get("return_msg").toString();
			}
			rejson.put("info", errStr);
			return rejson;
		}

		if(!remap.containsKey("result_code")){
			rejson.put("status", false);
			rejson.put("info", "参数错误2！！！");
			return rejson;
		}
		if(!remap.get("result_code").toString().equals("SUCCESS")){
			rejson.put("status", false);
			String errStr = "错误！";
			if(remap.containsKey("err_code")){
				errStr+=remap.get("err_code").toString();
			}
			if(remap.containsKey("err_code_des")){
				errStr+=remap.get("err_code_des").toString();
			}
			rejson.put("info",errStr);
			return rejson;
		}
		if(!remap.containsKey("sign")){
			rejson.put("status", false);
			rejson.put("info", "签名不存在！！！");
			return rejson;
		}
		if(Common.isNull(remap.get("sign"))){
			rejson.put("status", false);
			rejson.put("info", "签名不存在！！！");
			return rejson;
		}
		String wcSign = remap.get("sign").toString();
		remap.remove("sign");
		//验证签名
		String newSign = Tools.getSign(remap);
		System.out.println("接收微信Sign："+wcSign+" 服务器生成Sign："+newSign);
		if(!newSign.equals(wcSign)){
			rejson.put("status", false);
			rejson.put("info","验签不一致错误！");
			return rejson;
		}
		if(Common.isNull(remap.get("prepay_id"))){
			rejson.put("status", false);
			rejson.put("info", "获取不到预支付ID！！！");
			return rejson;
		}
		rejson.put("status", true);
		rejson.put("info", remap.get("prepay_id").toString());
		return rejson;
	}
	/**
	 * 企业 微信统一下单
	 */
	public static JSONObject createWechatOrder_Business(HttpServletRequest req,String wechatid,String orderNum,float amount) {
		JSONObject rejson = new JSONObject();
		//生成一个随机数
		String nonce_str = Common.dateToStr(new Date(), "yyyyMMddHH24mmss")+Common.getRandom(10000,99999);
		nonce_str=Common.getMD5(nonce_str);
		
		//构建订单所需表单
		Map<String,String> postMap = new TreeMap<String,String>();
		postMap.put("appid",WXCommon.otoappID);
		postMap.put("mch_id",WXCommon.mchID);
		postMap.put("sub_mch_id",WXCommon.subMchID);
		postMap.put("nonce_str", nonce_str);//随机数
		postMap.put("body", orderNum);//商品或支付单简要描述 32长度(放订单编码)
		//postMap.put("attach", vorderInfo.getUuid());//附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
		postMap.put("out_trade_no", orderNum);//商户系统内部的订单号,32个字符内
		postMap.put("total_fee", (int)(amount*100)+"");//总金额，整数，分为单位
		postMap.put("spbill_create_ip", Common.getRemortIP(req));//APP和网页支付提交用户端ip
		postMap.put("time_start", Common.getNowTime("yyyyMMddHH24mm"));//订单生成时间
		postMap.put("time_expire", Common.longToStr(System.currentTimeMillis()+(24*60*60*1000),"yyyyMMddHH24mm"));//订单失效时间
		postMap.put("notify_url","http://test.otoway.com/api/req.html");//通知地址: 结果回调URL
		postMap.put("trade_type", "JSAPI");//交易类型: 取值如下：JSAPI，NATIVE，APP，WAP
		postMap.put("sub_openid",wechatid);//rade_type=JSAPI，此参数必传
		String sign = getSign(postMap);
		postMap.put("sign", sign);//签名
		
		String reStr = Common.doPost(WXCommon.ORDER_API, Common.getMapToXml(postMap,true), null);
		System.out.println(reStr);
		Map<String,Object> remap = Common.getXmlToMap(reStr);
		if(!remap.containsKey("return_code")){
			rejson.put("status", false);
			rejson.put("info", "参数错误1！！！");
			return rejson;
		}
		if(!remap.get("return_code").toString().equals("SUCCESS")){
			rejson.put("status", false);
			String errStr = "错误！";
			if(remap.containsKey("return_msg")){
				errStr+=remap.get("return_msg").toString();
			}
			rejson.put("info", errStr);
			return rejson;
		}
		
		if(!remap.containsKey("result_code")){
			rejson.put("status", false);
			rejson.put("info", "参数错误2！！！");
			return rejson;
		}
		if(!remap.get("result_code").toString().equals("SUCCESS")){
			rejson.put("status", false);
			String errStr = "错误！";
			if(remap.containsKey("err_code")){
				errStr+=remap.get("err_code").toString();
			}
			if(remap.containsKey("err_code_des")){
				errStr+=remap.get("err_code_des").toString();
			}
			rejson.put("info",errStr);
			return rejson;
		}
		if(!remap.containsKey("sign")){
			rejson.put("status", false);
			rejson.put("info", "签名不存在！！！");
			return rejson;
		}
		if(Common.isNull(remap.get("sign"))){
			rejson.put("status", false);
			rejson.put("info", "签名不存在！！！");
			return rejson;
		}
		String wcSign = remap.get("sign").toString();
		remap.remove("sign");
		//验证签名
		String newSign = Tools.getSign(remap);
		System.out.println("接收微信Sign："+wcSign+" 服务器生成Sign："+newSign);
		if(!newSign.equals(wcSign)){
			rejson.put("status", false);
			rejson.put("info","验签不一致错误！");
			return rejson;
		}
		if(Common.isNull(remap.get("prepay_id"))){
			rejson.put("status", false);
			rejson.put("info", "获取不到预支付ID！！！");
			return rejson;
		}
		rejson.put("status", true);
		rejson.put("info", remap.get("prepay_id").toString());
		return rejson;
	}

	/**
	 * 微信查询订单状态接口
	 */
	public static JSONObject getWechatOrderStatus(String number) {
		JSONObject rejson = new JSONObject();
		if(Common.isNull(number)){
			rejson = Mqif.returnInfo(false, "订单不存在！");
			return rejson;
		}

		//构建查询订单所需表单
		//生成一个随机数
		String nonce_str = Common.dateToStr(new Date(), "yyyyMMddHH24mmss")+Common.getRandom(10000,99999);
		nonce_str=Common.getMD5(nonce_str);
		Map<String,String> postMap = new TreeMap<String,String>();
		postMap.put("appid",WXCommon.appID);
		postMap.put("mch_id",WXCommon.mchID);
		//postMap.put("transaction_id",vorderInfo.getEordernumber());//微信订单号
		postMap.put("out_trade_no", number);//系统订单号
		postMap.put("nonce_str", nonce_str);//随机数
		String sign = getSign(postMap);
		postMap.put("sign", sign);//签名

		String reStr = Common.doPost(WXCommon.ORDER_QUERY_API, Common.getMapToXml(postMap,false), null);
		System.out.println("收到："+reStr);
		Map<String,Object> remap = Common.getXmlToMap(reStr);
		if(!remap.containsKey("return_code")){
			rejson = Mqif.returnInfo(false, "订单生成失败2！"+reStr);
			return rejson;
		}
		if(!remap.get("return_code").toString().equals("SUCCESS")){
			rejson = Mqif.returnInfo(false, "查询订单失败"+remap.get("return_msg").toString());
			return rejson;
		}
		if(!remap.containsKey("sign")){
			rejson = Mqif.returnInfo(false, "查询订单失败，签名信息丢失！");
			return rejson;
		}

		String wcSign = remap.get("sign").toString();
		remap.remove("sign");
		String newSign = getSign(remap);
		System.out.println("接收微信Sign："+wcSign+" 服务器生成Sign："+newSign);
		if(!newSign.equals(wcSign)){
			rejson = Mqif.returnInfo(false, "验签失败！");
			return rejson;
		}
		if(!remap.get("result_code").toString().equals("SUCCESS")){
			rejson = Mqif.returnInfo(false, remap.get("err_code")+"");
			return rejson;
		}
		if(!remap.containsKey("trade_state")){
			rejson = Mqif.returnInfo(false, "订单状态数据丢失！");
			return rejson;
		}
		if(!remap.get("trade_state").toString().equals("SUCCESS")){
			rejson = Mqif.returnInfo(false, remap.get("trade_state")+""+remap.get("trade_state_desc"));
			return rejson;
		}
		
		rejson.put("status", true);
		rejson.put("info", remap);
		return rejson;
	}
	/**
	 * 企业 微信查询订单状态接口
	 */
	public static JSONObject getWechatOrderStatus_Business(String number) {
		JSONObject rejson = new JSONObject();
		if(Common.isNull(number)){
			rejson = Mqif.returnInfo(false, "订单不存在！");
			return rejson;
		}
		
		//构建查询订单所需表单
		//生成一个随机数
		String nonce_str = Common.dateToStr(new Date(), "yyyyMMddHH24mmss")+Common.getRandom(10000,99999);
		nonce_str=Common.getMD5(nonce_str);
		Map<String,String> postMap = new TreeMap<String,String>();
		postMap.put("appid",WXCommon.otoappID);
		postMap.put("mch_id",WXCommon.mchID);
		postMap.put("sub_mch_id",WXCommon.subMchID);
		//postMap.put("transaction_id",vorderInfo.getEordernumber());//微信订单号
		postMap.put("out_trade_no", number);//系统订单号
		postMap.put("nonce_str", nonce_str);//随机数
		String sign = getSign(postMap);
		postMap.put("sign", sign);//签名
		
		String reStr = Common.doPost(WXCommon.ORDER_QUERY_API, Common.getMapToXml(postMap,false), null);
		System.out.println("收到："+reStr);
		Map<String,Object> remap = Common.getXmlToMap(reStr);
		if(!remap.containsKey("return_code")){
			rejson = Mqif.returnInfo(false, "订单生成失败2！"+reStr);
			return rejson;
		}
		if(!remap.get("return_code").toString().equals("SUCCESS")){
			rejson = Mqif.returnInfo(false, "查询订单失败"+remap.get("return_msg").toString());
			return rejson;
		}
		if(!remap.containsKey("sign")){
			rejson = Mqif.returnInfo(false, "查询订单失败，签名信息丢失！");
			return rejson;
		}
		
		String wcSign = remap.get("sign").toString();
		remap.remove("sign");
		String newSign = getSign(remap);
		System.out.println("接收微信Sign："+wcSign+" 服务器生成Sign："+newSign);
		if(!newSign.equals(wcSign)){
			rejson = Mqif.returnInfo(false, "验签失败！");
			return rejson;
		}
		if(!remap.get("result_code").toString().equals("SUCCESS")){
			rejson = Mqif.returnInfo(false, remap.get("err_code")+"");
			return rejson;
		}
		if(!remap.containsKey("trade_state")){
			rejson = Mqif.returnInfo(false, "订单状态数据丢失！");
			return rejson;
		}
		if(!remap.get("trade_state").toString().equals("SUCCESS")){
			rejson = Mqif.returnInfo(false, remap.get("trade_state")+""+remap.get("trade_state_desc"));
			return rejson;
		}
		
		rejson.put("status", true);
		rejson.put("info", remap);
		return rejson;
	}
	
	
	/**
	 * 支付信息！
	 */
	public static JSONObject getPayInfoJSON(String wechatorderid) {
		//生成一个随机数
		String nonceStr = Common.dateToStr(new Date(), "yyyyMMddHH24mmss")+Common.getRandom(10000,99999);
		nonceStr=Common.getMD5(nonceStr);
		String timeStamp = Long.toString(System.currentTimeMillis() / 1000);

		JSONObject rejson = new JSONObject();
		rejson.put("appId", WXCommon.otoappID);
		rejson.put("timeStamp", timeStamp);
		rejson.put("nonceStr", nonceStr);
		rejson.put("package", "prepay_id="+wechatorderid);
		rejson.put("signType", "MD5");
		String sign = getSign(rejson);
		rejson.put("package_str", "prepay_id="+wechatorderid);
		rejson.put("wechatorderid", wechatorderid);
		rejson.put("paySign",sign);
		rejson.put("status", true);
		return rejson;
	}
	
}
