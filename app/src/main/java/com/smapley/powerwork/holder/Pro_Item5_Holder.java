package com.smapley.powerwork.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smapley.powerwork.R;
import com.smapley.powerwork.application.LocalApplication;
import com.smapley.powerwork.db.entity.UserEntity;
import com.smapley.powerwork.utils.MyData;

import org.xutils.x;

/**
 * Created by smapley on 15/12/30.
 */
public class Pro_Item5_Holder extends  BaseHolder{

    private ImageView pic;
    private TextView name;
    private View contenView;

    public Pro_Item5_Holder(View view) {
        super(view);
        contenView=view;
        pic=(ImageView)view.findViewById(R.id.pro_item5_iv_pic);
        name=(TextView)view.findViewById(R.id.pro_item5_tv_name);
    }
    public void setData(Context context,UserEntity mode){
        x.image().bind(pic, MyData.URL_PIC+mode.getPicUrl(), LocalApplication.getInstance().CirtlesImage);
        name.setText(mode.getTruename());
        contenView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
