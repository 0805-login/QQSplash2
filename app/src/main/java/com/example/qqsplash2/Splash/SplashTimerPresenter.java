package com.example.qqsplash2.Splash;

import android.util.Log;

import com.example.qqsplash2.mvp.base.BaseMvpPresenter;

public class SplashTimerPresenter extends BaseMvpPresenter<ISplashActivityContract.IView> implements ISplashActivityContract.IPresenter {
    //P层同时也持有Activity的强引用
//    private final SplashActivity mActivity;
    private CustomCountDownTimer timer;

    public SplashTimerPresenter(ISplashActivityContract.IView view) {
        super(view);
    }

    public void initTimer() {
        timer = new CustomCountDownTimer(5, new CustomCountDownTimer.ICountDownHandler() {
            @Override
            public void onTicker(int time) {
                getView().setTvTimer(time + "秒");//getEmptyView()返回为空，getView()可能会有空指针异常
//                mTvTimer.setText(time + "秒");
            }

            @Override
            public void onFinish() {
                getView().setTvTimer("跳过");
//                mTvTimer.setText("跳过");
            }
        });
        timer.start();
    }

    public void cancel() {
        timer.cancel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cancel();
        Log.e("SplashTimerPresenter","onViewDestroy");
    }

    @Override
    protected ISplashActivityContract.IView getEmptyView() {
        return ISplashActivityContract.emptyView;//预防控制帧异常
    }

}
