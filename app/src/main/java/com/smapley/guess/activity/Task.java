package com.smapley.guess.activity;

import com.smapley.guess.R;

import org.xutils.view.annotation.ContentView;

/**
 * Created by smapley on 15/12/30.
 */
@ContentView(R.layout.activity_task)
public class Task extends BaseActivity {

    private int tas_id;

    @Override
    protected void initParams() {
        initData();

    }

    private void initData() {
        tas_id=getIntent().getIntExtra("tas_id",-1);
    }
}
