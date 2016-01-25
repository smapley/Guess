package com.smapley.powerwork.fragment;

import android.content.Intent;
import android.os.*;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.smapley.powerwork.R;
import com.smapley.powerwork.activity.AddTask;
import com.smapley.powerwork.adapter.ProItem5Adapter;
import com.smapley.powerwork.db.entity.ProUseEntity;
import com.smapley.powerwork.db.entity.UserEntity;
import com.smapley.powerwork.http.service.ProUseListService;

import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smapley on 15/11/16.
 */
@ContentView(R.layout.fragment_pro_item5)
public class Pro_Item5 extends BaseFragment {


    private static final int GETDATA = 1;
    @ViewInject(R.id.pro_item5_rv_list)
    private RecyclerView recyclerView;


    private int pro_id;
    private List<UserEntity> list;
    private ProItem5Adapter adapter;

    private ProUseListService proUseListService=new ProUseListService() {
        @Override
        public void onSucceed() {
            getDataForDb();
        }
    };


    @Override
    protected void initParams(View view) {
        initData();
        initView();
        getDataForDb();
        getDataForWeb();
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new ProItem5Adapter(getActivity(),list);
        recyclerView.setAdapter(adapter);

    }

    private void initData() {
        pro_id = getArguments().getInt("pro_id");
        list=new ArrayList<>();
    }

    @Override
    public void getDataForDb() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<ProUseEntity> proUseEntities=null;
                try {
                    proUseEntities=dbUtils.selector(ProUseEntity.class).where("pro_id","=",pro_id).findAll();
                } catch (DbException e) {
                    e.printStackTrace();
                }
                if(proUseEntities!=null&&!proUseEntities.isEmpty()) {
                    list.clear();
                    for (ProUseEntity proUseEntity : proUseEntities) {
                        try {
                            list.add(dbUtils.findById(UserEntity.class,proUseEntity.getUse_id()));
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                    }
                    mhandler.obtainMessage(GETDATA).sendToTarget();

                }
            }
        }).start();
    }


    @Override
    public void getDataForWeb() {
        proUseListService.load(userBaseEntity,pro_id);
    }

    private Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GETDATA:
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Event({R.id.pro_item5_tv_add})
    private void onClick(View view){
        switch (view.getId()){
            case R.id.pro_item5_tv_add:
                Intent intent=new Intent(getActivity(), AddTask.class);
                startActivity(intent);
                break;
        }
    }
}
