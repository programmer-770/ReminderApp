package com.honneshrohmetra.reminder;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class ReminderManager {

    private Context myContext;
    private AlarmManager myAlarmManager;

    public ReminderManager(Context context) {
        myContext = context;
        myAlarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
    }

    public void setReminder(Long taskId, Calendar when) {

        Intent i = new Intent(myContext, OnAlarmReceiver.class);
        i.putExtra(RemindersDbAdapter.KEY_ROWID, (long)taskId);

        PendingIntent pi = PendingIntent.getBroadcast(myContext, 0, i, PendingIntent.FLAG_ONE_SHOT);

        myAlarmManager.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis(), pi);
    }
}
