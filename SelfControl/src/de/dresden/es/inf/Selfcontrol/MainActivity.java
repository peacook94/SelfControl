package de.dresden.es.inf.Selfcontrol;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;

import de.dresden.es.inf.Selfcontrol.Database.AppsDataSource;
import de.dresden.es.inf.Selfcontrol.Util.App;
import de.dresden.es.inf.Selfcontrol.Util.AppId;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.v4.app.INotificationSideChannel.Stub;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.inputmethodservice.*;

public class MainActivity extends Activity implements OnTouchListener{
	
	private final static String fileName = "count.txt";
	
	int counter = 0;
	String status = "";
	
	private AppsDataSource database;
	
	public AppsDataSource getDatabase(){
		return database;
	}
	
	
	private SuperService myServiceBinder;
	protected ServiceConnection mServerConn = new ServiceConnection() {
		
	    @Override
	    public void onServiceConnected(ComponentName name, IBinder binder) {
	    	myServiceBinder = ((SuperService.MyBinder) binder ).getService();
	    	Log.d("ServiceConnection","connected");
	    	//showServiceData();
	    }

	    @Override
	    public void onServiceDisconnected(ComponentName name) {
	    	Log.d("ServiceConnection","disconnected");
	    	//showServiceData();
	    	myServiceBinder = null;
	    }
	};
	
	private void showRunningApps(){
	    
		for(int i = 0; i < myServiceBinder.getRunningApps().size(); i++){
	    	
	    	String task = myServiceBinder.getRunningApps().get(i).topActivity.getPackageName();
	    	
	    	Toast.makeText(this, task, Toast.LENGTH_SHORT).show();
	    }

	}
	
	public Handler myHandler = new Handler() {
	    public void handleMessage(Message message) {
	        Bundle data = message.getData();
	    }
	};
	
	public void saveLocally() throws IOException{
		
//		for(int i=0; i<=fileList().length; i++){
//			if(fileList()[i].toString() == fileName) break;
//			
//		}
		
		FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
		fos.write(String.valueOf(counter).getBytes());
		fos.close();
		
	}
	
	public void doBindService() {
	    Intent intent = null;
	    intent = new Intent(this, SuperService.class);
	    // Create a new Messenger for the communication back
	    // From the Service to the Activity
	    Messenger messenger = new Messenger(myHandler);
	    intent.putExtra("MESSENGER", messenger);

	    bindService(intent, mServerConn, Context.BIND_AUTO_CREATE);
	}

	/**
	 * Diese Methode ist essentiell f�r jeden OnTouchListener
	 * In dieser Methode wird spezifiziert, was geschehen soll, wenn das MotionEvent
	 * ausgel�st wird. 
	 * Beim Auswerten
	 */
	@SuppressLint("ClickableViewAccessibility")
	public boolean onTouch(View v, MotionEvent event)
    {
		
		//es ist wichtig, dass man auf die Reihenfolge der Abfragen achtet
		//So ist es zum Beispiel n�tig, dass man das Event: ACTION_DOWN
		//abf�ngt, bevor ACTION_UP �berhaupt vom Programm behandelt wird.
		//Denn es ist ja logisch, dass das Programm "denkt": " na wenn ich kein
		//ACTION_DOWN bekomme, dann gibt es auch kein ACTION_UP!"
		if(event.getActionMasked() == MotionEvent.ACTION_DOWN) return true;
		if(event.getActionMasked() == MotionEvent.ACTION_UP){ 
		
			counter++;
        
			TextView textView = (TextView) findViewById(R.id.counter);
			textView.setText(String.valueOf(counter));
        

			return true;
		}
		 return false;
		//false indicates the event is not consumed
    }
	
	@Override
	public void onResume(){
		Log.d("activity", ""+ "onResume");
		
		database.open();
//	    if (myServiceBinder == null) {
//	        doBindService();
//	    }
	    super.onResume();
	}
	
	@Override
	protected void onPause() {
	    //FIXME put back
		
		database.close();

	    Log.d("activity", "onPause");
//	    if (myServiceBinder != null) {
//	        unbindService(mServerConn);
//	        myServiceBinder = null;
//	    }
	    super.onPause();
	}
	

	
	private void showServiceData() {  
	    String date = myServiceBinder.getDate().toString();
	    TextView myView = (TextView) findViewById(R.id.counter);

	    myView.setText(myServiceBinder.getRunnedTime(new GregorianCalendar(), myServiceBinder.getStartDate()));
	    
	    //showRunningApps();
	    
	    //Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
					
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        database = new AppsDataSource(this);
        database.open();
        

        //den gesamten Bilschirm touchsensitiv machen
        RelativeLayout mLinearLayoutMain = (RelativeLayout) findViewById(R.id.layout_main);
        mLinearLayoutMain.setOnTouchListener(this);
        
        
        
        //Den Button zum ACTIVITY-switchen einbinden
        Button switchButton = (Button) findViewById(R.id.mainButton1);
        
        
        //Nun brauchen wir einen OnClickListener, damit wir mitbekommen, wann auf den Button gedr�ckt wurde
        switchButton.setOnClickListener(new View.OnClickListener(){
        	
        	
        	public void onClick(View arg0){
        		//Neues Intent anlegen
        		Intent nextScreen = new Intent(getApplicationContext(), SekActivity.class);
        		
        		TextView mainTextView = (TextView) findViewById(R.id.counter);
        		//Intent mit Daten f�llen
        		nextScreen.putExtra("count", mainTextView.getText().toString());
        		
        		startActivity(nextScreen);
        	}
        });
        
//        Button dummyDaten = (Button) findViewById(R.id.setDummyData);
//        dummyDaten.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				App temp = new App(Calendar.getInstance().getTime(), AppId.BROWSER);
//								
//				String errorCode = database.addApp(temp);
//				Toast.makeText(getApplicationContext(), errorCode, Toast.LENGTH_SHORT).show();
//				
//			}
//		});
        
//        Button getdummyDaten = (Button) findViewById(R.id.getDummyData);
//        getdummyDaten.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Map<Date, AppId> temp = new HashMap<Date, AppId>();
//				
//				
//				try {
//					temp = database.getAppWithDates(AppId.BROWSER);
//					Toast.makeText(getApplicationContext(), String.valueOf(temp.size()), Toast.LENGTH_SHORT).show();
//					
//					for(Date date : temp.keySet()){
//						Toast.makeText(getApplicationContext(), date.toString(), Toast.LENGTH_SHORT).show();
//					}
//					
//					
//					if(temp != null)
//						Toast.makeText(getApplicationContext(), "Datenbankeintrag gefunden", Toast.LENGTH_SHORT);
//					
//				} catch (ParseException e) {
//					Toast.makeText(getApplicationContext(),"BROWSER not found", Toast.LENGTH_SHORT).show();
//				}
//				
//				
//			}
//		});
        
//        Button switchButton2 = (Button) findViewById(R.id.mainButton2);
//        switchButton2.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent nextScreen = new Intent(getApplicationContext(), Vis1.class);
//				
//				startActivity(nextScreen);
//			}
//		});
        
        Button serviceButton = (Button) findViewById(R.id.startServiceButton);
        
        serviceButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//toogles to start the Service or not
				startMyService();
				
			}
		});
        
//        Button getDataButton = (Button) findViewById(R.id.getServiceDataButton);
//        getDataButton.setOnClickListener(new View.OnClickListener(){
//        	@Override
//        	public void onClick(View v){
//        		showServiceData();
//        	}
//        });
        
        if(isMyServiceRunning(SuperService.class)){
        	Toast.makeText(this, "Service l�uft ohne Aufruf",Toast.LENGTH_SHORT).show();
        }
               
    }
	
	public boolean startMyService(){
		Intent i = new Intent(this, SuperService.class);
		
		if(!isMyServiceRunning(SuperService.class)){
			
			this.bindService(i, mServerConn, Context.BIND_AUTO_CREATE);
		    this.startService(i);
		  
		  return true;
		}else{
			
			this.stopService(new Intent(this, SuperService.class));
			this.unbindService(mServerConn);
			Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show();
		return false;
		}
	}
	
	
	
	private boolean isMyServiceRunning(Class<?> serviceClass) {
	    ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	    
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if (serviceClass.getName().equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    
	    return false;
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
