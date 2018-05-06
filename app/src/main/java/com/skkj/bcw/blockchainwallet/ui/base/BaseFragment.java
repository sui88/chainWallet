package com.skkj.bcw.blockchainwallet.ui.base;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;

import me.yokeyword.fragmentation.SupportFragment;

public class BaseFragment extends SupportFragment implements LifecycleRegistryOwner {

    private final LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
    private final LifecycleProvider<Lifecycle.Event> mLifecycleProvider
            = AndroidLifecycle.createLifecycleProvider(this);

    private ProgressHUDHelper mProgressHUDHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProgressHUDHelper = new ProgressHUDHelper(getContext(), getLifecycle());
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return mLifecycleRegistry;
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity)getActivity();
    }

    public void showProgressHUD() {
        mProgressHUDHelper.setCancellable(false)
                .show();
    }

    public void showProgressHUD(@Nullable Runnable onCancel) {
        mProgressHUDHelper.setOnCancelListener(dialog -> {
            if (onCancel != null)
                onCancel.run();
        })
                .setCancellable(onCancel != null)
                .show();
    }

    public void showAutoProgressHUD(@Nullable CharSequence label, @Nullable Runnable onCancel) {
        mProgressHUDHelper.getKProgressHUD().setLabel(label);
        mProgressHUDHelper.setOnCancelListener(dialog -> {
            if (onCancel != null)
                onCancel.run();
        })
                .setCancellable(onCancel != null)
                .showAuto();
    }

    public void dismissProgressHUD() {
        mProgressHUDHelper.dismiss();
    }

    public void showSuccess(CharSequence msg) {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) {
            activity.showSuccess(msg);
        }
    }

    public void showError(CharSequence msg) {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) {
            activity.showError(msg);
        }
    }

    public void showWarning(CharSequence msg) {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) {
            activity.showWarning(msg);
        }
    }

    public String getResString(@StringRes int resId, @StringRes int... formatArgs) {
        Object[] strArgs = new Object[formatArgs.length];
        for (int i = 0; i < formatArgs.length; i++) {
            strArgs[i] = getString(formatArgs[i]);
        }
        return getString(resId, strArgs);
    }

}
