package com.saint.carsale.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.saint.carsale.R;

import java.util.List;
import java.util.Map;

/**
 * Created by zzh on 16-1-11.
 */
public class HomeAdapter extends BaseAdapter {
    //模式
    final int VIEW_TYPE=1;
    final int VIEW_1=0;


    private Context context;
    private List<Map<String,Object>> list;
    public HomeAdapter(Context context,List list){
        this.context=context;
        this.list=list;
    }

    @Override
    public int getItemViewType(int position) {
       return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder=null;
        int type=getItemViewType(position);
        if (convertView==null){
            switch (type){
                case 0:
                    holder=new Holder();
                    convertView= LayoutInflater.from(context).inflate(R.layout.home_viewpager,null);
                    holder.imageLeft= (ImageView) convertView.findViewById(R.id.textLeft);
                    holder.imageRight= (ImageView) convertView.findViewById(R.id.textRight);
                    convertView.setTag(holder);
                    break;
            }

        }else {
            holder= (Holder) convertView.getTag();
        }
        switch (type){
            case VIEW_1:
                    holder.imageRight.setImageResource(R.mipmap.ic_launcher);
                holder.imageLeft.setImageResource(R.mipmap.ic_launcher);
                break;

        }

        return convertView;
    }
    private class Holder{
        ImageView imageLeft,imageRight;

    }
}
