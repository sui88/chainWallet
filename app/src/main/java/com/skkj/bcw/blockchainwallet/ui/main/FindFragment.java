package com.skkj.bcw.blockchainwallet.ui.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skkj.bcw.blockchainwallet.ui.base.BaseFragment;
import com.yejunsui.bcw.blockchainwallet.R;

/**
 * Created by skkj on 2017/12/22.
 */

public class FindFragment extends BaseFragment {

    public static FindFragment newInstance(){
        return new FindFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_find, container, false);
    }

}
