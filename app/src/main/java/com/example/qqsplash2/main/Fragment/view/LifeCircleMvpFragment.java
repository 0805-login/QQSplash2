package com.example.qqsplash2.main.Fragment.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qqsplash2.mvp.StaticProxy.MvpController;
import com.example.qqsplash2.mvp.view.IMvpView;

/*静态代理MvpController
* 代理设计模式
* 角色一：目标接口  MvpController实体类
* 角色二：目标对象  LifeCircleMvpActivity
* 角色三：代理对象  LifeCircleMvpPresenter
*
ps：静态代理与动态代理的区别？ 主要的区别是代理类是否提前创建存在的。
*
* */

//MVP底层框架View层的基类    关联View层和P层的生命周期
public class LifeCircleMvpFragment extends Fragment implements IMvpView {

    private MvpController mvpController;

    @Override
    public MvpController getMvpController() {
        if(this.mvpController == null){
            this.mvpController = new MvpController();
        }
        return this.mvpController;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle == null){
            bundle = new Bundle();
        }
        MvpController mvpController = this.getMvpController();
        if(mvpController != null){
            mvpController.onCreate(savedInstanceState,null,bundle);
            mvpController.onActivityCreated(savedInstanceState,null,bundle);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = this.getArguments();
        if(bundle == null){
            bundle = new Bundle();
        }
        MvpController mvpController = this.getMvpController();
        if(mvpController != null){
            mvpController.onActivityCreated(savedInstanceState,null,bundle);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MvpController mvpController = this.getMvpController();
        if(mvpController != null){
            mvpController.onViewDestroy();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        MvpController mvpController = this.getMvpController();
        if(mvpController != null){
            mvpController.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MvpController mvpController = this.getMvpController();
        if(mvpController != null){
            mvpController.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MvpController mvpController = this.getMvpController();
        if(mvpController != null){
            mvpController.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        MvpController mvpController = this.getMvpController();
        if(mvpController != null){
            mvpController.onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MvpController mvpController = this.getMvpController();
        if(mvpController != null){
            mvpController.onDestroy();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        MvpController mvpController = this.getMvpController();
        if(mvpController != null){
            mvpController.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MvpController mvpController = this.getMvpController();
        if(mvpController != null){
            mvpController.onActivityResult(requestCode, resultCode,data);
        }
    }
}
