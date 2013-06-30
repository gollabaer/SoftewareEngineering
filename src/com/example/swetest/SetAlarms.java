package com.example.swetest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SetAlarms extends Activity {

	private static int int1, int2, int3, int4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		//TODO Read Times from XML and apply them on ints
		
		
		int1 = 9;
		int2 = 14;
		int3 = 19;
		int4 = 23;
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_alarms);
		
		
		RadioButton rbutton9 = (RadioButton) findViewById(R.id.radio9);
		RadioButton rbutton10 = (RadioButton) findViewById(R.id.radio10);
		RadioButton rbutton11 = (RadioButton) findViewById(R.id.radio11);
		RadioButton rbutton12 = (RadioButton) findViewById(R.id.radio12);
		RadioButton rbutton13 = (RadioButton) findViewById(R.id.radio13);
		RadioButton rbutton14 = (RadioButton) findViewById(R.id.radio14);
		RadioButton rbutton15 = (RadioButton) findViewById(R.id.radio15);
		RadioButton rbutton16 = (RadioButton) findViewById(R.id.radio16);
		RadioButton rbutton17 = (RadioButton) findViewById(R.id.radio17);
		RadioButton rbutton18 = (RadioButton) findViewById(R.id.radio18);
		RadioButton rbutton19 = (RadioButton) findViewById(R.id.radio19);
		RadioButton rbutton20 = (RadioButton) findViewById(R.id.radio20);
		RadioButton rbutton21 = (RadioButton) findViewById(R.id.radio21);
		RadioButton rbutton22 = (RadioButton) findViewById(R.id.radio22);
		RadioButton rbutton23 = (RadioButton) findViewById(R.id.radio23);
		
		rbutton9.setChecked(int1 == 9);
		rbutton10.setChecked(int1 == 10);
		rbutton11.setChecked(int1 == 11);
		rbutton12.setChecked(int1 == 12);
		rbutton13.setChecked(int2 == 13);
		rbutton14.setChecked(int2 == 14);
		rbutton15.setChecked(int2 == 15);
		rbutton16.setChecked(int3 == 16);
		rbutton17.setChecked(int3 == 17);
		rbutton18.setChecked(int3 == 18);
		rbutton19.setChecked(int3 == 19);
		rbutton20.setChecked(int4 == 20);
		rbutton21.setChecked(int4 == 21);
		rbutton22.setChecked(int4 == 22);
		rbutton23.setChecked(int4 == 23);
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
	
	public void onOk(View view){
		
		System.out.println("Zeit1: "+ int1 + " Zeit2: "+ int2 + " Zeit3: " + int3+ " Zeit4: "+int4);
		super.onBackPressed();
		//TODO Save Times in XML
	}
	
	public void radioButtonClick(View view){
		

		switch (((RadioButton) view).getId()) {
		case R.id.radio9:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio9);
				int1 = 9;
			}
			break;
		case R.id.radio10:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio10);
				int1 = 10;
			}
			break;
		case R.id.radio11:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio11);
				int1 = 11;
			}
			break;
		case R.id.radio12:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio12);
				int1 = 12;
			}
			break;
		case R.id.radio13:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio13);
				int2 = 13;
			}
			break;
		case R.id.radio14:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio14);
				int2 = 14;
			}
			break;
		case R.id.radio15:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio15);
				int2 = 15;
			}
			break;
		case R.id.radio16:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio16);
				int3 = 16;
			}
			break;
		case R.id.radio17:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio17);
				int3 = 17;
			}
			break;
		case R.id.radio18:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio18);
				int3 = 18;
			}
			break;
		case R.id.radio19:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio19);
				int3 = 19;
			}
			break;
		case R.id.radio20:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio20);
				int4 = 20;
			}
			break;
		case R.id.radio21:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio21);
				int4 = 21;
			}
			break;
		case R.id.radio22:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio22);
				int4 = 22;
			}
			break;
		case R.id.radio23:
			if (((RadioButton) view).isChecked()) {
				System.out.println(R.id.radio23);
				int4 = 23;
			}
			break;
		}
		
	}
	
}
