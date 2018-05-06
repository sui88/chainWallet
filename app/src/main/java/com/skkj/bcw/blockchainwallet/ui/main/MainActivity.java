package com.skkj.bcw.blockchainwallet.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.skkj.bcw.blockchainwallet.ui.base.BaseActivity;
import com.skkj.bcw.blockchainwallet.ui.base.BaseFragment;
import com.skkj.bcw.blockchainwallet.ui.base.BottomNavigationViewEx;
import com.yejunsui.bcw.blockchainwallet.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.navigation)
    BottomNavigationViewEx mNavigation;

    private BaseFragment[] mFragments = new BaseFragment[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mNavigation.enableShiftingMode(false);
        mNavigation.enableItemShiftingMode(false);
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {;
            mFragments[0] = HomeFragment.newInstance();
            mFragments[1] = FindFragment.newInstance();
            mFragments[2] = UserFragment.newInstance();

            loadMultipleRootFragment(R.id.container, 0,
                    mFragments[0],
                    mFragments[1],
                    mFragments[2]
            );
        } else {
            mFragments[0] = findFragment(HomeFragment.class);
            mFragments[1] = findFragment(FindFragment.class);
            mFragments[2] = findFragment(UserFragment.class);
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        // 如果在存在上级页面，Pop
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            pop();
        }

        switch (item.getItemId()) {
            case R.id.nav_home:
                showHideFragment(mFragments[0]);
                return true;
            case R.id.nav_find:
                showHideFragment(mFragments[1]);
                return true;
            case R.id.nav_user:
                showHideFragment(mFragments[2]);
                return true;
        }

        return false;
    };

}
