package com.example.gongxingheng.spider;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Message;
import android.widget.Toast;

import static com.example.gongxingheng.spider.MainActivity.SHOW_INDEX;

/**
 * Created by gongxingheng on 2016/11/30.
 */

public class MyReceiver extends BroadcastReceiver {
    private ConnectivityManager mConnectivityManager;

    private NetworkInfo netInfo;
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {

            mConnectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            netInfo = mConnectivityManager.getActiveNetworkInfo();
            if(netInfo != null && netInfo.isAvailable()) {

                /////////////网络连接

            } else {
                ////////网络断开
                intent.setClass(context, ErrorActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("s","net");
                context.startActivity(intent);


            }
        }



    //则说明当前无网络连接
        //System.out.println("------------> Network is validate");

    }  //如果无网络连接activeInfo为null


}
