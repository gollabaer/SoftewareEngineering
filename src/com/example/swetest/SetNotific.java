package com.example.swetest;

import android.app.Service;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class SetNotific extends Service {
	
	
	
	public SetNotific() {
	}

	@Override 
	public IBinder onBind(Intent intent) { return null; }
	
	
	private void handleIntent(Intent intent) {
		 InputNotification.notify(this, "", 1);
	}
	 
	
	 
	 public void onStart(Intent intent, int startId) 
	 { handleIntent(intent); 
	 InputNotification.notify(this, "", 1);}
	
	 public int onStartCommand(Intent intent, int flags, int startId)
	 {
		 handleIntent(intent); 
		 return START_NOT_STICKY;
	 }
	 
	 protected void onPostExecute(Void result) {
		 // handle your data 
		 InputNotification.notify(this, "", 1);
		 stopSelf(); 
	 }
	 
	 public void onDestroy() 
	 { super.onDestroy();
	 
	 }
}
	
	
