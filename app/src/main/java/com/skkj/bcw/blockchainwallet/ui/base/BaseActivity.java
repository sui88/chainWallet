package com.skkj.bcw.blockchainwallet.ui.base;


import android.annotation.SuppressLint;
import android.arch.lifecycle.Lifecycle;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tapadoo.alerter.Alerter;
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;
import com.yejunsui.bcw.blockchainwallet.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

public class BaseActivity extends SupportActivity {
    @Nullable
    @BindView(R.id.title_bar)
    CommonTitleBar mTitleBar;

    private final LifecycleProvider<Lifecycle.Event> mLifecycleProvider = AndroidLifecycle.createLifecycleProvider(this);

    private List<WeakReference<DialogInterface>> mShowingDialogList = new ArrayList<>();
    private ProgressHUDHelper mProgressHUDHelper;
    private MenuPopupHelper mMenuHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProgressHUDHelper = new ProgressHUDHelper(this, getLifecycle());
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        ButterKnife.bind(this);

        if (mTitleBar != null) {
            ImageButton leftImageButton = mTitleBar.getLeftImageButton();
            if (leftImageButton != null) {
                leftImageButton.setOnClickListener(v -> onBackPressedSupport());
            }
            setTitle(getTitle());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        for (WeakReference<DialogInterface> reference : mShowingDialogList) {
            DialogInterface dialogInterface = reference.get();
            if (dialogInterface != null) {
                dialogInterface.dismiss();
            }
        }
    }

    @Override
    public void onBackPressedSupport() {
        if (isTaskRoot()) {
            moveTaskToBack(false);
        } else {
            finish();
        }
    }

    public LifecycleProvider<Lifecycle.Event> getLifecycleProvider() {
        return mLifecycleProvider;
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);

        if (mTitleBar != null) {
            TextView centerTextView = mTitleBar.getCenterTextView();
            if (centerTextView != null) {
                centerTextView.setText(title);
            }
        }
    }

    @Override
    public void setTitle(@StringRes int titleId) {
        super.setTitle(titleId);

        if (mTitleBar != null) {
            TextView centerTextView = mTitleBar.getCenterTextView();
            if (centerTextView != null) {
                centerTextView.setText(titleId);
            }
        }
    }

    public CommonTitleBar getTitleBar() {
        return mTitleBar;
    }

    @SuppressLint("RestrictedApi")
    public void setMenu(@MenuRes int resId, @NonNull View anchor) {
        PopupMenu popupMenu = new PopupMenu(this, anchor);
        popupMenu.inflate(resId);
        popupMenu.setOnMenuItemClickListener(BaseActivity.this::onMenuItemClick);

        mMenuHelper = new MenuPopupHelper(this, (MenuBuilder) popupMenu.getMenu(), anchor);
    }

    @SuppressLint("RestrictedApi")
    public void showMenu() {
        if (mMenuHelper != null) {
            mMenuHelper.show();
        }
    }

    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    public void keepScreenOn(boolean flag) {
        if (flag) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    public void showProgressHUD() {
        showProgressHUD(null);
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
        Alerter.create(this)
                .enableVibration(false)
                .enableSwipeToDismiss()
                .setDuration(3000L)
                .setBackgroundColorRes(R.color.success)
                .setText(msg == null ? null : msg.toString())
                .hideIcon()
                .show();
    }

    public void showError(CharSequence msg) {
        Alerter.create(this)
                .enableVibration(false)
                .enableSwipeToDismiss()
                .setDuration(3000L)
                .setBackgroundColorRes(R.color.error)
                .setText(msg == null ? null : msg.toString())
                .hideIcon()
                .show();
    }

    public void showWarning(CharSequence msg) {
        Alerter.create(this)
                .enableVibration(false)
                .enableSwipeToDismiss()
                .setDuration(3000L)
                .setBackgroundColorRes(R.color.warning)
                .setText(msg == null ? null : msg.toString())
                .hideIcon()
                .show();
    }

    public void showMessageBox(CharSequence msg) {
        showMessageBox(msg, null);
    }

    public void showMessageBox(CharSequence msg, final Runnable callback) {
        AlertDialog messageBox = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage(msg)
                .setPositiveButton(R.string.btn_confirm, (dialog, which) -> {
                    dialog.dismiss();
                    if (callback != null) callback.run();
                })
                .show();
        mShowingDialogList.add(new WeakReference<>(messageBox));
    }

    public void showConfirmDialog(CharSequence msg, @Nullable final Runnable callback) {
        AlertDialog messageBox = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage(msg)
                .setPositiveButton(R.string.btn_yes, (dialog, which) -> {
                    dialog.dismiss();
                    if (callback != null) callback.run();
                })
                .setNegativeButton(R.string.btn_no, (dialog, which) -> dialog.dismiss())
                .show();
        mShowingDialogList.add(new WeakReference<>(messageBox));
    }

    public String getResString(@StringRes int resId, @StringRes int... formatArgs) {
        Object[] strArgs = new Object[formatArgs.length];
        for (int i = 0; i < formatArgs.length; i++) {
            strArgs[i] = getString(formatArgs[i]);
        }
        return getString(resId, strArgs);
    }

}
