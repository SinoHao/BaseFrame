package com.hao.frame.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hao.frame.R;
import com.hao.frame.iface.IBaseActivity;
import com.hao.frame.utils.AppManager;

/**
 * Created by Hao on 2020/8/13
 * Activity封装基类
 */
public abstract class SuperBaseActivity extends AppCompatActivity implements IBaseActivity {
    private RelativeLayout mLoadingView;
    private RelativeLayout mErrorView;
    private View mChildView;
    private RelativeLayout mContainer;
    private View mBaseView;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        AppManager.getAppManager(this).addActivity(this);
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        mBaseView = LayoutInflater.from(this).inflate(R.layout.activity_super_base, null);
        mChildView = LayoutInflater.from(this).inflate(layoutResID, null);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mChildView.setLayoutParams(params);

        mContainer = mBaseView.findViewById(R.id.rl_container);
        mLoadingView = mBaseView.findViewById(R.id.rl_loading);
        mErrorView = mBaseView.findViewById(R.id.rl_error);

        setLoadingView(getLoadingViewId());

        setErrorView(getErrorViewId());

        mContainer.addView(mChildView);

        getWindow().setContentView(mBaseView);

    }

    @Override
    public void setContentView(View view) {
        mBaseView = LayoutInflater.from(this).inflate(R.layout.activity_super_base, null);
        mChildView = view;

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mChildView.setLayoutParams(params);

        mContainer = mBaseView.findViewById(R.id.rl_container);
        mLoadingView = mBaseView.findViewById(R.id.rl_loading);
        mErrorView = mBaseView.findViewById(R.id.rl_error);

        setLoadingView(getLoadingViewId());

        setErrorView(getErrorViewId());

        mContainer.addView(mChildView);

        getWindow().setContentView(mBaseView);

    }

    private void setLoadingView(@LayoutRes int layoutId) {
        mLoadingView.addView(LayoutInflater.from(this).inflate(layoutId, null));
    }

    private void setErrorView(@LayoutRes int layoutId) {
        mErrorView.addView(LayoutInflater.from(this).inflate(layoutId, null));
    }

    @Override
    public void showContent() {
        mChildView.setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
    }

    @Override
    public void showContentLoading() {
        mChildView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
    }

    @Override
    public void showContentError() {
        mChildView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
    }

    /**
     * 获取加载View
     */
    protected View getLoadingView() {
        return mLoadingView;
    }

    /**
     * 获取失败View
     */
    protected View getErrorView() {
        return mErrorView;
    }

    /**
     * 获取容器View
     */
    protected RelativeLayout getContainerView() {
        return mContainer;
    }

    /**
     * 获取baseView
     */
    protected View getBaseView() {
        return mBaseView;
    }

    /**
     * 获取状态栏高度
     *
     * @return 状态栏高度
     */
    protected int getStatusBarHeight() {
        int id = getResources().getIdentifier(
                "status_bar_height", "dimen", "android");
        return id > 0 ? getResources().getDimensionPixelSize(id) : id;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager(this).finishActivity(this);
    }
}
