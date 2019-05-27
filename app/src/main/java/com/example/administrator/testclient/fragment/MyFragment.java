package com.example.administrator.testclient.fragment;

import android.os.Handler;
import android.os.Message;
import android.view.View;

/**
 * @Author djm
 * @Data 2019-05-24.
 * @Time 14:28
 * Describe
 */
public abstract class MyFragment extends BaseFragment {
    @Override
    protected View getSuccessView() {
        return getLayout();
    }

    @Override
    protected Object requestData() {
        return getData();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 200) {
                refreshPage("");
            } else {
                refreshPage(null);
            }
        }
    };

    public void success() {
        handler.sendEmptyMessage(200);
    }

    public void file() {
        handler.sendEmptyMessage(100);
    }

    public abstract View getLayout();

    public abstract Object getData();
}
