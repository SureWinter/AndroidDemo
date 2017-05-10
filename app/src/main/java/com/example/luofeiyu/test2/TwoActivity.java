package com.example.luofeiyu.test2;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
//        // 传递字符串
//        Intent intent = getIntent();
//        String data = intent.getStringExtra("extra_data");
//        Log.d("sdasdasds",data);

        // 反向传值
        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener()) {
            @Override
                    public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(@"data_return","Hello adsasdadas");
                setResult(RESULT_OK,intent);
                finish();
            }
        };
    }
}
