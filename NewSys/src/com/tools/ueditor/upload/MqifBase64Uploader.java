package com.tools.ueditor.upload;


import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import com.tools.ueditor.PathFormat;
import com.tools.ueditor.define.AppInfo;
import com.tools.ueditor.define.BaseState;
import com.tools.ueditor.define.State;

public final class MqifBase64Uploader {

	public static State save(String content,Map<String, Object> conf) {
		String[] str = content.split(",");
		String suffix = "";
		if(str.length!=2){
			return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
		}else{
			String[] hstr = str[0].split(";");
			System.out.println(hstr[0]);
			if(!hstr[0].equals("data:image/png")&&!hstr[0].equals("data:image/jpeg")&&!hstr[0].equals("data:image/gif")){
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}
			suffix=hstr[0].replace("data:image/", ".");
		}
		
		byte[] data = decode(str[1]);

		long maxSize = ((Long) conf.get("maxSize")).longValue();

		if (!validSize(data, maxSize)) {
			return new BaseState(false, AppInfo.MAX_SIZE);
		}


		String savePath = PathFormat.parse((String) conf.get("savePath"),
				(String) conf.get("filename"));
		
		savePath = savePath + suffix;
		String physicalPath = (String) conf.get("rootPath") + savePath;

		State storageState = StorageManager.saveBinaryFile(data, physicalPath);

		if (storageState.isSuccess()) {
			storageState.putInfo("url", PathFormat.format(savePath));
			storageState.putInfo("type", suffix);
			storageState.putInfo("original", "");
		}

		return storageState;
	}

	private static byte[] decode(String content) {
		return Base64.decodeBase64(content);
	}

	private static boolean validSize(byte[] data, long length) {
		return data.length <= length;
	}
	
}