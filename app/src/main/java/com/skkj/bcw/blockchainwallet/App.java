package com.skkj.bcw.blockchainwallet;

import android.app.Application;

import com.skkj.bcw.blockchainwallet.inject.Components;
import com.skkj.bcw.blockchainwallet.tool.AppLogger;
import com.yejunsui.bcw.blockchainwallet.BuildConfig;

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        AppLogger.init();

         if(BuildConfig.DEBUG){

           Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
               @Override
               public void uncaughtException(Thread t, Throwable e) {
                   AppLogger.e(e, "Thread Name: " + t.getName());
                   AppLogger.e(e, "Throwable Class: " + e.getClass().getName());
               }
           });

        }

        Components.init(this);
    }

}
