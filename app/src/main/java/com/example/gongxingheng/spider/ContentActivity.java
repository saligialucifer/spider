package com.example.gongxingheng.spider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class ContentActivity extends AppCompatActivity {
    private TextView title;
    private String tit;
    private TextView content;
    private static final int SHOW_RESPONSE = 0;
    private String url="";
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case SHOW_RESPONSE:
                    ArrayList<String> t= (ArrayList<String>)msg.obj;
                    String c="";
                    tit = t.get(0);
                    title.setText(t.get(0));
                    for(int i =1;i<t.size();i++){
                        c += t.get(i)+"\n\n";
                    }
                    content.setText(c);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition fade = TransitionInflater.from(this).inflateTransition(R.transition.fade);
        //退出时使用
        getWindow().setExitTransition(fade);
        //第一次进入时使用
        getWindow().setEnterTransition(fade);
        //再次进入时使用
        getWindow().setReenterTransition(fade);
        setContentView(R.layout.activity_content);
        ShareSDK.initSDK(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.content_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title =(TextView)findViewById(R.id.title);
        content = (TextView)findViewById(R.id.content);
        sendRequestWithHttpURLConnection();
        Intent intent = getIntent();
        url = intent.getStringExtra("url");

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.content_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                //TODO search
                showShare();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void sendRequestWithHttpURLConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    MyContext c = new MyContext();
                    //stitle = c.getContent(url);
                    Message msg = Message.obtain();
                    msg.obj = c.getContent(url);
                    msg.what = SHOW_RESPONSE;
                    handler.sendMessage(msg);



                }catch (Exception e){
                    Intent intent = new Intent(ContentActivity.this,ErrorActivity.class);
                    intent.putExtra("s","fda");
                    startActivity(intent);
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("东大新闻");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("《"+tit+"》"+"  这篇文章不错");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        //oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        //oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);

// 启动分享GUI
        oks.show(this);
    }
}
