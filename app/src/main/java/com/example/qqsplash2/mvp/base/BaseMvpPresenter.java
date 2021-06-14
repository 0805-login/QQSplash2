package com.example.qqsplash2.mvp.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.qqsplash2.mvp.view.IMvpView;
import com.example.qqsplash2.mvp.presenter.LifeCircleMvpPresenter;

/*
* P层中间类  起到桥接LifeCircleMvpPresenter和ILifeCircle的作用
*
* 抽象中介者   空实现ILifeCircle中的接口
* */
public abstract class BaseMvpPresenter<T  extends IMvpView> extends LifeCircleMvpPresenter<T> {

    /*
    * protected abstract T getEmptyView();需要在View层具体实现，故在桥接类中不需要实现
    * */

    //父类含有带参构造方法时，子类也必须创建带参构造方法
    public BaseMvpPresenter(T view) {
        super(view);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, Intent intent, Bundle getArguments) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState, Intent intent, Bundle getArguments) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void destroyView() {

    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {

    }
}
