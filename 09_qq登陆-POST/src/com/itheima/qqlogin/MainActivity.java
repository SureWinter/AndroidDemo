package com.itheima.qqlogin;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText et_qq;
	private EditText et_pwd;
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			String result = (String) msg.obj;
			Toast.makeText(MainActivity.this, result, 0).show();
			
			
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et_qq = (EditText) findViewById(R.id.et_qq);
		

		et_pwd = (EditText) findViewById(R.id.et_pwd);
	}
	
	public void login(View view){
		final String qq = et_qq.getText().toString().trim();
		final String pwd = et_pwd.getText().toString().trim();
		final String urlStr = "http://192.168.12.28:8080/web/servlet/LoginServlet";
		
		if(TextUtils.isEmpty(qq) || TextUtils.isEmpty(pwd)){
			Toast.makeText(this, "������qq�����������", 0).show();
			return;
		}else{
			final String data = "username="+qq+"&password="+pwd;
			final String path = urlStr ;
			new Thread(){
				public void run() {
					try {
						URL url = new URL(path);
						HttpURLConnection connection = (HttpURLConnection) url.openConnection();

						//��������ʽΪPOST
						connection.setRequestMethod("POST");
						connection.setConnectTimeout(5000);
						//���ñ����ͼ����ݳ���
						connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
						connection.setRequestProperty("Content-Length", data.length()+"");
						//�������������������д����
						connection.setDoOutput(true);
						connection.getOutputStream().write(data.getBytes());
						
						int code = connection.getResponseCode();
						if(code == 200){
							InputStream is = connection.getInputStream();
							String result = StreamTools.readStream(is);
							
							Message msg= Message.obtain();
							msg.obj = result;
							handler.sendMessage(msg);
						}else
						{
							;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				};
			}.start();
		}
	}

	
}
