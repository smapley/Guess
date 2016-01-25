package com.smapley.powerwork.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smapley.powerwork.R;
import com.smapley.powerwork.db.entity.UserEntity;
import com.smapley.powerwork.holder.Pro_Item5_Holder;

import java.util.List;

/**
 * Created by smapley on 15/11/17.
 */
public class ProItem5Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<UserEntity> list;
    private LayoutInflater inflater;


    public ProItem5Adapter(Context context, List<UserEntity> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case 0:
                view = inflater.inflate(R.layout.adapter_pro_item5_item, parent, false);
                return new Pro_Item5_Holder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case 0:
                ((Pro_Item5_Holder) holder).setData(context, list.get(position));
                break;

        }
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        else
            return list.size();
    }


    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }
}
