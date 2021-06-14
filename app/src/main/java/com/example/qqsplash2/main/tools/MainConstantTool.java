package com.example.qqsplash2.main.tools;

/*
* 通过自定义注解实现常量
* */

import androidx.annotation.IntDef;

import static com.example.qqsplash2.main.tools.MainConstantTool.BEIJING;
import static com.example.qqsplash2.main.tools.MainConstantTool.HANGZHOU;
import static com.example.qqsplash2.main.tools.MainConstantTool.SHANGHAI;
import static com.example.qqsplash2.main.tools.MainConstantTool.SHENZHEN;

@IntDef({SHANGHAI,HANGZHOU,BEIJING,SHENZHEN})
public @interface MainConstantTool {

//    public static int ShangHai = 0;
    int SHANGHAI = 0;

    int HANGZHOU = 1;

    int BEIJING = 2;

    int SHENZHEN = 3;
}
