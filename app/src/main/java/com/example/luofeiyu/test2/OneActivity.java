package com.example.luofeiyu.test2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OneActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OneActivity.this, "退出", Toast.LENGTH_LONG).show();
                // 退出
//                finish();
                // 跳转
//                Intent intent = new Intent("abcd1234");
//                intent.addCategory("newbi");
//                startActivity(intent);
                // 打开网页
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("http://www.baidu.com"));
//                startActivity(intent);
                // 拨号
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:10086"));
//                startActivity(intent);

//                // 传递字符串
//                String data = "Android 真牛逼";
//                Intent intent = new Intent(OneActivity.this, TwoActivity.class);
//                intent.putExtra("extra_data", data);
//                startActivity(intent);

                // 反向传值

                Intent intent = new Intent(OneActivity.this, TwoActivity.class);
                startActivityForResult(intent, 1);

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "点击了增加", Toast.LENGTH_LONG).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "点击了减少", Toast.LENGTH_LONG).show();
                break;
            default:
        }
        return true;
    }



}
