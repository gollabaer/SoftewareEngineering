package com.example.swetest;

import java.io.FileOutputStream;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;

public class GenerateCode extends Activity {

	private static boolean valid = false; /*
										 * valid states if the inserted string
										 * is a valid usercode
										 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_generade_code);

		EditText CodeEdit = (EditText) findViewById(R.id.editTextCode); /*
																		 * get
																		 * the
																		 * EditText
																		 * for
																		 * Usercodeinput
																		 */

		/**
		 * The Inputfilter reduces the possible submissions to a maximum 0f 5
		 * Numbers. No other characters or special signs are allowed.
		 */
		InputFilter filter = new InputFilter() {
			public CharSequence filter(CharSequence source, int start, int end,
					Spanned dest, int dstart, int dend) {
				if (dstart > 4) {
					return "";
				}
				for (int i = start; i < end; i++) {
					if (!Character.isLetter(source.charAt(i))) {
						return "";
					}
				}
				return null;
			}
		};

		CodeEdit.setFilters(new InputFilter[] { filter });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.generade_code, menu);
		return true;
	}

	public void close(View view) {
		super.onBackPressed();
	}

	public void onOk(View view) {

		EditText CodeEdit = (EditText) findViewById(R.id.editTextCode); /*
																		 * get
																		 * the
																		 * EditText
																		 * for
																		 * Usercodeinput
																		 */
		String code = CodeEdit.getText().toString(); /*
													 * get the String form the
													 * EditText
													 */
		code = code.toUpperCase(); /* convert String to upper Case */

		// System.out.println(code.toString());
		valid = validate(code); /* if the inserted code is a valid Usercode */
		if (valid) {
			// System.out.println("true");
			boolean success = send(code);
			if (!success) {
				throw new SecurityException("Couldn't hand over user code.");
			}

		} else {
			// System.out.println("false");
			/**
			 * Errormessage is case Userinput doesn't match the criteria.
			 */
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle("Fehler");
			final Resources res = this.getResources();
			alertDialog.setMessage(res.getString(R.string.CodeError));
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

	public boolean validate(String code) {
		if (code.isEmpty()) {
			// System.out.println("invalid empty");
			return false;
		}
		if (code.length() != 5) {
			// System.out.println("invalid length");
			return false;
		}
		return true;
	}

	public boolean send(String code) {
		/**
		 * Preference UserCode is first set. Then a CSV-file with the Usercode
		 * as filename is created. Toast is shown and Activity is closed.
		 */
		MainActivity.setUserCode(this, code);
		createCSV();

		Context context = getApplicationContext();
		CharSequence text = "Benutzerprofil erfolgreich angelegt.";
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();

		finish();

		return true;
	}

	public void createCSV() {

		// create csv file
		String filename = getUsercodeAsString() + ".csv";

		FileOutputStream outputStream;

		try {
			outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
			outputStream.flush();
			outputStream.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public String getUsercodeAsString() {

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);

		String ausgabeUsercode = preferences.getString("Usercode", "UNDEF");

		return ausgabeUsercode;

	}

}
