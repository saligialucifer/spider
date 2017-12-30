package com.example.gongxingheng.spider;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by gongxingheng on 2016/11/17.
 */

public class Index {
    //classname为查找数据div里面的名字，因为东大class名字重复，需要一个数字来判断是哪一个div
    public ArrayList<String> getIndex(String classname){//需要优化一下爬虫，时间单独取出来
        try{
            Document doc = Jsoup.connect("http://neunews.neu.edu.cn/campus/part/"+classname).get();
            Elements content = doc.getElementsByClass("ny_middle").get(0).getElementsByTag("li");
            String t = "";
            ArrayList<String> text = new ArrayList<String>();
            for(Element h3 : content){
                t = h3.text();
                text.add(t);
            }
            return text;
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<String> getUrl(String classname){
        try{
            Document doc = Jsoup.connect("http://neunews.neu.edu.cn/campus/part/"+classname).get();
            Elements links = doc.getElementsByClass("ny_middle").get(0).getElementsByTag("a");
            String u = "";
            ArrayList<String> url = new ArrayList<String>();
            for(Element h3 : links){
                u = h3.attr("href");
                url.add(u);
            }
            return url;
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<String> getIndex_rcpy(){
        try{
            Document doc = Jsoup.connect("http://neunews.neu.edu.cn/rencaipeiyang/").get();//ny_middle
            Elements content = doc.getElementsByClass("ny_middle").get(0).getElementsByTag("li");
            String t = "";
            ArrayList<String> text = new ArrayList<String>();
            for(Element h3 : content){
                t = h3.text();
                text.add(t);
            }
            //Log.d("sb",text.get(0)+text.get(1));
            return text;
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }

    }
    public ArrayList<String> getUrl_rcpy(){
        try{
            Document doc = Jsoup.connect("http://neunews.neu.edu.cn/rencaipeiyang/").get();
            Elements links = doc.getElementsByClass("ny_middle").get(0).getElementsByTag("a");
            String u = "";
            ArrayList<String> url = new ArrayList<String>();
            for(Element h3 : links){
                u = h3.attr("href");
                url.add(u);
            }
            return url;
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
    public int getPages(String webname){
        try{
            Document doc = Jsoup.connect("http://neunews.neu.edu.cn/campus/part/"+webname).get();
            Elements content = doc.getElementsByClass("epages").get(0).getElementsByTag("strong");
            String t = content.first().text();
            return Integer.valueOf(t);
        }catch(IOException e){
            e.printStackTrace();
            return -1;
        }
    }


}
