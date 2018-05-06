package com.skkj.bcw.blockchainwallet.ui.generate_wallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.skkj.bcw.blockchainwallet.ui.base.BaseActivity;
import com.skkj.bcw.blockchainwallet.ui.create_wallet.CreateWalletActivity;
import com.skkj.bcw.blockchainwallet.ui.lead_wallet.ImportWalletActivity;
import com.yejunsui.bcw.blockchainwallet.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GenerateWalletActivity extends BaseActivity {
    @BindView(R.id.create_btn)
    Button mCreateBtn;
    @BindView(R.id.lead_btn)
    Button nLeadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_generate_wallet);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.create_btn)
    void onCreateOnClick(){
        Intent intent = new Intent(this, CreateWalletActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.lead_btn)
    void onLeadOnClick(){
        Intent intent = new Intent(this, ImportWalletActivity.class);
        startActivity(intent);
    }

}
