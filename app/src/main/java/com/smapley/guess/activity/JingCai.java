package com.smapley.guess.activity;

import android.view.View;

import com.smapley.guess.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by smapley on 16/1/30.
 */
@ContentView(R.layout.activity_jingcai)
public class JingCai extends BaseActivity {

    @ViewInject(R.id.layout)
    private View layout;
    @Override
    protected void initParams() {

    }

    @Override
    protected View FullScreen() {
        return layout;
    }
}
