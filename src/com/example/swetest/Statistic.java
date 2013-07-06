package com.example.swetest;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Statistic extends Activity {

	private static final String LINE = System.getProperty("line.separator");
	private static boolean valid = false;
	private static boolean timevalid = true;
	private static boolean numbervalid = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistic);
		TextView Message = (TextView) findViewById(R.id.textViewMessage);
		final Resources res = this.getResources();
		Message.setText(res.getString(R.string.InputMessage, "10"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.statistic, menu);
		return true;
	}

	public void close(View view) {
		super.onBackPressed();
	}

	PendingIntent pendingIntent;
	
	public void onOk(View view) {

		EditText NumberEdit = (EditText) findViewById(R.id.editTextNumber);
		String numberOfContacts = NumberEdit.getText().toString();

		EditText HoursEdit = (EditText) findViewById(R.id.EditTextHours);
		String hours = HoursEdit.getText().toString();

		EditText MinutesEdit = (EditText) findViewById(R.id.EditTextMinutes);
		String minutes = MinutesEdit.getText().toString();

		// System.out.println(code.toString());
				
		valid = validate(numberOfContacts, hours, minutes);
		if (valid) {
			// System.out.println("true");
			boolean success = send(Integer.parseInt(numberOfContacts), Integer.parseInt(hours), Integer.parseInt(minutes));
			if (!success) {
				throw new SecurityException("Couldn't hand over user code.");
			}
			
			
			Long curTime = MainActivity.getTime();
			
			curTime = curTime/(1000*60*60);
			SharedPreferences preferences = PreferenceManager
					.getDefaultSharedPreferences(this);
			
			int int1 = preferences.getInt("Zeit1", 9);
			int int2 = preferences.getInt("Zeit2", 13);
			int int3 = preferences.getInt("Zeit3", 16);
			int int4 = preferences.getInt("Zeit4", 20);
			
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

		} else {
			// System.out.println("false");
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle("Fehler");
			final Resources res = this.getResources();
			if (!numbervalid){
				numbervalid = true;
				alertDialog.setMessage(res.getString(R.string.StatisticsNumberError));
			}
			if (!timevalid){
				timevalid = true;
				alertDialog.setMessage(res.getString(R.string.StatisticsTimeError));
			}
			alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,
					res.getString(R.string.ok),
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});
			alertDialog.show();
		}
	}

	public static boolean validate(String snumber, String shours, String sminutes) {
		
		if (snumber.isEmpty()){
			numbervalid = false;
			return false;
		}
		if (shours.isEmpty()){
			timevalid = false;
			return false;
		}
		if (sminutes.isEmpty()){
			timevalid = false;
			return false;
		}

		int hours =  Integer.parseInt(shours);
		int minutes =  Integer.parseInt(sminutes);
		// aktuelle - getLastTime() // Millisekunden

		long diff = System.currentTimeMillis() - 10;// getLastTime();
		hours = (int) TimeUnit.HOURS.toMillis(hours);
		minutes = (int) TimeUnit.MINUTES.toMillis(minutes);

		if (diff - (hours + minutes) < 0) {
			timevalid = false;
			return false;
		} else {
			return true;

		}

	}

	public String getUsercodeAsString() {

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);

		String ausgabeUsercode = preferences.getString("Usercode", "UNDEF");

		return ausgabeUsercode;

	}

	public void createLine(int kontakte, int stunden, int minuten) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy",
				Locale.GERMANY);
		String datum = dateFormat.format(new java.util.Date());

		SimpleDateFormat uhrFormat = new SimpleDateFormat("HH:mm",
				Locale.GERMANY);
		String uhrzeit = uhrFormat.format(new java.util.Date());

		// create csv filename
		String filename = getUsercodeAsString() + ".csv";

		// Erstellt String zum schreiben
		String stringToWrite =

		getUsercodeAsString() + "::" + datum + "::" + "ALARMZEIT" + "::"
				+ uhrzeit + "::" + "ABBRUCH" + "::" + kontakte + "::" + stunden
				+ "::" + minuten;

		FileOutputStream outputStream;

		try {
			outputStream = openFileOutput(filename, Context.MODE_APPEND);
			outputStream.write(stringToWrite.getBytes());
			outputStream.write(LINE.getBytes());
			outputStream.flush();
			outputStream.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public boolean send(int numberOfContacts, int hours, int minutes) {

		createLine(numberOfContacts, hours, minutes);
		finish();

		return true;
	}

}
