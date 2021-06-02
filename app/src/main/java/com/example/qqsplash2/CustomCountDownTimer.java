package com.example.qqsplash2;

import android.os.Handler;

import java.util.logging.LogRecord;

public class CustomCountDownTimer implements Runnable{
    private int time;//总时间
    private int countDownTime;//当前时间
    private final ICountDownHandler countDownHandler;
    private boolean isRun;
    private final Handler handler;
    //1、实时回调当前时间，倒计时到几秒   观察者设计模式
    //2.支持动态传入总时间
    //3、每过一秒 总秒数减一
    //4、总时间倒计时为0时，要回调完成的状态


    public CustomCountDownTimer(int time,ICountDownHandler countDownHandler) {
        handler = new Handler();
        this.time = time;
        this.countDownTime = time;
        this.countDownHandler = countDownHandler;
    }

    //实现具体逻辑
    @Override
    public void run() {
        if(isRun){
            if(countDownHandler != null){
                countDownHandler.onTicker(countDownTime);
            }
            if(countDownTime == 0) {
                cancel();
                if (countDownHandler != null) {
                    countDownHandler.onFinish();
                }
            }else {
                    countDownTime = time--;
                    handler.postDelayed(this,1000);
                }
        }
    }

    //开启倒计时
    public void start(){
        isRun = true;
        handler.post(this);
    }

    //跳出循环 终止
    public void cancel(){
        isRun = false;
        handler.removeCallbacks(this);
    }

    //观察者回调接口（IOC数据回调）
    public interface ICountDownHandler {
        //倒计时回调
        void onTicker(int time);//实时回调当前时间

        //完成时回调
        void onFinish();//总时间倒计时为0时，要回调完成的状态
    }
}
