package com.example.qqsplash2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

//ctrl+alt+F  抽取成员变量
public class SplashActivity extends AppCompatActivity {

    private FullScreenVideoView mVideoView;//vv_play  VideoView_play
    private TextView mTvTimer;
    private CustomCountDownTimer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_main);

        mVideoView = findViewById(R.id.vv_play);
        mTvTimer = findViewById(R.id.tv_splash_timer);
        mTvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
        });

        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.splash));
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
    }
}
