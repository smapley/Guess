package com.smapley.guess.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smapley.guess.R;
import com.smapley.guess.application.LocalApplication;
import com.smapley.guess.mode.Pro_Item1_Mode;
import com.smapley.guess.utils.DateUtil;
import com.smapley.guess.utils.MyData;

import org.xutils.x;

/**
 * Created by smapley on 15/11/27.
 */
public class Pro_Item1_Holder extends BaseHolder {


    private ImageView use_pic;
    private TextView use_name;
    private TextView type;
    private TextView cre_date;
    private ImageView dynamic_pic;
    private TextView dynamic_name;
    private TextView praise_text;
    private TextView discuss_text;
    private String[] types;


    public Pro_Item1_Holder(View view) {
        super(view);

        use_pic = (ImageView) view.findViewById(R.id.adapter_pro_item1_iv_user_pic);
        use_name = (TextView) view.findViewById(R.id.adapter_pro_item1_tv_user_name);
        type = (TextView) view.findViewById(R.id.adapter_pro_item1_tv_type);
        cre_date = (TextView) view.findViewById(R.id.adapter_pro_item1_tv_cre_date);
        dynamic_pic = (ImageView) view.findViewById(R.id.adapter_pro_item1_iv_durl);
        dynamic_name = (TextView) view.findViewById(R.id.adapter_pro_item1_tv_dname);
        praise_text = (TextView) view.findViewById(R.id.adapter_pro_item1_tv_praise);
        discuss_text = (TextView) view.findViewById(R.id.adapter_pro_item1_tv_discuss);

    }

    public void setData(Context context, Pro_Item1_Mode mode) {
        types = context.getResources().getStringArray(R.array.dynamic_type);
        x.image().bind(use_pic, MyData.URL_PIC + mode.getUserEntity().getPicUrl(), LocalApplication.getInstance().CirtlesImage);
        use_name.setText(mode.getUserEntity().getTruename());
        type.setText(types[mode.getType()]);
        cre_date.setText(DateUtil.getDateString(mode.getDynamicEntity().getCre_date(), DateUtil.formatDate));
        switch (mode.getType()){
            case 0:
                //创建project
                dynamic_pic.setImageResource(R.mipmap.ico_project);
                dynamic_name.setText(mode.getProjectEntity().getName());
                break;
            case 1:
                //创建了任务
                dynamic_pic.setImageResource(R.mipmap.ico_task);
                dynamic_name.setText(mode.getTaskEntity().getName());
                break;
            case 2:
                //上传了文件
                switch (mode.getFileEntity().getType()){
                    case 1:
                        dynamic_pic.setImageResource(R.mipmap.ico_pic);
                        break;
                }
                dynamic_name.setText(mode.getFileEntity().getName());

                break;
        }


        praise_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        discuss_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
