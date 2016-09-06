package com.tools;
import com.util.base64.Base64;
public class Base64Util {
	/**
	 * Base64加密工具
	 * @param param 需要加密的字符串
	 * @return 处理后的字符创
	 */
	public static String encoding(String param) {
		return Base64.encode(param, "UTF-8");
	}

	/**
	 * Base64解密工具
	 * @param param 需要解密的字符创
	 * @return 处理后的字符创
	 */
	public static String decoding(String param) {
		return Base64.decode(param, "UTF-8");
	}
}
