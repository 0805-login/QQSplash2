package com.example.qqsplash2.main;

import androidx.fragment.app.Fragment;

import com.example.qqsplash2.mvp.presenter.ILifeCircle;
import com.example.qqsplash2.mvp.view.IMvpView;
import com.example.qqsplash2.mvp.StaticProxy.MvpController;

public interface IMainActivityContract {
    interface IView extends IMvpView {

        void showFragment(Fragment mFragment);

        void addFragment(Fragment mFragment);

        void hideFragment(Fragment mFragment);
    }

    interface IPresenter extends ILifeCircle {
        void initHomeFragment();

        void replaceFragment(int mCurrentFragmentIndex);

        int getCurrentCheckedIndex();

        int getTopPosition();

        int getBottomPosition();
    }

    IView emptyView = new IView() {
        @Override
        public void showFragment(Fragment mFragment) {

        }

        @Override
        public void addFragment(Fragment mFragment) {

        }

        @Override
        public void hideFragment(Fragment mFragment) {

        }

        @Override
        public MvpController getMvpController() {
            return null;
        }
    };
}
