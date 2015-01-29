package de.dresden.es.inf.Selfcontrol;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

/**
 * Der Superservice dient uns als Superklasse f�r alle unsere weiteren Services.
 * Er bringt sein Startdatum mit, welches Im Konstruktor gesetzt wird.
 * 
 * @author User
 *
 */

public class SuperService extends Service{
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private final IBinder mBinder = new MyBinder();
	private Messenger outMessenger;
	
	GregorianCalendar start;
	
	GregorianCalendar browserStart;
	
	public Date getDate(){
		return start.getTime();
	}
	
	public GregorianCalendar getStartDate(){
		return start;
	}
	
	public String getRunnedTime(GregorianCalendar today, GregorianCalendar past) {
		 
	    long difference = today.getTimeInMillis() - past.getTimeInMillis();
	    int days = (int)(difference / (1000 * 60 * 60 * 24));
	    int hours = (int)(difference / (1000 * 60 * 60) % 24);
	    int minutes = (int)(difference / (1000 * 60) % 60);
	    int seconds = (int)(difference / 1000 % 60);
	    int millis = (int)(difference % 1000);
	    
	    StringBuilder time = new StringBuilder();
	    time.append(days + " days, " +
	  	      hours + " hours, " +
		      minutes + " minutes, " +
		      seconds + " seconds and " +
		      millis + " milliseonds");
	    return time.toString();
	  }
	
	@Override
	public void onCreate(){
		super.onCreate();
		
		start = new GregorianCalendar();
		//startingDate = Calendar.getInstance().getTime();

		  /***/
		  Toast.makeText(this, "MirkoService running", Toast.LENGTH_SHORT).show();

		    final Handler handler = new Handler(){

		        @Override
		        public void handleMessage(Message msg) {
		            // TODO Auto-generated method stub
		            super.handleMessage(msg);
		            getRunningApps();
		        }

		    };
		    

		    new Thread(new Runnable(){
		        public void run() {
		        // TODO Auto-generated method stub
		        while(true)
		        {
		           try {
		        	   //5000 = 5 sekunden
		            Thread.sleep(5000);
		            handler.sendEmptyMessage(0);

		        } catch (InterruptedException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        	} 

		        }

		       }
		    }).start();
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
	
	
	//laufende apps auslesen
	public List<RunningTaskInfo> getRunningApps(){
		
		ActivityManager activityManager = (ActivityManager) this.getSystemService(Activity.ACTIVITY_SERVICE);
		List<RunningTaskInfo> runningTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
			
		//Toast.makeText(this, "getRunningApps wird aufgerufen", Toast.LENGTH_SHORT).show();
		
		//com.android.chrome
		for(int i = 0; i < runningTasks.size(); i++){
			if(runningTasks.get(i).topActivity.getPackageName().equals("com.android.chrome")){
				Toast.makeText(this, "Chrome l�uft", Toast.LENGTH_SHORT).show();
			}
		}
		
		return runningTasks;
	}
	
	
	public class MyBinder extends Binder {
	    SuperService getService() {
	        return SuperService.this;
	    }
	}
	
	

}
