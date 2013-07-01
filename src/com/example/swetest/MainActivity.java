package com.example.swetest;

import java.io.FileOutputStream;

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
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void startTime(View view){
		
		Intent intent = new Intent(this, SetAlarms.class);
		startActivity(intent);
	}
	
	public void startCodeGenerator(View view){
		Intent intent = new Intent(this, GenerateCode.class);
		startActivity(intent);
	}
	
	public void startCSV(View view){
		
		Intent intent = new Intent (this, CSV_activity.class);
		startActivity(intent);
		finish(); 
		
		
	}
	
	
	public void startStatistic(View view){
		//Intent intent = new Intent(this, SetAlarms.class);
		//startActivity(intent);
		InputNotification.notify(this, "10:00", 1);
	}
	
	public void test(View view){
		setup();
		setAlarm(5);
	}
	
	final static private long ONE_SECOND = 1000;
	final static private long ONE_MINUTE = ONE_SECOND * 60;
	final static private long ONE_HOUR = ONE_MINUTE * 60;
	final static private long ONE_DAY = ONE_HOUR * 24;

	PendingIntent pi;

	BroadcastReceiver br;

	AlarmManager am;

	private void setup() {

		br = new BroadcastReceiver() {

			@Override
			public void onReceive(Context c, Intent i) {

//				Toast.makeText(c, "Rise and Shine!", Toast.LENGTH_LONG).show();
				InputNotification.notify(c, "Mario", 1);
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
	
	public void setAlarm(long l) {
		System.out.println(getTime());
		System.out.println(l);
		if(getTime()>l*ONE_HOUR){
			am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
					SystemClock.elapsedRealtime() + (ONE_DAY-(getTime()-ONE_HOUR*l)), pi);
		}
		else{
			am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
					SystemClock.elapsedRealtime() + (ONE_HOUR*l-getTime()), pi);
		}
	}

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

		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		alertDialog.setTitle("Succes!");
		alertDialog.setMessage("Code erfolgreich gespeichert");
		alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ok",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
						
						
					}
				});
		alertDialog.show();

	}
	
	

	
	
	
public  void createCSV(Context context) {

	

	// create csv file
	String filename = "USERCODE.txt";

	FileOutputStream outputStream;

	try {
		outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
		outputStream.flush();
		outputStream.close();
		

	} catch (Exception e) {
		e.printStackTrace();
	
	}

}
	
	
}
