package com.example.qqsplash2.main.Fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.qqsplash2.base.ViewInject;
import com.example.qqsplash2.main.Fragment.view.LifeCircleMvpFragment;

import butterknife.ButterKnife;

public abstract class BaseMvpFragment extends LifeCircleMvpFragment {

    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = null;
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);//获取注解
        if(annotation != null){
            int mainlayoutid = annotation.mainlayoutid();
            if(mainlayoutid > 0){

                mView = initFragmentView(inflater,mainlayoutid);
                bindView(mView);
                afterBindView();

            }else {
                throw new RuntimeException("mainlayoutid < 0");
            }
        }else {
            throw new RuntimeException("mainlayoutid = null");
        }
        return mView;
    }

    protected View initFragmentView(LayoutInflater inflater,int mainlayoutid){
        return inflater.inflate(mainlayoutid, null);
    }

    //模板方法 设计模式
    public abstract void afterBindView();

    //View的依赖注入绑定
    private void bindView(View mView) {
        ButterKnife.bind(this,mView);
    }
}
