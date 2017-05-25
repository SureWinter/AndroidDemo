package com.example.luofeiyu.mobilesafe.Activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luofeiyu.mobilesafe.R;
import com.example.luofeiyu.mobilesafe.Utils.StreamUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LaunchActivity extends AppCompatActivity implements View.OnClickListener {

    protected static final String tag = "LaunchActivity";

    private TextView version_name;
    private int mLocalVersionCode;
    private String mVersionDes;
    private String mDownloadUrl;


    protected static final int UPDATE_VERSION = 100;
    protected static final int ENTER_HOME = 101;
    protected static final int URL_ERROR = 102;
    protected static final int IO_ERROR = 103;
    protected static final int JSON_ERROR = 104;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        // 初始化UI
        initUI();

        //
        initData();

        TextView text = new TextView(getApplicationContext());
        text.setOnClickListener(this);
    }

    private void initUI() {
        version_name = (TextView) findViewById(R.id.version_name);
    }

    private void initData() {
        version_name.setText("版本名称"+getVersionName());

        mLocalVersionCode = getVersionCode();

        checkVersion();
    }

    private void checkVersion() {

        new Thread(){
            public void run() {
                Message msg = Message.obtain();
                long startTime = System.currentTimeMillis();
                try {
                    URL url = new URL("http://www.iosfly.com/checkVersionJson.php");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(2000);
                    connection.setReadTimeout(2000);

                    if (connection.getResponseCode() == 200) {
                        InputStream is = connection.getInputStream();
                        String json = StreamUtil.streamToString(is);
                        Log.i(tag,json);
                        JSONObject jsonObject = new JSONObject(json);
                        String versionName = jsonObject.getString("versionName");
                        mVersionDes = jsonObject.getString("versionDes");
                        String versionCode = jsonObject.getString("versionCode");
                        mDownloadUrl = jsonObject.getString("downloadUrl");

                        Log.i(tag, versionName);
                        Log.i(tag, mVersionDes);
                        Log.i(tag, versionCode);
                        Log.i(tag, mDownloadUrl);

                        if (mLocalVersionCode < Integer.parseInt(versionCode)) {
                            msg.what = UPDATE_VERSION;
                        } else {
                            msg.what = ENTER_HOME;
                        }
                    }
                }
                catch (MalformedURLException e) {
                    e.printStackTrace();;
                    msg.what = URL_ERROR;
                }catch (IOException e) {
                    e.printStackTrace();
                    msg.what = IO_ERROR;
                }catch (JSONException e) {
                    e.printStackTrace();
                    msg.what = JSON_ERROR;
                }finally {
                    long endTime = System.currentTimeMillis();
                    if (endTime - startTime < 4000){
                        try {
                            Thread.sleep(4000-(endTime-startTime));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    mHandler.sendMessage(msg);

                }
            }

        }

    }

    private Handler mHandler = new Handler() {
        @Override

        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case UPDATE_VERSION:
                    // 弹出提示框
                    showUpdateDialog();

                    Toast.makeText(LaunchActivity.this, "niubi", Toast.LENGTH_SHORT).show();
                    break;
                case ENTER_HOME:
                    // 进入程序主界面
                    break;
                case URL_ERROR:
                    break;
                case IO_ERROR:
                    break;
                case JSON_ERROR:
                    break;
            }
        }


    };

    private void showUpdateDialog() {

        Log.i(tag,);
        Log.i(tag,)
        Log.i(tag,)
        Log.i(tag,)
        Log.i(tag,)
        Log.i(tag,)
        Log.i(tag,)
        Log.i(tag,)
    }


    private String getVersionName() {
        PackageManager pm = getPackageManager();

        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    private int getVersionCode() {
        PackageManager pm = getPackageManager();

        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void onClick(View v) {

    }
}
