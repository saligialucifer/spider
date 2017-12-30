package com.example.gongxingheng.spider;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by gongxingheng on 2016/11/29.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;                        //运行上下文
    private ArrayList<String> listItems;    //商品信息集合
    private LayoutInflater listContainer;           //视图容器
    public final class ListItemView{                //自定义控件集合
        public TextView title;

    }


    public MyAdapter(Context context, ArrayList<String> listItems) {
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        this.listItems = listItems;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return listItems.size();
    }

    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }







    /**
     * ListView Item设置
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        //自定义视图
        ListItemView  listItemView = null;

            listItemView = new ListItemView();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.list_item, null);
            //获取控件对象
            listItemView.title = (TextView)convertView.findViewById(R.id.indexid);
            //设置控件集到convertView
            //convertView.setTag(listItemView);

        convertView.setMinimumHeight(200);
        listItemView.title.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
        listItemView.title.setText(listItems.get(position));

        if(position%2==0){
            convertView.setBackgroundColor(0xfff0f0f0);
        }

        return convertView;
    }
}
