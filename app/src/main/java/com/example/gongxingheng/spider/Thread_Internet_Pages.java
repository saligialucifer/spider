package com.example.gongxingheng.spider;

import android.os.Handler;
import android.os.Message;

import static com.example.gongxingheng.spider.MainActivity.SHOW_PAGE;

/**
 * Created by gongxingheng on 2016/11/21.
 */

public class Thread_Internet_Pages implements Runnable {
    private String webname;
    private Handler handler;
    public Thread_Internet_Pages(Handler handler,String n){
        this.webname = n;
        this.handler =handler;
    }
    @Override
    public void run() {
        try{
            Index c = new Index();
            //List<String> text = c.getContent("xyfc",0);
            //Log.d("MainActivity",text.get(0));
            if(webname.equals("人才培养")){

            }else{
                Message msg = Message.obtain();
                msg.obj = c.getPages(webname);
                msg.what = SHOW_PAGE;
                handler.sendMessage(msg);

            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
