package com.hao.baseframe.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hao.baseframe.R;
import com.hao.baseframe.control.BaseControl;
import com.hao.frame.base.SuperBaseActivity;

import butterknife.ButterKnife;

/**
 * Created by Hao on 2020/8/13
 */
public abstract class BaseActivity extends SuperBaseActivity {

    private long mLastToastTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        View baseView = LayoutInflater.from(this).inflate(R.layout.activity_base, null);
        View childView = LayoutInflater.from(this).inflate(layoutResID, null);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        childView.setLayoutParams(params);

        RelativeLayout container = baseView.findViewById(R.id.rl_container);

        container.addView(childView);

        setContentView(baseView);

        ButterKnife.bind(this, baseView);

        initView();
    }

    @Override
    public void initView() {

    }

    @Override
    public int getLoadingViewId() {
        return R.layout.layout_loading_view;
    }

    @Override
    public int getErrorViewId() {
        return R.layout.layout_loading_error;
    }

    protected void showToast(String message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    protected void showLongToast(String message) {
        showToast(message, Toast.LENGTH_LONG);
    }

    private void showToast(String message, int length) {
        long current = System.currentTimeMillis();
        if (current - mLastToastTime > BaseControl.TOAST_SHORT_INTERVAL) {
            Toast.makeText(this, message, length).show();
            mLastToastTime = current;
        }
    }

}
