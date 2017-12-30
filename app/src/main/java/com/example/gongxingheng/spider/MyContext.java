package com.example.gongxingheng.spider;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by gongxingheng on 2016/11/21.
 */

public class MyContext {
    private ArrayList<String> p=null;
    public ArrayList<String> getContent(String u){
        try{
            String t = "http://neunews.neu.edu.cn";
            String url = t+u;

            Document doc = Jsoup.connect(url).get();
            Element content = doc.getElementsByClass("ny_wzbt1").get(0);
            p = new ArrayList<String>();
            p.add(content.text());

            Elements ps = doc.getElementById("content").getElementsByTag("p");
            if(ps==null){
                ps = doc.getElementById("content").getElementsByTag("br");
            }else{
                if("".equals(ps.first().text())){
                    ps = doc.getElementById("content").getElementsByTag("span");

                }
            }

            for(Element e:ps){
                p.add(e.text());
            }

            return p;
        }catch(IOException e){
            e.printStackTrace();


            return null;
        }
    }
}
