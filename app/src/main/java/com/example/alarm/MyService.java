package com.example.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    public MyService() {
    }
    private Integer alarmhr;
    private Integer alarmmin;
    private Ringtone ringtone;
    private Timer timer = new Timer();
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        alarmhr = intent.getIntExtra("alarmhr",0);
        alarmmin = intent.getIntExtra("alarmmin",0);

        ringtone = RingtoneManager.getRingtone(getApplicationContext(),RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        timer.scheduleAtFixedRate(new TimerTask() {
            int time = 0;
            @Override
            public void run() {
//                long time = 0;

                if(time >0){
                    ringtone.stop();
                    timer.cancel();
                    stopSelf();
                }
                else if(Calendar.getInstance().getTime().getHours() == alarmhr &&
                Calendar.getInstance().getTime().getMinutes() == alarmmin && time ==0){

                    ringtone.play();
                    time = 1;

                }

            }
        },0,10000);

        return super.onStartCommand(intent, flags, startId);
    }

    private void cancelAlarm() {
        if (alarmManager != null && pendingIntent != null) {
            alarmManager.cancel(pendingIntent);
        }
    }

    @Override
    public void onDestroy() {
        ringtone.stop();
        timer.cancel();
        super.onDestroy();
        cancelAlarm();
    }
}