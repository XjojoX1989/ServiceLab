package project.chris.servicelab;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyService myService;
    private Button btStart, btStop, btBind, btUnbind, btIntentService;
    private String TAG = "＊MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByIds();

    }

    private void findViewByIds() {
        btStart = findViewById(R.id.btStart);
        btStop = findViewById(R.id.btStop);
        btBind = findViewById(R.id.btBind);
        btUnbind = findViewById(R.id.btUnbind);
        btIntentService = findViewById(R.id.btIntentService);
        btStart.setOnClickListener(this);
        btStop.setOnClickListener(this);
        btBind.setOnClickListener(this);
        btUnbind.setOnClickListener(this);
        btIntentService.setOnClickListener(this);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected");
            TestService.DownloadBinder binder = (TestService.DownloadBinder) service;
            binder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected");
        }


    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btStart:
                Intent startIntent = new Intent(MainActivity.this, TestService.class);
                startService(startIntent);
                break;
            case R.id.btStop:
                Intent stopIntent = new Intent(MainActivity.this, TestService.class);
                stopService(stopIntent);
                break;
            case R.id.btBind:
                Intent bindIntent = new Intent(MainActivity.this, TestService.class);
                bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.btUnbind:
                unbindService(serviceConnection);
                break;
            case R.id.btIntentService:
                Log.e(TAG, "Thread id :"+Thread.currentThread().getId());
                startService(new Intent(MainActivity.this, MyIntentService.class));
                break;

        }
    }
}
