package com.qianfeng.day6_drawerlayout_viewpager;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initViews();
    }

    private void initViews() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }

    private void initData() {
        mFragments = new ArrayList<>();
        BlankFragment frag1 = new BlankFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("color", Color.RED);
        frag1.setArguments(bundle1);
        mFragments.add(frag1);
        BlankFragment frag2 = new BlankFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("color", Color.BLUE);
        frag2.setArguments(bundle2);
        mFragments.add(frag2);
        BlankFragment frag3 = new BlankFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putInt("color", Color.GREEN);
        frag3.setArguments(bundle3);
        mFragments.add(frag3);
    }

    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
