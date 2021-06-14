package com.example.qqsplash2.Splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.qqsplash2.main.MainActivity;
import com.example.qqsplash2.R;
import com.example.qqsplash2.base.BaseMvpActivity;
import com.example.qqsplash2.base.ViewInject;

import java.io.File;

import butterknife.BindView;

/*
* 中介者设计模式 4 个角色
第一个角色：抽象中介者->MvpPresenter （定义接口）
* 第二个角色：具体中介者->具体功能模块（例如：LoginPresente 等…）
* 第三个角色：抽象同事 M层：MvpModel V层：MvpView
第四个角色：具体同事 M层：例如->LoginModel…
V层：例如->MainActivity…
*
* */
@ViewInject(mainlayoutid = R.layout.activity_splash_main)
//ctrl+alt+F  抽取成员变量
public class SplashActivity extends BaseMvpActivity implements ISplashActivityContract.IView {

    @BindView(R.id.vv_play)
    FullScreenVideoView mVideoView;
    @BindView(R.id.tv_splash_timer)
    TextView mTvTimer;
    private ISplashActivityContract.IPresenter timerPresenter;

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_splash_main);
////        mVideoView = findViewById(R.id.vv_play);
////        mTvTimer = findViewById(R.id.tv_splash_timer);
//        /*把初始化Timer及相关内容抽离出到Presenter层
//        * 1、先创建initTimerPresenter方法
//        * 2、在View层创建P层的强引用对象
//        * */
//        initTimerPresenter();
//        initListener();
//        initVideo();
//        /*把初始化Timer及相关内容抽离出到Presenter层
//        initTimer();*/
//    }

    @Override
    public void afterBindView() {
        initTimerPresenter();
        initListener();
        initVideo();
    }
    private void initTimerPresenter() {
        //Activity持有P层强引用
        timerPresenter = new SplashTimerPresenter(this);
        timerPresenter.initTimer();
    }

    /*private void initTimer() {
        timer = new CustomCountDownTimer(5, new CustomCountDownTimer.ICountDownHandler() {
            @Override
            public void onTicker(int time) {
                mTvTimer.setText(time + "秒");
            }

            @Override
            public void onFinish() {
                mTvTimer.setText("跳过");
            }
        });
        timer.start();
    }*/

    private void initVideo() {
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.splash));
    }

    private void initListener() {
        mTvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });

        //播放器从闲置状态进入准备状态，准备状态之后即为播放状态
        //观察者设计模式  观察者：界面   被观察者：播放器mVideoView
        //IOC数据回调
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        //进行连续播放
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        timerPresenter.onDestroy();
//        timerPresenter.cancel();
//        timer.cancel();
    }*/

    @Override
    public void setTvTimer(String s) {
        mTvTimer.setText(s);
    }


}
