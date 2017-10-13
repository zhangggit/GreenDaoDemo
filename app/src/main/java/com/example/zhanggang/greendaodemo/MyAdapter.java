package com.example.zhanggang.greendaodemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhanggang on 2017/10/13.
 * listview的适配器
 */

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<User> list;

    public MyAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder =null;
        if (view==null){  //当view为空时创建视图
            view=View.inflate(context,R.layout.item,null);
            holder=new ViewHolder();
            holder.name=view.findViewById(R.id.item_name);
            holder.sex=view.findViewById(R.id.item_sex);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        //赋值
        holder.name.setText("姓名："+list.get(i).getName());
        holder.sex.setText("性别："+list.get(i).getSex());
        return view;
    }
    class ViewHolder{
        TextView name,sex;
    }
}
