package com.example.swetest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;

public class GenerateCode extends Activity {
	
	private static boolean valid = false;
	
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
	
	public void onOk(View view){
		EditText CodeEdit = (EditText)findViewById(R.id.editTextCode);
	    String code = CodeEdit.getText().toString();
	    code = code.toUpperCase();
//	    System.out.println(code.toString());
	    valid = validate(code);
	    if (valid){
//	    			System.out.println("true");
			    	boolean success = send(code);
			    	if (!success){
			    		throw new SecurityException ("Couldn't hand over user code.");
			    	}
			    	close(view);
	    }
	    else{
//	    		System.out.println("false");
		    	AlertDialog alertDialog = new AlertDialog.Builder(this).create();  
	            alertDialog.setTitle("Fehler"); 
	            final Resources res = this.getResources();
	            alertDialog.setMessage(res.getString(R.string.CodeError));  
	            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, res.getString(R.string.ok), new DialogInterface.OnClickListener() {           	 
	            	public void onClick(DialogInterface dialog, int id){
	            		dialog.cancel();
	            	}
	            });  
	            alertDialog.show();
	    }
	}
	
	public boolean validate(String code){
		if (code.isEmpty()){ 
//			System.out.println("invalid empty");	
			return false;			
		}
		if (code.length()!=5 ){
//			System.out.println("invalid length");	
			return false;
		}
		if (code.matches(".*\\d.*")){
//			System.out.println("invalid number");	
			return false;
		}
		return true;
	}
	
	public boolean send(String code){
		return true;
	}
	
}
