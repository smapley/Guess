package com.smapley.powerwork.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smapley.powerwork.R;
import com.smapley.powerwork.application.LocalApplication;
import com.smapley.powerwork.db.entity.ProjectEntity;
import com.smapley.powerwork.db.entity.UserBaseEntity;
import com.smapley.powerwork.http.callback.HttpCallBack;
import com.smapley.powerwork.http.params.BaseParams;
import com.smapley.powerwork.utils.MyData;

import org.xutils.x;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by smapley on 15/10/30.
 */
public class Sea_Item_Holder extends BaseHolder {

    private ImageView image;
    private TextView name;
    private TextView user;
    private View contentView;

    public Sea_Item_Holder(View view) {
        super(view);
        contentView = view;
        image = (ImageView) view.findViewById(R.id.adapter_sea_item_iv_image);
        name = (TextView) view.findViewById(R.id.adapter_sea_item_tv_name);
        user = (TextView) view.findViewById(R.id.adapter_sea_item_tv_user);
    }


    public void setData(final Context context, final ProjectEntity projectEntity, final UserBaseEntity userBaseEntity) {
        x.image().bind(image, MyData.URL_PIC + projectEntity.getPic_url(), LocalApplication.getInstance().CirtlesImage);
        name.setText(projectEntity.getName());
        user.setText(projectEntity.getCre_date() + "");
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SweetAlertDialog dialog = new SweetAlertDialog(context);
                dialog.showText(R.string.join)
                        .showCancelButton()
                        .showConfirmButton()
                        .setOnSweetClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onConfirmClick(SweetAlertDialog dialog) {
                                dialog.dismiss();
                                BaseParams params = new BaseParams(MyData.URL_JoinProject, userBaseEntity);
                                params.addBodyParameter("pro_id", projectEntity.getPro_id() + "");
                                x.http().post(params, new HttpCallBack(context,R.string.joing) {
                                    @Override
                                    public void onResult(String result, SweetAlertDialog dialog) {
                                        dialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE).commit();
                                    }
                                });
                            }

                            @Override
                            public void onFirstClick(SweetAlertDialog dialog) {

                            }

                            @Override
                            public void onCancelClick(SweetAlertDialog dialog) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        });
    }
}
