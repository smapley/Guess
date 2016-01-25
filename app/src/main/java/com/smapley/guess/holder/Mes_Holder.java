package com.smapley.guess.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smapley.guess.R;
import com.smapley.guess.application.LocalApplication;
import com.smapley.guess.db.modes.MessageMode;
import com.smapley.guess.utils.DateUtil;
import com.smapley.guess.utils.MyData;

import org.xutils.x;

/**
 * Created by smapley on 15/12/30.
 */
public class Mes_Holder extends BaseHolder{

    private ImageView pic;
    private TextView title;
    private TextView time;
    private TextView content;

    public Mes_Holder(View view) {
        super(view);
        pic=(ImageView) view.findViewById(R.id.mes_iv_pic);
        title=(TextView)view.findViewById(R.id.mes_tv_title);
        time=(TextView)view.findViewById(R.id.mes_tv_time);
        content=(TextView)view.findViewById(R.id.mes_tv_content);
    }

    public void setData(Context context,MessageMode mode){

        x.image().bind(pic, MyData.URL_PIC+mode.getUserEntity().getPicUrl(), LocalApplication.getInstance().CirtlesImage);
        title.setText(mode.getUserEntity().getTruename());
        time.setText(DateUtil.getDateString(mode.getMessageEntity().getRefresh(),DateUtil.formatDate));
        content.setText(mode.getMessageEntity().getDetails());
    }
}
