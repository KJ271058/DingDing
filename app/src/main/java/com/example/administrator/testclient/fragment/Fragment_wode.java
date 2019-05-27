package com.example.administrator.testclient.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.testclient.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_wode extends MyFragment {
    @BindView(R.id.heard)
    CircleImageView heard;
    @BindView(R.id.guanzhu_num)
    TextView guanzhuNum;
    @BindView(R.id.fengsi_num)
    TextView fengsiNum;
    @BindView(R.id.gexing_text)
    TextView gexingText;
    @BindView(R.id.my_info)
    TextView myInfo;
    @BindView(R.id.my_curriculum)
    TextView myCurriculum;
    @BindView(R.id.my_money)
    TextView myMoney;
    @BindView(R.id.dongtai)
    LinearLayout dongtai;
    @BindView(R.id.kebiaotixing)
    LinearLayout kebiaotixing;
    @BindView(R.id.gonggao)
    LinearLayout gonggao;
    Unbinder unbinder;

    @Override
    public View getLayout() {
        return View.inflate(getActivity(), R.layout.fragment_wode, null);
    }

    @Override
    public Object getData() {
        return "1";
    }

    @Override
    public void onClick(View v) {

    }

    @OnClick({R.id.my_info, R.id.my_curriculum, R.id.my_money, R.id.dongtai, R.id.kebiaotixing, R.id.gonggao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_info:
                break;
            case R.id.my_curriculum:
                break;
            case R.id.my_money:
                break;
            case R.id.dongtai:
                break;
            case R.id.kebiaotixing:
                break;
            case R.id.gonggao:
                break;
        }
    }

}
