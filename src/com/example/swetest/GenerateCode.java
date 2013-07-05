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
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;

public class GenerateCode extends Activity {

	private static boolean valid = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_generade_code);

		EditText CodeEdit = (EditText) findViewById(R.id.editTextCode);

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
		EditText CodeEdit = (EditText) findViewById(R.id.editTextCode);
		String code = CodeEdit.getText().toString();
		code = code.toUpperCase();
		// System.out.println(code.toString());
		valid = validate(code);
		if (valid) {
			// System.out.println("true");
			boolean success = send(code);
			if (!success) {
				throw new SecurityException("Couldn't hand over user code.");
			}

		} else {
			// System.out.println("false");
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
		if (code.matches(".*\\d.*")) {
			// System.out.println("invalid number");
			return false;
		}
		return true;
	}

	public String getUsercodeAsString() {

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);

		String ausgabeUsercode = preferences.getString("Usercode", "UNDEF");

		return ausgabeUsercode;

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

	public boolean send(String code) {

		MainActivity.setUserCode(this, code);
		createCSV();

		finish();

		return true;
	}

}
