package com.smapley.guess.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.smapley.guess.R;
import com.smapley.guess.adapter.ProItem2Adapter;
import com.smapley.guess.db.entity.TaskEntity;
import com.smapley.guess.http.service.PTaskListService;
import com.smapley.guess.mode.BaseMode;
import com.smapley.guess.mode.Pro_Item2_Group_Mode;

import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smapley on 15/11/16.
 */
@ContentView(R.layout.fragment_pro_item2)
public class Pro_Item2 extends BaseFragment {

    private static final int GETTASK = 1;
    @ViewInject(R.id.pro_item2_rv_list)
    private RecyclerView pro_item2_rv_list;

    private ProItem2Adapter adapter;

    private List<BaseMode> listData;

    private int pro_id;

    private PTaskListService pTaskListService=new PTaskListService() {
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


    private void initData() {
        pro_id = getArguments().getInt("pro_id");

        listData = new ArrayList<>();
        Pro_Item2_Group_Mode myGroup = new Pro_Item2_Group_Mode();
        myGroup.setName(getString(R.string.pro_item2_group_mytask));
        myGroup.setIsShowAdd(true);
        listData.add(myGroup);

        Pro_Item2_Group_Mode otherGroup = new Pro_Item2_Group_Mode();
        otherGroup.setName(getString(R.string.pro_item2_group_othertask));
        otherGroup.setIsShowAdd(false);
        listData.add(otherGroup);
    }

    private void initView() {
        pro_item2_rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ProItem2Adapter(getActivity(), listData);
        pro_item2_rv_list.setAdapter(adapter);
    }

    public void getDataForDb() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<TaskEntity> listTask=dbUtils
                            .selector(TaskEntity.class)
                            .where("pro_id", "=", pro_id + "")
                            .findAll();
                    if(listTask!=null&&!listTask.isEmpty())
                        mhandler.obtainMessage(GETTASK,listTask).sendToTarget();
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }



    public void getDataForWeb() {
       pTaskListService.load(userBaseEntity,pro_id);
    }


    private Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GETTASK:
                    adapter.removeMyTask();
                    adapter.addMyTask((List<TaskEntity>) msg.obj);
                    break;
            }
        }
    };
}
