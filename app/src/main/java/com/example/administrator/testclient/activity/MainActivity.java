package com.example.administrator.testclient.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.administrator.testclient.R;
import com.example.administrator.testclient.fragment.Fragment_main;
import com.example.administrator.testclient.fragment.Fragment_moving;
import com.example.administrator.testclient.fragment.Fragment_news;
import com.example.administrator.testclient.fragment.Fragment_wode;
import com.example.administrator.testclient.util.BottomUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    private MenuItem menuItem;
    List<Fragment> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setBtnAndView();
    }



    private void setBtnAndView() {
        BottomUtil.disableShiftMode(bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.main_btn:
                            viewpager.setCurrentItem(0);
                            break;
                        case R.id.moving_btn:
                            viewpager.setCurrentItem(1);
                            break;
                        case R.id.news_btn:
                            viewpager.setCurrentItem(2);
                            break;
                        case R.id.wode_btn:
                            viewpager.setCurrentItem(3);
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
        list.add(new Fragment_main());
        list.add(new Fragment_moving());
        list.add(new Fragment_news());
        list.add(new Fragment_wode());
        viewpager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
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
}
