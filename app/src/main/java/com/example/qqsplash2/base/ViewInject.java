package com.example.qqsplash2.base;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
* @Retention：注解的保留期
* - RetentionPolicy.SOURCE 注解只在源码阶段保留，
* 在编译器进行编译时它将被丢弃忽视。
* - RetentionPolicy.CLASS
* 注解只被保留到编译进行的时候，它并不会被加载到 JVM 中。
* - RetentionPolicy.RUNTIME 注解可以保留到程序运行的时候，
* 它会被加载进入 到 JVM 中，所以在程序运行时可以获取到它们
*
@Target：作用域
* ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
* ElementType.CONSTRUCTOR 可以给构造方法进行注解
* ElementType.FIELD 可以给属性进行注解
* ElementType.LOCAL_VARIABLE 可以给局部变量进行注解 E
* lementType.METHOD 可以给方法进行注解
* ElementType.PACKAGE 可以给一个包进行注解
* ElementType.PARAMETER 可以给一个方法内的参数进行注解
  ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举
* */
@Retention(RUNTIME)//运行时 注解
@Target(TYPE)//类 接口 注解
public @interface ViewInject {
    int mainlayoutid() default -1;
}
