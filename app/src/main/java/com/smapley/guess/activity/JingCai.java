package com.smapley.guess.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import com.smapley.guess.R;
import com.smapley.guess.adapter.JingcaiAdapter;
import com.smapley.guess.http.service.GetZhuangService;
import com.smapley.guess.mode.GetZhuangMode;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smapley on 16/1/30.
 */
@ContentView(R.layout.activity_jingcai)
public class JingCai extends BaseActivity {

    @ViewInject(R.id.layout)
    private View layout;

    @ViewInject(R.id.listview)
    private ListView listView;

    private List<GetZhuangMode> listData=new ArrayList<>();
    private JingcaiAdapter adapter;

    private GetZhuangService getZhuangService=new GetZhuangService() {
        @Override
        public void Succ(List<GetZhuangMode> data) {
            listData.clear();
            listData.addAll(data);
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void initParams() {

        adapter=new JingcaiAdapter(this,listData);
        listView.setAdapter(adapter);

        getZhuangService.load();
    }

    @Override
    protected View FullScreen() {
        return layout;
    }

    @Event({R.id.shuaxin,R.id.tianjia})
    private void onClick(View view){
        switch (view.getId()){
            case R.id.shuaxin:
                getZhuangService.load();
                break;
            case R.id.tianjia:
                startActivity(new Intent(JingCai.this,AddZhuang.class));
                break;
        }

    }
}
