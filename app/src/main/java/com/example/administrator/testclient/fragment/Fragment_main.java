package com.example.administrator.testclient.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.administrator.testclient.R;
import com.example.administrator.testclient.adapter.TextItemAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.support.constraint.Constraints.TAG;

public class Fragment_main extends MyFragment {
    @BindView(R.id.line1)
    ListView line1;
    List<String> list = new ArrayList<>();
    @BindView(R.id.scrollable)
    ScrollView scrollable;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            line1.setAdapter(new TextItemAdapter(list));
            InitView();
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getInfor();
    }

    private void InitView() {
        View listItem = line1.getAdapter().getView(0,null,line1);
        listItem.measure(0, 0);
        int item_y =listItem.getMeasuredHeight();

        ViewGroup.LayoutParams params = line1.getLayoutParams();
        params.height =(line1.getDividerHeight()+item_y) * list.size();
        line1.setLayoutParams(params);
    }

    private void getInfor() {
        list.clear();
        for (int i = 0; i < 55; i++) {
            list.add(i + "测试数据");
        }
        handler.sendEmptyMessage(200);
    }

    @Override
    public View getLayout() {
        return View.inflate(getActivity(), R.layout.fragment_main, null);
    }

    @Override
    public Object getData() {
        return 1;
    }

    @Override
    public void onClick(View v) {

    }

}
