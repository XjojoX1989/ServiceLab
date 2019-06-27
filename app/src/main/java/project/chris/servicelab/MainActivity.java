package project.chris.servicelab;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder iBinder) {
                MyService.MyBinder myBinder = (MyService.MyBinder) iBinder;
                myService = myBinder.getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                myService = null;
            }
        };


        findViewById(R.id.btService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                intent.putExtra("test", "test service");
                startService(intent);

            }
        });

        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("test", "test bind");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        findViewById(R.id.btStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);

            }
        });
    }

}
