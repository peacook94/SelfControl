package de.dresden.es.inf.Selfcontrol;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

/**
 * Der Superservice dient uns als Superklasse für alle unsere weiteren Services.
 * Er bringt sein Startdatum mit, welches Im Konstruktor gesetzt wird.
 * 
 * @author User
 *
 */

public class SuperService extends Service{
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private final IBinder mBinder = new MyBinder();
	private Messenger outMessenger;
	
	Date startingDate = new Date();
	
	public Date getDate(){
		return startingDate;
	}
	
	@Override
	public void onCreate(){
		super.onCreate();
		
		startingDate = Calendar.getInstance().getTime();
	}

	@Override
	public IBinder onBind(Intent intent) {
		Bundle extras = intent.getExtras();
	    Log.d("service","onBind");
	    // Get messager from the Activity
	    if (extras != null) {
	        Log.d("service","onBind with extra");
	        outMessenger = (Messenger) extras.get("MESSENGER");
	    }
	    return mBinder;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
      //TODO do something useful
		
		Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();
	  return Service.START_STICKY;
	}
	
	public class MyBinder extends Binder {
	    SuperService getService() {
	        return SuperService.this;
	    }
	}

}
