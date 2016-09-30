package com.qianfeng.day1_butterknife_home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.home_tab_home_rb)
    RadioButton mGiftRb;

    @BindView(R.id.home_tab_open_rb)
    RadioButton mOpenRb;

    @BindView(R.id.home_tab_hot_rb)
    RadioButton mHotRb;

    @BindView(R.id.home_tab_special_rb)
    RadioButton mSpecialRb;

    @BindString(R.string.gift)
    String mGiftStr;

    @BindString(R.string.open)
    String mOpenStr;

    @BindString(R.string.hot)
    String mHotStr;

    @BindString(R.string.special)
    String mSpecialStr;

    Fragment mFragmentGift;
    Fragment mFragmentOpen;
    Fragment mFragmentHot;
    Fragment mFragmentSpecial;
    //当前显示的Fragment
    Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initFragments();

        addFirstFragment();
    }

    private void initFragments() {
        mFragmentGift = BlankFragment.newInstance(mGiftStr);
        mFragmentHot = BlankFragment.newInstance(mHotStr);
        mFragmentOpen = BlankFragment.newInstance(mOpenStr);
        mFragmentSpecial = BlankFragment.newInstance(mSpecialStr);
    }

    private void addFirstFragment() {
        FragmentTransaction trans = this.getSupportFragmentManager()
                .beginTransaction();
        trans.add(R.id.home_content_layout,mFragmentGift).commit();
        mCurrentFragment = mFragmentGift;
    }

    private void switchFragment(Fragment fragment){
        FragmentTransaction trans = this.getSupportFragmentManager()
                .beginTransaction();
        if(fragment.isAdded()){
            trans.hide(mCurrentFragment).show(fragment).commit();
        }else{
            trans.hide(mCurrentFragment).add(R.id.home_content_layout,fragment).commit();
        }
        mCurrentFragment = fragment;
    }

    @OnClick(R.id.home_tab_home_rb)
    void clickGift(){
        switchFragment(mFragmentGift);
    }

    @OnClick(R.id.home_tab_open_rb)
    void clickOpen(){
        switchFragment(mFragmentOpen);
    }

    @OnClick(R.id.home_tab_hot_rb)
    void clickHot(){
        switchFragment(mFragmentHot);
    }

    @OnClick(R.id.home_tab_special_rb)
    void clickSpecail(){
        switchFragment(mFragmentSpecial);
    }
}
