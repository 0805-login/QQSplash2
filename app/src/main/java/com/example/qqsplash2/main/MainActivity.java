package com.example.qqsplash2.main;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.example.qqsplash2.R;
import com.example.qqsplash2.base.BaseMvpActivity;
import com.example.qqsplash2.base.ViewInject;
import com.example.qqsplash2.main.tools.MainConstantTool;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 自定义注解
 * 注解：@BindView本身就是注解，而@Retention和@Target用于修饰它，则它俩就是元注解
 * 元注解: 元注解是一种基本注解，但是它能够应用到其它的注解上面。
 *
 * @Retention：注解的保留期
 * @Target：作用域
 */

//对类进行注解
@ViewInject(mainlayoutid = R.layout.activity_main)
public class MainActivity extends BaseMvpActivity implements IMainActivityContract.IView{

    IMainActivityContract.IPresenter mPresenter = new MainActivityPresenter(this);

    @BindView(R.id.fac_main_button)
    FloatingActionButton facMainButton;
    @BindView(R.id.rg_main_shanghai)
    RadioButton rbMainShanghai;
    @BindView(R.id.rg_main_hangzhou)
    RadioButton rbMainHangzhou;
    @BindView(R.id.rg_main_beijing)
    RadioButton rbMainBeijing;
    @BindView(R.id.rg_main_shenzhen)
    RadioButton rbMainShenzhen;
    @BindView(R.id.rg_main_top)
    RadioGroup rgMainTop;
    @BindView(R.id.rg_main_bottom)
    RadioGroup rgMainBottom;
    @BindView(R.id.fl_bottom)
    FrameLayout flBottom;
    private boolean isChangeTopOrBottom;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        *//*setContentView(R.layout.activity_main);

        * 使用对类进行注解进行替换
        *
        @Viewinject(mainlayoutid = R.layout.activity_main)
        * *//*
        changeAnime(rgMainBottom,rgMainTop);
    }*/

    @Override
    public void afterBindView() {
        initHomeFragment();
        changeAnime(rgMainBottom,rgMainTop);
        initCheckListener();
    }

    private void initCheckListener() {
        rbMainShanghai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbMainShanghai.getId() == mPresenter.getCurrentCheckedIndex()){
                    return;
                }
                mPresenter.replaceFragment(MainConstantTool.SHANGHAI);
            }
        });

        rbMainHangzhou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbMainHangzhou.getId() == mPresenter.getCurrentCheckedIndex()){
                    return;
                }
                mPresenter.replaceFragment(MainConstantTool.HANGZHOU);
            }
        });

        rbMainBeijing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbMainBeijing.getId() == mPresenter.getCurrentCheckedIndex()){
                    return;
                }
                mPresenter.replaceFragment(MainConstantTool.BEIJING);
            }
        });

        rbMainShenzhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbMainShenzhen.getId() == mPresenter.getCurrentCheckedIndex()){
                    return;
                }
                mPresenter.replaceFragment(MainConstantTool.SHENZHEN);
            }
        });
    }

    //初始化Fragment
    private void initHomeFragment() {
        mPresenter.initHomeFragment();
    }


    @OnClick(R.id.fac_main_button)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fac_main_button:
                isChangeTopOrBottom = !isChangeTopOrBottom;
                if(isChangeTopOrBottom){
                    changeAnime(rgMainTop,rgMainBottom);
                    handleTopPosition();
                }else {
                    changeAnime(rgMainBottom,rgMainTop);
                    handleBottomPosition();
                }
                break;
        }
    }

    //北京2  深圳3
    private void handleBottomPosition() {
        if(mPresenter.getTopPosition() != MainConstantTool.HANGZHOU){
            mPresenter.replaceFragment(MainConstantTool.SHANGHAI);//java枚举不太适合在android中应用，占用内存较大
            rbMainShanghai.setChecked(true);
        }else {
            mPresenter.replaceFragment(MainConstantTool.HANGZHOU);
            rbMainHangzhou.setChecked(true);
        }
        /*if(mPresenter.getCurrentCheckedIndex() > 1){//应该记录当前position然后再进行判断

        }else {

        }*/
    }

    //上海0 杭州1
    private void handleTopPosition() {
        if(mPresenter.getBottomPosition() != MainConstantTool.SHENZHEN){
            mPresenter.replaceFragment(MainConstantTool.BEIJING);
            rbMainBeijing.setChecked(true);
        }else {
            mPresenter.replaceFragment(MainConstantTool.SHENZHEN);
            rbMainShenzhen.setChecked(true);
        }
    }

    //补间动画
    private void changeAnime(RadioGroup gone,RadioGroup show) {
        //消失的动画
        gone.clearAnimation();//清除自身动画
        Animation animationGone =  AnimationUtils.loadAnimation(this,R.anim.main_tab_translate_hide);
        gone.startAnimation(animationGone);
        gone.setVisibility(View.GONE);

        //出现的动画
        show.clearAnimation();//清除自身动画
        Animation animationShow =  AnimationUtils.loadAnimation(this,R.anim.main_tab_translate_show);
        show.startAnimation(animationShow);
        show.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().show(mFragment).commit();//commit()提交事务
    }

    @Override
    public void addFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main_content,mFragment).commit();
    }

    @Override
    public void hideFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().hide(mFragment).commit();
    }
}