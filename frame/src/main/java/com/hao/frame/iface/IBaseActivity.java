package com.hao.frame.iface;

/**
 * Created by Hao on 2020/8/15
 */
public interface IBaseActivity {

    void initView();

    /**
     * 获取加载布局
     */
    int getLoadingViewId();

    /**
     * 获取失败布局
     */
    int getErrorViewId();

    /**
     * 显示内容
     */
    void showContent();

    /**
     * 显示加载Loading
     */
    void showContentLoading();

    /**
     * 显示加载失败
     */
    void showContentError();
}
