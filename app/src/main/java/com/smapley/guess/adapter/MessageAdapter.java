package com.smapley.guess.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smapley.guess.R;
import com.smapley.guess.db.modes.MessageMode;
import com.smapley.guess.holder.Mes_Holder;
import com.smapley.guess.mode.BaseMode;

import java.util.List;

/**
 * Created by smapley on 15/10/26.
 */
public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<BaseMode> list;
    private LayoutInflater inflater;

    public MessageAdapter(Context context, List<BaseMode> list) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        switch (viewType) {
            case 0:
                view = inflater.inflate(R.layout.adapter_mes_item, parent, false);
                return new Mes_Holder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case 0:
                ((Mes_Holder) holder).setData(context, (MessageMode) list.get(position));
                break;
        }

    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        else return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }


}
