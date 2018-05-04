package com.skkj.bcw.blockchainwallet.inject;

import android.content.Context;

import com.skkj.bcw.blockchainwallet.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final App mApplication;

    AppModule(App app) {
        mApplication = app;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }

}
