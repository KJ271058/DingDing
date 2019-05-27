package com.example.administrator.testclient.fragment;

import android.view.View;

import com.example.administrator.testclient.R;

public class Fragment_news extends MyFragment {
    @Override
    public View getLayout() {
        return View.inflate(getActivity(),R.layout.fragment_news,null);
    }

    @Override
    public Object getData() {
        return null;
    }

    @Override
    public void onClick(View v) {

    }
}
