package com.skkj.bcw.blockchainwallet.ui.lead_wallet;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.skkj.bcw.blockchainwallet.inject.Components;
import com.yejunsui.bcw.blockchainwallet.R;

public class ImportWayPagerAdapter extends FragmentPagerAdapter {

    public ImportWayPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return MnemonicFragment.newInstance();
            case 1:
                return KeyStoreFragment.newInstance();
            case 2:
                return PrivateKeyFragment.newInstance();
            case 3:
                return ObserveFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return Components.appComponent.context().getString(R.string.import_way_mnemonic);
            case 1:
                return Components.appComponent.context().getString(R.string.import_way_key_store);
            case 2:
                return Components.appComponent.context().getString(R.string.import_way_private_key);
            case 3:
                return Components.appComponent.context().getString(R.string.import_way_observe);
        }
        return "";
    }
}
