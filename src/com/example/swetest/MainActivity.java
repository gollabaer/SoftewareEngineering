package com.example.swetest;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

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

	public void startCSV(View view) {

		Intent intent = new Intent(this, CSV_activity.class);
		startActivity(intent);
		finish();

	}

	public void startStatistic(View view) {
		Intent intent = new Intent(this, Statistic.class);
		startActivity(intent);
		// InputNotification.notify(this, "10:00", 1);
	}

	public void test(View view) {
		setup();
		setAlarm(19);
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
	 * Legt Elemnte für AlarmManager an.
	 */
	public void setup() {

		br = new BroadcastReceiver() {

			@Override
			public void onReceive(Context c, Intent i) {

				// Definiert die Ausgabe
				InputNotification.notify(c, "Mario", 1);
			}

		};

		registerReceiver(br, new IntentFilter("Mario"));

		pi = PendingIntent.getBroadcast(this, 0, new Intent("Mario"), 0);

		am = (AlarmManager) (this.getSystemService(Context.ALARM_SERVICE));

	}

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

	/**
	 * Setz die Alarmzeit
	 * 
	 * @param l
	 *            Uhrzeit(z.B. 15 für 15Uhr)
	 */
	public void setAlarm(long l) {
		System.out.println(getTime());
		System.out.println(l);
		if (getTime() > l * ONE_HOUR) {
			am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
					SystemClock.elapsedRealtime()
							+ (ONE_DAY - (getTime() - ONE_HOUR * l)), pi);
		} else {
			am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
					SystemClock.elapsedRealtime() + (ONE_HOUR * l - getTime()),
					pi);
		}
	}

	/**
	 * Zerstört alarmzeiten
	 */
	protected void onDestroy() {
		am.cancel(pi);
		unregisterReceiver(br);
		super.onDestroy();
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
