package com.smapley.powerwork.fragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.smapley.powerwork.R;
import com.smapley.powerwork.adapter.MessageAdapter;
import com.smapley.powerwork.db.modes.MessageMode;
import com.smapley.powerwork.db.service.MessageService;
import com.smapley.powerwork.http.service.MessageListService;
import com.smapley.powerwork.mode.BaseMode;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smapley on 15/10/25.
 */
@ContentView(R.layout.fragment_message)
public class Message extends BaseFragment {

    private static final int GETDATA = 1;
    @ViewInject(R.id.mes_rv_list)
    private RecyclerView mes_rv_list;

    private List<BaseMode> mes_lis_data;
    private MessageAdapter adapter;

    private MessageListService messageListService = new MessageListService() {
        @Override
        public void onSucceed() {
            getDataForDb();
        }
    };

    @Override
    protected void initParams(View view) {

        initView();

        getDataForWeb();


    }

    private void initView() {
        mes_rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        mes_lis_data=new ArrayList<>();
        adapter = new MessageAdapter(getActivity(), mes_lis_data);
        mes_rv_list.setAdapter(adapter);
    }

    @Override
    public void getDataForDb() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<MessageMode> messageModes = MessageService.findByUseId(userBaseEntity.getUseId());
                mes_lis_data.clear();
                mes_lis_data.addAll(messageModes);
                mhandler.obtainMessage(GETDATA).sendToTarget();
            }
        }).start();


    }


    @Override
    public void getDataForWeb() {
        messageListService.load(userBaseEntity);
    }

    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GETDATA:
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };
}
