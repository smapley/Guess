package com.smapley.guess.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.smapley.guess.R;
import com.smapley.guess.http.params.AddZhuangParams;
import com.smapley.guess.http.service.AddZhuangService;
import com.smapley.guess.utils.MyData;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by smapley on 16/1/30.
 */
@ContentView(R.layout.activity_addzhuang)
public class AddZhuang extends BaseActivity {

    @ViewInject(R.id.layout)
    private View layout;
    @ViewInject(R.id.title)
    private EditText title;
    @ViewInject(R.id.type)
    private Spinner type;
    @ViewInject(R.id.money)
    private EditText money;
    @ViewInject(R.id.peilv)
    private EditText peilv;
    @ViewInject(R.id.password)
    private EditText password;

    private AddZhuangService addZhuangService = new AddZhuangService() {
        @Override
        public void Succ(String data) {
            if(Integer.parseInt(data)>0){
                showToast("房间创建成功！");
            }else{
                showToast("房间创建失败！");
            }
        }
    };

    @Override
    protected void initParams() {

    }

    public void publish(View view) {
        addZhuangService.load(new AddZhuangParams(MyData.user1,title.getText().toString(),
                "1", money.getText().toString(),
                peilv.getText().toString(), password.getText().toString()));
    }

    @Override
    protected View FullScreen() {
        return null;
    }
}
