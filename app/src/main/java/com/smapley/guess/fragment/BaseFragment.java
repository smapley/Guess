package com.smapley.guess.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.smapley.guess.application.LocalApplication;

import org.xutils.x;

/**
 * Created by smapley on 15/10/22.
 */
public abstract class BaseFragment extends Fragment {

    private boolean injected = false;
    protected SharedPreferences sp_user;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        sp_user = LocalApplication.getInstance().sp_user;

        initParams(view);
        injected = true;
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }
    }


    /**
     * 设置参数
     */
    protected abstract void initParams(View view);


    protected void showToast(int data) {
        Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
    }
    protected void showToast(String data) {
        Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
    }

}
