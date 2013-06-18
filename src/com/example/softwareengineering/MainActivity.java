package com.example.softwareengineering;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

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
	
	
	/**
	 * Funktion für Button 
	 * "Erstelle deinen Benutzercode"
	 * , öffnet das Fenster mit der Benutzercodegenerierung
	 */
	public void usercode(View view){
		setContentView(R.layout.usercode);
	}
	
	public void optionen(View view){
		setContentView(R.layout.optionen);
	}

}
