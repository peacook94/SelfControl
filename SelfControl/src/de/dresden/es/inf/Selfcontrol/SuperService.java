package de.dresden.es.inf.Selfcontrol;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
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
	
	Date startingDate = new Date();
	
	@Override
	public void onCreate(){
		super.onCreate();
		
		startingDate = Calendar.getInstance().getTime();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
      //TODO do something useful
		
		Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();
	  return Service.START_STICKY;
	}

}
