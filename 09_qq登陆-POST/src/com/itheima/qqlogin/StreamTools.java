package com.itheima.qqlogin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTools {
	
	/**
	 * 把输入流转换成一个字符串
	 * @param is 输入流
	 * @return 返回一个字符串，如果转换失败就返回一个空字符
	 */
	public static String readStream(InputStream is){
		
		try {
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			byte[] buffer = new byte[1024];
			int len = -1;
			while((len = is.read(buffer)) != -1){
				 baos.write(buffer, 0, len);
				}
			is.close();
			return new String(baos.toByteArray());
		} catch (Exception e) {
			return "";
		}
		
	
	}

}
