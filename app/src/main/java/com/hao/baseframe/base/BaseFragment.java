package com.hao.baseframe.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hao.baseframe.R;
import com.hao.baseframe.control.BaseControl;
import com.hao.frame.base.SuperBaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Hao on 2020/8/13
 */
public abstract class BaseFragment extends SuperBaseFragment {

    private long mLastToastTime;

    @Override
    protected void setContentView(int layoutResID) {

        View baseView = LayoutInflater.from(mContext).inflate(R.layout.activity_base_fragment, null);
        View childView = LayoutInflater.from(mContext).inflate(layoutResID, null);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        childView.setLayoutParams(params);

        RelativeLayout container = baseView.findViewById(R.id.rl_container);

        container.addView(childView);

        setContentView(baseView);

        ButterKnife.bind(this, baseView);

        initView();
    }

    @Override
    protected int setLoadingViewId() {
        return R.layout.layout_loading_view;
    }

    @Override
    protected int setErrorViewId() {
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
            Toast.makeText(mContext, message, length).show();
            mLastToastTime = current;
        }
    }
}
