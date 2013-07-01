package com.example.swetest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;

public class Verwalter {
	
	
	private static final String LINE = System.getProperty("line.separator");
	
	
	
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

	
	
	
	
	
	

}
