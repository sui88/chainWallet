package com.skkj.bcw.blockchainwallet.ui.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by skkj on 2018/5/4.
 * 对KProgressHUD进行了封装，添加了一些默认配置，
 * 并且可以联动生命周期，在Destroy时自动dismiss，无需再手动处理
 */
public class ProgressHUDHelper implements LifecycleObserver {
    // 组件支持显示一个假进度条，是我加的特技，以下定义了要用到的几个属性
    // 每多少毫秒进度就更新一次
    private static final int SIM_PROGRESS_INTERVAL = 116;
    // 每次给进度值加多少
    private static final int SIM_PROGRESS_STEP = 1;
    // 进度值的起点，一般都是从0开始了
    private static final int SIM_PROGRESS_BEGIN = 0;
    // 进度值的终点，免得冲到最大值了任务还没完成，这很假
    private static final int SIM_PROGRESS_END = 95;
    // 进度最大值
    private static final int SIM_PROGRESS_MAX = 100;

    private KProgressHUD mProgressHUD;
    private Handler mSimulationHandler;

    public ProgressHUDHelper(Context context, Lifecycle lifecycle, KProgressHUD.Style style) {
        mProgressHUD = KProgressHUD.create(context)
                .setStyle(style)
                .setBackgroundColor(Color.TRANSPARENT)
                .setDimAmount(0.6f)
                .setCancellable(false)
                .setCanceledOnTouchOutside(false);
        lifecycle.addObserver(this);

        mSimulationHandler = new Handler(Looper.getMainLooper());
    }

    public ProgressHUDHelper(Context context, Lifecycle lifecycle) {
        this(context, lifecycle, KProgressHUD.Style.SPIN_INDETERMINATE);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(){
        dismiss();
    }

    public KProgressHUD getKProgressHUD(){
        return mProgressHUD;
    }

    public ProgressHUDHelper show(){
        mProgressHUD.show();
        return this;
    }

    public ProgressHUDHelper showAuto(){
        mProgressHUD.setMaxProgress(SIM_PROGRESS_MAX)
                    .setProgress(SIM_PROGRESS_BEGIN);
        return show().autoProgress();
    }


    public ProgressHUDHelper dismiss() {
        mSimulationHandler.removeCallbacksAndMessages(null);
        mProgressHUD.dismiss();

        return setOnCancelListener(null);
    }

    public ProgressHUDHelper showGrace(int graceTimeMs) {
        mProgressHUD.setGraceTime(graceTimeMs)
                .show();

        return this;
    }

    public ProgressHUDHelper setStyle(KProgressHUD.Style style) {
        mProgressHUD.setStyle(style);

        return this;
    }

    public ProgressHUDHelper setCancellable(boolean isCancellable) {
        mProgressHUD.setCancellable(isCancellable);

        return this;
    }


    public ProgressHUDHelper setOnCancelListener(DialogInterface.OnCancelListener listener) {
        mProgressHUD.setOnCancelListener(listener);

        return this;
    }

    public ProgressHUDHelper autoProgress(){
        mSimulationHandler.postDelayed(()->{
            long progress = mProgressHUD.getProgress() + SIM_PROGRESS_STEP;
            if(progress > SIM_PROGRESS_END){
                progress = SIM_PROGRESS_END;
            }
            mProgressHUD.setProgress(progress);
            if(progress < SIM_PROGRESS_END){
                autoProgress();
            }

        },SIM_PROGRESS_INTERVAL);

        return  this;
    }
}
