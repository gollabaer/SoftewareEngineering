package com.example.swetest;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

	public void onOk(View view) {

		EditText NumberEdit = (EditText) findViewById(R.id.editTextNumber);
		Integer numberOfContacts = Integer.parseInt(NumberEdit.getText()
				.toString());

		EditText HoursEdit = (EditText) findViewById(R.id.EditTextHours);
		Integer hours = Integer.parseInt(HoursEdit.getText().toString());

		EditText MinutesEdit = (EditText) findViewById(R.id.EditTextMinutes);
		Integer minutes = Integer.parseInt(MinutesEdit.getText().toString());

		// System.out.println(code.toString());
		valid = validate(hours, minutes);
		if (valid) {
			// System.out.println("true");
			boolean success = send(numberOfContacts, hours, minutes);
			if (!success) {
				throw new SecurityException("Couldn't hand over user code.");
			}

		} else {
			// System.out.println("false");
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle("Fehler");
			final Resources res = this.getResources();
			alertDialog.setMessage(res.getString(R.string.StatisticsError));
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

	public static boolean validate(int hours, int minutes) {

		// aktuelle - getLastTime() // Millisekunden

		long diff = System.currentTimeMillis() - 10;// getLastTime();
		hours = (int) TimeUnit.HOURS.toMillis(hours);
		minutes = (int) TimeUnit.MINUTES.toMillis(minutes);

		if (diff - (hours + minutes) < 0) {
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

		getUsercodeAsString() + "::" + datum + "::" + getCurrentAlarmTimeAsInt() + "::"
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

	
	
	public int getCurrentAlarmTimeAsInt(){
		
		
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);


		
		
		return  preferences.getInt("AlarmTime", -77);
		
	}
	
	
	public boolean send(int numberOfContacts, int hours, int minutes) {

		createLine(numberOfContacts, hours, minutes);
		finish();
		
		
		//LastTime Šndern!!!
		
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);

		SharedPreferences.Editor editor = preferences.edit();
		
		editor.putInt("lastTime", getCurrentAlarmTimeAsInt() );
		editor.commit();

		

		return true;
	}

}
