package com.example.administrator.testclient.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.testclient.R;
import com.example.administrator.testclient.fragment.moving.Fragment_moving_1;
import com.example.administrator.testclient.fragment.moving.Fragment_moving_2;
import com.example.administrator.testclient.util.BottomUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Fragment_moving extends MyFragment {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    @BindView(R.id.fabu)
    ImageView fabu;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private MenuItem menuItem;
    List<Fragment> list = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setBottom();
    }

    private void setBottom() {
        BottomUtil.disableShiftMode(bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.moving_btn_popular:
                            viewpager.setCurrentItem(0);
                            break;
                        case R.id.moving_btn_attention:
                            viewpager.setCurrentItem(1);
                            break;
                    }
                    return false;
                });

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigation.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigation.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //禁止ViewPager滑动
//        viewpager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

        setupViewPager(viewpager);
    }

    private void setupViewPager(ViewPager viewpager) {
        list = new ArrayList<>();
        list.add(new Fragment_moving_1());
        list.add(new Fragment_moving_2());
        viewpager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        viewpager.setOffscreenPageLimit(3);
    }

    @Override
    public View getLayout() {
        return View.inflate(getActivity(),R.layout.fragment_moving,null);
    }

    @Override
    public Object getData() {
        return "123";
    }

    @Override
    public void onClick(View v) {

    }
}
