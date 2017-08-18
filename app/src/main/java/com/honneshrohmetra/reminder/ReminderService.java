package com.honneshrohmetra.reminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;

public class ReminderService extends WakeupSid {

    public ReminderService() {
        super("ReminderService");
    }
   static MediaPlayer mMediaPlayer;
    @Override
    void doReminderWork(Intent intent) {
        Log.d("ReminderService", "Doing work.");
        Long rowId = intent.getExtras().getLong(RemindersDbAdapter.KEY_ROWID);


        Intent notificationIntent = new Intent(this.getApplicationContext(), ReminderEditActivity.class);
        notificationIntent.putExtra(RemindersDbAdapter.KEY_ROWID, rowId);
        notificationIntent.putExtra("stop","stop");

        PendingIntent pi = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);
        final NotificationManager mNM = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        Notification mNotify  = new Notification.Builder(this)
                .setContentTitle("You have got a TASK TO DO  " + "!")
                .setContentText("Tap here to stop alarm")
                .setSmallIcon(R.mipmap.reminder)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_beeps);
        mMediaPlayer.start();


        mNM.notify(0, mNotify);
    }
}
