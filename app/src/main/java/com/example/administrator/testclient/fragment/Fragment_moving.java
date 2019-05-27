package com.example.administrator.testclient.fragment;

import android.view.View;

import com.example.administrator.testclient.R;

import java.util.ArrayList;

public class Fragment_moving extends MyFragment {
    @Override
    public View getLayout() {
        return View.inflate(getActivity(),R.layout.fragment_moving,null);
    }

    @Override
    public Object getData() {
        return new ArrayList<>();
    }

    @Override
    public void onClick(View v) {

    }
}
