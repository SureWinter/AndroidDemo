package com.itheima.qqlogin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTools {
	
	/**
	 * ��������ת����һ���ַ���
	 * @param is ������
	 * @return ����һ���ַ��������ת��ʧ�ܾͷ���һ�����ַ�
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
