package com.example.swetest;
import android.view.View;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

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
	
	public void startStatistic(View view){
		//Intent intent = new Intent(this, SetAlarms.class);
		//startActivity(intent);
		InputNotification.notify(this, "10:00", 1);
	}
	

}
