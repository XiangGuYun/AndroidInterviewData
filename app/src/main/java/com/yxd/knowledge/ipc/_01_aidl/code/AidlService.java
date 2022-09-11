package com.yxd.knowledge.ipc._01_aidl.code;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * 服务端
 *
 * 在test进程中
 */
public class AidlService extends Service {

    /**
     * 当客户端绑定时执行
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    private IBinder iBinder = new IMyAidlInterface.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {
            Log.d("YXD", "收到了客户端的请求，参数是"+a+"和"+b);
            return a + b;
        }
    };
}
