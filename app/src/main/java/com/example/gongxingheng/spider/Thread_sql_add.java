package com.example.gongxingheng.spider;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by gongxingheng on 2016/12/6.
 */

public class Thread_sql_add implements Runnable {
    public static final int XYWX = 0;
    public static final int XYFC = 1;
    public static final int XSKY = 2;
    public static final int ZSJY = 3;
    private SQLiteDatabase db;
    private MySQLiteOpenHelper mydb;
    private MyContext myContext;
    private Index index;
    private ContentValues values;
    private ArrayList<String>context;
    private ArrayList<String>urls;
    private int page = 0;
    private int flag=0;
    public Thread_sql_add(MySQLiteOpenHelper mydb,int flag){
        this.mydb = mydb;
        this.db = mydb.getWritableDatabase();
        myContext = new MyContext();
        index = new Index();
        values = new ContentValues();
        this.flag = flag;
    }
    @Override
    public void run() {
        try{

            switch (flag){
                case XYWX:
                    additem("DDRW");
                    break;
                case XYFC:
                    additem("XYCQ");
                    break;
                case XSKY:
                    additem("XKJS");
                    break;
                case ZSJY:
                    additem("ZSJY");
                    break;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void additem(String name){
        page = index.getPages(name+".html")/30;
        String tablename="";
        switch (name){
            case "DDRW":
                tablename = "xywx";
                break;
            case "XYCQ":
                tablename = "xyfc";
                break;
            case "XKJS":
                tablename = "xsky";
                break;
            case "ZSJY":
                tablename = "zsjy";
                break;
        }
        if(page!=0){
            for(int i = 1;i<page;i++){
                urls = index.getUrl(name+"_"+i+".html");
                for(String url :urls){
                    context = myContext.getContent(url);
                    String title = context.get(0);
                    String p="";
                    for(int j =1;j<context.size();j++){
                        p+=context.get(j);
                    }
                    values.put("title",title);
                    values.put("content",p);
                    db.insert(tablename,null,values);
                    values.clear();
                }

            }
        }else{
            urls = index.getUrl(name+".html");
            for(String url :urls){
                context = myContext.getContent(url);
                String title = context.get(0);
                String p="";
                for(int j =1;j<context.size();j++){
                    p+=context.get(j);
                }
                values.put("title",title);
                values.put("content",p);
                db.insert(tablename,null,values);
                values.clear();
            }
        }


    }
}
