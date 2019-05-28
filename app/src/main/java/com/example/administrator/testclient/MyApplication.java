package com.example.administrator.testclient;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.xutils.x;

import java.util.HashSet;
import java.util.Set;

public class MyApplication extends Application {
    private static MyApplication myApplication;
    private static Handler mainHandler;
    //    private static Context AppContext;
    private Set<Activity> allActivities;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        mainHandler = new Handler();
        x.Ext.init(this);
        x.Ext.setDebug(false);
        myApplication = this;
    }

    public static MyApplication getContext() {
        return myApplication;
    }

    public static Handler getHandler(){
        return mainHandler;
    }

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
