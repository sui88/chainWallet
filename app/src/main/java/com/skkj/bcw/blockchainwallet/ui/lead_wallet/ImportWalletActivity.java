package com.skkj.bcw.blockchainwallet.ui.lead_wallet;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.skkj.bcw.blockchainwallet.ui.base.BaseActivity;
import com.yejunsui.bcw.blockchainwallet.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImportWalletActivity extends BaseActivity {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.tab)
    TabLayout mTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_wallet);
        ButterKnife.bind(this);

        mViewPager.setAdapter(new ImportWayPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
