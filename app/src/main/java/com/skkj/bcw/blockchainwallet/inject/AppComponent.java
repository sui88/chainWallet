package com.skkj.bcw.blockchainwallet.inject;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by skkj on 2017/6/6.
 * 生命周期跟Application一样的组件
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Context context();

}
