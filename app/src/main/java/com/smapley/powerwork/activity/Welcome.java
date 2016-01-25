package com.smapley.powerwork.activity;

import android.content.Intent;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.smapley.powerwork.R;
import com.smapley.powerwork.utils.ThreadSleep;

import org.xutils.view.annotation.ContentView;

/**
 * Created by smapley on 15/12/21.
 */
@ContentView(R.layout.activity_welcom)
public class Welcome extends BaseActivity {
    @Override
    protected void initParams() {
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "f0EvBpr2oi4ElkMz1Ko7L7NC");
        new ThreadSleep().sleep(2000, new ThreadSleep.Callback() {
            @Override
            public void onCallback(int number) {
                startActivity(new Intent(Welcome.this,MainActivity.class));
                finish();
            }
        });
    }
}
