package com.example.swetest;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.view.KeyEvent;
import android.view.View;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private static final String LINE = System.getProperty("line.separator");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		Button buttonTime = (Button) findViewById(R.id.buttonTime);
		Button buttonInput = (Button) findViewById(R.id.buttonInput);
		
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		String Usercode = preferences.getString("Usercode", "UNDEF");
		int Alarm = preferences.getInt("Zeit1", -1);
		
		setTimeAndInputButtonEnable(buttonTime, buttonInput, Usercode, Alarm);
		
		setCodeButtonEnable(Usercode);
		
		
	}

	private void setCodeButtonEnable(String Usercode) {
		Button buttonCode = (Button) findViewById(R.id.buttonCode);
		if (Usercode != "UNDEF") {
			buttonCode.setEnabled(false);
		} else {
			buttonCode.setEnabled(true);
		}
	}

	private void setTimeAndInputButtonEnable(Button buttonTime,
			Button buttonInput, String Usercode, int Alarm) {
		if (Usercode == "UNDEF") {
			buttonTime.setEnabled(false);
			buttonInput.setEnabled(false);
		}else{
			if (Alarm == -1) {
				buttonInput.setEnabled(false);
				buttonTime.setEnabled(true);
			} else {
				buttonTime.setEnabled(true);
				buttonInput.setEnabled(true);
			}
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	    	super.moveTaskToBack(true);
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void startTime(View view) {

		Intent intent = new Intent(this, SetAlarms.class);
		startActivity(intent);
	}

	public void startCodeGenerator(View view) {
		Intent intent = new Intent(this, GenerateCode.class);
		startActivity(intent);
	}

	public void startStatistic(View view) {
		Intent intent = new Intent(this, Statistic.class);
		startActivity(intent);
		// InputNotification.notify(this, "10:00", 1);
	}

	// Hilfsvariablen um die Alarmzeiten zu übergeben
	final static private long ONE_SECOND = 1000;
	final static private long ONE_MINUTE = ONE_SECOND * 60;
	final static private long ONE_HOUR = ONE_MINUTE * 60;
	final static private long ONE_DAY = ONE_HOUR * 24;

	PendingIntent pi;

	BroadcastReceiver br;

	AlarmManager am;


	/**
	 * 
	 * @return aktuelle Tageszeit
	 */
	public static long getTime() {
		long time = System.currentTimeMillis();

		while (time > ONE_DAY) {
			time = time - ONE_DAY;
		}

		return time;
	}

	public static void setUserCode(Context context, String actualUsercode) {

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(context);

		SharedPreferences.Editor editor = preferences.edit();

		editor.putString("Usercode", actualUsercode);
		editor.commit();

		editor.putInt("lastTime", -77);
		editor.commit();

	}

	public String getUsercodeAsString() {

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);

		String ausgabeUsercode = preferences.getString("Usercode", "UNDEF");

		return ausgabeUsercode;

	}

}
