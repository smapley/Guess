package com.smapley.guess.activity;

import android.support.v7.app.ActionBar;
import android.view.View;

import com.smapley.guess.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by smapley on 16/1/30.
 */
@ContentView(R.layout.activity_mingxi)
public class MingXi extends BaseActivity {

    @ViewInject(R.id.layout)
    private View layout;
    @Override
    protected void initParams() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    protected View FullScreen() {
        return layout;
    }


}
