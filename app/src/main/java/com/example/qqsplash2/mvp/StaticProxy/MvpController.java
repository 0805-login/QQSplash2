package com.example.qqsplash2.mvp.StaticProxy;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.qqsplash2.mvp.presenter.ILifeCircle;
import com.example.qqsplash2.mvp.view.IMvpView;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/*静态代理MvpController
* 代理设计模式
* 角色一：目标接口  MvpController实体类
* 角色二：目标对象  LifeCircleMvpActivity
* 角色三：代理对象  LifeCircleMvpPresenter
*
ps：静态代理与动态代理的区别？ 主要的区别是代理类是否提前创建存在的。
*
* */
public class MvpController implements ILifeCircle {
    //存放的是P层的实例   一个Activity可以对应多个P层
    private Set<ILifeCircle> lifeCircles = new HashSet<>();

    public static MvpController newInstance(){
        return new MvpController();
    }

    public void savePresenter(ILifeCircle iLifeCircle){
        this.lifeCircles.add(iLifeCircle);
    }

    //在View层调用代理类的逻辑处理
    @Override
    public void onCreate(Bundle savedInstanceState, Intent intent, Bundle getArguments) {
        //遍历Set<ILifeCircle>
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();//遍历器
        while (iterator.hasNext()){
            ILifeCircle next = iterator.next();
            if(intent == null){
                intent = new Intent();
            }
            if(getArguments == null){
                getArguments = new Bundle();
            }
            next.onCreate(savedInstanceState,intent,getArguments);
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState, Intent extras, Bundle getArguments) {
        Iterator var3 = this.lifeCircles.iterator();
        while (var3.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var3.next();
            if (extras == null) {
                extras = new Intent();
            }
            if (getArguments == null) {
                getArguments = new Bundle();
            }
            presenter.onActivityCreated(savedInstanceState, extras,getArguments);
        }
    }

    @Override
    public void onStart() {
        Iterator var1 = this.lifeCircles.iterator();
        while (var1.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var1.next();
            presenter.onStart();
        }
    }

    @Override
    public void onResume() {
        Iterator var2 = this.lifeCircles.iterator();
        while (var2.hasNext()) {
            ILifeCircle presenter1 = (ILifeCircle) var2.next();
            presenter1.onResume();
        }
    }

    @Override
    public void onPause() {
        Iterator var1 = this.lifeCircles.iterator();
        while (var1.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var1.next();
            presenter.onPause();
        }
    }

    @Override
    public void onStop() {
        Iterator var1 = this.lifeCircles.iterator();
        while (var1.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var1.next();
            presenter.onStop();
        }
    }

    @Override
    public void onDestroy() {
        Iterator var1 = this.lifeCircles.iterator();

        while (var1.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var1.next();
            presenter.onDestroy();
        }
        this.lifeCircles.clear();
    }

    @Override
    public void destroyView() {
        Iterator var1 = this.lifeCircles.iterator();
        while (var1.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var1.next();
            presenter.destroyView();
        }
    }

    @Override
    public void onViewDestroy() {
        Iterator var1 = this.lifeCircles.iterator();
        while (var1.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var1.next();
            presenter.onViewDestroy();
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        Iterator var2 = this.lifeCircles.iterator();
        while (var2.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var2.next();
            presenter.onNewIntent(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Iterator var4 = this.lifeCircles.iterator();
        while (var4.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var4.next();
            presenter.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Iterator var2 = this.lifeCircles.iterator();
        while (var2.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var2.next();
            presenter.onSaveInstanceState(outState);
        }
    }

    @Override
    public void attachView(IMvpView iMvpView) {
        Iterator var1 = this.lifeCircles.iterator();
        while (var1.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var1.next();
            presenter.attachView(iMvpView);
        }
    }
}
