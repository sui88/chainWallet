package com.skkj.bcw.blockchainwallet.ui.create_wallet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.skkj.bcw.blockchainwallet.ui.base.BaseActivity;
import com.skkj.bcw.blockchainwallet.ui.lead_wallet.ImportWalletActivity;
import com.skkj.bcw.blockchainwallet.ui.main.MainActivity;
import com.yejunsui.bcw.blockchainwallet.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateWalletActivity extends BaseActivity{

    @BindView(R.id.create_btn)
    Button mCreateBtn;
    @BindView(R.id.import_btn)
    Button mImportBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wallet);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.create_btn)
    void onCreateOnClick(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.import_btn)
    void onLeadOnClick(){
        Intent intent = new Intent(this, ImportWalletActivity.class);
        startActivity(intent);
    }

}
