package com.smapley.guess.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.smapley.guess.R;
import com.smapley.guess.adapter.NoteAdapter;
import com.smapley.guess.db.entity.NoteEntity;
import com.smapley.guess.http.service.NoteListService;

import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smapley on 15/10/29.
 */
@ContentView(R.layout.activity_notes)
public class NoteList extends BaseActivity {

    private static final int GETDATA = 1;
    private static final int SAVEDATA = 2;
    @ViewInject(R.id.title_tv_name)
    private TextView title_tv_name;

    @ViewInject(R.id.not_rv_list)
    private RecyclerView not_rv_list;

    private List<NoteEntity> not_lis_data;
    private NoteAdapter not_pa_adapter;


    private NoteListService noteListService=new NoteListService() {
        @Override
        public void onSucceed() {
            getDataForDb();
        }
    };

    @Override
    protected void initParams() {
        initView();
        getDataForDb();
        getDataForWeb();


    }

    private void getDataForWeb() {
       noteListService.load(userBaseEntity);
    }

    private void getDataForDb() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<NoteEntity> listNote = dbUtils.selector(NoteEntity.class)
                            .where("use_id", "=", userBaseEntity.getUseId())
                            .orderBy("cre_date", true)
                            .findAll();
                    if (listNote != null && !listNote.isEmpty())
                        mhandler.obtainMessage(GETDATA, listNote).sendToTarget();
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initView() {
        title_tv_name.setText(R.string.notes);
        not_rv_list.setLayoutManager(new LinearLayoutManager(this));
        not_lis_data = new ArrayList<>();
        not_pa_adapter = new NoteAdapter(this, not_lis_data);
        not_rv_list.setAdapter(not_pa_adapter);
    }

    @Event({R.id.title_iv_back})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
        }
    }

    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GETDATA:
                    not_pa_adapter.addAll((List<NoteEntity>) msg.obj);
                    break;
                case SAVEDATA:
                    getDataForDb();
                    break;
            }
        }
    };
}
