package com.a0xffffffff.dinesum;

import android.app.ActionBar;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements MainFragment.OnFragmentInteractionListener {
    private static final String SELECTED_ITEM = "arg_selected_item";

    private BottomNavigationViewEx mBottomNav;
    private ViewPager mViewPager;

    private SparseIntArray items;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.vp);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mBottomNav = (BottomNavigationViewEx) findViewById(R.id.bnve);
        mBottomNav.enableAnimation(false);
        mBottomNav.enableShiftingMode(false);
        mBottomNav.enableItemShiftingMode(false);
        mBottomNav.setTextVisibility(false);
        mBottomNav.setItemHeight(200); //px
        mBottomNav.setIconSize(40, 40); //dp
        mBottomNav.setCurrentItem(0);
    }

    private void initData() {
        fragments = new ArrayList<>(3);
        items = new SparseIntArray(3);

        MainFragment userFragment = MainFragment.newInstance("User");
        MainFragment homeFragment = MainFragment.newInstance("Home");
        MainFragment addFragment = MainFragment.newInstance("Add");

        fragments.add(userFragment);
        fragments.add(homeFragment);
        fragments.add(addFragment);

        items.put(R.id.menu_user, 0);
        items.put(R.id.menu_home, 1);
        items.put(R.id.menu_add, 2);

        mViewPager.setAdapter(new VpAdapter(getFragmentManager(), fragments));
    }

    private void initEvent() {
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationViewEx.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = items.get(item.getItemId());
                if (previousPosition != position) {
                    previousPosition = position;
                    mViewPager.setCurrentItem(position);
                }

                return true;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNav.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void updateToolbarText(CharSequence text) {
        Log.d("bahhh", "got here in updateToolbarText");
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Hi");
        }
    }

    public void onFragmentInteraction() {
        // TODO
    }

    /**
     * view pager adapter
     */
    private static class VpAdapter extends FragmentPagerAdapter {
        private List<Fragment> data;

        public VpAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }
    }
}


