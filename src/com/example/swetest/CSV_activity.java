package com.example.swetest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CSV_activity extends Activity {

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

}
