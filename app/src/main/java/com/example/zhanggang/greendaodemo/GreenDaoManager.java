package com.example.zhanggang.greendaodemo;

import com.wxample.zhanggang.greendao.gen.DaoMaster;
import com.wxample.zhanggang.greendao.gen.DaoSession;

/**
 * Created by zhanggang on 2017/10/13.
 */

public class GreenDaoManager {

    public static GreenDaoManager greenDaoManager;
    private DaoSession daoSession;
    private DaoMaster daoMaster;

    public GreenDaoManager() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(App.context(), "zhanggang.db", null);
        daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public static GreenDaoManager getInstance() {
        if (greenDaoManager == null) {
            greenDaoManager = new GreenDaoManager();
        }
        return greenDaoManager;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public DaoMaster getDaoMaster() {
        return daoMaster;
    }
    public DaoSession getNewSession(){
        daoSession = daoMaster.newSession();
        return daoSession;
    }
}
