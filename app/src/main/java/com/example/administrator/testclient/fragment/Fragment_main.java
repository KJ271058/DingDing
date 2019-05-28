package com.example.administrator.testclient.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.example.administrator.testclient.MyApplication;
import com.example.administrator.testclient.R;
import com.example.administrator.testclient.adapter.MovingItemAdapter;
import com.example.administrator.testclient.adapter.TextItemAdapter;
import com.example.administrator.testclient.bean.ImgUrl;
import com.example.administrator.testclient.view.MyListView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static java.lang.Thread.sleep;

//import com.facebook.drawee.view.SimpleDraweeView;

public class Fragment_main extends MyFragment {
    @BindView(R.id.line1)
    MyListView line1;
    @BindView(R.id.scrollable)
    ScrollView scrollable;
    @BindView(R.id.suo_text)
    EditText suoText;
    @BindView(R.id.sousuo)
    ImageView sousuo;
    @BindView(R.id.banner)
    XBanner banner;
    @BindView(R.id.refresh)
    XRefreshView refresh;
    List<String> list = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            success();
            line1.setAdapter(new MovingItemAdapter(list));
            initBanner();
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

    private void initBanner() {
        //加载广告图片
        List<ImgUrl> data = new ArrayList<>();
        data.add(new ImgUrl("https://ps.ssl.qhmsg.com/bdr/864__/t01250f5c40f9900558.jpg"));
        data.add(new ImgUrl("https://p2.ssl.qhimgs1.com/bdr/864__/t015dbef757980bc522.jpg"));
        data.add(new ImgUrl("https://ps.ssl.qhmsg.com/bdr/864__/t0154687f04bb6e87c8.jpg"));
        data.add(new ImgUrl("https://p2.ssl.qhimgs1.com/bdr/864__/t01b9381bea71afa5c2.jpg"));
        data.add(new ImgUrl("https://p0.ssl.qhimgs1.com/sdr/200_200_/t01c99fa99ef860d604.jpg"));
        data.add(new ImgUrl("https://p0.ssl.qhimgs1.com/bdr/864__/t0196686dc2a333456a.jpg"));
        banner.setBannerData(R.layout.lunbo_layout, data);
        banner.setAutoPlayAble(data.size() > 0);
        //设置广告图片点击事件
        banner.setOnItemClickListener((banner12, model, view, position) -> Toast.makeText(MyApplication.getContext(), "点击了第" + (position + 1) + "图片", Toast.LENGTH_SHORT).show());
        banner.loadImage((banner, model, view, position) -> {
            SimpleDraweeView draweeView = (SimpleDraweeView) view;
            draweeView.setImageURI((String) ((ImgUrl) model).getXBannerUrl());
        });
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
