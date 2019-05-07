package com.cn.nj.putian.newodnclient.ui.main.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.nj.putian.newodnclient.R;

public final class AlarmFragment extends Fragment {

    public static AlarmFragment newFragment() {
        return new AlarmFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.alarm_fragment,container,false);
        return v;
    }
}
