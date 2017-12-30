package com.example.gongxingheng.spider;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

import static com.example.gongxingheng.spider.MainActivity.SHOW_INDEX;

/**
 * Created by gongxingheng on 2016/11/20.
 */
//该线程用于爬取对应链接的目录信息
public class Thread_Internet_index implements Runnable {
    private Handler handler;
    private String classname;
    public Thread_Internet_index(Handler handler, String n){
        this.handler = handler;
        this.classname = n;
    }
    @Override
    public void run() {
        try{
            Index c = new Index();
            //List<String> text = c.getContent("xyfc",0);
            //Log.d("MainActivity",text.get(0));

                Message msg = Message.obtain();
                msg.obj = c.getIndex(classname);
                msg.what = SHOW_INDEX;
                handler.sendMessage(msg);



        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
