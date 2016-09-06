package com.mqif.action.api.wechat.common;


public class WXCommon {
	
	//这个就是自己要保管好的私有Key了（切记只能放在自己的后台代码里，不能放在任何可能被看到源代码的客户端程序中）
	// 每次自己Post数据给API的时候都要用这个key来对所有字段进行签名，生成的签名会放在Sign这个字段，API收到Post数据的时候也会用同样的签名算法对Post过来的数据进行签名和验证
	// 收到API的返回的时候也要用这个key来对返回的数据算下签名，跟API的Sign数据进行比较，如果值不一致，有可能数据被第三方给篡改
	
	public static String access_token = "";
	public void setAccess_token(String access_token){
		WXCommon.access_token = access_token;
	}
	public static String jsapi_ticket = "";
	public void setJsapi_ticket(String jsapi_ticket){
		WXCommon.jsapi_ticket = jsapi_ticket;
	}
	
    /**PAY Key*/
    public static String key = "";
	
    /**APPSECRET*/
    public static String appSecret = "6a4ee270e5100df03bd4628f5048e0a0";//aydeno
    
	/**TOKEN*/
    public static String toKen = "";
    
    

    /**EncodingAESKey*/
    public static String EncodingAESKey = "";
    
	//微信分配的公众号ID（开通公众号之后可以获取到）
	public static String appID = "wx8138008a92c324d5";//Aydeno
	
	public static String otoappID = "wx3ada2b0a68c3c511";//商通道

	//微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
	public static String mchID = "";

	//受理模式下给子商户分配的子商户号
	public static String subMchID = "";

	//HTTPS证书的本地路径
	public static String certLocalPath = "";

	//HTTPS证书密码，默认密码等于商户号MCHID
	public static String certPassword = "";

	//是否使用异步线程的方式来上报API测速，默认为异步模式
	public static boolean useThreadToDoReport = true;

	//服务器IP
	public static String ip = "120.24.231.137";
	
	//0）获取openid的路径
	public static String OPENID_API = "https://open.weixin.qq.com/connect/oauth2/authorize";
	
	//0）统一下单API
	public static String ORDER_API = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	//0）查询订单API
	public static String ORDER_QUERY_API = "https://api.mch.weixin.qq.com/pay/orderquery";
	
	//0）获取ACCESS_TOKEN_API的路径
	public static String ACCESS_TOKEN_API = "https://api.weixin.qq.com/cgi-bin/token";
	
	//0）获取JSAPI_TICKET_API 的路径
	public static String JSAPI_TICKET_API = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

	public boolean isUseThreadToDoReport() {
		return useThreadToDoReport;
	}

	public void setUseThreadToDoReport(boolean useThreadToDoReport) {
		WXCommon.useThreadToDoReport = useThreadToDoReport;
	}

	public void setAppSecret(String appSecret) {
		WXCommon.appSecret = appSecret;
	}
	
	public void setToKen(String toKen) {
		WXCommon.toKen = toKen;
	}
	
	public void setAppID(String appID) {
		WXCommon.appID = appID;
	}

	public void setMchID(String mchID) {
		WXCommon.mchID = mchID;
	}

	public void setSubMchID(String subMchID) {
		WXCommon.subMchID = subMchID;
	}

	public void setCertLocalPath(String certLocalPath) {
		WXCommon.certLocalPath = certLocalPath;
	}

	public void setCertPassword(String certPassword) {
		WXCommon.certPassword = certPassword;
	}

	public void setIp(String ip) {
		WXCommon.ip = ip;
	}

	public String getAppSecret() {
		return appSecret;
	}
	
	public String getToKen() {
		return toKen;
	}
	
	public String getAppid(){
		return appID;
	}
	
	public String getMchid(){
		return mchID;
	}

	public String getSubMchid(){
		return subMchID;
	}
	
	public String getCertLocalPath(){
		return certLocalPath;
	}
	
	public String getCertPassword(){
		return certPassword;
	}

	public String getIP(){
		return ip;
	}



}
