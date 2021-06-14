package com.example.qqsplash2.Splash;

import com.example.qqsplash2.mvp.presenter.ILifeCircle;
import com.example.qqsplash2.mvp.view.IMvpView;
import com.example.qqsplash2.mvp.StaticProxy.MvpController;

public interface ISplashActivityContract {

    interface IView extends IMvpView {
        void setTvTimer(String s);
    }

    interface IPresenter extends ILifeCircle {
        void initTimer();
    }

    //预防空指针异常
    IView emptyView = new IView() {
        @Override
        public void setTvTimer(String s) {

        }

        @Override
        public MvpController getMvpController() {
            return null;
        }
    };
}
