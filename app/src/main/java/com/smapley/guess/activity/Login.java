package com.smapley.guess.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.smapley.guess.R;
import com.smapley.guess.http.params.Reg1Params;
import com.smapley.guess.http.params.SendSMSParams;
import com.smapley.guess.http.service.Reg1Service;
import com.smapley.guess.http.service.SendSMSService;
import com.smapley.guess.utils.ThreadSleep;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by smapley on 15/10/26.
 */
@ContentView(R.layout.activity_login)
public class Login extends BaseActivity {


    @ViewInject(R.id.log_bt_send)
    private Button log_bt_send;
    @ViewInject(R.id.log_et_username)
    private EditText log_et_username;
    @ViewInject(R.id.log_et_password)
    private EditText log_et_password;

    private String log_st_usernmae;
    private String log_st_password;

    private boolean canSend = true;

    private SendSMSService sendSMSService = new SendSMSService() {
        @Override
        public void Succ() {
            canSend = false;
            new ThreadSleep().isLoop().sleep(1000, new ThreadSleep.Callback() {
                @Override
                public void onCallback(ThreadSleep threadSleep, int number) {
                    log_bt_send.setText("已发送 (" + (60 - number) + ")");
                    if (number >= 60) {
                        threadSleep.stop();
                        canSend = true;
                        log_bt_send.setText(R.string.send);
                    }
                }
            });
        }
    };

    private Reg1Service reg1Service = new Reg1Service() {
        @Override
        public void Succ() {
            afterLogin();
        }

        @Override
        public void Fail() {
            showToast(R.string.log_fail);
        }
    };

    @Override
    protected void initParams() {
        initView();
    }

    public void initView() {
        log_et_password.setText(log_st_password);
        log_et_username.setText(log_st_usernmae);
    }

    public void checkMes(View view) {
        if (canSend) {
            log_st_usernmae = log_et_username.getText().toString();
            if (log_st_usernmae != null && log_st_usernmae.length() == 11) {
                sendSMSService.load(new SendSMSParams(log_st_usernmae));
            } else {
                showToast(R.string.log_err_phone);
            }
        }
    }


    public void checkLogin(View view) {
        if (log_st_usernmae != null && !log_st_usernmae.equals("")) {
            log_st_password = log_et_password.getText().toString();
            if (log_st_password != null && !log_st_password.equals("")) {
                reg1Service.load(new Reg1Params(log_st_usernmae, log_st_password));
            } else {
                showToast(R.string.log_null_password);
            }
        } else {
            showToast(R.string.log_null_username);
        }
    }


    private void afterLogin() {
        SharedPreferences.Editor editor = sp_user.edit();
        editor.putString("phone", log_st_usernmae);
        editor.putBoolean("islogin", true);
        editor.commit();
        toNextActivity();
    }


    private void toNextActivity() {
        startActivity(new Intent(Login.this, MainActivity.class));
        finish();
    }


}
