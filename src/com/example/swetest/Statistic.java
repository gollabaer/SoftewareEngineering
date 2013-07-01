package com.example.swetest;

import java.util.concurrent.TimeUnit;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Statistic extends Activity {
	
	private static boolean valid = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistic);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.statistic, menu);
		return true;
	}

	public void close(View view){
		super.onBackPressed();
	}
	
	public void onOk(View view){
		
		EditText NumberEdit = (EditText)findViewById(R.id.editTextNumber);
	    Integer numberOfContacts = Integer.parseInt(NumberEdit.getText().toString());
	    
	    EditText HoursEdit = (EditText)findViewById(R.id.EditTextHours);
	    Integer hours = Integer.parseInt(HoursEdit.getText().toString());
	    
	    EditText MinutesEdit = (EditText)findViewById(R.id.EditTextMinutes);
	    Integer minutes = Integer.parseInt(MinutesEdit.getText().toString());
	   
//	    System.out.println(code.toString());
	    valid = validate(hours, minutes);
	    if (valid){
//	    			System.out.println("true");
			    	boolean success = send(numberOfContacts, hours, minutes);
			    	if (!success){
			    		throw new SecurityException ("Couldn't hand over user code.");
			    	}
			    	
	    }
	    else{
//	    		System.out.println("false");
		    	AlertDialog alertDialog = new AlertDialog.Builder(this).create();  
	            alertDialog.setTitle("Fehler"); 
	            final Resources res = this.getResources();
	            alertDialog.setMessage(res.getString(R.string.StatisticsError));  
	            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, res.getString(R.string.ok), new DialogInterface.OnClickListener() {           	 
	            	public void onClick(DialogInterface dialog, int id){
	            		dialog.cancel();
	            	}
	            });  
	            alertDialog.show();
	    }
	}
	
	public static boolean validate(int hours, int minutes) {

		// aktuelle - getLastTime() // Millisekunden

		long diff = System.currentTimeMillis() - 10;//getLastTime();
		hours = (int) TimeUnit.HOURS.toMillis(hours);
		minutes = (int) TimeUnit.MINUTES.toMillis(minutes);

		if (diff - (hours + minutes) < 0) {
			return false;
		} else {
			return true;

		}

	}
	
	public boolean send(int numberOfContacts, int hours, int minutes){
			
		finish();
		
		return true;
	}

	
}
