package com.skkj.bcw.blockchainwallet.inject;

import com.skkj.bcw.blockchainwallet.App;

public class Components {
    public static  AppComponent appComponent;

    public static void init(App application){
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(application))
                    .build();
    }
}
