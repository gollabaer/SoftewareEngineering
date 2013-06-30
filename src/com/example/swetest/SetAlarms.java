package com.example.swetest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;

public class SetAlarms extends Activity {

	private static int int1, int2, int3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_alarms);
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
	
	public void radioButtonClick(View view){
		
		if(((RadioButton) view).isChecked()){
			
		 System.out.println(view.getId());
			
		}
	}
	
}
