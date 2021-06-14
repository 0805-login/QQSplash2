package com.example.qqsplash2.mvp.presenter;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.qqsplash2.mvp.view.IMvpView;

public interface ILifeCircle {
    //包含Activity或者Fragment的生命周期的一些方法，通过基类自动调取P层同步的方法
    void onCreate(Bundle savedInstanceState, Intent intent,Bundle getArguments);

    void onActivityCreated(Bundle savedInstanceState, Intent intent,Bundle getArguments);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void destroyView();

    void onViewDestroy();

    void onNewIntent(Intent intent);

    void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);

    void onSaveInstanceState(Bundle bundle);

    void attachView(IMvpView iMvpView);

}
