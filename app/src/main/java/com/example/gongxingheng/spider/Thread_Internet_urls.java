package com.example.gongxingheng.spider;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;

import static com.example.gongxingheng.spider.MainActivity.SHOW_URL;

/**
 * Created by gongxingheng on 2016/11/20.
 */
//该线程用于爬取链接信息
public class Thread_Internet_urls implements Runnable {
    private Handler handler;
    private String classname;
    public Thread_Internet_urls( Handler handler, String n){

        this.handler = handler;
        this.classname = n;
    }
    @Override
    public void run() {
        try{
            Index c = new Index();
            //List<String> text = c.getContent("xyfc",0);
            //Log.d("MainActivity",text.get(0));
            if(classname.equals("人才培养")){
                Message msg = Message.obtain();
                msg.obj = c.getUrl_rcpy();
                msg.what = SHOW_URL;
                handler.sendMessage(msg);
            }else{
                Message msg = Message.obtain();
                msg.obj = c.getUrl(classname);
                msg.what = SHOW_URL;
                handler.sendMessage(msg);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
