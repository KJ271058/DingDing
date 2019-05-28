package com.example.administrator.testclient.fragment.moving;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.andview.refreshview.XRefreshView;
import com.example.administrator.testclient.R;
import com.example.administrator.testclient.adapter.MovingItemAdapter;
import com.example.administrator.testclient.fragment.MyFragment;
import com.example.administrator.testclient.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Fragment_moving_1 extends MyFragment {


    @BindView(R.id.line)
    MyListView line;
    @BindView(R.id.refresh)
    XRefreshView refresh;

    List<String> list = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            success();
            line.setAdapter(new MovingItemAdapter(list));
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRefresh();
        getInfor();
    }

    private void setRefresh() {
        refresh.setMoveForHorizontal(true);
        refresh.setPullLoadEnable(true);
        refresh.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(() -> {
                    list.clear();
                    getInfor();
                    refresh.stopRefresh();
                }, 2000);
            }

            @Override
            public void onRefresh(boolean isPullDown) {

            }

            @Override
            public void onLoadMore(boolean isSilence) {

                new Handler().postDelayed(() -> {
                    //填写加载更多的网络请求，一般page++
                    getInfor();
                    refresh.stopLoadMore();
                }, 1000);
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
    }


    private void getInfor() {
        for (int i = 0; i < 5; i++) {
            list.add(i + "测试数据");
        }
        handler.sendEmptyMessage(200);
    }

    @Override
    public View getLayout() {
        return View.inflate(getActivity(), R.layout.fragment_moving_1, null);
    }

    @Override
    public Object getData() {
        return 1;
    }

    @Override
    public void onClick(View v) {

    }
}
