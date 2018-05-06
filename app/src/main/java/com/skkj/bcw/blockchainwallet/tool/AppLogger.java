package com.skkj.bcw.blockchainwallet.tool;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.yejunsui.bcw.blockchainwallet.BuildConfig;

public class AppLogger {

    public static void init(){
        if(!BuildConfig.DEBUG){
            return;
        }

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true) // (Optional) Whether to show thread info or not. Default true
                .methodCount(2)       // (Optional) How many method line to show. Default 2
                .methodOffset(5)      // (Optional) Hides internal method calls up to offset. Default 5
                .tag("四块科技钱包运行日志") // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));


    }

    public static void d(Object object){
        Logger.d(object);
    }

    public static void d(String message, Object... arg){
        Logger.d(message,arg);
    }

    public static void e(String message, Object... arg){
        Logger.e(message, arg);
    }

    public static void e(Throwable throwable, String message, Object... arg){
        Logger.e(throwable, message, arg);
    }

    public static void i(String message, Object... arg){
        Logger.i(message, arg);
    }

    public static void w(String message, Object... arg){
        Logger.w(message, arg);
    }

    public static void wtf(String message, Object... arg){
        Logger.wtf(message, arg);
    }

    public static void json(String json){
        Logger.json(json);
    }

    public static void xml(String xml){
        Logger.xml(xml);
    }

}
