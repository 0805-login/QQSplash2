package com.example.qqsplash2.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.qqsplash2.mvp.view.LifeCircleMvpActivity;

import butterknife.ButterKnife;

public abstract class BaseMvpActivity extends LifeCircleMvpActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);//获取注解
        if(annotation != null){
            int mainlayoutid = annotation.mainlayoutid();
            if(mainlayoutid > 0){
                setContentView(mainlayoutid);
                bindView();
                afterBindView();
            }else {
                throw new RuntimeException("mainlayoutid < 0");
            }
        }else {
            throw new RuntimeException("mainlayoutid = null");
        }
    }

    //模板方法 设计模式   父类定义一套规则，具体的实现由子类完成
    public abstract void afterBindView();

    //View的依赖注入绑定
    private void bindView() {
        ButterKnife.bind(this);
    }
}
