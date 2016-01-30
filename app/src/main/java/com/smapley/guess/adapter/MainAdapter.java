package com.smapley.guess.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smapley.guess.R;
import com.smapley.guess.activity.MainActivity;

import java.util.List;
import java.util.Map;

/**
 * Created by hao on 2015/11/9.
 */
public class MainAdapter extends BaseAdapter {

    private List<Map<String, String>> list;
    private LayoutInflater inflater;
    private Context context;

    public MainAdapter(Context context, List<Map<String, String>> list) {
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
        final Map<String, String> map = list.get(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.layout = (LinearLayout) convertView.findViewById(R.id.layout);
            viewHolder.num = (TextView) convertView.findViewById(R.id.list_item1);
            viewHolder.gold = (TextView) convertView.findViewById(R.id.list_item2);
            viewHolder.pei = (TextView) convertView.findViewById(R.id.list_item3);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        boolean check = false;
        for (Map map1 : MainActivity.removeList) {
            if (map1.equals(map)) {
                check = true;
            }
        }
        viewHolder.layout.setBackgroundColor(check ? Color.YELLOW : Color.TRANSPARENT);
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.check(map);
                notifyDataSetChanged();
            }
        });
        viewHolder.num.setText(map.get("number"));
        viewHolder.gold.setText(map.get("gold"));
        viewHolder.pei.setText(map.get("pei"));


        return convertView;
    }

    public class ViewHolder {
        LinearLayout layout;
        TextView num;
        TextView gold;
        TextView pei;
    }
}
