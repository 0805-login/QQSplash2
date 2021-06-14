package com.example.qqsplash2.main;


import androidx.fragment.app.Fragment;

import com.example.qqsplash2.R;
import com.example.qqsplash2.main.HangZhouFragment.HangZhouFragment;
import com.example.qqsplash2.main.BeiJingFragment.BeiJingFragment;
import com.example.qqsplash2.main.ShangHaiFragment.ShangHaiFragment;
import com.example.qqsplash2.main.ShenZhenFragment.ShenZhenFragment;
import com.example.qqsplash2.mvp.base.BaseMvpPresenter;

public class MainActivityPresenter extends BaseMvpPresenter<IMainActivityContract.IView> implements IMainActivityContract.IPresenter{

    //用于记录当前是第几个Fragment
    private int mCurrentFragmentIndex;

    //存放四个Fragment，四个Fragment可以复用，因此不必销毁
    private Fragment[] mFragments = new Fragment[4];

    private int mCurrentCheckedId;
    private int mTopPosition;
    private int mBottomPosition;

    public MainActivityPresenter(IMainActivityContract.IView view) {
        super(view);
    }

    @Override
    protected IMainActivityContract.IView getEmptyView() {
        return IMainActivityContract.emptyView;
    }

    //初始化Fragment
    @Override
    public void initHomeFragment() {
        mCurrentFragmentIndex = 0;//0代表上海
        replaceFragment(mCurrentFragmentIndex);//切换到当前默认的上海界面
    }

    //切换Fragment的方法
    public void replaceFragment(int mCurrentFragmentIndex) {

        for (int i = 0; i < mFragments.length; i++) {

            if(mCurrentFragmentIndex != i){

                if(mFragments[i] != null){

                    hideFragment(mFragments[i]);
                }
            }
        }

        Fragment mFragment = mFragments[mCurrentFragmentIndex];

        if(mFragment != null){

            addAndShowFragment(mFragment);
            setCurChecked(mCurrentFragmentIndex);

        }else {
            newCurrentFragment(mCurrentFragmentIndex);
            setCurChecked(mCurrentFragmentIndex);
        }
    }

    //创建当前Fragment
    private void newCurrentFragment(int mCurrentFragmentIndex) {
        Fragment fragment = null;
        switch (mCurrentFragmentIndex){
            case 0:
                fragment = new ShangHaiFragment();
                break;
            case 1:
                fragment = new HangZhouFragment();
                break;
            case 2:
                fragment = new BeiJingFragment();
                break;
            case 3:
                fragment = new ShenZhenFragment();
                break;
        }
        mFragments[mCurrentFragmentIndex] = fragment;
        addAndShowFragment(fragment);
    }

    //显示Fragment
    private void addAndShowFragment(Fragment mFragment) {
        if(mFragment.isAdded()){
            getView().showFragment(mFragment);
        }else {
            getView().addFragment(mFragment);
        }
    }

    //隐藏Fragment
    private void hideFragment(Fragment mFragment) {
        if(mFragment != null && mFragment.isVisible()){
            getView().hideFragment(mFragment);
        }
    }

    @Override
    public int getCurrentCheckedIndex() {
        return mCurrentFragmentIndex;
    }



    //记录当前角标
    private void setCurChecked(int mCurrentFragmentIndex) {
        this.mCurrentFragmentIndex = mCurrentFragmentIndex;
        switch (mCurrentFragmentIndex){
            case 0:
                mCurrentCheckedId = R.id.rg_main_shanghai;
                mTopPosition = 0;
                break;
            case 1:
                mCurrentCheckedId = R.id.rg_main_hangzhou;
                mTopPosition = 1;
                break;
            case 2:
                mCurrentCheckedId = R.id.rg_main_beijing;
                mBottomPosition = 2;
                break;
            case 3:
                mCurrentCheckedId = R.id.rg_main_shenzhen;
                mBottomPosition = 3;
                break;
        }
    }

    @Override
    public int getTopPosition() {
        return mTopPosition;
    }

    @Override
    public int getBottomPosition() {
        return mBottomPosition;
    }
}
