package com.hao.frame.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hao.frame.R;
import com.hao.frame.iface.IBaseFragment;

/**
 * Created by Hao on 2020/8/13
 * Fragment封装基类
 */
public abstract class SuperBaseFragment extends Fragment implements IBaseFragment {

    private RelativeLayout mLoadingView;
    private RelativeLayout mErrorView;
    private View mChildView;
    private View mBaseView;
    private RelativeLayout mContainer;
    protected Context mContext;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getContext();

        mBaseView = inflater.inflate(R.layout.fragment_super_base, null);

        setContentView(getContentView());

        return mBaseView;
    }

    protected void setContentView(@LayoutRes int layoutResID) {
        mChildView = LayoutInflater.from(getContext()).inflate(layoutResID, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mChildView.setLayoutParams(params);

        mContainer = mBaseView.findViewById(R.id.rl_container);
        mLoadingView = mBaseView.findViewById(R.id.rl_loading);
        mErrorView = mBaseView.findViewById(R.id.rl_error);

        setLoadingView(getLoadingViewId());

        setErrorView(getLoadingViewId());

        mContainer.addView(mChildView);
    }

    protected void setContentView(View view) {
        mChildView = view;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mChildView.setLayoutParams(params);

        mContainer = mBaseView.findViewById(R.id.rl_container);
        mLoadingView = mBaseView.findViewById(R.id.rl_loading);
        mErrorView = mBaseView.findViewById(R.id.rl_error);

        setLoadingView(getLoadingViewId());

        setErrorView(getErrorViewId());

        mContainer.addView(mChildView);
    }

    private void setLoadingView(@LayoutRes int layoutId) {
        mLoadingView.addView(LayoutInflater.from(mContext).inflate(layoutId, null));
    }

    private void setErrorView(@LayoutRes int layoutId) {
        mErrorView.addView(LayoutInflater.from(mContext).inflate(layoutId, null));
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
}
