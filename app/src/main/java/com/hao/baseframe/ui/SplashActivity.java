package com.hao.baseframe.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.hao.baseframe.R;
import com.hao.baseframe.base.BaseActivity;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by Hao on 2020/8/13
 */
@RuntimePermissions
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SplashActivityPermissionsDispatcher.initPermissionsWithPermissionCheck(this);
    }

    /**
     * 跳转页面
     */
    private void jumpPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    /**
     * 初始化权限
     */
    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void initPermissions() {
        jumpPage();
    }

    /**
     * 展示为什么要获取权限
     */
    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showWhy(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("应用没有获得权限，无法初始化，请打开相关权限")
                .setPositiveButton("确定", (dialog, button) -> request.proceed())
                .setNegativeButton("取消", (dialog, button) -> request.cancel())
                .show();
    }

    /**
     * 当用户拒绝获取权限的提示
     */
    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showDenied() {
        Toast.makeText(mContext, "无法获得权限", Toast.LENGTH_SHORT).show();
        jumpPage();
    }

    /**
     * 当用户勾选不再提示并且拒绝的时候调用的方法
     */
    @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showNeverAskAgain() {
        Toast.makeText(mContext, "跳转设置", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SplashActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
