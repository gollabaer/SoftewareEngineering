package com.example.swetest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class GenerateCode extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_generade_code);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.generade_code, menu);
		return true;
	}
	
	public void close(View view){
		super.onBackPressed();
	}
	
	

}
