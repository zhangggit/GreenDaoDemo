package com.example.zhanggang.greendaodemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhanggang on 2017/10/13.
 */

public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        GreenDaoManager.getInstance();
    }
    public static Context context(){
        return context;
    }
}
