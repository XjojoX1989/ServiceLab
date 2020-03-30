package project.chris.servicelab

import android.app.IntentService
import android.content.Intent
import android.util.Log


class MyIntentService : IntentService("MyIntentService") {
    val TAG = "＊MyIntentService"
    override fun onHandleIntent(intent: Intent?) {
        Log.e(TAG, "MyIntentService")
        Log.e(TAG, "Thread id : " + Thread.currentThread().id)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy")
    }
}