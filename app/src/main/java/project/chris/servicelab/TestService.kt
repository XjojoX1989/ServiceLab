package project.chris.servicelab

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class TestService : Service() {
    val TAG = "＊TestService"
    val mBinder = DownloadBinder()
    override fun onBind(intent: Intent?): IBinder? {
        Log.e(TAG, "onBind");
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        val intent =  Intent(this, MainActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, 0)
        val notification = NotificationCompat.Builder(this)
                .setContentTitle("This is title")
                .setContentText("This is content")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build()
        startForeground(1, notification)
        Log.e(TAG, "onCreate");
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand");
        val executorService = Executors.newSingleThreadExecutor()
        executorService.submit{
            //call api or download

            //stopSelf()
        }
        executorService.shutdown()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy");
    }

    class DownloadBinder : Binder() {
        val TAG = "＊DownloadBinder"
        var progress: Int = 0
        fun startDownload() {
            Log.e(TAG, "startDownload");
//
//            while (progress < 100) {
//                Log.e(TAG, "progress : $progress %")
//                progress++
//            }
            Log.e(TAG, "progress : " + getNowProgress() + " %")

        }

        fun getNowProgress(): Int {
            return progress
        }
    }
}