package com.saint.carsale.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saint.carsale.BaseFragment;
import com.saint.carsale.R;

/**
 * Created by zzh on 16-1-9.
 */
public class InterrogationFargment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.teishifragment,container,false);
        return view;
    }
}
