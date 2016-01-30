package com.smapley.guess.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smapley.guess.R;
import com.smapley.guess.mode.GetZhuangMode;

import java.util.List;

/**
 * Created by hao on 2015/11/9.
 */
public class JingcaiAdapter extends BaseAdapter {

    private List<GetZhuangMode> list;
    private LayoutInflater inflater;
    private Context context;

    public JingcaiAdapter(Context context, List<GetZhuangMode> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final GetZhuangMode map = list.get(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_jingcai_item, null);
            viewHolder = new ViewHolder();
//            viewHolder.layout = (LinearLayout) convertView.findViewById(R.id.layout);
//            viewHolder.num = (TextView) convertView.findViewById(R.id.list_item1);
//            viewHolder.gold = (TextView) convertView.findViewById(R.id.list_item2);
//            viewHolder.pei = (TextView) convertView.findViewById(R.id.list_item3);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }




        return convertView;
    }

    public class ViewHolder {
        TextView title;
        ImageView paiming;
        ImageView jiami;

        TextView gold;
        TextView pei;
    }
}
