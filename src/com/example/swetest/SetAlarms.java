package com.example.swetest;

import java.util.Calendar;

import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SetAlarms extends Activity {

	private static int int1, int2, int3, int4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		//TODO Read Times from XML and apply them on ints
		
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		
		int1 = preferences.getInt("Zeit1", 9);
		int2 = preferences.getInt("Zeit2", 13);
		int3 = preferences.getInt("Zeit3", 16);
		int4 = preferences.getInt("Zeit4", 20);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_alarms);
		
		
		RadioButton rbutton9  = (RadioButton) findViewById(R.id.radio9 );
		RadioButton rbutton10 = (RadioButton) findViewById(R.id.radio10);
		RadioButton rbutton11 = (RadioButton) findViewById(R.id.radio11);
		RadioButton rbutton12 = (RadioButton) findViewById(R.id.radio12);
		RadioButton rbutton13 = (RadioButton) findViewById(R.id.radio13);
		RadioButton rbutton14 = (RadioButton) findViewById(R.id.radio14);
		RadioButton rbutton15 = (RadioButton) findViewById(R.id.radio15);
		RadioButton rbutton16 = (RadioButton) findViewById(R.id.radio16);
		RadioButton rbutton17 = (RadioButton) findViewById(R.id.radio17);
		RadioButton rbutton18 = (RadioButton) findViewById(R.id.radio18);
		RadioButton rbutton19 = (RadioButton) findViewById(R.id.radio19);
		RadioButton rbutton20 = (RadioButton) findViewById(R.id.radio20);
		RadioButton rbutton21 = (RadioButton) findViewById(R.id.radio21);
		RadioButton rbutton22 = (RadioButton) findViewById(R.id.radio22);
		RadioButton rbutton23 = (RadioButton) findViewById(R.id.radio23);
		
		rbutton9.setChecked(int1 == 9);
		rbutton10.setChecked(int1 == 10);
		rbutton11.setChecked(int1 == 11);
		rbutton12.setChecked(int1 == 12);
		rbutton13.setChecked(int2 == 13);
		rbutton14.setChecked(int2 == 14);
		rbutton15.setChecked(int2 == 15);
		rbutton16.setChecked(int3 == 16);
		rbutton17.setChecked(int3 == 17);
		rbutton18.setChecked(int3 == 18);
		rbutton19.setChecked(int3 == 19);
		rbutton20.setChecked(int4 == 20);
		rbutton21.setChecked(int4 == 21);
		rbutton22.setChecked(int4 == 22);
		rbutton23.setChecked(int4 == 23);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_alarms, menu);
		return true;
	}

	public void close(View view){
		super.onBackPressed();
	}
	
	PendingIntent pendingIntent;
	
	public void onOk(View view){
		
		System.out.println("Zeit1: "+ int1 + " Zeit2: "+ int2 + " Zeit3: " + int3+ " Zeit4: "+int4);
		
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);

		SharedPreferences.Editor editor = preferences.edit();

		editor.putInt("Zeit1", int1);
		editor.commit();
		

		editor.putInt("Zeit2", int2);
		editor.commit();
		

		editor.putInt("Zeit3", int3);
		editor.commit();
		

		editor.putInt("Zeit4", int4);
		editor.commit();
		
		
		Long curTime = MainActivity.getTime();
		
		curTime = curTime/(1000*60*60);
		

		int nexttime = 0;
		
		if(curTime>=int4 || curTime < int1){
			nexttime = int1;
		}
		
		if(curTime>= int3 && curTime < int4) {
			nexttime = int4;
		}
		
		if(curTime>= int2 && curTime < int3) {
			nexttime = int3;
		}
		
		if(curTime>= int1 && curTime < int2) {
			nexttime = int2;
		}
		
		
		//----------------------------------------------------------------------------------------
		Intent myIntent = new Intent(this , SetNotific.class);     
	    AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
	    pendingIntent = PendingIntent.getService(this, 0, myIntent, 0);

	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.HOUR_OF_DAY, nexttime);
	    calendar.set(Calendar.MINUTE, 00);
	    calendar.set(Calendar.SECOND, 00);
	    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
		//----------------------------------------------------------------------------------------

		
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
	public void radioButtonClick(View view){
		

		switch (((RadioButton) view).getId()) {
		case R.id.radio9:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio9);
				int1 = 9;
			}
			break;
		case R.id.radio10:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio10);
				int1 = 10;
			}
			break;
		case R.id.radio11:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio11);
				int1 = 11;
			}
			break;
		case R.id.radio12:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio12);
				int1 = 12;
			}
			break;
		case R.id.radio13:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio13);
				int2 = 13;
			}
			break;
		case R.id.radio14:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio14);
				int2 = 14;
			}
			break;
		case R.id.radio15:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio15);
				int2 = 15;
			}
			break;
		case R.id.radio16:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio16);
				int3 = 16;
			}
			break;
		case R.id.radio17:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio17);
				int3 = 17;
			}
			break;
		case R.id.radio18:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio18);
				int3 = 18;
			}
			break;
		case R.id.radio19:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio19);
				int3 = 19;
			}
			break;
		case R.id.radio20:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio20);
				int4 = 20;
			}
			break;
		case R.id.radio21:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio21);
				int4 = 21;
			}
			break;
		case R.id.radio22:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio22);
				int4 = 22;
			}
			break;
		case R.id.radio23:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio23);
				int4 = 23;
			}
			break;
		}
		
	}
	
	
	
	
	
	
	//COPY OF THE ALARMSMANAGERSTUFF------------------------------------------------------------------------------
	

	//Hilfsvariablen um die Alarmzeiten zu übergeben
	final static private long ONE_SECOND = 1000;
	final static private long ONE_MINUTE = ONE_SECOND * 60;
	final static private long ONE_HOUR = ONE_MINUTE * 60;
	final static private long ONE_DAY = ONE_HOUR * 24;

	PendingIntent pi;

	BroadcastReceiver br;

	AlarmManager am;

	/**
	 * Legt Elemente für AlarmManager an.
	 */
	public void setup() {

		br = new BroadcastReceiver() {

			@Override
			public void onReceive(Context c, Intent i) {

				//Definiert die Ausgabe
				InputNotification.notify(c, "Mario", 1);
			}

		};

		registerReceiver(br, new IntentFilter("com.authorwjf.wakeywakey"));

		pi = PendingIntent.getBroadcast(this, 0, new Intent(
				"com.authorwjf.wakeywakey"), 0);

		am = (AlarmManager) getSystemService(ALARM_SERVICE);

	}

	/**
	 * 
	 * @return aktuelle Tageszeit
	 */
	public static long getTime(){
		long time = System.currentTimeMillis();
		
		while(time>ONE_DAY){
			time = time - ONE_DAY;
		}
		
		return time;
	}
	
	/**
	 * Setz die Alarmzeit
	 * 
	 * @param l Uhrzeit(z.B. 15 für 15Uhr)
	 */
	public void setAlarm(long l) {
		System.out.println("getTime() + l*HOUR" + getTime() +""+l*ONE_HOUR);
		if(getTime()>l*ONE_HOUR)
			System.out.println("Ausgabe1: "+SystemClock.elapsedRealtime() + (ONE_DAY-(getTime()-ONE_HOUR*l)));
		else
			System.out.println("Ausgabe: "+(SystemClock.elapsedRealtime() + (ONE_HOUR*l-getTime())));
		System.out.println(l);
		System.out.println("elapsedRealtime: "+SystemClock.elapsedRealtime());
		if(getTime()>l*ONE_HOUR){
			am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
					SystemClock.elapsedRealtime() + (ONE_DAY-(getTime()-ONE_HOUR*l)), pi);
		}
		else{
			am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
					SystemClock.elapsedRealtime() + (ONE_HOUR*l-getTime()), pi);
		}
	}

	//------------------------------------------------------------------------------------------------------------
	
	
}
