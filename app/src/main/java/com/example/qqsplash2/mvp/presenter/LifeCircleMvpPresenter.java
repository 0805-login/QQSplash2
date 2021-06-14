package com.example.qqsplash2.mvp.presenter;

import com.example.qqsplash2.mvp.view.IMvpView;
import com.example.qqsplash2.mvp.StaticProxy.MvpController;

import java.lang.ref.WeakReference;

public abstract class LifeCircleMvpPresenter<T extends IMvpView> implements ILifeCircle {//泛型绑定 泛型约束
    protected WeakReference<T> weakReference;//弱引用
    //定义P层构造
    protected LifeCircleMvpPresenter(){
        super();
    }

    /*
    * 强引用（StrongReference）：只要某个对象有强引用与之关联，JVM 必定不会回收这个对象，
    即使在内存不足的情况下，JVM 宁愿抛出 OutOfMemory 错误也不会回收这种对象。
    * 软引用（SoftReference）：对于软引用关联着的对象，只有在内存不足的时候 JVM 才会回收该对象。
    * 弱引用(WeakReference) : 弱引用也是用来描述非必需对象的，当 JVM 进行垃圾 回收时，
    无论内存是否充足，都会回收被弱引用关联的对象。
    * 虚引用（PhantomReference）: 在任何时候都可能被垃圾回收器回收。
    * */

    public LifeCircleMvpPresenter(IMvpView iMvpView){
        super();
        attachView(iMvpView);//将传递过来的View层参数iMvpView进行弱引用
        MvpController mvpController = iMvpView.getMvpController();
        mvpController.savePresenter(this);
    }

    @Override
    public void attachView(IMvpView iMvpView) {
        //做弱引用
        if(weakReference == null){
            weakReference = new WeakReference(iMvpView);//预防空指针异常
        }else {//如果弱引用存在，则获取出来，跟当前传递的对象不一致则重新创建一份弱引用，确保传递过来的参数对P层进行数据的同步
            T view = (T) weakReference.get();
            if (view != iMvpView) {
                weakReference = new WeakReference(iMvpView);
            }
        }
    }

    @Override
    public void onDestroy() {
        weakReference = null;
    }

    protected T getView(){//在P层获取View层的引用
        /*//三元运算符
        语法为：条件表达式?表达式1:表达式2。
        说明：问号前面的位置是判断的条件，判断结果为bool型，为true时调用表达式1，为false时调用表达式2。
        其逻辑为：“如果条件表达式成立或者满足则执行表达式1，否则执行第二个。”常用在设置默认值,例如某个值不一定存在,则判断这个值是否存在,不存在给默认值(表达式2)。*/
        T view = weakReference != null ? weakReference.get() : null;
        if(view == null){
            return getEmptyView();
        }
        return view;
    }

    protected abstract T getEmptyView();
}

