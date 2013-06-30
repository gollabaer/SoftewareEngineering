package com.example.swetest;

import android.os.SystemClock;

import android.app.Activity;

import android.app.AlarmManager;

import android.app.PendingIntent;

import android.content.BroadcastReceiver;

import android.content.Context;

import android.content.Intent;

import android.content.IntentFilter;

import android.widget.Toast;

public class Alarm extends Activity {

	final static private long ONE_SECOND = 1000;
	final static private long ONE_MINUTE = ONE_SECOND * 60;
	final static private long ONE_HOUR = ONE_MINUTE * 60;
	final static private long ONE_DAY = ONE_HOUR * 24;

	PendingIntent pi;

	BroadcastReceiver br;

	AlarmManager am;

	
	public Alarm(){
		this.setup();
	}

	public Alarm(int time){
		this.setup();
		setAlarm(time);
	}
	
	
	private void setup() {

		br = new BroadcastReceiver() {

			@Override
			public void onReceive(Context c, Intent i) {

				Toast.makeText(c, "Rise and Shine!", Toast.LENGTH_LONG).show();
			}

		};

		registerReceiver(br, new IntentFilter("com.authorwjf.wakeywakey"));

		pi = PendingIntent.getBroadcast(this, 0, new Intent(
				"com.authorwjf.wakeywakey"), 0);

		am = (AlarmManager) (this.getSystemService(Context.ALARM_SERVICE));

	}

	public static long getTime(){
		long time = System.currentTimeMillis();
		
		while(time>ONE_DAY){
			time = time - ONE_DAY;
		}
		
		return time;
	}
	
	public void setAlarm(int time) {
		am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
				SystemClock.elapsedRealtime() + (ONE_HOUR*time-getTime()), pi);
	}

	protected void onDestroy() {
		am.cancel(pi);
		unregisterReceiver(br);
		super.onDestroy();
	}
}
