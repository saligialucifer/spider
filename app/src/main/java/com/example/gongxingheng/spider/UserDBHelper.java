package com.example.gongxingheng.spider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gongxingheng on 2016/12/9.
 */
public class UserDBHelper{
    private static UserDBHelper instance ;
    private SQLiteDatabase sqLiteDatabase;
    private LoginDBHelper loginDBHelper;
    public UserDBHelper(Context context){
        loginDBHelper = new LoginDBHelper(context);
        sqLiteDatabase = loginDBHelper.getWritableDatabase();
    }
    public static UserDBHelper getInstance(Context context){
        if(instance==null){
            instance = new UserDBHelper(context);
        }
        return instance;
    }

    public class LoginDBHelper extends SQLiteOpenHelper {
        public final static int VERSION = 1;// 版本号
        public final static String TABLE_NAME = "user" ;// 表名
        // 后面ContentProvider使用
        public final static String DATABASE_NAME = "dongdaxinwen_user.db";
        public LoginDBHelper(Context context) {
            super(context, DATABASE_NAME, null, VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String sql ="create table if not exists user ("
                    +"account text primary key,password text,nickname text)";
            sqLiteDatabase.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }


    }
    public boolean insert(String name,String pass,String nickname){
        ContentValues contentValues = new ContentValues();
        contentValues.put("account",name);
        contentValues.put("password",pass);
        contentValues.put("nickname",nickname);
        long flag =0;
            flag =sqLiteDatabase.insert("user","account",contentValues);
        if (flag == -1){
            return false;
        }else{
            return true;
        }


    }
    public String serch(String name){
        String pass="";
        String sql ="select password from user where account =?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,new String[]{name});
        while(cursor.moveToNext()){
            pass = cursor.getString(cursor.getColumnIndex("password"));
        }
        return pass;
    }
}

