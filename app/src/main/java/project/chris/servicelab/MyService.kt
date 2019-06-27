package project.chris.servicelab

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

public class MyService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        Log.e("＊＊＊", "onBind")
        return MyBinder()
    }

    //啟動或bind這個service元件時，第一個呼叫的方法
    override fun onCreate() {
        Log.e("＊＊＊", "onCreate")
    }

    //啟動service元件時呼叫的方法
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("＊＊＊", "onStartCommand")
        stopSelf();
        return super.onStartCommand(intent, flags, startId)
    }

    //停止service時呼叫的方法
    override fun onDestroy() {
        Log.e("＊＊＊", "onDestroy")
    }

    inner class MyBinder : Binder() {
        fun getService(): MyService {
            return this@MyService
        }
    }
}