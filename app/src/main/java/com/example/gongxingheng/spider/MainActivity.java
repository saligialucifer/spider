package com.example.gongxingheng.spider;


import android.app.ActivityOptions;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


import android.transition.Transition;
import android.transition.TransitionInflater;

import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.AdapterView;

import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.twotoasters.jazzylistview.JazzyListView;
import com.twotoasters.jazzylistview.effects.CardsEffect;

import net.steamcrafted.loadtoast.LoadToast;

import java.util.ArrayList;
import java.util.List;


import me.drakeet.materialdialog.MaterialDialog;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,AdapterView.OnItemClickListener,SeekBar.OnSeekBarChangeListener {
    public static final int SHOW_INDEX = 0;
    public static final int SHOW_URL = 1;
    public static final int SHOW_PAGE = 2;
    public static final int XIAOYUANWENXUE = 3;
    public static final int XIAOYOUFENGCAI = 4;
    public static final int XUESHUKEYAN = 5;
    public static final int ZHAOSHENGJIUYE = 6;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private lib.homhomlib.design.SlidingLayout slidingLayout;


    private SQLiteDatabase db;
    private SeekBar seekBar;
    private JazzyListView jazzyListView;
    private Image3DSwitchView image3DSwitchView;
    private TextView startshow;
    private TextView version;


    private int pages;
    private int location;
    private int tag;
    private LoadToast lt;
    private ArrayList<String> urls = null;
    private MyAdapter aa;
    private MainActivity context;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_INDEX://实例化当前被选择的listview,添加适配器
                    ArrayList<String> text = (ArrayList<String>) msg.obj;
                    aa = new MyAdapter(context, text);
                    aa.notifyDataSetChanged();
                    jazzyListView.setAdapter(aa);
                    jazzyListView.setVisibility(View.VISIBLE);
                    //lv.setAdapter(aa);
                    //lv.setVisibility(View.VISIBLE);
                    slidingLayout.setVisibility(View.VISIBLE);
                    seekBar.setVisibility(View.VISIBLE);
                    image3DSwitchView.setVisibility(View.GONE);
                    startshow.setVisibility(View.GONE);
                    version.setVisibility(View.GONE);

                    break;
                case SHOW_URL://实例化链接数组
                    urls = (ArrayList<String>) msg.obj;
                    break;
                case SHOW_PAGE:
                    pages = (int) msg.obj;
                    seekBar.setProgress(0);
                    seekBar.setMax(pages / 30);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Toast.makeText(MainActivity.this,"jij",Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);





        jazzyListView = (JazzyListView) findViewById(R.id.list);
        jazzyListView.setOnItemClickListener(this);
        jazzyListView.setTransitionEffect(new CardsEffect());

        jazzyListView.setVisibility(View.GONE);
        //lv = (ListView) findViewById(R.id.lv);
        //lv.setOnItemClickListener(this);
        //lv.setVisibility(View.GONE);
        context = this;
        slidingLayout = (lib.homhomlib.design.SlidingLayout)findViewById(R.id.slidingLayout);
        slidingLayout.setVisibility(View.GONE);

        lt =  new LoadToast(context);
        lt.setTextColor(Color.BLACK).setBackgroundColor(Color.WHITE).setProgressColor(Color.BLACK);
        lt.setTranslationY(100);

        seekBar = (SeekBar)findViewById(R.id.sb);

//        seekBar = (SeekBar) findViewById(R.id.sb);
        seekBar.setOnSeekBarChangeListener(this);

        image3DSwitchView = (Image3DSwitchView)findViewById(R.id.image_switch_view);
        image3DSwitchView.setOnImageSwitchListener(new Image3DSwitchView.OnImageSwitchListener() {
            @Override
            public void onImageSwitch(int currentImage) {
                // Log.d("TAG", "current image is " + currentImage);
            }
        });
        image3DSwitchView.setCurrentImage(1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        startshow = (TextView)findViewById(R.id.startshow);
        version = (TextView)findViewById(R.id.version);


        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        db = mySQLiteOpenHelper.getWritableDatabase();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }








    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //跳转
        String tem;

        switch (tag) {
            case XIAOYUANWENXUE:
                if (location == 0) {
                    tem = "DDRW.html";
                } else {
                    tem = "DDRW_" + location + ".html";
                }
                showlist(tem);
                showurl(tem);
                //MyToastshow("当前位置",location+1+"");

                lt.success();


                //Toast.makeText(MainActivity.this, "当前位置" + (location + 1), Toast.LENGTH_SHORT).show();
                break;
            case XIAOYOUFENGCAI:
                if (location == 0) {
                    tem = "XYCQ.html";
                } else {
                    tem = "XYCQ_" + location + ".html";
                }
                showlist(tem);
                showurl(tem);
                lt.success();
                //MyToastshow("当前位置",location+1+"");
                //Toast.makeText(MainActivity.this, "当前位置" + (location + 1), Toast.LENGTH_SHORT).show();
                break;
            case XUESHUKEYAN:

                if (location == 0) {
                    tem = "XKJS.html";
                } else {
                    tem = "XKJS_" + location + ".html";
                }
                showlist(tem);
                showurl(tem);
                lt.success();
                //MyToastshow("当前位置",location+1+"");
                //Toast.makeText(MainActivity.this, "当前位置" + (location + 1), Toast.LENGTH_SHORT).show();
                break;
            case ZHAOSHENGJIUYE:

                if (location == 0) {
                    tem = "ZSJY.html";
                } else {
                    tem = "ZSJY_" + location + ".html";
                }
                showlist(tem);
                showurl(tem);
                lt.success();
                //Toast.makeText(MainActivity.this, "当前位置" + (location + 1), Toast.LENGTH_SHORT).show();

                //Toast.makeText(MainActivity.this,"当前位置"+(location+1),Toast.LENGTH_SHORT).show();
                break;
        }

    }



    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //拖动中
        lt.show();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean formUser) {
        //progress值为
        location = progress;
        lt.setText("正在跳转到……"+(location+1));


    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        image3DSwitchView.clear();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {

            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        switch (id) {

            case R.id.xywx:
                showlist("DDRW.html");//传入的是http://neunews.neu.edu.cn/campus/part/DDRW.html后面部分
                showurl("DDRW.html");
                //addinfo(mySQLiteOpenHelper,XYWX);
                tag = XIAOYUANWENXUE;


                getPage("DDRW.html");
                break;
            case R.id.xyfc:
                showlist("XYCQ.html");
                showurl("XYCQ.html");
                //addinfo(mySQLiteOpenHelper,XYFC);
                tag = XIAOYOUFENGCAI;

                getPage("XYCQ.html");
                break;

            case R.id.xsky:
                showlist("XKJS.html");
                showurl("XKJS.html");
                //addinfo(mySQLiteOpenHelper,XSKY);
                tag = XUESHUKEYAN;

                getPage("XKJS.html");
                break;
            case R.id.zsjy:
                showlist("ZSJY.html");
                showurl("ZSJY.html");
                //addinfo(mySQLiteOpenHelper,ZSJY);
                tag = ZHAOSHENGJIUYE;

                getPage("ZSJY.html");
                break;
            case R.id.nav_send:
                final MaterialDialog  mMaterialDialog = new MaterialDialog(this).setTitle("android技术交流");
                mMaterialDialog.setPositiveButton("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                            }
                        });
                mMaterialDialog.show();

                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        send(arg2);//agr2是listview被点击的那个菜单

    }

    private void getPage(String n) {//起线程得到一共多少页
        new Thread(new Thread_Internet_Pages(handler, n)).start();
    }

    private void showlist(String n) {//n作为网页最后一部分传入 起线程得到目录
        new Thread(new Thread_Internet_index(handler, n)).start();//index线程获取目录的信息，同时传入当前的handler
    }

    private void showurl(String n) {//起线程得到链接
        new Thread(new Thread_Internet_urls(handler, n)).start();//url线程获取的是目录对应链接
    }
    private void addinfo(MySQLiteOpenHelper db,int flag){//对应点击添加数据库
        new Thread(new Thread_sql_add(db,flag)).start();
    }



    private void send(int p) {//listview点击就是做这个方法，把链接传给展示内容activity
        Intent intent = new Intent(context, ContentActivity.class);
        intent.putExtra("url", urls.get(p));
        //Log.d("urls",urls.get(p+1));

        Transition fade = TransitionInflater.from(this).inflateTransition(R.transition.fade);
        //退出时使用
        getWindow().setExitTransition(fade);
        //第一次进入时使用
        getWindow().setEnterTransition(fade);
        //再次进入时使用
        getWindow().setReenterTransition(fade);
        startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        //startActivity(intent);

    }


}



