package com.example.gongxingheng.spider;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.widget.TextView;


public class ErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String statu = intent.getStringExtra("s");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        TextView tv = (TextView)findViewById(R.id.tv);

        if(statu.equals("net")){
            tv.setText("不好意思，没有可用的网络");
        }else{
            tv.setText("非常抱歉，没能爬取到网页╮(╯▽╰)╭");
        }

        TextPaint tp = tv.getPaint();
        tp.setFakeBoldText(true);
    }
    @Override
    public void onBackPressed() {
//数据是使用Intent返回
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        Intent intent = new Intent();
        intent.putExtra("error","neterror");
        startActivity(intent);
        //registerReceiver(MyReceiver, mFilter);
        //设置返回数据


    }
}
