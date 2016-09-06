package com.mqif.action.api.wechat;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.mqif.action.api.wechat.common.AesException;
import com.mqif.action.api.wechat.common.WXCommon;
import com.opensymphony.xwork2.ActionSupport;
import com.tools.Common;
import com.tools.Struts2Util;

@Namespace("/api/wechat")
public class GatwayAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String shopnum;
	public String getShopnum() {return shopnum;}
	public void setShopnum(String shopnum) {this.shopnum = shopnum;}

	
	@Action(value="req")
	public String wxreq() throws IOException {
		return NONE;
	}
	
	@Action(value="gateway")
	public String gateway() throws IOException {
		//1. 解析请求参数
		Map<String, String> params = getRequestParams(Struts2Util.getRequest());

		//打印本次请求日志，开发者自行决定是否需要
		System.out.println("微信请求串"+params.toString());
		
		//核验请求参数
		if(!params.containsKey("signature")
				||!params.containsKey("timestamp")
				||!params.containsKey("nonce")
				||!params.containsKey("echostr")
				||Common.isNull(params.get("signature"))
				||Common.isNull(params.get("timestamp"))
				||Common.isNull(params.get("nonce"))
				||Common.isNull(params.get("echostr"))
				){
			response("缺少请求参数错误!");
			return NONE;
		}
		//加入本地和微信token
		params.put("token", WXCommon.toKen);
		
//		//map取出Key,按字典排序
//		Collection<String> keyset = params.keySet();
//		List<String> keylist = new ArrayList<String>(keyset);
//		Collections.sort(keylist);
//		
//		//按字典取出值拼接成新字符串
//		String sign = "";
//		for (int i = 0; i < keylist.size(); i++) {
//			if(!keylist.get(i).equals("echostr")
//					&&!keylist.get(i).equals("signature")
//					&&!keylist.get(i).equals("shopnum")
//					){
//				sign+=params.get(keylist.get(i));
//				System.out.println("KEY:"+keylist.get(i)+" VALUE:"+params.get(keylist.get(i)));
//			}
//		}
		String sign = "";
		try {
			sign = getSHA1(params.get("token"), params.get("timestamp"), params.get("nonce"));
		} catch (AesException e) {
			e.printStackTrace();
			response("签名加密错误!");
			return NONE;
		}
		System.out.println("加密后:"+sign);
		if(!sign.equals(params.get("signature"))){
			response("微信收到签名错误: "+params.get("signature"));
			return NONE;
		}
		//成功返回echostr
		response(params.get("echostr"));
		return NONE;
	}
	private void response(String str){
		Struts2Util.getResponse().reset();
		Struts2Util.getResponse().setContentType("text/html");
		Struts2Util.getResponse().setCharacterEncoding("UTF-8");
		try {
			PrintWriter printWriter = Struts2Util.getResponse().getWriter();
			printWriter.print(str);
			System.out.println("回应:"+str);
			Struts2Util.getResponse().flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 用SHA1算法生成安全签名
	 * @param token 票据
	 * @param timestamp 时间戳
	 * @param nonce 随机字符串
	 * @param encrypt 密文
	 * @return 安全签名
	 * @throws AesException 
	 */
	public static String getSHA1(String token, String timestamp, String nonce) throws AesException {
		try {
			String[] array = new String[] { token, timestamp, nonce};
			StringBuffer sb = new StringBuffer();
			// 字符串排序
			Arrays.sort(array);
			for (int i = 0; i < 3; i++) {
				sb.append(array[i]);
			}
			String str = sb.toString();
			// SHA1签名生成
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes());
			byte[] digest = md.digest();

			StringBuffer hexstr = new StringBuffer();
			String shaHex = "";
			for (int i = 0; i < digest.length; i++) {
				shaHex = Integer.toHexString(digest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexstr.append(0);
				}
				hexstr.append(shaHex);
			}
			return hexstr.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AesException(AesException.ComputeSignatureError);
		}
	}
	
    public static Map<String, String> getRequestParams(HttpServletRequest request){
        
        Map<String, String> params = new HashMap<String, String>();
        if(null != request){
            Set<String> paramsKey = request.getParameterMap().keySet();
            for(String key : paramsKey){
            	if(!key.equals("mqif")&&!key.equals("vshop")){
            		params.put(key, request.getParameter(key));
            	}
            }
        }
        return params;
    }
}
