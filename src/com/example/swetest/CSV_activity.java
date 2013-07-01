package com.example.swetest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class CSV_activity extends Activity {

	private static final String LINE = System.getProperty("line.separator");
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_csv_activity);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.csv_activity, menu);
		return true;
	}

	
	public void createCSV(View view) {

		EditText ausgabe = (EditText) findViewById(R.id.ausgabe);

		// create csv file
		String filename = "USERCODE.txt";

		FileOutputStream outputStream;

		try {
			outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
			outputStream.flush();
			outputStream.close();
			ausgabe.setText("Datei " + filename + " erfolgreich gespeichert!");

		} catch (Exception e) {
			e.printStackTrace();
			ausgabe.setText("Datei NICHT in " + filename
					+ " erfolgreich gespeichert!");
		}

	}

	public void createXML(View view) {

		EditText ausgabe = (EditText) findViewById(R.id.ausgabe);

		// create csv file
		String filename = "config.xml";

		String usercode = "ABCDE";
		String seperator = "::";

		String time1 = "10:00";
		String time2 = "14:00";
		String time3 = "17:00";
		String time4 = "23:00";

		FileOutputStream outputStream;

		try {
			outputStream = openFileOutput(filename, Context.MODE_PRIVATE);

			outputStream.write(usercode.getBytes());
			outputStream.write(seperator.getBytes());

			outputStream.write(time1.getBytes());
			outputStream.write(LINE.getBytes());

			outputStream.write(time2.getBytes());
			outputStream.write(LINE.getBytes());

			outputStream.write(time3.getBytes());
			outputStream.write(LINE.getBytes());

			outputStream.write(time4.getBytes());
			outputStream.write(LINE.getBytes());

			outputStream.flush();
			outputStream.close();
			ausgabe.setText("Datei " + filename + " erfolgreich gespeichert!");

		} catch (Exception e) {
			e.printStackTrace();
			ausgabe.setText("Datei NICHT in " + filename
					+ " erfolgreich gespeichert!");
		}

	}

	public void setUserCodeExample(View view) {

		EditText UserCodeText;
		UserCodeText = (EditText) findViewById(R.id.eingabe);
		String text = UserCodeText.getText().toString();

		setUserCode(view, text);

	}

	public void setUserCode(View view, String actualUsercode) {

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);

		SharedPreferences.Editor editor = preferences.edit();

		editor.putString("Usercode", actualUsercode);
		editor.commit();

		AlertDialog alertDialog = new AlertDialog.Builder(this).create();

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

	public void getUserCode(View view) {

	    	AlertDialog alertDialog = new AlertDialog.Builder(this).create();  
            alertDialog.setTitle("Aktueller Code:"); 
            
            alertDialog.setMessage(getUsercodeAsString());  
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, ("Ok!"), new DialogInterface.OnClickListener() {           	 
            	public void onClick(DialogInterface dialog, int id){
            		dialog.cancel();
            		
       	
            	}
            });  
            alertDialog.show();

		
	}
	
	
	public String getUsercodeAsString(){
		
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		String ausgabeUsercode = preferences.getString("Usercode", "");
		
		if (!ausgabeUsercode.equalsIgnoreCase("")) {
		return ausgabeUsercode;}
		
		else return "UNDEF";
		
		
		
		
		
	}

	public void createLine(View view) {

		EditText ausgabe = (EditText) findViewById(R.id.ausgabe);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy",
				Locale.GERMANY);
		String datum = dateFormat.format(new java.util.Date());

		// create csv file
		String filename = "USERCODE.txt";
		String string = "CODE::"
				+ datum
				+ "::ALARMZEIT::ANTWORTZEIT::ABBRUCH::KONTAKTE::STUNDEN::MINUTEN";
		FileOutputStream outputStream;

		try {
			outputStream = openFileOutput(filename, Context.MODE_APPEND);
			outputStream.write(string.getBytes());
			outputStream.write(LINE.getBytes());
			outputStream.flush();
			outputStream.close();
			ausgabe.setText("Zeile erfolgreich gespeichert");

		} catch (Exception e) {
			e.printStackTrace();
			ausgabe.setText("Zeile nicht gespeichert");
		}
	}

	public void getLastTime(View view) {

		EditText ausgabe = (EditText) findViewById(R.id.ausgabe);

		String line = "";
		String sb = new String();

		try {
			// File zum Lesen oeffenen
			FileInputStream in = new FileInputStream(this.getFilesDir()
					+ "/USERCODE.txt");

			// Wenn das File existiert
			// zum einlesen vorbereiten
			InputStreamReader input = new InputStreamReader(in);
			BufferedReader buffreader = new BufferedReader(input);
			try {
				while ((line = buffreader.readLine()) != null) {
					// Log.d(DEBUG_TAG, "LogLine: " + line);

					String[] splitResult = line.split("::");

					sb = splitResult[1];
					// sb.append(line).append("\n"); // Diese Version haengt
					// noch einen Line Separator (Line Feed) hinten dran.
				}

			} catch (Exception e) {
				e.printStackTrace();

			}

			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();

			}

		} catch (java.io.FileNotFoundException e) {
			e.printStackTrace();

		}

		ausgabe.setText(sb);

	}
	
	
	
	
	
	
	
}
