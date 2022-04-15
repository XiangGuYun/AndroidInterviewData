package com.yxd.knowledge.ipc.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("Service Started");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Service Destroyed");
    }
}