package com.skkj.bcw.blockchainwallet.ui.lead_wallet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skkj.bcw.blockchainwallet.ui.base.BaseFragment;
import com.yejunsui.bcw.blockchainwallet.R;

public class PrivateKeyFragment extends BaseFragment {

    public static PrivateKeyFragment newInstance(){
        return new PrivateKeyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_private_key, container, false);
        return view;
    }

}
